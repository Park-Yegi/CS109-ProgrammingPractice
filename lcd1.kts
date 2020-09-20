val digits = 
  arrayOf(arrayOf(true, true, true, true, true, true, false),  // 0
	arrayOf(true, true, false, false, false, false, false),    // 1
	arrayOf(true, false, true, true, false, true, true),       // 2
	arrayOf(true, true, true, false, false, true, true),       // 3
	arrayOf(true, true, false, false, true, false, true),      // 4
	arrayOf(false, true, true, false, true, true, true),       // 5
	arrayOf(false, true, true, true, true, true, true),        // 6
	arrayOf(true, true, false, false, false, true, false),     // 7
	arrayOf(true, true, true, true, true, true, true),         // 8
	arrayOf(true, true, true, false, true, true, true),        // 9
	arrayOf(false, false, false, false, false, false, false))  // Blank

fun lcdDigit(digit:Char, k:Int, c:Char) : String {
	var result = ""
	val c_string = c.toString()
	val d = if ('0' <= digit && digit <= '9') digit - '0' else 10
	val d_int = d.toInt()
	
	result += " "
	if (digits[d_int][5] == true) {
		for (i in 1..k) {
			result += c_string
		}
	} else {
		for (i in 1..k) {
			result += " "
		}
	}
	result += " "
	result += "\n"

	if (digits[d_int][4] == true) {
		for (i in 1..k) {
			result += c_string
			for (j in 1..k) {
				result += " "
			}
			if (digits[d_int][0] == true) {
				result += c_string
			} else {
				result += " "
			}
			result += "\n"
		}
	} else {
		for (i in 1..k) {
			result += " "
			for (j in 1..k) {
				result += " "
			}
			if (digits[d_int][0] == true) {
				result += c_string
			} else {
				result += " "
			}
			result += "\n"
		}
	}

	result += " "
	if (digits[d_int][6] == true) {
		for (i in 1..k) {
			result += c_string
		}
	} else {
		for (i in 1..k) {
			result += " "
		}
	}
	result += " "
	result += "\n"
	
	if (digits[d_int][3] == true) {
		for (i in 1..k) {
			result += c_string
			for (j in 1..k) {
				result += " "
			}
			if (digits[d_int][1] == true) {
				result += c_string
			} else {
				result += " "
			}
			result += "\n"
		}
	} else {
		for (i in 1..k) {
			result += " "
			for (j in 1..k) {
				result += " "
			}
			if (digits[d_int][1] == true) {
				result += c_string
			} else {
				result += " "
			}
			result += "\n"
		}
	}
	
	result += " "
	if (digits[d_int][2] == true) {
		for (i in 1..k) {
			result += c_string
		}
	} else {
		for (i in 1..k) {
			result += " "
		}
	}
	result += " "
	
	return result
	}
	
fun combine(left: String, sep: String, right: String): String {
	val left_list = left.split("\n")
	val right_list = right.split("\n")
	var total_list = mutableListOf<String>()
	val length = left_list.size
	
	for (i in 0 until length){
		total_list.add(left_list[i] + sep + right_list[i])
	}
	
	val result = total_list.joinToString(separator = "\n")
	return result
}

fun lcd(s: String, k: Int, c: Char, sep: String): String {
  var result = lcdDigit(s[0], k, c)
  for (i in 1 until s.length)
    result = combine(result, sep, lcdDigit(s[i], k, c))
  return result
}

fun clearScreen() {
  println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
}

fun clock() {
  val form = java.text.SimpleDateFormat("HH mm ss")
  var current = form.format(java.util.Calendar.getInstance().getTime())
  clearScreen()
  println(lcd(current, 4, '#', " "))
  while (true) {
    Thread.sleep(100)
    val ntime = form.format(java.util.Calendar.getInstance().getTime())
    if (ntime != current) {
      current = ntime
      clearScreen()
      println(lcd(current, 4, '#', " "))
    }
  }
}

clearScreen()
clock()