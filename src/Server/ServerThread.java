package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * @author Pham Minh Hieu
 */
public class ServerThread implements Runnable {

    private Socket socket;
    private String name;

    public ServerThread(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;

        new Thread(this).start();
    }

    public static final int loginServer = 1;
    public static final int loginDatabase = 2;
    public static final int insertThongTin = 3;
    public static final int thiTracNghiem = 4;

    public int flag(String str) {
        if (str.equals("1")) {
            return loginServer;
        } else if (str.equals("2")) {
            return loginDatabase;
        } else if (str.equals("3")) {
            return insertThongTin;
        } else if (str.equals("4")) {
            return thiTracNghiem;
        } else {
            return -1;
        }
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String receive = dis.readUTF();
            String receiveArray[] = receive.split("///");
            int flag = flag(receiveArray[0]);
            switch (flag) {
                case loginServer:
                    if (receiveArray[1].equals("localhost") && receiveArray[2].equals("1433")) {
                        System.out.println("Arrdress: " + receiveArray[1] + "\nPort: " + receiveArray[2]);
                        dos.writeUTF("Connected");
                    } else {
                        dos.writeUTF("Connected fail");
                    }
                    break;
                case loginDatabase:
                    System.out.print(receive);
                    //nếu đăng nhập thành công thì gửi thông báo kết nối thành công
                    if (ConnectDB.getConnect(receive) == true) {
                        dos.writeUTF("Connected");
                    } else {
                        dos.writeUTF("Connected fail");
                    }
                    break;
                case insertThongTin:
                    String[] arrStr = receive.split("///");
                    System.out.println("Ho ten: " + arrStr[1] + "\nMSSV: " + arrStr[2] + "" + "\nSDT: " + arrStr[3]);
                    if (ConnectDB.insertThongTin(receiveArray[1], receiveArray[2], receiveArray[3])) {
                        dos.writeUTF("Success");
                    } else {
                        dos.writeUTF("Connected fail");
                    }
                    break;
                case thiTracNghiem:
                    System.out.println("Có Client đang thi trắc nghiệm");
                    String strCauHoi = ConnectDB.getAllCauHoi();
                    dos.writeUTF(strCauHoi);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
