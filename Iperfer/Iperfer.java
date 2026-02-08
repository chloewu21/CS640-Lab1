package Iperfer;
import java.io.*;
import java.net.*;

public class Iperfer {
    public static void main(String[] args) throws IOException{
        if (args.length == 0) {
            System.out.println("Error: missing arguments");
            System.exit(1);
        }
        // Client mode
        if (args[0].equals("-c")) {
            if (args.length != 7 || !args[1].equals("-h") || !args[3].equals("-p") || !args[5].equals("-t")) {
                System.out.println("Error: missing or additional arguments");
                System.exit(1);
            }
            String host = args[2];
            int port = 0;
            int time = 0;
            try {
                port = Integer.parseInt(args[4]);
                time = Integer.parseInt(args[6]);
                if (port < 1024 || port > 65535) {
                    System.out.println("Error: port number must be in the range 1024 to 65535");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: port number must be in the range 1024 to 65535");
                System.exit(1);
            }
            clientMode(host, port, time);
        }
        // Server mode
        else if (args[0].equals("-s")) {
            if (args.length != 3 || !args[1].equals("-p")) {
                System.out.println("Error: missing or additional arguments");
                System.exit(1);
            }
            int port = 0;
            try {
                port = Integer.parseInt(args[2]);
                if (port < 1024 || port > 65535) {
                    System.out.println("Error: port number must be in the range 1024 to 65535");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
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
    private static void clientMode(String host, int port, int time) throws IOException {
        Socket socket = new Socket(host, port); // create socket
        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[1000]; // buffer to send 1000-byte chunks
        long total = 0; // total number of bytes sent
        long start = System.currentTimeMillis(); // start time in milliseconds
        while (System.currentTimeMillis() - start < time * 1000) {
            out.write(buffer);
            total += buffer.length;
        }
        System.out.printf("sent=%d KB\trate=%.3f Mbps\n", total / 1000, ((total * 8.0) / 1000000) / time);
        out.close();
        socket.close();
    }

    /**
     * Listen for TCP connections from a client and receive data until client closes the connection.
     * Read data in 1000 byte chunks. Keep a running total of the number of bytes received.
     * After the client closes the connection, print a one line summary with total number of bytes
     * received (kilobytes) and the rate at which traffic was received (Mbps).
     * @param port Port to listen on
     */
    private static void serverMode(int port) throws IOException{
        ServerSocket serverSocket = new ServerSocket(port);
        Socket client = serverSocket.accept();
        InputStream in = client.getInputStream();
        byte[] buffer = new byte[1000]; // buffer to read 1000-byte chunks
        long total = 0; // total number of bytes received
        int read;
        long start = System.currentTimeMillis();
        while ((read = in.read(buffer)) != -1) {
            total += read;
        }
        long end = System.currentTimeMillis();
        System.out.printf("received=%d KB\trate=%.3f Mbps\n", total / 1000, ((total * 8.0) / 1000) / (end - start));
        in.close();
        client.close();
        serverSocket.close();
    }
}