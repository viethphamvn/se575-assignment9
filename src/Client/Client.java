package Client;

import TunableParameters.TunableParameters;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket socket;

    public Client(){

    }

    public void run(){
        PrintWriter output;
        try{
            socket = new Socket("localhost", TunableParameters.COMMON_PORT);
            output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String line = null;
            while (!"done".equalsIgnoreCase(line)) {
                line = scanner.nextLine();
                output.println(line);
            }
            scanner.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
