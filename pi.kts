val random = java.util.Random()

fun pi(n: Int){
	var count = 0
	for (i in 1..n){
		val x = random.nextDouble()
		val y = random.nextDouble()
		if (x*x + y*y <= 1){
			count+=1
		}
	}
	println("Among $n samples there were $count hits")
	val countdouble = count.toDouble()
	val ndouble = n
	val four = 4* (countdouble/ndouble)
	println("4 * ($count/$n) = $four")
	}

val n = if (args.size==1) args[0].toInt() else 100
pi(n)