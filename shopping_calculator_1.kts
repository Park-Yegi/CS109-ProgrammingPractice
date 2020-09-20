import org.otfried.cs109.readString

println("Shopping Calculator V0.1")

var total = 0

while (1>0){
	var name = readString("What did you buy?").trim()
	
	if (name.isEmpty()){
		break
	}
	
	var numberstring = readString("How many $name did you buy?").trim()
	var number = numberstring.toInt()
	var pricestring = readString("What is the price of one $name?").trim()
	var price = pricestring.toInt()
	var price2 = number*price
	println("You bought $number $name for $price2 KRW")
	total += price2
}

println("In total, you spent $total KRW")