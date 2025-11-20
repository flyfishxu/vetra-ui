package com.flyfishxu.vetraui.core

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.flyfishxu.vetraui.core.theme.VetraTheme
import com.flyfishxu.vetraui.core.theme.vetraShadow
import kotlin.math.roundToInt

/**
 * Pull-to-refresh state enum representing all possible states
 */
enum class PullToRefreshState {
    /** Idle state, no interaction */
    Idle,

    /** User is pulling down */
    Pulling,

    /** Pulled beyond threshold, ready to refresh */
    ReadyToRefresh,

    /** Refresh is in progress */
    Refreshing,

    /** Refresh completed, animating out */
    Completing
}

/**
 * Vetra Pull to Refresh
 *
 * A modern, elegant pull-to-refresh component with smooth animations and gesture handling.
 * Built from scratch without Material components, following Vetra design principles.
 *
 * Features:
 * - Clear state management with enum-based states
 * - Dynamic loading animation that grows with pull progress
 * - Smooth enter and exit animations
 * - Elegant gesture recognition
 * - Cross-platform compatibility
 *
 * @param refreshing Whether refresh is currently in progress
 * @param onRefresh Callback when user triggers refresh
 * @param modifier Modifier for the container
 * @param enabled Whether pull-to-refresh is enabled
 * @param indicatorColor Color of the refresh indicator
 * @param indicator Custom refresh indicator composable
 * @param content The scrollable content
 */
@OptIn(ExperimentalVetraApi::class)
@Composable
fun VetraPullToRefresh(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    indicatorColor: Color? = null,
    indicator: @Composable (VetraPullToRefreshState) -> Unit = { state ->
        VetraDefaultRefreshIndicator(state = state, color = indicatorColor)
    },
    content: @Composable () -> Unit
) {
    val state = rememberVetraPullToRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh,
        enabled = enabled
    )

    // Animate indicator position smoothly
    val animatedOffsetY by animateFloatAsState(
        targetValue = state.indicatorOffsetY,
        animationSpec = spring(
            dampingRatio = 0.8f,
            stiffness = 380f
        ),
        label = "indicator_offset"
    )

    Box(
        modifier = modifier
            .clipToBounds()
            .nestedScroll(state.nestedScrollConnection)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset { IntOffset(0, animatedOffsetY.roundToInt()) }
                    .padding(top = 16.dp)
            ) {
                indicator(state)
            }
        }
    }
}

/**
 * State holder for Vetra Pull to Refresh component
 *
 * Manages the pull gesture, animations, and refresh state using clear state-based architecture.
 *
 * @param refreshing Whether refresh is currently in progress
 * @param onRefresh Callback when user triggers refresh
 * @param enabled Whether pull-to-refresh is enabled
 * @param threshold Distance to pull before triggering refresh (in pixels)
 */
@ExperimentalVetraApi
@Stable
class VetraPullToRefreshState(
    refreshing: Boolean,
    private val onRefresh: () -> Unit,
    enabled: Boolean,
    private val threshold: Float = 100f
) {
    internal var pullOffset by mutableFloatStateOf(0f)
        private set

    var isRefreshing by mutableStateOf(refreshing)
        internal set

    var isEnabled by mutableStateOf(enabled)

    // Current state of the pull-to-refresh
    var currentState by mutableStateOf(PullToRefreshState.Idle)
        private set

    // Pull progress from 0 to 1 (based on threshold)
    val pullProgress: Float by derivedStateOf {
        (pullOffset / threshold).coerceIn(0f, 1f)
    }

    // Extended progress that can go beyond 1.0 for over-pull effect
    val extendedProgress: Float by derivedStateOf {
        (pullOffset / threshold).coerceIn(0f, 1.5f)
    }

    // Indicator offset - always animate from screen top
    val indicatorOffsetY: Float by derivedStateOf {
        when (currentState) {
            PullToRefreshState.Idle -> -190f  // Completely hidden above component (56dp indicator + 16dp padding + buffer)
            PullToRefreshState.Refreshing -> 60f
            PullToRefreshState.Completing -> -190f  // Slide back up completely
            else -> (pullOffset * 0.6f - 30f).coerceIn(
                -90f,
                60f
            )  // Follow pull, starting from above
        }
    }

    // Update state based on pull offset and refreshing status
    private fun updateState() {
        currentState = when {
            isRefreshing -> PullToRefreshState.Refreshing
            pullOffset >= threshold -> PullToRefreshState.ReadyToRefresh
            pullOffset > 0 -> PullToRefreshState.Pulling
            else -> PullToRefreshState.Idle
        }
    }

    internal val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            if (!isEnabled || currentState == PullToRefreshState.Refreshing) return Offset.Zero

            return if (available.y < 0 && pullOffset > 0) {
                // Scrolling up while pulling, consume the pull offset
                val consumed = if (pullOffset + available.y >= 0) {
                    available.y
                } else {
                    -pullOffset
                }
                pullOffset += consumed
                updateState()
                Offset(0f, consumed)
            } else {
                Offset.Zero
            }
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            if (!isEnabled || currentState == PullToRefreshState.Refreshing) return Offset.Zero

            if (source == NestedScrollSource.UserInput && available.y > 0) {
                // Apply drag resistance for smoother feel
                val dragMultiplier = when {
                    pullOffset < threshold -> 0.6f
                    else -> 0.3f // More resistance when over threshold
                }
                val newOffset = available.y * dragMultiplier
                pullOffset += newOffset
                updateState()
                return Offset(0f, available.y)
            }

            return Offset.Zero
        }

        override suspend fun onPreFling(available: Velocity): Velocity {
            return if (pullOffset > 0) {
                if (pullOffset >= threshold && !isRefreshing) {
                    // Trigger refresh
                    isRefreshing = true
                    updateState()
                    onRefresh()
                }
                animatePullOffsetTo(0f)
                available
            } else {
                Velocity.Zero
            }
        }

        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            if (pullOffset > 0 && !isRefreshing) {
                animatePullOffsetTo(0f)
            }
            return Velocity.Zero
        }
    }

    private suspend fun animatePullOffsetTo(target: Float) {
        val start = pullOffset
        val durationNanos = 250_000_000L // 250ms for smoother animation
        val startTime = withFrameNanos { it }

        while (pullOffset != target) {
            val currentTime = withFrameNanos { it }
            val elapsed = currentTime - startTime
            val progress = (elapsed.toFloat() / durationNanos).coerceIn(0f, 1f)

            // Use easing for smoother animation
            val easedProgress = FastOutSlowInEasing.transform(progress)
            pullOffset = start + (target - start) * easedProgress
            updateState()

            if (progress >= 1f) {
                pullOffset = target
                updateState()
                break
            }

            kotlinx.coroutines.delay(16)
        }
    }

    // Trigger completion animation when refresh finishes
    internal suspend fun completeRefresh() {
        currentState = PullToRefreshState.Completing
        // Smoothly animate back to top
        animatePullOffsetTo(0f)
        kotlinx.coroutines.delay(50)
        currentState = PullToRefreshState.Idle
    }
}

/**
 * Remember and create a [VetraPullToRefreshState]
 */
@ExperimentalVetraApi
@Composable
fun rememberVetraPullToRefreshState(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    enabled: Boolean = true,
    threshold: Float = 100f
): VetraPullToRefreshState {
    val state = remember {
        VetraPullToRefreshState(
            refreshing = refreshing,
            onRefresh = onRefresh,
            enabled = enabled,
            threshold = threshold
        )
    }

    // Update state when props change
    val wasRefreshing = state.isRefreshing
    state.isRefreshing = refreshing
    state.isEnabled = enabled

    // Trigger completion animation when refresh finishes
    LaunchedEffect(refreshing) {
        if (wasRefreshing && !refreshing) {
            state.completeRefresh()
        }
    }

    return state
}

/**
 * Default refresh indicator for Vetra Pull to Refresh
 *
 * An elegant circular indicator with a growing arc that responds to pull progress.
 * When refreshing, it becomes a full spinning circle.
 *
 * @param state The pull-to-refresh state
 * @param color Custom color for the indicator
 */
@ExperimentalVetraApi
@Composable
fun VetraDefaultRefreshIndicator(
    state: VetraPullToRefreshState,
    color: Color? = null,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val shadows = VetraTheme.shadows
    val indicatorColor = color ?: colors.brand

    // Track rotation for spinning state
    val rotation = remember { Animatable(0f) }

    // Track current arc sweep angle with smooth animation
    val arcSweep = remember { Animatable(0f) }

    // Sync arc sweep with pull progress
    LaunchedEffect(state.pullProgress, state.currentState) {
        when (state.currentState) {
            PullToRefreshState.Idle -> {
                // Smoothly animate to 0
                arcSweep.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    )
                )
            }

            PullToRefreshState.Pulling, PullToRefreshState.ReadyToRefresh -> {
                // Follow pull progress instantly for responsiveness
                arcSweep.snapTo(state.pullProgress * 280f)
            }

            PullToRefreshState.Refreshing -> {
                // First ensure arc is at full
                if (arcSweep.value < 280f) {
                    arcSweep.animateTo(
                        targetValue = 280f,
                        animationSpec = tween(150, easing = FastOutSlowInEasing)
                    )
                }
                // Then start spinning
                rotation.snapTo(0f)
                while (state.currentState == PullToRefreshState.Refreshing) {
                    rotation.animateTo(
                        targetValue = 360f,
                        animationSpec = tween(1000, easing = LinearEasing)
                    )
                    rotation.snapTo(0f)
                }
            }

            PullToRefreshState.Completing -> {
                // Stop rotation and smoothly shrink arc to 0
                rotation.snapTo(0f)
                arcSweep.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }

    Box(
        modifier = modifier
            .size(56.dp)
            .vetraShadow(
                elevation = shadows.md,
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(colors.canvasElevated),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(28.dp)) {
            val strokeWidth = 3.dp.toPx()
            val radius = size.minDimension / 2f - strokeWidth / 2f
            val centerOffset = Offset(size.width / 2f, size.height / 2f)

            val currentSweep = arcSweep.value

            if (currentSweep > 0) {
                if (state.currentState == PullToRefreshState.Refreshing) {
                    // Spinning arc
                    drawArc(
                        color = indicatorColor,
                        startAngle = rotation.value - 90f,
                        sweepAngle = 280f,
                        useCenter = false,
                        topLeft = Offset(
                            centerOffset.x - radius,
                            centerOffset.y - radius
                        ),
                        size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                        style = Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    )
                } else {
                    // Static growing/shrinking arc
                    drawArc(
                        color = indicatorColor,
                        startAngle = -90f,
                        sweepAngle = currentSweep,
                        useCenter = false,
                        topLeft = Offset(
                            centerOffset.x - radius,
                            centerOffset.y - radius
                        ),
                        size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                        style = Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    )
                }
            }
        }
    }
}


/**
 * Compact refresh indicator that appears inline
 *
 * A more subtle indicator that can be used in tight spaces.
 *
 * @param state The pull-to-refresh state
 * @param color Custom color for the indicator
 */
@ExperimentalVetraApi
@Composable
fun VetraCompactRefreshIndicator(
    state: VetraPullToRefreshState,
    color: Color? = null,
    modifier: Modifier = Modifier
) {
    val colors = VetraTheme.colors
    val indicatorColor = color ?: colors.brand

    val rotation = remember { Animatable(0f) }
    val arcSweep = remember { Animatable(0f) }

    LaunchedEffect(state.pullProgress, state.currentState) {
        when (state.currentState) {
            PullToRefreshState.Idle -> {
                arcSweep.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(200, easing = FastOutSlowInEasing)
                )
            }

            PullToRefreshState.Pulling, PullToRefreshState.ReadyToRefresh -> {
                arcSweep.snapTo(state.pullProgress * 280f)
            }

            PullToRefreshState.Refreshing -> {
                if (arcSweep.value < 280f) {
                    arcSweep.animateTo(280f, tween(150, easing = FastOutSlowInEasing))
                }
                rotation.snapTo(0f)
                while (state.currentState == PullToRefreshState.Refreshing) {
                    rotation.animateTo(360f, tween(1000, easing = LinearEasing))
                    rotation.snapTo(0f)
                }
            }

            PullToRefreshState.Completing -> {
                rotation.snapTo(0f)
                arcSweep.animateTo(0f, tween(250, easing = FastOutSlowInEasing))
            }
        }
    }

    Box(
        modifier = modifier
            .size(36.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(20.dp)) {
            val strokeWidth = 2.5.dp.toPx()
            val radius = size.minDimension / 2f - strokeWidth / 2f
            val centerOffset = Offset(size.width / 2f, size.height / 2f)

            val currentSweep = arcSweep.value

            if (currentSweep > 0) {
                if (state.currentState == PullToRefreshState.Refreshing) {
                    drawArc(
                        color = indicatorColor,
                        startAngle = rotation.value - 90f,
                        sweepAngle = 280f,
                        useCenter = false,
                        topLeft = Offset(
                            centerOffset.x - radius,
                            centerOffset.y - radius
                        ),
                        size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                        style = Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    )
                } else {
                    drawArc(
                        color = indicatorColor,
                        startAngle = -90f,
                        sweepAngle = currentSweep,
                        useCenter = false,
                        topLeft = Offset(
                            centerOffset.x - radius,
                            centerOffset.y - radius
                        ),
                        size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                        style = Stroke(
                            width = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    )
                }
            }
        }
    }
}
