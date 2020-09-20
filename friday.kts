val monthLength = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val leapMonthLength = listOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

fun dayNumbers(monthDays: List<Int>) : List<Int>{
	val L = mutableListOf<Int>()
	for (i in 0..11){
		var date = 12
		for (j in 0 until i){
			date+=monthDays[j]
		}
		L.add(date)
	}
	return L
}

fun weekDays(days: List<Int>): List<Int> {
	//7로 나눈 나머지 0,1,2,3,4,5,6 순서로 list만들기
	val W = mutableListOf(0,0,0,0,0,0,0)
	for (i in days){
		val m = i%7
		W[m]+=1
	}
	return W
}

println(weekDays(dayNumbers(monthLength)))
println(weekDays(dayNumbers(leapMonthLength)))