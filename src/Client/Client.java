package Client;

import TunableParameters.TunableParameters;
import Utils.SystemWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket socket;

    public void run(){
        PrintWriter output;
        BufferedReader input;
        try{
            socket = new Socket("localhost", TunableParameters.COMMON_PORT);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            String line = null;
            while (!"done".equalsIgnoreCase(line)) {
                line = scanner.nextLine();
                output.println(line);
            }
            scanner.close();
            readResultFromServer(input);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readResultFromServer(BufferedReader reader) throws IOException {
        String line;
        while(!((line = reader.readLine()).equalsIgnoreCase("done"))){
            SystemWrapper.println(line);
        }
    }
}
