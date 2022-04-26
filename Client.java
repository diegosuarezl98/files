package serverProject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author diego
 */
public class Client extends Application {
//Streams

    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    static final int PORT = 1234;

    @Override //overriding start method
    public void start(Stage primaryStage) { //stage
        BorderPane textPane = new BorderPane();
        textPane.setPadding(new Insets(5, 5, 5, 5));
        textPane.setStyle("-fx-border-color: blue");
        textPane.setLeft(new Label("enter a number: "));//ASK USER FOR NUMBER 

        TextField tf = new TextField();

        tf.setAlignment(Pos.BOTTOM_RIGHT);
        textPane.setCenter(tf);

        BorderPane mainPane = new BorderPane();
        // Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(textPane);

        // Create and place scene
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("The Client"); // Title
        primaryStage.setScene(scene); // Place the scene in the stage

        // Display the stage
        primaryStage.show();

        tf.setOnAction(e -> {
            try {
                //getting number from text field 
                int number = Integer.parseInt(tf.getText());

                //send number to server
                toServer.writeInt(number); //writting number to server 
                toServer.flush();

                int answer = fromServer.readInt();

            } catch (IOException ex) {
                System.err.println(ex);
            }

        });

        try {

            //getting hostname
            InetAddress ip;
            ip = InetAddress.getLocalHost();
            String hostname;
            hostname = ip.getHostName();
            // Create a socket to connect to the server
            Socket socket = new Socket(hostname, PORT);
            fromServer = new DataInputStream(socket.getInputStream());
            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ta.appendText(ex.toString() + '\n' + "yo");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
