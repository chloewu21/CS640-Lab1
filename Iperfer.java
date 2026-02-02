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

    private static void clientMode(String host, int port, int time) {

    }

    private static void serverMode(int port) {

    }
}