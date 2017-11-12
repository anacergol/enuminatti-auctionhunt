package org.academiadecodigo.enuminatti.auctionhunt.server;

/**
 * Created by Someone who is not me on 07/11/17.
 */
public class Item {

    private User user;
    private String itemName;
    private String itemDescription;
    private int askingPrice; //price setted by user to sell
    private int actualBid; //last bid
    private String pictureURL;

    /**
     *
     * @param user
     * @param itemName
     * @param itemDescription
     * @param askingPrice
     * @param pictureURL
     */
    public Item(User user, String itemName, String itemDescription, int askingPrice, String pictureURL) {
        this.user = user;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.askingPrice = askingPrice;
        this.pictureURL = pictureURL;
    }

    /**
     *
     * @return
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *
     * @return
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     *
     * @return
     */
    public int getAskingPrice() {
        return askingPrice;
    }

    /**
     *
     * @return
     */
    public int getActualBid() {
        return actualBid;
    }

    /**
     *
     * @param actualBid
     */
    public void setActualBid(int actualBid) {
        this.actualBid = actualBid;
    }

    /**
     *
     * @return
     */
    public String getPictureURL() {
        return pictureURL;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}