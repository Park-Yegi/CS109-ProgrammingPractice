import org.otfried.cs109ui.*
import org.otfried.cs109ui.ImageCanvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle

import java.awt.image.BufferedImage

// Input h in [0,359], s in [0,255], v in [0,255]
// Output r,g,b in [0,255]
fun hsvtorgb1(h: Int, s: Int, v: Int): Triple<Int, Int, Int> {
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

fun main(args: Array<String>){
	var h = 0
	var s = 0
	var v = 255
	
	while (v > 0) {
		setTitle("Rainbow for v = $v")
		var img = BufferedImage(360, 256, BufferedImage.TYPE_INT_RGB)
		for (x in 0 until 360) {
			for (y in 0 until 256) {
				var red = hsvtorgb1(h, s, v).first
				var green = hsvtorgb1(h, s, v).second
				var blue = hsvtorgb1(h, s, v).third
				var color = (red * 65536) + (green * 256) + blue
				img.setRGB(x, y, color)
				s += 1
			}
			s = 0
			h += 1
		}
		h = 0
		show(img)
		waitForMs(100)
		v -= 1
	}
	close()
}