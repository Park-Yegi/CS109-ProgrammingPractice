import org.otfried.cs109.readString

println("Shopping Calculator V0.2")

var total = 0
val L = mutableListOf<Triple<String, Int, Int>>()
val L2 = mutableListOf<Int>()

while (1>0){
	var name = readString("What did you buy?").trim()
	
	if (name.isEmpty()){
		break
	}
	
	var numberstring = readString("How many $name did you buy?").trim()
	var number = numberstring.toInt()
	var pricestring = readString("What is the price of one $name?").trim()
	var price = pricestring.toInt()
	L.add(Triple(name, number, price))
	var price2 = number*price
	println("You bought $number $name for $price2 KRW")
	L2.add(price2)
	total += price2
}

println("Your purchases:")
println("-------------------------------------------------")

for (i in L.indices){
	var forname = L[i].first
	var fornumber = L[i].second
	var forprice = L[i].third
	var forprice2 = fornumber*forprice
	println("%-20s %2d x %4d KRW    %4d KRW".format(forname, fornumber, forprice, forprice2))
}

println("-------------------------------------------------")
println("Total price: \t \t \t \t $total KRW")
println("-------------------------------------------------")
