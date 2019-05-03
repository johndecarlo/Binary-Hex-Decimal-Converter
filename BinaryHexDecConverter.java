/*
   Binary-Hexadecimal-Decimal Converter
   John DeCarlo
   May 2nd, 2019
*/

import java.util.*;
import java.io.*;

public class BinaryHexDecConverter {
   
   //Driver method
   public static void main(String[]arg) {
      Scanner reader = new Scanner(System.in);
      String type = input();           //Call our input method to get input from the user
      while(!type.equals("E") && !type.equals("e")) { //Exit the program
         String value = "";
         if(type.equals("B") || type.equals("b")) {   //Binary number
            value = getBinary();       //Call the getBinary method to get a binary number from the user
            convertBinary(value);      //Convert our binary # to hex and decimal
         } else if(type.equals("H") || type.equals("h")) {  //Hexadecimal number
            value = getHex();          //Call the getHex method to get a hexadecimal number from the user
            convertHex(value);         //Convert our hexadecimal to binary and decimal
         } else if(type.equals("D") || type.equals("d")) {  //Decimal number
            value = getDecimal();      //Call the getDecimal method to get a number from the user
            convertDecimal(value);     //Convert our decimal to binary and hex
         }
         type = input();      //Keep running until we call exit
      }
   }
   
   //Asks for a binary input and will check until given one
   public static String getBinary() {
      Scanner reader = new Scanner(System.in);
      String binary = "";           //String value that will hold our binary input
      boolean valid = false;        //Boolean that tells us if we have a valid answer
      while(valid==false) {         //Run until we have a vaild answer
         boolean error = false;     //Create boolean error to alert if the input is incorrect
         System.out.println("Enter a binary number: ");
         binary = reader.next();    //Get our input from the user
         for(int i = 0; i < binary.length(); i++) {
            if(!(binary.charAt(i) == '0') && !(binary.charAt(i) == '1')) { //If the value isn't a '0' or a '1'
               error = true;        //We've found an error, set error to true
               break;               //Break and exit the for-loop
            }
         }
         if(error==true)            //If error is true
            System.out.println("That is an invalid binary input");   //Print and go back to the teop
         else
            valid = true;           //Our input is valid
      } 
      return binary;    //Return our binary number
   }
   
   //Get a hexadecimal input from the user
   public static String getHex() {
      Scanner reader = new Scanner(System.in);
      String hex = "";           //Hexadecimal value we will return
      boolean valid = false;     //Set to false because we don't have a vaild input
      while(valid==false) {      //Run until we have a vaild answer
         boolean error = false;  //Set to false because we don't have a error in our input
         System.out.println("Enter a hex number: ");
         hex = reader.next();    //Read in our input from the user
         for(int i = 0; i < hex.length(); i++) {
            if(!isHexValue(hex.charAt(i))) {    //Check for each char if they are a legal hex value
               error = true;                    //If not, set error to true
               break;                           //Break and exit the for-loop
            }
         }
         if(error==true)   //If we found an error in our user input
            System.out.println("That is an invalid binary input");   //Print and go back to the top
         else
            valid = true;  //Valid input, set valid to true
      }
      return hex;    //Return our user input
   }
   
   //Check if the char in the input is a legal hex value
   public static boolean isHexValue(char c) {
      if(!(c == '0') && !(c == '1') && !(c == '2') && !(c == '3') && !(c == '4') && !(c == '5') && !(c == '6') && !(c == '7') && !(c == '8') && !(c == '9') 
         && !(c == 'A') && !(c == 'a') && !(c == 'B') && !(c == 'b') && !(c == 'C') && !(c == 'c') && !(c == 'D') && !(c == 'd') && !(c == 'E') && !(c == 'e') && !(c == 'F') && !(c == 'f'))
         return false;  //If not any of these values, return false
      else
         return true;   //Valid input, return true
   }
   
   //Get a decimal input from the user
   public static String getDecimal() {
      Scanner reader = new Scanner(System.in);
      String decimal = "";       //User input value for decimal
      boolean valid = false;     //We do not have a vaild answer, set to false
      while(valid==false) {      //Run until we have a vaild answer
         boolean error = false;     //Error value to tell if our input has an error in it
         System.out.println("Enter a decimal number: ");
         decimal = reader.next();   //Get our decimal input from the user
         for(int i = 0; i < decimal.length(); i++) {
            if(!isDecimalValue(decimal.charAt(i))) {  //Check to see if the input is valid
               error = true;        //If not, set error to true
               break;               //Break and exit the for-loop
            }
         }
         if(error==true)            //If we found error to be true
            System.out.println("That is an invalid binary input");   //Print and return to the top
         else
            valid = true;           //Input is valid, set valid to true
      }  
      return decimal;   //Return our user input
   }
   
   //Check to see if the char we input is a legal decimal value (is a number)
   public static boolean isDecimalValue(char c) {
      if(!(c == '0') && !(c == '1') && !(c == '2') && !(c == '3') && !(c == '4') && !(c == '5') && !(c == '6') && !(c == '7') && !(c == '8') && !(c == '9'))
         return false;  //Not a valid char, return false
      else
         return true;   //Valid char, return true
   }
   
   //Turn a binary number into hex and decimal
   public static void convertBinary(String value) {
      String binary = value;  //Our binary parameter
      String hex = "0x";      //Our string hex value we will return
      int decimal = 0;        //Integer decimal value we will return
      int count = 0;          
      int extra_bits = binary.length() % 4;  //Check to see how many extra bits we need to add on
      while(extra_bits % 4 != 0) {           //While the total number of bits is not divisible by 4
         binary = "0" + binary;              //Add a zero to the front 
         extra_bits++;                       //Increase count
      }
      String byte1 = "";      //Temporary byte holder to seperate every four-bits
      for(int i = 0; i < binary.length(); i++) {   //Traverse through binary input
         byte1 += String.valueOf(binary.charAt(i));   //Add the bit to our byte value
         if(byte1.length() % 4 == 0) {       //If we have 4 bits
            hex += binaryToHex(byte1);       //Convert the 4 bits to a hex value
            byte1 = "";                      //Reset our byte holder
         }
      } 
      System.out.println("Hexadecimal: " + hex);   //Print out the result
      for(int i = 0; i < binary.length(); i++) {   //Traverse through our binary input
         if(binary.charAt(i) == '1') {             //If the char is equal to a 1
            decimal += (int) Math.pow(2, binary.length() - 1 - i);   //Add 2^n to the power of the decimal place
         }
      }
      System.out.println("Decimal: " + decimal);   //Print out our decimal result
      System.out.println();
   }
   
   //Convert our hexadecimal input into a binary and decimal
   public static void convertHex(String value) {
      String hex = value;  //Set our basic hex input value
      String binary = "";  //The binary value we will be printing out
      int decimal = 0;     //The decimal value we will be printing out
      for(int i = 0; i < hex.length(); i++) {   //For the length of the hex
         binary += hexToBinary(hex.charAt(i));  //Add to the binary output the hex's binary value
         if(i != hex.length() - 1)     //If we haven't reached the last hex value
            binary += " ";             //Include a space to seperate the values
         decimal += hexToDecimal(hex.charAt(i)) * (int) Math.pow(16, hex.length() - 1 - i);  //Get the decimal value of the hex and multiply it by 16^n
      }
      System.out.println("Binary: " + binary);     //Print out the binary value
      System.out.println("Decimal: " + decimal);   //Print out the decimal value
      System.out.println();
   }
   
   //Convert our hexadecimal input into a binary and decimal
   public static void convertDecimal(String value) {
      int decimal = Integer.parseInt(value); //Convert the string value to an int
      String binary = "";     //The binary value we will be returning
      String hex = "0x";      //The hex value that we will be returning
      int space_count = 0;    //Count how many spaces we have in our binary
      int count = 0;          //Counter for how many times we divide
      while(decimal != 0) {      //While the decimal is not zero
         if(decimal % 2 == 0)       //If the decimal is divisible by 0
            binary = "0" + binary;  //Add a '0' to the binary output
         else
            binary = "1" + binary;  //Add a '1' to the binary output
         decimal = decimal/2;    //Divide the decimal by 2
         count++;                //Increment count
         if(count % 4 == 0 && decimal != 0) {   //If we have divided 4 times and decimal is not zero
            binary = " " + binary;     //Add a space between the four bits
            space_count++;       //Increment the space count
         }
      }
      while((binary.length() - space_count) % 4 != 0) {  //While our length minus the amount of spaces is not divisible by 4
         binary = "0" + binary;           //Add a zero for padding at the front of the binary 
      }
      System.out.println("Binary: " + binary);     //Print out the binary value
      String[] bits = binary.split(" ");     //Get our binary output and split it based on the spaces
      for(int i = 0; i < bits.length; i++)   //For the number of bytes we creates
         hex += binaryToHex(bits[i]);        //Get their hexadecimal values and add them to hex
      System.out.println("Hexadecimal: " + hex);   //Print out the hex value
      System.out.println();
   }
   
   public static String binaryToHex(String binary) {
      if(binary.equals("0000")) {   //Binary value is 0000
         return "0"; //Hex value is 0
      } else if(binary.equals("0001")) {  //Binary value is 0001
         return "1"; //Hex value is 1
      } else if(binary.equals("0010")) {  //Binary value is 0010
         return "2"; //Hex value is 2
      } else if(binary.equals("0011")) {  //Binary value is 0011
         return "3"; //Hex value is 3
      } else if(binary.equals("0100")) {  //Binary value is 0100
         return "4"; //Hex value is 4
      } else if(binary.equals("0101")) {  //Binary value is 0101
         return "5"; //Hex value is 5
      } else if(binary.equals("0110")) {  //Binary value is 0110
         return "6"; //Hex value is 6
      } else if(binary.equals("0111")) {  //Binary value is 0111
         return "7"; //Hex value is 7
      } else if(binary.equals("1000")) {  //Binary value is 1000
         return "8"; //Hex value is 8
      } else if(binary.equals("1001")) {  //Binary value is 1001
         return "9"; //Hex value is 9
      } else if(binary.equals("1010")) {  //Binary value is 1010
         return "A"; //Hex value is A
      } else if(binary.equals("1011")) {  //Binary value is 1011
         return "B"; //Hex value is B
      } else if(binary.equals("1100")) {  //Binary value is 1100
         return "C"; //Hex value is C
      } else if(binary.equals("1101")) {  //Binary value is 1101
         return "D"; //Hex value is D
      } else if(binary.equals("1110")) {  //Binary value is 1110
         return "E"; //Hex value is E
      }  else if(binary.equals("1111")) { //Binary value is 1110
         return "F"; //Hex value is F
      } else
         return ""; //Hex value is nothing
   }
   
   public static int hexToDecimal(char hex) {
      if(hex == '0') {  //Hex value is 0
         return 0;      //Return its decimal value
      } else if(hex == '1') {    //Hex value is 1
         return 1;      //Return its decimal value
      } else if(hex == '2') {    //Hex value is 2
         return 2;      //Return its decimal value
      } else if(hex == '3') {    //Hex value is 3
         return 3;      //Return its decimal value
      } else if(hex == '4') {    //Hex value is 4
         return 4;      //Return its decimal value
      } else if(hex == '5') {    //Hex value is 5
         return 5;      //Return its decimal value
      } else if(hex == '6') {    //Hex value is 6
         return 6;      //Return its decimal value
      } else if(hex == '7') {    //Hex value is 7
         return 7;      //Return its decimal value
      } else if(hex == '8') {    //Hex value is 8
         return 8;      //Return its decimal value
      } else if(hex == '9') {    //Hex value is 9
         return 9;      //Return its decimal value
      } else if(hex == 'A'  || hex == 'a') { //Hex value is A
         return 10;     //Return its decimal value
      } else if(hex == 'B'  || hex == 'b') { //Hex value is B
         return 11;     //Return its decimal value
      } else if(hex == 'C'  || hex == 'c') { //Hex value is C
         return 12;     //Return its decimal value
      } else if(hex == 'D'  || hex == 'd') { //Hex value is D
         return 13;     //Return its decimal value
      } else if(hex == 'E'  || hex == 'e') { //Hex value is E
         return 14;     //Return its decimal value
      }  else if(hex == 'F' || hex == 'f') { //Hex value is F
         return 15;     //Return its decimal value
      } else
         return -1;     //Return negative one (error value)
   }
   
   public static String hexToBinary(char hex) {
      if(hex == '0') {     //Hex value is 0
         return "0000";    //Return its binary value
      } else if(hex == '1') {    //Hex value is 1
         return "0001";    //Return its binary value
      } else if(hex == '2') {    //Hex value is 2
         return "0010";    //Return its binary value
      } else if(hex == '3') {    //Hex value is 3
         return "0011";    //Return its binary value
      } else if(hex == '4') {    //Hex value is 4
         return "0100";    //Return its binary value
      } else if(hex == '5') {    //Hex value is 5
         return "0101";    //Return its binary value
      } else if(hex == '6') {    //Hex value is 6
         return "0110";    //Return its binary value
      } else if(hex == '7') {    //Hex value is 7
         return "0111";    //Return its binary value
      } else if(hex == '8') {    //Hex value is 8
         return "1000";    //Return its binary value
      } else if(hex == '9') {    //Hex value is 9
         return "1001";    //Return its binary value
      } else if(hex == 'A'  || hex == 'a') {    //Hex value is A
         return "1010";    //Return its binary value
      } else if(hex == 'B'  || hex == 'b') {    //Hex value is B
         return "1011";    //Return its binary value
      } else if(hex == 'C'  || hex == 'c') {    //Hex value is C
         return "1100";    //Return its binary value
      } else if(hex == 'D'  || hex == 'd') {    //Hex value is D
         return "1101";    //Return its binary value
      } else if(hex == 'E'  || hex == 'e') {    //Hex value is E
         return "1110";    //Return its binary value
      }  else if(hex == 'F' || hex == 'f') {    //Hex value is F
         return "1111";    //Return its binary value
      } else
         return "";  //Return a string with nothing in it
   }
   
   public static String input() {
      Scanner reader = new Scanner(System.in);
      String type = "";       //Holds our input type
      System.out.println("Select your input type.\nBinary (B) : Hexadecimal (H) : Decimal (D) : Exit (E)");
      type = reader.next();   //Gets our input from the user
      while(!type.equals("B") && !type.equals("b") && !type.equals("H") && !type.equals("h") && !type.equals("D") && !type.equals("d") && !type.equals("E") && !type.equals("e")) {
         System.out.println("That is an invalid input type.");
         System.out.println("Select your input type.\nBinary (B) : Hexadecimal (H) : Decimal (D) : Exit (E)");
         type = reader.next();   //Gets our input from the user
      }
      System.out.println();
      return type;            //Return the input from the user
   }
}