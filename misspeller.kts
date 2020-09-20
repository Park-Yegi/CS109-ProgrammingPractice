fun readPronounciations(): Map<String, Set<String>> {  //(영어단어:{가능한 발음 모두})이 들어있는 map
  val words = java.io.File("words.txt").useLines { it.toSet() + setOf("i", "a") }
  val M = mutableMapOf<String, Set<String>>()
  java.io.File("cmudict.txt").forEachLine { l ->
    if (l[0].isLetter()) {
      val p = l.trim().split(Regex("\\s+"), 2)
      val i = p[0].indexOf('(')
      val word = (if (i >= 0) p[0].substring(0,i) else p[0]).toLowerCase()
      if (word in words) {
	val pro = p[1]
	val S = M.getOrElse(word) { emptySet<String>() }
	M[word] = S + pro
      }
    }
  }
  return M
}

fun reverseMap(m: Map<String, Set<String>>): Map<String,Set<String>> {  //(발음:{그 발음인 단어들})이 들어있는 map
  var r = mutableMapOf<String,MutableSet<String>>()
  for ((word, pros) in m) {
	for (pro in pros){
		val s = r.getOrElse(pro) { mutableSetOf<String>() }
		s.add(word)
		r[pro] = s
	}
  }
  return r
}


fun homophoneMap(): Map<String, List<String>>{  //(word : [발음 같은 words])
	val m = readPronounciations()
	val r = reverseMap(m)
	val result = mutableMapOf<String, List<String>>()
	for ((k, v) in r){  //v는 Set
		if (v.size !=1){
			for (i in v){
				val homophone = mutableListOf<String>()
				for (j in v){
					if (i != j){
						homophone.add(j)
					}
				}
				result.getOrPut(i){homophone}
			}
		}
	}
	return result
}

fun misspellLine(s: String): Int{
  var i = 0
  val total = homophoneMap()  //map(word : [발음 같은 words]), 같은 발음인 단어 없으면 null
  var sub_s = ""
  while (i < s.length) {
	if (s[i] in 'a'..'z' || s[i] in 'A'..'Z'){
		sub_s += s[i].toString()
	}else{
		if (sub_s in total){
			var samephone = total[sub_s]!!
			val random = java.util.Random()
			var p = samephone[random.nextInt(samephone.size)]
			if (sub_s == sub_s.toLowerCase()){
				p = p.toLowerCase()
			}else if (sub_s == sub_s.toUpperCase()){
				p = p.toUpperCase()
			} else if (sub_s[0].toString() == sub_s.toUpperCase()){
				p = p[0].toUpperCase() + p.substring(1)
			}
			print(p)
		}else{
			print(sub_s)
		}
		sub_s = ""
		print(s[i])
	}
	i++
  }
  return 0
}

if (args.size != 1) {
  println("Usage: kts misspeller.kts <filename>")
  kotlin.system.exitProcess(1)
}
val hm = homophoneMap()
java.io.File(args[0]).forEachLine { misspellLine(it, hm) }