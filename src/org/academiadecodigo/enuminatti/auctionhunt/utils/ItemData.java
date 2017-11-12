package org.academiadecodigo.enuminatti.auctionhunt.utils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Someone who is not me on 12/11/17.
 */

public class ItemData {

    /**
     *
     * @param file
     * @param name
     * @param itemName
     * @param path
     * @param price
     * @throws IOException
     */
    public static void save(String file, String name, String itemName, String path, String price) throws IOException {

        BufferedReader read = new BufferedReader(new FileReader(file));

        BufferedWriter save = new BufferedWriter(new FileWriter(file, true));

        int itemNumber = 0;

        String line;

        while ((line = read.readLine()) != null) {
            if (line.contains("<------------------->")) {
                itemNumber = itemNumber + 1;
            }

            //itemNumber = 1;
        }

        String newPath = load(file, path);

        save.write("ID: " + name + "\n" +
                "Item ID: " + itemNumber + "\n" +
                "Item name: " + itemName + "\n" +
                "Path: " + newPath + "\n" +
                "Price: " + price + "€\n" +
                "Upload Date: " + getDateTime() + "\n<------------------->");

        save.newLine();
        save.flush();
        save.close();
    }

    /**
     *
     * @param file
     * @param Path
     * @return
     */
    public static String load(String file, String Path) {

        BufferedReader read = null;

        String newPath = Path;

        int counternumber = 1;

        try {
            read = new BufferedReader(new FileReader(file));

            String line = "";

            while ((line = read.readLine()) != null) {
                if (line.contains(Path)) {
                    if(newPath.contains("_" + counternumber)) {
                        counternumber++;
                    }
                    newPath = Path.concat("_" + counternumber);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newPath;
    }

    /**
     *
     * @return
     */
    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
