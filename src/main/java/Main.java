import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * SAMPLE RESPONSE

// Status line
HTTP/1.1  // HTTP version
200       // Status code
OK        // Optional reason phrase
\r\n      // CRLF that marks the end of the status line

// Headers (empty)
\r\n      // CRLF that marks the end of the headers

// Response body (empty)

 */

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");


    try {
      ServerSocket serverSocket = new ServerSocket(4221);
      System.out.println("Watiting for connection...");
    
      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors
      serverSocket.setReuseAddress(true);
    
      Socket socket = serverSocket.accept(); // Wait for connection from client.
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      String request = in.readLine();
      String[] parts = request.split(" ");
      String method = parts[0];
      String path = parts[1];
      String protocol = parts[2];

      if (path.equals("/")) {
        out.println(protocol + " 200 OK\r\n\r\n");
      } else {
        out.println(protocol + " 404 Not Found\r\n\r\n");
      }
      socket.close();
      
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }
}
