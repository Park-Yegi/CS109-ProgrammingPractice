//
// Mastermind game
// 

import org.otfried.cs109.readString

val MaxNumGuesses = 10
val CODE_LENGTH = 3
val random = java.util.Random()

// Create secret: Letters from A-F.
fun createSecret(): String {
  var c = "ABCDEF"
  var secret = ""
  for (i in 1 .. CODE_LENGTH) {
    val index = random.nextInt(c.length)
    val letter = c[index]
    secret = secret + letter
  }
  return secret
}

// Check if guess is legal: Letters from A-F.
// Returns pair (ok: Boolean, message : String) 
fun checkGuess(guess: String): Pair<Boolean, String> {
  if (guess.length != CODE_LENGTH)
    return Pair(false, "Your guess must have $CODE_LENGTH letters")
  for (i in 0 until CODE_LENGTH) {
    val letter = guess[i]
    if (letter !in "ABCDEF")
      return Pair(false, "You can only use letters A, B, C, D, E, and F.")
  }
  return Pair(true, "")
}

// read a guess from the terminal
fun getGuess(): String {
  while (true) {
    var guess = readString("Enter your guess> ")
    guess = guess.trim().toUpperCase().replace(" ", "")
    val (ok, msg) = checkGuess(guess)
    if (ok)
      return guess
    println(msg)
  }
}

// Compute (pos, let) where pos is the number of correct letters in
// the correct position, and let is the number of correct letters in
// the wrong position.
fun evaluateGuess(secret: String, guess: String): Pair<Int, Int> {
  var pos = 0
  var let = 0
  for (i in 0 until CODE_LENGTH) {
    if (guess[i] == secret[i])
      pos += 1
    else if (guess[i] in secret)
      let += 1
  }
  return Pair(pos, let)
}

// Show history of guessing  
fun showHistory(h: Array<String>, current: Int, secret: String) {
  for (count in 0 until current) {
    val guess = h[count]
    val (pos, let) = evaluateGuess(secret, guess)
    println("%2d: %s : %d positions, %d letters".format(count+1, 
    		       	  	     	                guess, pos, let))
  }
}

// main game  
fun main() {    
  val secret = createSecret()
  val history = Array<String>(MaxNumGuesses) { "" }
  var current = 0
  println("Welcome to Mastermind!")
  println("I have created a secret combination:")
  println("Four distinct letters from A - F.")
  println("You have $MaxNumGuesses guesses to find it.")
  while (true) {
    showHistory(history, current, secret)
    if (current == MaxNumGuesses) {
      println("My secret was $secret, you failed to find it in $current guesses!")
      return
    }
    val guess = getGuess()
    history[current] = guess
    current += 1
    val pos = evaluateGuess(secret, guess).first
    if (pos == CODE_LENGTH) {
      println("My secret was $secret, you guessed correctly in $current guesses!")

      return
    }
  }
}

main()
