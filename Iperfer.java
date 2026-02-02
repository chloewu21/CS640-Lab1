import java.io.*;
import java.net.*;

public class Iperfer {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: missing or additional arguments");
            System.exit(1);
        }
        if (args[0].equals("-c")) {
            if (args.length != 7) {
                System.out.println("Error: missing or additional arguments");
                System.exit(1);
            }
            String host = args[2];
            int port = Integer.parseInt(args[4]);
            int time = Integer.parseInt(args[6]);
            if (port < 1024 || port > 65535) {
                System.out.println("Error: port number must be in the range 1024 to 65535");
                System.exit(1);
            }
            clientMode(host, port, time);
        }
        else if (args[0].equals("-s")) {
            if (args.length != 3) {
                System.out.println("Error: missing or additional arguments");
                System.exit(1);
            }
            int port = Integer.parseInt(args[2]);
            if (port < 1024 || port > 65535) {
                System.out.println("Error: port number must be in the range 1024 to 65535");
                System.exit(1);
            }
            serverMode(port);
        }
        else {
            System.out.println("Error: missing or additional arguments");
            System.exit(1);
        }
        
    }

    /**
     * Establish TCP connection with server and send data as quickly as possible for time seconds. 
     * Data should be sent in chunks of 1000 bytes and should be all 0s. Keep a running total of 
     * the number of bytes sent. After time seconds, stop sending data and close the connection.
     * Print a one line summary with total number of bytes send (kilobytes) and the rate at which 
     * traffic could be sent (Mbps).
     * @param host Server host name
     * @param port Server port
     * @param time Duration in seconds for which data should be generated
     */
    private static void clientMode(String host, int port, int time) {

    }

    /**
     * Listen for TCP connections from a client and receive data until client closes the connection.
     * Read data in 1000 byte chunks. Keep a running total of the number of bytes received.
     * After the client closes the connection, print a one line summary with total number of bytes
     * received (kilobytes) and the rate at which traffic was received (Mbps).
     * @param port Port to listen on
     */
    private static void serverMode(int port) {

    }
}