import java.io.File
import java.awt.image.BufferedImage
import org.otfried.cs109ui.*
import org.otfried.cs109.*

data class Pos(val x: Int, val y: Int) {
	fun dx(d: Int): Pos = Pos(x+d, y)
	fun dy(d: Int): Pos = Pos(x, y+d)
}

class Block(p: Pos) {
	var a = mutableListOf(p)
	
	override fun toString(): String {
		if (a.size ==1){
			var x1 = a[0].x.toString()
			var y1 = a[0].y.toString()
			return "Block{Pos(x=" + x1 + ", y=" + y1 + ")}"
		}else{
			var x1 = a[0].x.toString()
			var y1 = a[0].y.toString()
			var x2 = a[1].x.toString()
			var y2 = a[1].y.toString()
			return "Block{Pos(x=" + x1 + ", y=" + y1 + "),Pos(x=" + x2+ ", y=" + y2 +")}"
		}
	}
	
	fun positions(): List<Pos> {
		return a
	}
	
	fun isStanding(): Boolean {
		if (a.size == 1) {
			return true
		} else {
			return false
		}
	}
	
	fun left(): Unit {
		if (a.size == 1) {
			var p1 = a[0].dx(-2)
			var p2 = a[0].dx(-1)
			a = mutableListOf(p1, p2)
		}else{
			var p1 = a[0].dx(-1)
			var p2 = a[1].dx(-1)
			if (a[0].x == a[1].x){
				a = mutableListOf(p1,p2)
			}else{
				a = mutableListOf(p1)
			}
		}
	}
	
	fun right(): Unit {
		if (a.size == 1) {
			var p1 = a[0].dx(1)
			var p2 = a[0].dx(2)
			a = mutableListOf(p1, p2)
		}else{
			var p1 = a[0].dx(1)
			var p2 = a[1].dx(1)
			if (a[0].x == a[1].x){
				a = mutableListOf(p1,p2)
			}else{
				a = mutableListOf(p2)
			}
		}
	}
	
	fun up(): Unit {
		if (a.size == 1) {
			var p1 = a[0].dy(-2)
			var p2 = a[0].dy(-1)
			a = mutableListOf(p1, p2)
		}else{
			var p1 = a[0].dy(-1)
			var p2 = a[1].dy(-1)
			if (a[0].x == a[1].x){
				a = mutableListOf(p1)
			}else{
				a = mutableListOf(p1,p2)
			}
		}
	}
	
	fun down(): Unit {
		if (a.size == 1) {
			var p1 = a[0].dy(1)
			var p2 = a[0].dy(2)
			a = mutableListOf(p1, p2)
		}else{
			var p1 = a[0].dy(1)
			var p2 = a[1].dy(1)
			if (a[0].x == a[1].x){
				a = mutableListOf(p2)
			}else{
				a = mutableListOf(p1,p2)
			}
		}
	}
}


class Terrain(fname: String) {
	val filename = fname
	
	fun makeMap(fname: String): Map<Pair<Int,Int>,Char> {
		val file = java.io.File(fname)
		val terrain = mutableMapOf<Pair<Int,Int>,Char>()
		var j = 0
		file.forEachLine {
			if (it != ""){
				val li = it.toList()
				for (i in 0 until li.size) {
					val k = Pair(i,j)
					terrain[k] = li[i]
				}
			}
			j++
		}
		return terrain
	}
	
	fun start(): Pos {
		var result = Pos(0,0)
		for ((po,type) in makeMap(filename)) {
			if (type == 'S') {
				result = Pos(po.first, po.second)
			}
		}
		return result
	}
	
	fun target(): Pos {
		var result = Pos(0,0)
		for ((po,type) in makeMap(filename)) {
			if (type == 'T') {
				result = Pos(po.first, po.second)
			}
		}
		return result
	}
	
	fun width(): Int {
		var w = 0
		for ((po, type) in makeMap(filename)) {
			if (po.first > w) {
				w = po.first
			}
		}
		return w+1
	}
	
	fun height(): Int {
		var h = 0
		for ((po, type) in makeMap(filename)) {
			if (po.second > h) {
				h = po.second
			}
		}
		return h+1
	}
	
	fun at(p:Pos): Int {
		val pa = Pair(p.x, p.y)
		if (makeMap(filename)[pa] == 'o' || makeMap(filename)[pa] == 'S' || makeMap(filename)[pa] == 'T') {
			return 2
		} else if (makeMap(filename)[pa] == '.') {
			return 1
		} else {
			return 0
		}
	}
	
	fun canHold(b: Block): Boolean {
		val terrain = makeMap(filename)
		val p = b.positions() //p의 type: List<Pos>
		if (p.size == 1) {
			if (terrain[Pair(p[0].x, p[0].y)] == null || terrain[Pair(p[0].x, p[0].y)] == '.' || terrain[Pair(p[0].x, p[0].y)] == '-'){
				return false
			} else {
				return true
			}
		}else if (p.size ==2){
			if (terrain[Pair(p[1].x, p[1].y)] == '-' || terrain[Pair(p[1].x, p[1].y)] == '-' ){
				return false
			} else if (terrain[Pair(p[1].x, p[1].y)] == null || terrain[Pair(p[1].x, p[1].y)] == null){
				return false
			} else {
				return true
			}
		}
		return false
	}
}


fun tileSize(t: Terrain): Int {
  var ts = 60
  while (ts > 5) {
    if (t.width() * ts <= 800 && t.height() * ts <= 640)
      return ts
    ts -= 2
  }
  return ts
}


fun makeMove(b: Block, ch: Char) {
	if (ch == 'l') {
		b.left()
	} else if (ch == 'u') {
		b.up()
	} else if (ch == 'r') {
		b.right()
	} else {
		b.down()
	}
	return
}


fun draw(canvas: ImageCanvas, terrain: Terrain, ts: Double, block: Block) {
	val t = terrain.target()
	val w = terrain.width()
	val h = terrain.height()
	
	var y_position = ts/7.0
	
	canvas.clear(Color.WHITE)
	for (y in 0 until h) {
		var x_position = ts/7.0
		for (x in 0 until w) {
			if (terrain.at(Pos(x,y)) != 0) {
				if (Pos(x,y) in block.positions()) {
					canvas.setColor(Color.RED)
				} else if (terrain.at(Pos(x,y)) == 2) {
					if (Pos(x,y) == t){
						canvas.setColor(Color.GREEN)
					} else {
						canvas.setColor(Color.BLUE)
					}
				} else if (terrain.at(Pos(x,y)) == 1) {
					canvas.setColor(Color.YELLOW)
				}
				canvas.drawRectangle(x_position, y_position, ts, ts, DrawStyle.FILL)
			}
			x_position = x_position + ts + ts/15.0
		}
		y_position = y_position + ts + ts/15.0
	}
}
 
 
fun playLevel(level: Int) {
  val terrain = Terrain("terrains/level%02d.txt".format(level))
  val ts = tileSize(terrain)*14.0/15.0
  val image = BufferedImage(ts * terrain.width() + 20, 
                            ts * terrain.height() + 20, 
                            BufferedImage.TYPE_INT_RGB)
  val canvas = ImageCanvas(image)
  var block = Block(terrain.start())
  var moves = 0

  while (true) {
    setTitle("Bloxorz Level $level ($moves moves)")
    draw(canvas, terrain, ts.toDouble(), block)
    show(image)
    val ch = waitKey()
    if (ch in "lurd") {
      makeMove(block, ch)
      moves += 1
    }
    if (block.isStanding() && block.positions().first() == terrain.target()) {
      showMessage("Congratulations, you solved level $level")
      return
    }
    if (!terrain.canHold(block)) {
      showMessage("You fell off the terrain")
      block = Block(terrain.start())
    }
  }
}


fun main(args: Array<String>) {
  var level = 1
  while (level <= 9) {
	playLevel(level)
	level++
  }
  return
}