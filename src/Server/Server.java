package Server;

import Server.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Pham Minh Hieu
 */
public class Server {

    static final int PORT = 8000;
    private ServerSocket server = null;

    public Server() {
        try {
            server = new ServerSocket(PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void action() throws IOException {
        Socket socket = null;
        int i = 0;
        System.out.println("Server is running...");
        try {
            while ((socket = server.accept()) != null) {
                new ServerThread(socket, "Client#" + i);
                System.out.printf("Thread for Client#%d generating...%n", i++);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().action();
    }
}
