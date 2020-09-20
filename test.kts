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
	
println(digits[0][5] == true)