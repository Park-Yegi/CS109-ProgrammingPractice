
// check if ch is a letter
fun isLetter(ch: Char) = ch in 'a'..'z' || ch in 'A'..'Z'

// shuffle(s) returns a randomly shuffled copy of the string s
fun shuffle(s: String): String {
  val l = s.toMutableList()
  java.util.Collections.shuffle(l)
  return l.joinToString("")
}

fun reorderLetters(s: String) {
  var i = 0
  var middle_list = mutableListOf<String>()
  while (i < s.length) {
	if (isLetter(s[i])) {
		if (i-1 > 0 && i+1 <s.length) {
		//var s_str_1 = s[i-1].toString()
		//var s_str_2 = s[i+1].toString()
		if (isLetter(s[i-1]) != true) {
			print(s[i])
		} else if (isLetter(s[i+1]) != true) {
			var middle_string = middle_list.joinToString("")
			print(shuffle(middle_string))
			middle_list.clear()
			print(s[i])
		} else {
			var s_str = s[i].toString()
			middle_list.add(s_str)
		}
		} else {
			print(s[i])
		}
	} else {
		print(s[i])
	}
	i++
	
  }
  println()
}

if (args.size != 1)
  println("Usage: kts readable.kts <filename>")
else
  java.io.File(args[0]).forEachLine { reorderLetters(it) }
