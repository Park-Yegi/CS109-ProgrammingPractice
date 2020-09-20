import org.otfried.cs109.Context
import org.otfried.cs109.MiniApp

import org.otfried.cs109.Canvas
import org.otfried.cs109.Color
import org.otfried.cs109.DrawStyle
import org.otfried.cs109.TextAlign

fun sq(x: Double) = x * x

class Main(val ctx: Context) : MiniApp {
  var gravity = arrayOf(0.0, 0.0, 0.0)

  init {
    ctx.setTitle("Spirit level")
    ctx.onGravity { x, y, z -> updateGravity(x, y, z) }
  }

  fun updateGravity(x: Double, y: Double, z: Double) {
    gravity = arrayOf(x, y, z)
    ctx.update()
  }

  override fun onDraw(canvas: Canvas) {
    val x = canvas.width / 2.0
	val y = canvas.height / 2.0
	canvas.clear(Color(255, 255, 192))
    canvas.setColor(Color.BLUE)
	canvas.setFont(48.0)
	canvas.drawCircle(x, y, x/5.0, DrawStyle.STROKE) //Small Circle
	canvas.drawCircle(x, y, x-5.0, DrawStyle.STROKE) //Large Circle
    
	val angle = Math.acos(Math.abs(gravity[2] / 9.810))
	var distance = angle * 500.0
	if (distance > 195.0) {
		distance = 195.0
	}
	
	val direction = Math.atan2(-gravity[1], gravity[0])
	val position = Pair(distance*Math.cos(direction), distance*Math.sin(direction))
	
	canvas.setColor(Color.RED)
    canvas.drawCircle(x + position.first, y + position.second, x/6.0, DrawStyle.FILL)
  }
}
