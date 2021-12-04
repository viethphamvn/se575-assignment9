package Server;

import Client.ClientHandler;
import TunableParameters.TunableParameters;
import Utils.SystemWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(){

    }

    public void run(){
        try {
            serverSocket = new ServerSocket(TunableParameters.COMMON_PORT);
            while(true) {
                Socket client = serverSocket.accept();
                SystemWrapper.println("New client connected " + client.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            }
        } catch (IOException e){

        }

    }

}
