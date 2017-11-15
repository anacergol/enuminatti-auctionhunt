package org.academiadecodigo.enuminatti.auctionhunt.server;

import org.academiadecodigo.enuminatti.auctionhunt.utils.Security;
import org.academiadecodigo.enuminatti.auctionhunt.utils.UserData;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 10/11/17.
 */
public final class ParseServer {

    private Socket clientSocket = null;
    private static ParseServer instance;

    /**
     *
     */
    private ParseServer() {
    }

    /**
     * @return
     */
    public static ParseServer getInstance() {
        if (instance == null) {
            synchronized (ParseServer.class) {
                if (instance == null) {
                    instance = new ParseServer();
                }
            }
        }
        return instance;
    }


    /**
     * @param line
     */
    public void validateData(String line) {

        System.out.println(line);
        if (line.startsWith("/regist/")) {
            registerDecodificate(line);
            return;
        }
        if (line.startsWith("/login/")) {
            loginDecodificate(line);
            return;
        }
        if (line.startsWith("/item/")) {
            itemDecodificate(line);
        }
        if (line.endsWith(".jpg")) {
            itemDecodificate(line);
        }

        if (line.startsWith("/withdraw/")) {
            withdrawDecodificate(line);
        }
        if (line.startsWith("/deposit/")) {
            depositDecodificate(line);
        }
    }

    private void depositDecodificate(String line) {

        line = line.replace("/deposit/", "");
        String[] words = line.split("#");

        MoneyService moneyService = (MoneyService) ServiceRegistry.getInstance().getService("MoneyService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            moneyService.depositMoney(words[0], words[1]);

            out.println("/deposit/done/" + words[0] + "#" + UserData.getInstance().userFunds(words[0]));

        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

    }


    private void withdrawDecodificate(String line) {

        line = line.replace("/withdraw/", "");
        String[] words = line.split("#");

        MoneyService moneyService = (MoneyService) ServiceRegistry.getInstance().getService("MoneyService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (!moneyService.withdrawMoney(words[0], words[1])) {
                return;
            }

            out.println("/withdraw/done/" + words[0] + "#" + UserData.getInstance().userFunds(words[0]));

        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

    }

    /**
     * @param line
     */
    private void itemDecodificate(String line) {

        byte[] bytes = new byte[1024];
        line = line.replace("/item/", "");
        //String[] words = line.split("€");
        int length = Integer.parseInt(line);
        System.out.println(length);

        int bytesReadTotal = 0;

                System.out.println(line + "<-----------");
        try {

            FileOutputStream itemOutput = new FileOutputStream("resources/test.jpg");
            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            int bytesReaden;

            while (bytesReadTotal != length) {
                bytesReaden = dataIn.read(bytes);
                itemOutput.write(bytes, 0, bytesReaden);
                bytesReadTotal += bytesReaden;
                itemOutput.flush();
            }
            System.out.println("done reading");

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(words[0]);
    }


    /**
     * @param line
     */
    private void loginDecodificate(String line) {


        line = line.replace("/login/", "");
        String[] words = line.split("#");

        UserService userService = (UserService) ServiceRegistry.getInstance().getService("UserService");
        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (userService.authenticate(words[0], words[1])) {
                out.println("/login/done/" + words[0] + "#" + userService.getUserFunds(words[0]));
                return;
            }

            out.println("login not done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param line
     */
    private void registerDecodificate(String line) {

        line = line.replace("/regist/", "");
        String[] words = line.split("#");

        UserService userService = (UserService) ServiceRegistry.getInstance().getService("UserService");

        try {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            if (!userService.findByName(words[0])) {
                userService.addUser(new User(words[0], words[1], Security.getHash(words[2]), 0));
                System.out.println("Users: " + userService.count());
                out.println("/regist/done/" + line);
                return;

            }

            out.println("register not done");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param clientSocket
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

}
