import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.readString

import java.awt.image.BufferedImage

// Input h in [0,359], s in [0,255], v in [0,255]
// Output r,g,b in [0,255]
fun hsvtorgb(h: Int, s: Int, v: Int): Triple<Int, Int, Int> {
  if (s == 0) {
    // no color, just grey
    return Triple(v, v, v)
  } else {
    val sector = h / 60
    val f = (h % 60) 
    val p = v * ( 255 - s ) / 255
    val q = v * ( 15300 - s * f ) / 15300
    val t = v * ( 15300 - s * ( 60 - f )) / 15300
    return when(sector) {
      0 -> Triple(v, t, p)
      1 -> Triple(q, v, p)
      2 -> Triple(p, v, t)
      3 -> Triple(p, q, v)
      4 -> Triple(t, p, v)
      else -> Triple(v, p, q)
    }
  }
}

fun randomHSV(): Triple<Int, Int, Int> {
	val random = java.util.Random()
	return Triple(random.nextInt(360), 128 + random.nextInt(128), 128 + random.nextInt(128))
}

fun main(args: Array<String>){
	val random = java.util.Random()
	val delta = if (args.size == 1) args[0].toInt() else 20
	var total_test = 1
	var correct_test = 0
	
	while (true) {
		// set canvas
		setTitle("How good is your color vision?")
		val img = BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB)
		val can = ImageCanvas(img)
		can.clear(Color.WHITE)
		
		// set standard color & different colror
		var standard = randomHSV()
		var h = standard.first
		var s = standard.second
		var v = standard.third
		var standardRGB = hsvtorgb(h, s, v)
		var diff_h = h
		
		if (h < delta) {
			diff_h += delta
		} else if (h >= 340){
			diff_h -= delta
		} else {
			var filpCoin = random.nextInt(2)
			if (filpCoin == 0) {
				diff_h += delta
			} else {
				diff_h -= delta
			}
		}
		
		var diffRGB = hsvtorgb(diff_h, s, v)
		var diff_box = random.nextInt(16)
		val box = listOf("1a", "1b", "1c", "1d", "2a", "2b", "2c", "2d", "3a", "3b", "3c", "3d", "4a", "4b", "4c", "4d")
		var diff_box_str = box[diff_box]
		
		// Draw boxes
		can.setColor(Color(standardRGB.first, standardRGB.second, standardRGB.third))
		for (i in 0..15) {
			var quotient = (i/4).toDouble()
			var remainder = (i%4).toDouble()
			if (i != diff_box) {
				can.drawRectangle(60.0 + (110.0 * remainder), 60.0 + (110.0 * quotient), 100.0, 100.0, DrawStyle.FILL)
			}
		}
		can.setColor(Color(diffRGB.first, diffRGB.second, diffRGB.third))
		val quotient = (diff_box/4).toDouble()
		val remainder = (diff_box%4).toDouble()
		can.drawRectangle(60.0 + (110.0 * remainder), 60.0 + (110.0 * quotient), 100.0, 100.0, DrawStyle.FILL)
		
		// Draw Texts
		can.setColor(Color.BLACK)
		can.setFont(20.0, "Batang")
		can.drawText("A", 110.0, 30.0)
		can.drawText("B", 220.0, 30.0)
		can.drawText("C", 330.0, 30.0)
		can.drawText("D", 440.0, 30.0)
		can.drawText("1", 30.0, 110.0)
		can.drawText("2", 30.0, 220.0)
		can.drawText("3", 30.0, 330.0)
		can.drawText("4", 30.0, 440.0)
		
		// Start the game
		show(img)
		var answer = readString("Which square has a different color? (x to exit)")
		if (answer == "x") {
			close()
		} else if (answer == box[diff_box]) {
			correct_test += 1
			println("That is correct")
			println("You answered $correct_test of $total_test tests correctly")
		} else {
			println("That is not correct. Square $diff_box_str has a different color.")
			show(img)
			waitForMs(100)
			readString("Press Enter for the next question>")
		}
		total_test += 1
	}
}