package org.academiadecodigo.enuminatti.auctionhunt.utils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Someone who is not me on 12/11/17.
 */
public class ItemData {

    public static void save(String file, String name, String itemName, String path, String price) throws IOException {

        BufferedReader read = new BufferedReader(new FileReader(file));

        BufferedWriter save = new BufferedWriter(new FileWriter(file, true));

        int itemnumber = 0;

        String line;

        while ((line = read.readLine()) != null) {
            if (line.equals("<------------------->")) {
                itemnumber = itemnumber + 1;
            }
        }

        String newPath = load(file, path);

        save.write("ID: " + name + "\n" +
                "Item ID: " + itemnumber + "\n" +
                "Item name: " + itemName + "\n" +
                "Path: " + newPath + "\n" +
                "Price: " + price + "€\n" +
                "Uploud Date: " + getDateTime() + "\n<------------------->");

        save.newLine();
        save.flush();
        save.close();
    }

    public static String load(String file, String Path) {
        BufferedReader read;

        String newPath = Path;

        try {
            read = new BufferedReader(new FileReader(file));

            String line = "";

            while ((line = read.readLine()) != null) {
                if(line.equals(Path)){
                    newPath = Path.concat("_1");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newPath;
    }

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
