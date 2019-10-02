package com.example;

import java.math.BigInteger;
import java.util.Scanner;
/*NOTE TO CREATORS OF THE TASK: the term "Magic number" is incorrect, because
there is no definition of this term that would be linked to maths. Next time
use "Cyclic number" instead , :)
 */
/*List of cyclic numbers in https://en.wikipedia.org/wiki/Cyclic_number was used for testing purposes*/
public class Magic_number {
    private static boolean isNumeric(String strnum) {
        if(strnum.startsWith("0"))
            strnum = strnum.replaceFirst ("^0*", "");//remove leading zeroes
        try {
            BigInteger a = new BigInteger(strnum);//check if only digits are present in the string
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private static String leftRotate(String str, int d) {
        return str.substring(d) + str.substring(0, d);
    }

    private static String rightRotate(String str, int d) {
        return leftRotate(str, str.length() - d);
    }
    //both rotate functions  were "borrowed" from https://www.geeksforgeeks.org/left-rotation-right-rotation-string-2/

    private static boolean isMagic(BigInteger d, int len) {
        String initialInput;//initial input string
        initialInput=String.format("%0"+len+"d",d);//adding leading zeroes to input
        String rotString;// rotated multiplied string
        int Magic_checks = 0;//how many rotated strings were identical to input
        BigInteger multiplied;//multiplied integer val
        if(initialInput.length()==1)// one digit cyclic numbers do not exist
            return false;
        if(initialInput.startsWith("00"))// cyclic numbers do not start with two leading zeroes or more
            return false;
        for (int i = 1; i <= len; i++) {
            multiplied=d.multiply(BigInteger.valueOf(i));
            if(multiplied.toString().length()>len) //if multiplied string was larger than len - return false
                return false;
            for (int j = 1; j <= len; j++) {//rotate multiplied string until it's identical to input
                rotString=multiplied.toString();
                if(rotString.length()<len)
                    rotString=String.format("%0"+len+"d",multiplied);
                rotString = rightRotate(rotString, j);
                if (rotString.equals(initialInput)) {
                    Magic_checks++;//increment if rotated string is equal to input
                    break;
                }
            }
            if (Magic_checks != i)//return false if string rotation did not produce
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        String num = in.nextLine();//original input
        BigInteger d;
        while (!isNumeric(num) || num.startsWith("-")){//retry if incorrect input is passed
            System.out.print("Invalid input, try again: ");
            num = in.nextLine();
        }
        d=new BigInteger(num);// assign input to BigInteger
        int len=num.length();
        boolean magic;
        magic = isMagic(d,len);
        if (magic)
            System.out.println("It's magic!");
        else
            System.out.println("Not magic");

    }
}
