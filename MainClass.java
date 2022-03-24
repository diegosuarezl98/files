package com.mycompany.analyzetext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import sun.misc.IOUtils;
import javax.swing.*;
import javax.swing.JOptionPane;
/**
 *
 * @author diego
 */
public class MainClass {

    public static void main(String[] args) throws IOException {

                userInterface();

                countWords();      
   
    }
    


    //User interface
    public static void userInterface(){
    //FILE CHOOSER?
        
        //Setting up a frame 
        JFrame frame = new JFrame("Word Counter"); //set the tittle of the window 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close operation
        frame.setSize(500,500); //sizing
        JLabel L = new JLabel("How do i output here");  
        frame.getContentPane().add( L ); 
        frame.setVisible(true); //show it
        //Welcome message
        JOptionPane.showMessageDialog(frame,"Welcome to the word counter!");

        String select = JOptionPane.showInputDialog(frame,"Select an option: \n"+"\nPress 1 to exit."+"\n"+"\nPress 2 to run the program");
                            int select1 = Integer.parseInt(select);
                            int option = 0;
                            switch(option){

                                case 1:
                                {
                                    countWords();
                                }
                                case 2:
                                {
                                    System.exit(1);
                                }

                            }

        }



        //Word count
        public static void countWords(){

            try{

                File f = new File("C:\\Users\\diego\\Desktop\\Assignments\\theFile.txt");
                FileInputStream fin=new FileInputStream(f);    
                //count the word
                int count;

                //making byte array
                byte[] byteArray = new byte[(int)f.length()];
                fin.read(byteArray);

                String s = new String(byteArray);

                String[] words = s.split(" "); // splits whole article into individual word strings

                // finds list of unique words
                List<String> wordList = new ArrayList<String>(Arrays.asList(words));
                for (int i = 1; i < wordList.size(); i++) {
                    for (int j = 0; j < i; j++)
                    {
                        if(wordList.get(i).equals(wordList.get(j)))
                        {
                            wordList.remove(i);
                            i--;
                            break;
                        }
                    }
                }

                int[] countList = new int[wordList.size()];

                // counts how many times each word appears
                for(int i = 0; i < wordList.size(); i++)
                {
                    for(int j = 0; j < words.length; j++)
                    {
                        if(wordList.get(i).equals(words[j]))
                        {
                            countList[i]++;
                        }
                    }
                }

                // prints both lists
                for (int i = 0; i < wordList.size(); i++)
                {

                    System.out.println(wordList.get(i) + "\t\tCount: " + countList[i]);

                }

              }catch(Exception e){System.out.println(e);
            }

        }
}
