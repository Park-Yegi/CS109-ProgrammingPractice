val random = java.util.Random()

fun pi(n: Int){
	var count = 0
	for (i in 1..n){
		val y0 = random.nextDouble()
		val angle = random.nextDouble() * Math.PI/2.0
		val y1 = y0 + Math.sin(angle)
		if (y1>1){
			count+=1
		}
	}
	println("Among $n needle throws, $count crossed a line")
	val countdouble = count.toDouble()
	val ndouble = n
	val two = 2* (ndouble/countdouble)
	println("2 * ($n/$count) = $two")
	}
	
val n = if (args.size==1) args[0].toInt() else 100
pi(n)