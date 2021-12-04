package Client;

import Utils.Alphabetizer;
import Utils.CircularShifter;
import Utils.SystemWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        //sendResult(processClientInput(getClientInput()));
        try {
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            printWriter.println("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendResult(List<String> result) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(clientSocket.getOutputStream());
            for(String line : result){
                writer.println(line);
            }
            closeConnection(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> processClientInput(List<String> lines) {
        CircularShifter circularShifter = new CircularShifter();
        Alphabetizer alphabetizer = new Alphabetizer();

        List<String> circularShiftedLines = circularShifter.shiftLines(lines);
        List<String> alphabetizedLines = alphabetizer.alphabetize(circularShiftedLines);
        return alphabetizedLines;
    }

    private void closeConnection(PrintWriter writer) throws IOException {
        writer.close();
        clientSocket.close();
    }

    private List<String> getClientInput(){
        BufferedReader input;
        List<String> lines = new ArrayList<>();
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            while (!((line = input.readLine()).equalsIgnoreCase("done"))){
                lines.add(line);
                SystemWrapper.println(line);
            }
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
