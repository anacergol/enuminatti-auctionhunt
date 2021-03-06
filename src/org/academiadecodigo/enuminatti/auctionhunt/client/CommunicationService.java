package org.academiadecodigo.enuminatti.auctionhunt.client;

import org.academiadecodigo.enuminatti.auctionhunt.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 15/11/17.
 */
public class CommunicationService implements Runnable, Service {

    private Socket clientSocket;

    public String readData() {

        String line = null;

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            line = in.readLine();
            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    /**
     * @param data
     */
    public void sendData(String data) {

        byte[] bytes = new byte[1024];
        DataOutputStream itemOutput;
        DataInputStream dataInputStream;

        try {
           /* if (data.startsWith("/item/")) {

                itemOutput = new DataOutputStream(clientSocket.getOutputStream());
                dataInputStream = new DataInputStream(new FileInputStream(data));

                int bytesReaden = dataInputStream.read(bytes);

                while (bytesReaden != -1) {

                    itemOutput.write(bytes, 0, bytesReaden);
                    itemOutput.flush();
                    bytesReaden = dataInputStream.read(bytes);
                }
            }*/

            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadImage(String path, String data) {


        byte[] bytes = new byte[512 * 2048];
        DataInputStream dataIn = null;
        DataOutputStream dataOut = null;
        try {

            System.out.println("PATH:" + path);
            dataOut = new DataOutputStream(clientSocket.getOutputStream());
            File file = new File(path);
            dataIn = new DataInputStream(new FileInputStream(file));
            int bytesRead = dataIn.read(bytes);

            String msgProtocol = data + "#" + file.length() + "\n";

            System.out.println("PROTOCOL:"+ msgProtocol);

            System.out.println("FLENGTH:" + file.length());
            dataOut.write(msgProtocol.getBytes());
            while (bytesRead != -1) {

                System.out.println("teste");
                dataOut.write(bytes, 0, bytesRead);
                bytesRead = dataIn.read(bytes);
                dataOut.flush();
            }
            System.out.println("send photo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        System.out.println(this);
        System.out.println(Thread.currentThread().getName());

        while (true) {

            String answer = ParseClient.getInstance().decodeServerMessage(readData());

            System.out.println("ANSWER:" + answer);
            System.out.println(Thread.currentThread().getName());
            System.out.println(this);

            if (answer != null) {


                if (Navigation.getInstance().getController() instanceof LogicController) {


                    LogicController logicController = (LogicController) Navigation.getInstance().getController();
                    logicController.changeView(answer);

                }

                if (Navigation.getInstance().getController() instanceof ProfileController)
                    ((ProfileController) Navigation.getInstance().getController()).changeView(answer);
            }
        }
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
