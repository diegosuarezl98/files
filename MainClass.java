/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author diego
 */
public class MainClass {

    public static void main(String[] args) throws IOException {
    
        try{
            File f = new File("C:\\Users\\diego\\Desktop\\theFile.txt");
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
//first time i go through the fil eread for Strings and if  it is unique store in an Array 
//use while and iterate hroug