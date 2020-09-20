val monthLength = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val leapMonthLength = arrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val fourYears = (365 + 365 + 365 + 366)
 

// returns the number of days since 1901/01/01 (day 0)
// returns -1 if illegal, or out of range 1901~2099
fun date2num(year: Int, month: Int, day: Int): Int {
  if (year < 1901 || year > 2099 || month < 1 || month > 12)
    return -1
  val is_leap = (year % 4 == 0)
  val ml = if (is_leap) leapMonthLength else monthLength
  if (day < 1 || day > ml[month-1])
    return -1

  val yearn = year - 1901
  val monthn = month - 1
  val dayn = day - 1

  var total = 0
  total += fourYears * (yearn / 4)
  total += 365 * (yearn % 4)
  for (m in 0 until monthn)
    total += ml[m]
  total += dayn
  return total
} 

// decode a string date
// returns (0,0,0) if not in "YYYY/MM/DD" format
fun string2date(s: String): Triple<Int, Int, Int> {
	val result = Triple(0, 0, 0)
	val splitList = s.split("/")
	
	if (splitList.size != 3) {
		return result
	}
	val year = splitList[0]
	val month = splitList[1]
	val day = splitList[2]
	
	if (s.length != 10) {
		return result
	}
	
	for (i in 0..9) {
		if ( s[i] != '/' ) {
		// 0<= s <= 9 /  s>=0 and s<=9 / s<0 or s>9
			if (s[i] < '0' || s[i] > '9') {
				return result
	}
	}
	}

	if (year.length != 4 || month.length != 2 || day.length != 2){
		return result
	} else {
		val yearInt = year.toInt()
		val monthInt = month.toInt()
		val dayInt = day.toInt()
		
		val result2 = Triple(yearInt, monthInt, dayInt)
		return result2
	}
}

fun num2date(n: Int): Triple<Int, Int, Int> {
	val new_n = n+1
	val nDivision = new_n/fourYears
	val nDivision2 = (new_n-fourYears*nDivision)/365
	val year = 1901 + nDivision*4 + nDivision2
	
	var rest = new_n - fourYears*nDivision - 365*nDivision2
	var count = 0
	if (new_n-fourYears*nDivision < 365) {
		for (i in leapMonthLength) {
			if (rest > i) {
				rest -= i
				++count
			} else {
				break
			}
		}
	} else {
		for (i in monthLength) {
			if (rest > i) {
				rest -= i
				++count
			} else {
				break
			}
		}
	}
	
	val day =rest
	val month = count+1
	var date = Triple(year, month, day)
	
	if (year % 4 != 0 && month == 2 && day == 29) {
		date = Triple(year, 3, 1)
	}
	
	return date;
}

fun testAll() {
  for (year in 1901 .. 2099) {
    for (month in 1 .. 12) {
      for (day in 1 .. 28) {
        val n = date2num(year, month, day)
		var (y,m,d) = num2date(n)
	if (year != y || month != m || day != d)
	  println("Incorrect conversion day number $n <-> $y, $m, $d")
      }
    }
  }
}