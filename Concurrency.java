
package concurrency;

/*Make an array of 200 million random numbers between 1 and 10.
 Compute the sum in parallel using multiple threads.
 Then compute the sum with only one thread,
 and display the sum and times for both cases.*/
import java.util.Random;
/**
 *
 * @author diego
 */
public class Concurrency {

//method to print random number taking in min and max as prameters. 
        public static double generateRandom(int min, int max){
                Random rand = new Random();
                double randomInt = min + rand.nextInt() * (max - min);
                System.out.println(randomInt); //printing random integer
                return randomInt;
        }

//generating numbers list
public static void generateNumbers(double[] numbers, int amount){
    for (int i = 0; i < amount; i++) {
numbers[i] = generateRandom(1, 10);
        
    }



                }


    public static void main(String[] args) {
        //up to 2,000,000
        int size = 2000000;
        double[] numbers = new double[size];
double[] numbers = new double[size];
        System.out.println("Generated nunmbers are the following. ");
            //generating random numbers 
            generateRandom(1,10);


       
    }
    
}
