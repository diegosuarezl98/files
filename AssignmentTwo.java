
package assignmenttwo;

import java.util.Scanner;

/**
 * @author diego
 */
public class AssignmentTwo {


/**
 *Fibonacci Method
 *
 *This method takes in a number for n. 
 *Then it returns the number using the fibonacci function
 *@param n
 *@return fibonacci
 */
public static int fibonacci(int n){
//if n is less than or equal to o ne, return n*/
    if(n<=1)
    return n;
    return fibonacci(n-1)+fibonacci(n-2);

}



    public static void main(String[] args) {
//declaring n
int n;
//scanner
Scanner scanner = new Scanner(System.in);

//ask for n value
System.out.println("Enter a value for n: ");
n = scanner.nextInt();

//printing "nth" number
System.out.println("the "+n+"th number is " + fibonacci(n));
       
    }
    
}
