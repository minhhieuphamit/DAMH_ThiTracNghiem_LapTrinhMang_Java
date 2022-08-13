package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
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

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String receive = dis.readUTF();
            String receiveArray[] = receive.split("///");
            //int flag = flag(receiveArray[0]);
            switch (Integer.parseInt(receiveArray[0])) {
                case 1:
                    if (receiveArray[1].equals("localhost") && receiveArray[2].equals("1433")) {
                        System.out.println("Arrdress: " + receiveArray[1] + "\nPort: " + receiveArray[2]);
                        dos.writeUTF("Connected");
                    } else {
                        dos.writeUTF("Connected fail");
                    }
                    break;
                case 2:
                    System.out.print(receive);
                    if (ConnectDB.getConnect(receive) == true) {
                        dos.writeUTF("Connected");
                    } else {
                        dos.writeUTF("Connected fail");
                    }
                    break;
                case 3:
                    String[] arrStr = receive.split("///");
                    System.out.println("Ho ten: " + arrStr[1] + "\nMSSV: " + arrStr[2] + "" + "\nSDT: " + arrStr[3]);
                    if (ConnectDB.insertThongTin(receiveArray[1], receiveArray[2], receiveArray[3]) == true) {
                        dos.writeUTF("Success");
                    } else {
                        dos.writeUTF("Connected fail");
                    }
                    break;
                case 4:
                    System.out.println("Có Client đang thi trắc nghiệm...");
                    String strCauHoi = ConnectDB.getAllCauHoi();
                    String arrCauHoi[] = strCauHoi.split("///");
                    dos.writeUTF(strCauHoi);
                    String receiveAnswer = dis.readUTF();
                    System.out.println("Client đã trả lời: " + receiveAnswer);
                    String arrAnswer[] = receiveAnswer.split("///");
                    int cauDung = 0;
                    ArrayList<String> Answer = new ArrayList<>();
                    for (int i = 0; i < arrCauHoi.length; i += 7) {
                        Answer.add(arrCauHoi[i + 6]);
                    }
                    System.out.println(Answer);
                    for (int i = 0; i < arrAnswer.length; i++) {
                        if (Answer.get(i).equals(arrAnswer[i])) {
                            cauDung++;
                        }
                    }
                    System.out.println("Số câu đúng: " + cauDung);
                    dos.writeUTF(String.valueOf(cauDung));
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
