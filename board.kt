class Board {
  private val a = Array<Array<Int>>(4) { arrayOf(0, 0, 0, 0) } 

  private fun displayRow(s: StringBuilder, row: Int, 
			 form: String?, term: String) {
    for (col in 0 until 4) {
      if (form == null) {
	val m = a[row][col]
	if (m != 0) {
	  var ms = "   " + m.toString()
	  ms = ms.substring(ms.length - 3)
	  s.append(if (m < 1000) "|$ms " else "|$m")
	} else
	  s.append("|    ")
      } else
	s.append(form)
    }
    s.append(term)
    s.append('\n')
  }

  override fun toString(): String {
    val s = StringBuilder()
    for (row in 0 .. 3) {
      displayRow(s, row, "o----", "o")
      displayRow(s, row, "|    ", "|")
      displayRow(s, row, null, "|")
      displayRow(s, row, "|    ", "|")
    }
    displayRow(s, 3, "o----", "o")
    return s.toString()
  }

  // for debugging and testing
  constructor(vararg contents: Int) {
    if (contents.size != 0) {
      assert(contents.size == 15)
      for (row in 0 until 4)
        for (col in 0 until 4)
          a[row][col] = contents[4 * row + col]
    }
  }  

  // for debugging and testing
  fun toList(): List<Int> = a.flatMap { it.toList() }

  fun cell(row: Int, col: Int): Int = a[row][col]

  // is the board completely filled?
  fun isFull(): Boolean {
	for (i in 0 .. 3){
		for (j in 0 .. 3){
			if (a[i][j] == 0){
				return false
			}
		}
	}
	return true
  }
  
  fun insert() {
	val Two_or_Four = listOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 4)
	var notEmpty = mutableListOf<Pair<Int, Int>>()
    for (i in 0 .. 3){
		for (j in 0 .. 3){
			if (a[i][j] == 0){
				val element = Pair(i, j)
				notEmpty.add(element)
			}
		}
	}
	
	val random = java.util.Random()
	var index = random.nextInt(Two_or_Four.size)
	var new = Two_or_Four[index]
	var index2 = random.nextInt(notEmpty.size)
	var new2 = notEmpty[index2]
	a[new2.first][new2.second] = new
  }
	
	
  fun pushLeft(): Int {
	for (i in 0 .. 3){
		var count0 = 0
		var notZero = mutableListOf<Int>()
		for (j in 0 .. 3){
			if (a[i][j] == 0){
				count0 += 1
			}else{
				notZero.add(a[i][j])
			}
		}
		
		for (k in 0 .. (notZero.size-1)){
			a[i][k] = notZero[k]
		}
		for (s in notZero.size .. 3){
			a[i][s] = 0
		}
	}
	
	var result = 0
	for (x in 0..3){
		var y = 0
		while (y < 3){
			if (a[x][y] == a[x][y+1] && a[x][y] != 0){
				result += a[x][y]*2
				a[x][y] = a[x][y]*2
				if (y != 2){
					for (z in (y+1) .. 2){
						a[x][z] = a[x][z+1]
						a[x][z+1] = 0
					}
				}else{
					a[x][3] = 0
				}
			}
			y += 1
		}
	}
	return result
  }
  
  private fun mirror() {
	for (i in 0 .. 3){
		for (j in 0 .. 1){
			var temp = a[i][j]
			a[i][j] = a[i][3-j]
			a[i][3-j] = temp
		}
	}
  }
  
  private fun transpose() {
	for (i in 0 .. 3){
		for (j in 0 .. 3){
			if (i < j){
				var temp = a[i][j]
				a[i][j] = a[j][i]
				a[j][i] = temp
			}
		}
	}
  }

  fun pushRight(): Int {
    mirror()
	val result = pushLeft()
	mirror()
	return result 
  }

  fun pushUp(): Int {
    transpose()
	val result = pushLeft()
	transpose()
	return result
  }

  fun pushDown(): Int {
    transpose()
	mirror()
	val result = pushLeft()
	mirror()
	transpose()
	return result
  }

  // pushes in direction ch (in 'lrud')
  // returns number of points
  fun push(ch: Char): Int = when(ch) {
    'l' -> pushLeft()
    'r' -> pushRight()
    'u' -> pushUp()
    'd' -> pushDown()
    else -> 0
  }
}

