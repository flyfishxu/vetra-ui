package com.flyfishxu.vetraui.core.indication

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import kotlinx.coroutines.launch

/**
 * Vetra Press Indication
 *
 * A simple, elegant press feedback that darkens the content when pressed.
 * This creates a subtle, responsive feel without the complexity of ripple effects.
 *
 * Design Philosophy:
 * - Minimal and clean
 * - Instant feedback
 * - Smooth transitions
 * - Platform-agnostic
 *
 * @param pressColor The overlay color applied when pressed (default: semi-transparent black)
 * @param pressAlpha The maximum alpha value when fully pressed (default: 0.1)
 */
class VetraPressIndication(
    private val pressColor: Color = Color.Black,
    private val pressAlpha: Float = 0.1f
) : IndicationNodeFactory {
    
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return VetraPressIndicationNode(
            interactionSource = interactionSource,
            pressColor = pressColor,
            pressAlpha = pressAlpha
        )
    }

    override fun hashCode(): Int {
        var result = pressColor.hashCode()
        result = 31 * result + pressAlpha.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VetraPressIndication) return false
        
        if (pressColor != other.pressColor) return false
        if (pressAlpha != other.pressAlpha) return false
        
        return true
    }
}

/**
 * The actual indication node that handles the press animation
 */
private class VetraPressIndicationNode(
    private val interactionSource: InteractionSource,
    private val pressColor: Color,
    private val pressAlpha: Float
) : Modifier.Node(), DrawModifierNode {
    
    private val animatedAlpha = Animatable(0f)
    
    override fun onAttach() {
        coroutineScope.launch {
            var pressCount = 0
            
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        pressCount++
                        animatedAlpha.animateTo(
                            targetValue = pressAlpha,
                            animationSpec = spring()
                        )
                    }
                    is PressInteraction.Release,
                    is PressInteraction.Cancel -> {
                        pressCount = (pressCount - 1).coerceAtLeast(0)
                        if (pressCount == 0) {
                            animatedAlpha.animateTo(
                                targetValue = 0f,
                                animationSpec = spring()
                            )
                        }
                    }
                }
            }
        }
    }
    
    override fun ContentDrawScope.draw() {
        // Draw the original content first
        drawContent()
        
        // Draw the press overlay on top if there's any alpha
        val currentAlpha = animatedAlpha.value
        if (currentAlpha > 0f) {
            drawRect(
                color = pressColor.copy(alpha = currentAlpha),
                size = size
            )
        }
    }
}

/**
 * Creates a press indication with the specified color and alpha
 *
 * @param pressColor The overlay color (default: black for darkening effect)
 * @param pressAlpha The maximum opacity when pressed (default: 0.1 for subtle effect)
 */
fun vetraPressIndication(
    pressColor: Color = Color.Black,
    pressAlpha: Float = 0.1f
): VetraPressIndication = VetraPressIndication(pressColor, pressAlpha)

