/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pham Minh Hieu
 */
public class Server {

    public static void loginServer() {
        ServerSocket ss = null;
        try {
            System.out.println("Server is already...");
            ss = new ServerSocket(8888);
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Server is already has a client connected");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String str = dis.readUTF();
                String[] arr = str.split("///");
                System.out.println("Address: " + arr[0] + "\nPort: " + arr[1]);
                ss.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loginDatabase() {
        try {
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("Database already...");
            while (true) {
                Socket socket = ss.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("Database is already connected to the client");
                String str = dis.readUTF();
                String[] arrStr = str.split("///");
                if (ConnectDB.getConnect(str)) {
                    dos.writeUTF("Success");

                } else {
                    dos.writeUTF("Fail");
                    System.out.println("Database connection failed!!!");
                }
                ss.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insertThongTin() {
        try {
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Receiving student information");
            while (true) {
                Socket socket = ss.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("Student information received");
                String str = dis.readUTF();
                String[] arrStr = str.split("///");
                System.out.println("Ho ten: " + arrStr[0] + "\nMSSV: " + arrStr[1] + "" + "\nSDT: " + arrStr[2]);
                if (ConnectDB.insertThongTin(arrStr[0], arrStr[1], arrStr[2])) {
                    dos.writeUTF("Success");
                } else {
                    dos.writeUTF("Fail");
                    System.out.println("Database connection failed!!!");
                }
                ss.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ThiTracNghiem() {
        int core = 0;
        try {
            ServerSocket ss = new ServerSocket(10000);
            System.out.println("Thi Trac Nghiem aldready...");

            Socket socket = ss.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String str = ConnectDB.getAllCauHoi();
            dos.writeUTF(str);
            String[] cauHoi = str.split("///");
            String anwClient = dis.readUTF();
            System.out.println(anwClient);

            String[] arrAnw = anwClient.split("///");
            int dem = 0;
            String answer = "";
            for (int i = 1; i < arrAnw.length; i = i + 2) {
                dem++;
                answer += dem + " " + arrAnw[i] + " - " + cauHoi[(dem * 8) - 1] + "\n";
                if (arrAnw[i].equals(cauHoi[(dem * 8) - 1])) {
                    core++;
                }
            }
            answer += "Diem cua ban la: " + core;
            dos.writeUTF(answer);
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        loginServer();
        loginDatabase();
        insertThongTin();
        ThiTracNghiem();
    }
}
