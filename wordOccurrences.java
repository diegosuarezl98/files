package com.mycompany.analyzetext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import sun.misc.IOUtils;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 * Word Occurrences Project
 *
 * @author Diego A. Suarez Larios
 */
public class MainClass {

    /**
     * - Executes methods for user interface and word count calculations.
     */
    static JTextField textField;
    static JFrame theFrame;
    static JButton submit;
    static JLabel theLabel;
    static JTextArea textArea;

    // default constructor
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        userInterface();

    }

    /**
     *
     * - Contains Jframe objects presented to the user with options to interact
     * with the program or exit.Furthermore, there is a welcome message each
     * time the program is started, followed by a switch-case statement to
     * present the user with the options.
     *
     * @throws java.lang.ClassNotFoundException
     */
    public static void userInterface() throws ClassNotFoundException, SQLException {
        //frame 
        theFrame = new JFrame("Word Occurences");
        // label
        theLabel = new JLabel(" Welcome to Word Counter! ");
        // button
        submit = new JButton("Pick a file");
        //text area
        textArea = new JTextArea(50, 50);
        //make it scrollable
        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  //when button is pushed
                theLabel.setText("Word occurence counter is running!");
                countWords();

                //Database Code
                Connection conn1 = null;
                try {
                    // connect way #1
                    String url1 = "jdbc:mysql://127.0.0.1:3306/wordoccurrences";        //database connection parameters
                    String user = "root";
                    String password = "";

                    conn1 = DriverManager.getConnection(url1, user, password); //database connection

                    if (conn1 != null) {//success
                        System.out.println("Connected to the database test1");
                    }
                } catch (SQLException ex) {//failed
                    System.out.println("Connection failed");
                    ex.printStackTrace();
                }
                //Creating statement.
                PreparedStatement ps;
                try {

                    Scanner input = new Scanner(System.in);
                    System.out.println("Enter a word:");
                    String word = input.next();
                    String query = " insert into wordoccurrences.word (word) values (word);";
                    ps = conn1.prepareStatement(query);
                    //Setting the word entered by user in the query.
                    ps.setString(1, word);
                    //Executing the query.
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        theFrame.setVisible(true);
        // create a panel to add buttons and textfield
        JPanel p = new JPanel();
        // add stuff to panel     
        p.add(submit);
        p.add(theLabel);
        // p.add(textArea);
        p.add(scroll);

        // add panel to frame and size
        theFrame.add(p);
        theFrame.setSize(600, 600);
        theFrame.show();

    }

    /**
     *
     * This method contains the calculations done to present the user with the
     * amount of occurrences a word has in the document. Said document is found
     * by using a file input stream followed by looping through the array of
     * words to produce the required output.
     */
    public static void countWords() {
        JFileChooser chooser = new JFileChooser(); //choose a file 
        chooser.showOpenDialog(null);
        try {
            File f = chooser.getSelectedFile();//new File("C:\\Users\\diego\\Desktop\\Assignments\\theFile.txt"); ORIGINAL FILE PATH
            FileInputStream fin = new FileInputStream(f);

            //now, count!
            int count;
            //making byte array
            byte[] byteArray = new byte[(int) f.length()];
            fin.read(byteArray);
            String s = new String(byteArray);
            String[] words = s.split(" "); // splits whole article into individual word strings
            // finds list of unique words
            List<String> wordList = new ArrayList<String>(Arrays.asList(words));
            for (int i = 1; i < wordList.size(); i++) {
                for (int j = 0; j < i; j++) {
                    if (wordList.get(i).equals(wordList.get(j))) {
                        wordList.remove(i);
                        i--;
                        break;
                    }
                }
            }
            int[] countList = new int[wordList.size()];
            // counts how many times each word appears
            for (int i = 0; i < wordList.size(); i++) {
                for (int j = 0; j < words.length; j++) {
                    if (wordList.get(i).equals(words[j])) {
                        countList[i]++;
                    }
                }
            }
            // Prints both lists
            StringBuilder sb = new StringBuilder(); //String builder obkect
            for (int i = 0; i < wordList.size(); i++) {
                System.out.println(wordList.get(i) + "\t\tCount: " + countList[i]);
                String text = (wordList.get(i) + " ----> Count: " + countList[i] + "\n\n");
                theLabel.setText("Done!");
                sb.append(text);
                String texto = sb.toString();
                printTextField(texto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *
     * This method simply displays the output in the text area
     */
    public static void printTextField(String text) { //printing to textArea

        textArea.setText(text);

    }

}
