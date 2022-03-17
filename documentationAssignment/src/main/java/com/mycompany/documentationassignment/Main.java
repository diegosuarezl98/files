
package com.mycompany.documentationassignment;

/**
 *
 * @author diego
 */
public class Main {
    public static int fibonacciRec(int n){             
        if(n<=1){       //Recursion
              return 1;
                }else{
                return fibonacciRec(n-1)+fibonacciRec(n-2);
                     }                   
    }
    public static void main(String[] args) {
        int n= 9;
        System.out.println("this is done with recursion: \n"
 + "<---------------------------------->");
for(int i = 0; i < 10; i++){
    System.out.println(fibonacciRec(i));} 
        System.out.println(fibonacciRec(n));//Using fibonacciRec RECURSION FUNCTION


int theNumber =9;
int  previousNumber= 0,prevNum = 0, currentNumber = 1;
        System.out.println(previousNumber);
        System.out.println(currentNumber);              //ITERATION
System.out.println("\n");
System.out.println("this is done with iteration: \n"
 + "<---------------------------------->");
for(int i = 1; i<theNumber-1; i++){
    previousNumber = prevNum;
    prevNum= currentNumber;
    currentNumber = previousNumber + prevNum;
    

    System.out.println(currentNumber);

}
    }
}

