fun main(args: Array<String>) {
   println("-------THE NUMBER GUESSING GAME!-------\n")
   println("Try to guess the 4 digit number using the hints -> n:m")
   println("n = number of correct digits, m = digits in correct position\n")
   println("GAME START:")
   game(generateNumber())
}

//loops input until user guesses correct number
fun game(toguess: Array<Int>){
   printArray(toguess)
   println("Enter a 4 digit number: ")
   var counter = 1
   while(true){
      print("User Input: ")
      val number = readln()
      if(checkInput(number)){
         val guess = inputToArray(Integer.valueOf(number))
         val matches = matchingDigits(toguess,guess)
         print(" -> Output: ${matches[0]} : ${matches[1]} \n")
         //end loop if guess is correct
         if(guess.contentEquals(toguess)){
            println("Hurray you guessed the number in $counter tries!")
            break
         }
         counter++
      }
   }
}

//generate random array with no repeating digits and extract first four digits of that array
fun generateNumber(): Array<Int> {
   val randomArray = arrayOf(1,2,3,4,5,6,7,8,9,0)
   randomArray.shuffle()
   val randomNumber = Array(4){0}
   for (i in 0 .. 3){
      randomNumber[i] = randomArray[i]
   }
   return randomNumber
}

//checks if the input is made of numbers and exactly 4 digits long
fun checkInput(number: String): Boolean{
   while(true){
      try{
         Integer.valueOf(number)
         if(number.length > 4 || number.length < 4){
            println("Please enter a 4 digit Number")
            return false
         }
         return true
      }catch(e: Exception){
         println("Please enter a 4 Digit Number")
         return false
      }
   }
}

//converts the user guess into an integer array
fun inputToArray(input: Int): Array<Int> {
   var guess = input
   val guessArray = Array(4){0}
   var divisor = 1000

   for (i in guessArray.indices){
      guessArray[i] = guess/divisor
      guess %= divisor
      divisor /= 10
   }
   return guessArray
}

//checks if a digit of the guess matches and increments the counters
fun matchingDigits(toguess: Array<Int>, guess: Array<Int>): Array<Int>{
   var justDigit = 0
   var digitAndPlace = 0
   val alreadyAddedNumbers = mutableListOf<Int>()
   for(i in toguess.indices){
      if(guess[i] == toguess[i]){
         digitAndPlace++
      }
      for(j in toguess.indices){
         if(guess[i] == toguess[j]){
            if(!alreadyAddedNumbers.contains(guess[i])){
               justDigit++
               alreadyAddedNumbers.add(guess[i])
            }
         }
      }
   }
   return arrayOf(justDigit,digitAndPlace)
}

//prints an array to console if needed
fun printArray(toPrint: Array<Int>){
   for (i in toPrint){
      print(i)
   }
   println()
}