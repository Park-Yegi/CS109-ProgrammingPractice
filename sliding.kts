import org.otfried.cs109.readString

data class Pos(val row: Int, val col: Int)
val random = java.util.Random()

fun makeField(rows: Int, cols: Int): Array<Array<Int>>{
	val field = Array(rows) { Array(cols) {0}}
	var num = 1
	for (i in 0 until rows) {
		for (j in 0 until cols){
			field[i][j] = num
			num++
		
		}
	}
	field[rows-1][cols-1] = 0
	return field
}

fun displayField(field: Array<Array<Int>>): String{
	var rows = field.size
	var cols = field[0].size
	var result = ""
	for (i in 0 until rows){
		for (j in 0 until cols){
			if (field[i][j] != 0){
			result += "o----o "
			}else{
			result += "       "
			}
		}
		result += "\n"
		for (j in 0 until cols){
			if (field[i][j] != 0){
			result += "|    | "
			}else{
			result += "       "
			}
		}
		result += "\n"
		for (j in 0 until cols){
			if (field[i][j] != 0){
			result += "| "
			result += "%2d".format(field[i][j])
			result += " | "
			}else{
			result += "       "
			}
		}
		result += "\n"
		for (j in 0 until cols){
			if (field[i][j] != 0){
			result += "|    | "
			}else{
			result += "       "
			}
		}
		result += "\n"
		for (j in 0 until cols){
			if (field[i][j] != 0){
			result += "o----o "
			}else{
			result += "       "
			}
		}
		result += "\n"
	}
	return result
}


fun findEmpty(field: Array<Array<Int>>): Pos {
	var rows = field.size
	var cols = field[0].size
	for (i in 0 until rows){
		for (j in 0 until cols){
			if (field[i][j] == 0){
				return Pos(i,j)
			}
		}
	}
	return Pos(-1,-1)
}

fun makeMove(field: Array<Array<Int>>, delta: Pos) {
	val rows = field.size
	val cols = field[0].size
	val now = findEmpty(field)
	val upd = Pos(now.row + delta.row, now.col + delta.col)
	if (upd.row <= rows-1 && upd.col <= cols -1){
		if (upd.row >= 0 && upd.col >= 0){
		field[now.row][now.col] = field[upd.row][upd.col]
		field[upd.row][upd.col] = 0
		}
	}
}

fun shuffle(field: Array<Array<Int>>, iter: Int){
	val moves = arrayOf(Pos(1,0), Pos(-1,0), Pos(0,1), Pos(0,-1))
	for (i in 1..iter){
		val mov = moves[random.nextInt(4)]
		makeMove(field,mov)
	}
}
	
fun strToMove(s: String): Pos{
	val t = s.toLowerCase()
	if (t.contains("left") || t.contains("l")) {             //left
		return Pos(0,1)
	}else if (t.contains("right") || t.contains("r")) {      //right
		return Pos(0,-1)
	}else if (t.contains("up") || t.contains("u")) {         //up
		return Pos(1,0)
	}else if (t.contains("down") || t.contains("dn")) {      //down
		return Pos(-1,0)
	}else{                                                   //unrecognized string
		return Pos(0,0)
	}
}
	
fun playGame(rows: Int, cols: Int){
	val f = makeField(rows,cols)
	shuffle(f, 1000)
	while (true){
		println(displayField(f))
		val s = readString("What is your move> ")
		println()
		if (s == "quit")
			return
		val move = strToMove(s)
		makeMove(f,move)
	}
}

val rows = if (args.size==2) args[0].toInt() else 4
val cols = if (args.size==2) args[1].toInt() else 4
playGame(rows, cols)
