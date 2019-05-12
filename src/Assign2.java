//STUDENT: Jason Pettit
//COURSE: CST338-30_SU19
//EXERCISE: Mod 2 Casino

import java.util.*;
import java.lang.Math;

//MAIN CLASS
public class Assign2
{

   public static void main(String[] args)
   {
      
      int bet = 0;
      int multiplier = 0;
      int winnings = 0;
      
      //I DONT THINK I WANT TO DECLARE THESE VARIABLES HERE BUT I DON'T KNOW
      //ANOTHER WAY
      TripleString thePull = pull();
      int numPulls = thePull.MAX_PULLS; //get a THIS SHOULD BE ACCESSED IN A STATIC WAY ERROR BUT STILL WORKS
     
      do
      {
         //getBet() will prompt the user to enter a value between MIN_BET
         //and MAX_BET and return a number between those values.
         //Those values are currently MIN_BET 0 and MAX_BET 100
         bet = getBet();
         numPulls = numPulls - 1;
         
         //A bet of zero ends the game per the specification
         if (bet != 0)
         {
            thePull = pull();
            multiplier = getPayMultiplier(thePull);
            winnings = bet * multiplier;
            thePull.saveWinnings(winnings);
            System.out.println(display(thePull, winnings));
         }
      }while(bet != 0 && numPulls != 0);
      
      //If the user enters a bet of zero before they exceed MAX_PULLS
      //displayWinnings(), otherwise they have reached MAX_PULLS and 
      //the game displaysWinnings() and ends.
      if (numPulls > 0)
      {
         thePull.displayWinnings();
      }
      else
      {
         System.out.println("\nYou reached the maximum number of 40 pulls!");
         thePull.displayWinnings();
      }

   }

   public static int getBet()
   // getBet() prompts the user for an integer between MIN_BET and MAX_BET
   // as defined in the casino specification and returns the value of thisBet
   // if it is in the valid range.
   {
     int MIN_BET = 0;
     int MAX_BET = 100;
     int thisBet = 0;
     
     Scanner keyboard = new Scanner(System.in);
     
     do
     {
     System.out.print("How much would you like to bet (1 - 100) or 0 to "
           + "quit? ");
     thisBet = keyboard.nextInt();
     } while(thisBet < MIN_BET || thisBet > MAX_BET);
    
     //keyboard.close();
     
     return thisBet;   
   }
   
   public static TripleString pull()
   // use the randString() method to populate the strings in an instance
   // of the TripleString class
   {
      TripleString pullString = new TripleString();
      
      String string1 = randString();
      String string2 = randString();
      String string3 = randString();
      
      pullString.setString1(string1);
      pullString.setString2(string2);
      pullString.setString3(string3);
      
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
         multiplier = 0;
      
      return multiplier;
   }
   
   public static String display(TripleString thePull, int winnings)
   { 
      if(winnings > 0)
      {
         return "\nwhirrrrr...and your pull is...\n" + thePull + 
               "\ncongratulations you win: $" + winnings + "\n";
      }
      else
      {
         return "\nsorry, you lose.\n";
      }
   }
}

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
    
   //concatenate the three strings into a single string per the specification
   //this represents the three wheels of the slot machine spin
   public String toString()
   {
      String pullString = string1 + " " + string2 + " " + string3;
      return pullString;
   }
   
   //Save the value of winnings to the appropriate index in the array,
   //index comes from numPulls
   public boolean saveWinnings(int winnings)
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
   
   public void displayWinnings()
   {
      int totalWinnings = 0;
      
      System.out.println("\nThanks for playing at the Casino!");     
      System.out.println("Your individual winnings were:");
      
      for (int i = 0; i < numPulls; i++)
      {
         System.out.print(pullWinnings[i] + " ");
         totalWinnings = totalWinnings + pullWinnings[i];
      }
      
      System.out.println("\nYour total winnings were: $" + totalWinnings);
   }   
   
}