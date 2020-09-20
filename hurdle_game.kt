//
// Template for LED matrix animation
// 

import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import java.awt.image.BufferedImage
import org.otfried.cs109.DrawStyle

// --------------------------------------------------------------------

// Put the student ids of all members of your team in the following list.
// It is enough if one of you submits the file.

val authors = listOf(20160253, 20160256)

// --------------------------------------------------------------------

// global variables to control the animation

var x: Int = 0
var y: Int = 0
var z: Int = 0
var x_map: Int = 0
var score: Int = 0
val message1 = "HURDLE"
val message11 = "  GAME "
val message2 = "GAME"
val message22 = "OVER"
val message3 ="SCORE"
var selector = 0


// --------------------------------------------------------------------

// setup() is called once to set up your animation:

fun setup() {
	// setup your global variables
	x = 32
	y = 0
	selector = 0
}

// --------------------------------------------------------------------

// loop() is called in regular intervals to compute the next frame
// of the animation.
// The argument leds is a bitmap of size 32 x 16.
// loop() needs to draw the next frame of the animation on this bitmap.
// Black means LED off, anything else means LED on.
// The bitmap is already cleared to black before loop() is called.
// If loop() returns true, then the animation ends.

fun loop(leds: BufferedImage): Boolean {
	val g = ImageCanvas(leds)
	g.setFont(10.0, "SansSerif")
	g.setColor(Color.RED)
	val wid = g.textWidth(message1)
	
	 
	if (selector == 0){         // message "PACMAN GAME"
		if (x >= -wid+20){
			g.drawText(message1, x.toDouble(), 8.0)
			g.drawText(message11, x.toDouble(), 16.0)
			g.done()
			x -= 2
		} else{
			selector += 1
		}
		return false
		
	} else if (selector == 1){    // Hurdle GAME
		if (x_map > -111){
			// Make a map
			g.drawRectangle(0.0, 14.0, 32.0, 2.0, DrawStyle.FILL)
			g.beginShape()
			g.moveTo(x_map.toDouble(), 14.0)
			g.lineTo(x_map.toDouble() +25.0, 14.0)
			g.lineTo(x_map.toDouble() +25.0, 11.0)
			g.lineTo(x_map.toDouble() +26.0, 11.0)
			g.lineTo(x_map.toDouble() +27.0, 14.0)
			g.lineTo(x_map.toDouble() +40.0, 14.0)
			g.lineTo(x_map.toDouble() +41.0, 9.0)
			g.lineTo(x_map.toDouble() +41.0, 14.0)
			g.lineTo(x_map.toDouble() +55.0, 14.0)
			g.lineTo(x_map.toDouble() +56.0, 12.0)
			g.lineTo(x_map.toDouble() +57.0, 10.0)
			g.lineTo(x_map.toDouble() +58.0, 12.0)
			g.lineTo(x_map.toDouble() +59.0, 14.0)
			g.lineTo(x_map.toDouble() +85.0, 14.0)
			g.lineTo(x_map.toDouble() +86.0, 9.0)
			g.lineTo(x_map.toDouble() +86.0, 14.0)
			g.lineTo(x_map.toDouble() +100.0, 14.0)
			g.lineTo(x_map.toDouble() +101.0, 12.0)
			g.lineTo(x_map.toDouble() +102.0, 10.0)
			g.lineTo(x_map.toDouble() +103.0, 12.0)
			g.lineTo(x_map.toDouble() +104.0, 14.0)
			g.lineTo(x_map.toDouble() +115.0, 14.0)
			g.lineTo(x_map.toDouble() +115.0, 11.0)
			g.lineTo(x_map.toDouble() +116.0, 11.0)
			g.lineTo(x_map.toDouble() +117.0, 14.0)
			g.lineTo(x_map.toDouble() +149.0, 14.0)
			g.closePath()
			g.drawShape()
			x_map -= 1
			g.drawCircle(x_map.toDouble() + 70.0, 5.0, 2.0, DrawStyle.FILL)
			
			// Make the character
			val jump_list3 = listOf(17, 23, 32, 38, 47, 53, 77, 83, 92, 98)
			val jump_list6 = listOf(18, 19, 20, 21, 22, 33, 34, 35, 36, 37, 48, 49, 50, 51, 52, 78, 79, 80, 81, 82, 93, 94, 95, 96, 97)
			
			//val yy = 0.0
			var yy = 0.0
			if (score in jump_list3){
				yy = -3.0 
			}else if (score in jump_list6){
				yy = -6.0
			}

			val y1 = if (score%3 == 1) yy - 1.0 else yy
			val y2 = if (score%3 == 2) yy - 1.0 else yy
			score += 1
		
			g.beginShape() // body
			g.moveTo(8.0,12.0+yy)
			g.lineTo(7.0,10.0+yy)
			g.lineTo(6.0,9.0+yy)
			g.lineTo(5.0,9.0+yy)
			g.lineTo(4.0,10.0+yy)
			g.lineTo(3.0,12.0+yy)
			g.closePath()
			g.drawShape()
			
			g.beginShape() // fore leg
			g.moveTo(6.0,13.0+y1)
			g.lineTo(6.0,12.0+y1)
			g.drawShape(DrawStyle.STROKE_AND_FILL)
			
			g.beginShape()
			g.moveTo(7.0,13.0+y1)
			g.lineTo(7.0,13.0+y1)
			g.drawShape(DrawStyle.STROKE_AND_FILL)
			
			
			g.beginShape() // back leg
			g.moveTo(4.0,13.0+y2)
			g.lineTo(3.0,13.0+y2)
			g.drawShape(DrawStyle.STROKE_AND_FILL)
			
			g.beginShape()
			g.moveTo(3.0,12.0+y2)
			g.lineTo(3.0,12.0+y2)
			g.drawShape(DrawStyle.STROKE_AND_FILL)
			
			
			g.done()
			
		}else{
			selector += 1
		}
		
		return false
		
	} else if (selector == 2){    // message "GAME OVER"
		if (y % 2 == 0){
			g.drawText(message2, 0.0, 8.0)
			g.drawText(message22, 0.0, 16.0)
			g.done()
		}
		if (y == 5){
			selector += 1
		}
		y += 1
		return false

	} else if (selector == 3){
		if (z < 10){
			g.setFont(9.0, "SansSerif")
			g.drawText(message3, 0.0, 8.0)
			g.setFont(11.0, "SansSerif")
			g.drawText(score.toString(), 0.0, 17.0)
			z += 1
		}else{
			selector += 1
		}
			
		return false
		
	} else{                        // End animation
		return true
	}
}
