package serverProject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {
 static final int PORT = 1234;
    @Override //START METHOD
    public void start(Stage primaryStage) {
//setting up window code
        // Text area for displaying contents
        TextArea ta = new TextArea();
        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server"); // stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
                Platform.runLater(()
                        -> ta.appendText("Server started at " + new Date() + '\n'));

                // Listen for a connection request
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());

                while (true) {
                // Receive number from the client
                    int number = inputFromClient.readInt();
                 // use isPrime on the number
                    boolean answer = isPrime(number);
                // Send answer to client 
                    outputToClient.writeBoolean(answer);
                    Platform.runLater(() -> {
                        ta.appendText("Number received from client: "
                                + number + '\n' + "is the number prime??");
                        ta.appendText("Answer is: " + answer + '\n');
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();

    }

  public static void main(String[] args) {
        launch(args);
    }


//method to determine if prime or not 
    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

  
}
