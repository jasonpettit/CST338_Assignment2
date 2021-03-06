//STUDENT: Jason Pettit
//COURSE: CST338-30_SU19
//EXERCISE: Mod 2 Casino

import java.util.*;
import java.lang.Math;

//START Assign2 CLASS
public class Assign2
{
   static Scanner keyboard = new Scanner(System.in);
         
   //START MAIN
   public static void main(String[] args)
   {
      int bet = 0;
      int multiplier = 0;
      int winnings = 0;
      int numPulls = TripleString.MAX_PULLS;
      
      TripleString game = new TripleString();

      do
      {
         //getBet() will prompt the user to enter a value between MIN_BET
         //and MAX_BET and return a number between those values.
         //Those values are currently MIN_BET 0 and MAX_BET 100
         bet = getBet();
         numPulls -= 1;
         
         //A bet of zero ends the game per the specification
         if (bet != 0)
         {
            game = pull();
            multiplier = getPayMultiplier(game);
            winnings = bet * multiplier;
            if (game.saveWinnings(winnings) == true)
            {
               display(game, winnings);
            }
            else
            {
               System.out.println("saveWinnings write to array failed");
               break;
            }
         }     
      } 
      while(bet != 0 && numPulls != 0);
      
      //If the user enters a bet of zero before they exceed MAX_PULLS
      //displayWinnings(), otherwise they have reached MAX_PULLS and 
      //the game displaysWinnings() and ends.
      if (numPulls > 0)
      {
         System.out.print(game.displayWinnings());
      }
      else
      {
         System.out.println("\nYou reached the maximum number of 40 pulls!");
         System.out.print(game.displayWinnings());
      }
   keyboard.close();  
   }
   //END MAIN

   public static int getBet()
   // getBet() prompts the user for an integer between MIN_BET and MAX_BET
   // as defined in the casino specification and returns the value of thisBet
   // if it is in the valid range.
   {
     int MIN_BET = 0;
     int MAX_BET = 100;
     int thisBet = 0;
     
     do
     {
     System.out.print("How much would you like to bet (1 - " + MAX_BET + ") or "
           + "0 to quit? ");
     thisBet = keyboard.nextInt();
     } 
     while(thisBet < MIN_BET || thisBet > MAX_BET);
     
     return thisBet;   
   }
   
   public static TripleString pull()
   // use the randString() method to populate the strings in an instance
   // of the TripleString class
   {
      TripleString pullString = new TripleString();
            
      pullString.setString1(randString());
      pullString.setString2(randString());
      pullString.setString3(randString());
      
      return pullString;
   }
   
   private static String randString()
   //assign BAR, Cherries, Space, or 7 to a string based on the probabilities
   //provided in the specification. BAR = 50%, Cherries = 25%, Space = 12.5%,
   //and 7 = 12.5%
   {
      String randomString = "";
      double randomNum = Math.random() * 1000;
      
      //12.5% = 125 of 1000
      //25% = 250 of 1000
      //50% = 500 of 1000
      if(randomNum >= 0 && randomNum < 125)
      {
         randomString = "Space";
      }
      else if(randomNum >= 125 && randomNum < 250)
      {
         randomString = "7";
      }
      else if(randomNum >= 250 && randomNum < 500)
      {
         randomString = "Cherries";
      }
      else
      {
         randomString = "BAR";
      }
         
      return randomString;
   }
   
   public static int getPayMultiplier(TripleString thePull)
   //According to the specification a multiplier is determined according
   //to the pattern of the three strings.
   {
      int multiplier = 0;
      
      if (thePull.getString1() == "Cherries" && 
            thePull.getString2() != "Cherries")
      {
         multiplier = 5;
      }
      else if (thePull.getString1() == "Cherries" && 
            thePull.getString2() == "Cherries" && 
            thePull.getString3() != "Cherries")
      {
         multiplier = 15;
      }
      else if (thePull.getString1() == "Cherries" &&
            thePull.getString2() == "Cherries" &&
            thePull.getString3() == "Cherries")
      {
         multiplier = 30;
      }
      else if (thePull.getString1() == "BAR" && 
            thePull.getString2() == "BAR" && 
            thePull.getString3() == "BAR")
      {
         multiplier = 50;
      }
      else if (thePull.getString1() == "7" && 
            thePull.getString2() == "7" && 
            thePull.getString3() == "7")
      {
         multiplier = 100;
      }
      else 
      {
         multiplier = 0;         
      }

      return multiplier;
   }
   
   public static void display(TripleString thePull, int winnings)
   //Show the results of the specific pull after each pull
   { 
      if(winnings > 0)
      {
         System.out.println("\nwhirrrrr...and your pull is...\n" + thePull + 
               "\ncongratulations you win: $" + winnings + "\n");
      }
      else
      {
         System.out.println("\nwhirrrrr...and your pull is...\n" +
               thePull + "\nsorry, you lose.\n");
      }
   }
}
//END Assign2 CLASS

//START TripleString class
class TripleString
{
   //The maximum number of pulls allowed as defined in the specification
   public static final int MAX_PULLS = 40;
   private static int numPulls = 0;
   
   //The maximum length of a string allowed as defined in the specification,
   //and the three strings representing the wheels on the slot machine
   public static final int MAX_LEN = 20;
   private String string1, string2, string3;
   
   //Store the winnings from each pull into an array, up to MAX_PULLS
   private static int[] pullWinnings = new int[MAX_PULLS];
   
   //Default Constructor
   TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   
   //validate that a string is within the specification and doesn't exceed
   //MAX_LEN and is not null.
   private boolean validString(String str)
   {
      if(str != null && str.length() <= MAX_LEN)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   //Mutators and accessors one each for string1, string2, string3
   //mutators should be booleans and call validString() per the specification
   //START mutators
   public boolean setString1(String str)
   {
      if (validString(str))
      {
         string1 = str;
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean setString2(String str)
   {
      if (validString(str))
      {
         string2 = str;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   public boolean setString3(String str)
   {
      if (validString(str))
      {
         string3 = str;
         return true;
      }
      else
      {
         return false;
      }
   }
   //END mutators
   
   //START accessors
   public String getString1()
   {
      return string1;
   }
   
   public String getString2()
   {
      return string2;
   }
   
   public String getString3()
   {
      return string3;
   }
   //END accessors
    
   
   public String toString()
   //concatenate the three strings into a single string per the specification
   //this represents the three wheels of the slot machine spin
   {
      String pullString = string1 + " " + string2 + " " + string3;
      return pullString;
   }
   

   public boolean saveWinnings(int winnings)
   //Save the value of winnings to the appropriate index in the array,
   //index comes from numPulls
   {  
      if(numPulls < MAX_PULLS)
      {
         pullWinnings[numPulls] = winnings;
         numPulls += 1;
         return true;
      }
      else
      {
         return false;
      }
   }
   
   public String displayWinnings()
   //At the end of the game, triggered by either the player entering 0
   //or, the player reaching the maximum number of pulls NUM_PULLS
   //summarize the results of each spin by displaying each element in the 
   //pullWinnings[] and sum and display their totalWinnings
   {
      int totalWinnings = 0;
      String gameResults = "\nThanks for playing at the Casino!\n"
            + "Your individual winnings were:\n";
    
      for (int i = 0; i < numPulls; i++)
      {
         gameResults = gameResults + pullWinnings[i] + " ";
         totalWinnings = totalWinnings + pullWinnings[i];
      }
      
      gameResults = gameResults + "\nYour total winnings were: $" 
            + totalWinnings;
      
      return gameResults;
   }   
   
}
//END TripleString class


/***********************SAMPLE RUN*********************************************
How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
Cherries Cherries Space
congratulations you win: $75

How much would you like to bet (1 - 100) or 0 to quit? 55

whirrrrr...and your pull is...
BAR BAR Cherries
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 555
How much would you like to bet (1 - 100) or 0 to quit? 555
How much would you like to bet (1 - 100) or 0 to quit? -2
How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
Space Cherries 7
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
7 BAR BAR
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR BAR Cherries
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
Space BAR BAR
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
Cherries BAR BAR
congratulations you win: $25

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR BAR BAR
congratulations you win: $250

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR BAR BAR
congratulations you win: $250

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR 7 Cherries
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR 7 Cherries
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR Cherries Cherries
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR Cherries Cherries
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR BAR BAR
congratulations you win: $250

How much would you like to bet (1 - 100) or 0 to quit? 5

whirrrrr...and your pull is...
BAR Cherries Cherries
sorry, you lose.

How much would you like to bet (1 - 100) or 0 to quit? 0

Thanks for playing at the Casino!
Your individual winnings were:
75 0 0 0 0 0 25 250 250 0 0 0 0 250 0 
Your total winnings were: $850

***WHAT IF I ENTER A LETTER

How much would you like to bet (1 - 100) or 0 to quit? t
Exception in thread "main" java.util.InputMismatchException
   at java.util.Scanner.throwFor(Unknown Source)
   at java.util.Scanner.next(Unknown Source)
   at java.util.Scanner.nextInt(Unknown Source)
   at java.util.Scanner.nextInt(Unknown Source)
   at Assign2.getBet(Assign2.java:79)
   at Assign2.main(Assign2.java:28)
   
***WHAT IF I ENTER A SPECIAL CHARACTER

How much would you like to bet (1 - 100) or 0 to quit? !
Exception in thread "main" java.util.InputMismatchException
   at java.util.Scanner.throwFor(Unknown Source)
   at java.util.Scanner.next(Unknown Source)
   at java.util.Scanner.nextInt(Unknown Source)
   at java.util.Scanner.nextInt(Unknown Source)
   at Assign2.getBet(Assign2.java:79)
   at Assign2.main(Assign2.java:28)
   
***WHAT IF PLAYER JUST HITS RETURN

How much would you like to bet (1 - 100) or 0 to quit? 





******************************************************************************/