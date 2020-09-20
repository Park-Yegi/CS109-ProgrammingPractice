val words = java.io.File("words.txt").useLines{ it.toSet() }

fun sortWord(s: String): String =
    s.toCharArray().sorted().joinToString(separator="")

fun findAnagrams(s: String) : List<String> {
	val s_lower = s.toLowerCase()
	var s_noSpace = ""
	for (i in 1..s_lower.length){
		if (s_lower[i-1] != ' '){
				s_noSpace += s_lower[i-1].toString()
			}
		}
	return words.filter{sortWord(s_noSpace) == sortWord(it)}
}

fun ePluribusUnum(phrase: String){
	println("E pluribus unum: '$phrase' --> '${findAnagrams(phrase)}'")
}


print("The number of words that contain 'cie' is ")
println(words.filter{"cie" in it}.size)
print("The number of words that contain 'cei' is ")
println(words.filter{"cei" in it}.size)
print("The number of words that contain 'ei' but not 'cei' is ")
println(words.filter{"ei" in it && "cei" !in it}.size)
print("The number of words that contain 'ie' but not 'cie' is ")
println(words.filter{"ie" in it && "cie" !in it}.size)
//According to the result of above, I think the rule is almost true.

fun wordpuzzle(): Int{
	val word7 = words.filter{it.length == 7}
	val wordSecondT = word7.filter{it[0].toString() + "t" + it.substring(1) in words}
	//The answer for doctor and fish is "surgeon"
	
	val word6 = words.filter{it.length == 6}
	val word_startwithZ = word6.filter{"z" + it in words}
	//The answer for Eggs and Animals is "oology"
	
	val word10 = words.filter{it.length == 10 }
	val wordMiddleT = word10.filter{it.substring(0,5) + "t" + it.substring(5) in words}
	//The answer for Make you famous and Make you infamous is "immorality"
	
	val wordMiddleC = words.filter{it.length == 7 && it[4] == 'c'}
	val wordMiddleD = wordMiddleC.filter{it.substring(0,4) + "d" + it.substring(5) in words}
	//The answer for sweet and sticky and It's foot operated is "treale"
	
	val word_startwithE = words.filter{it.length == 6 && it[0] == 'e'}
	val word_startwithE2 = word_startwithE.filter{"au" + it.substring(1) in words}
	val word_startwithE3 = word_startwithE2.filter{"cre" + it.substring(1) in words}
	val word_startwithE4 = word_startwithE3.filter{"expe" + it.substring(1) in words}
	//The answer for next puzzle is "edited"
	
	val word3 = words.filter{it.length == 3}
	val word_startwithRH = words.filter{it.substring(0,2) == "rh" && it.length ==5}
	val word_startwithRH2 = word_startwithRH.filter{"b" + it.substring(2) in words}
	val word_startwithRH3 = word_startwithRH2.filter{"c" + it.substring(2) in words}
	val word_startwithRH4 = word_startwithRH3.filter{"t" + it.substring(2) in words}
	//The answer for last puzzle is "omb"
	
	return 0	
}