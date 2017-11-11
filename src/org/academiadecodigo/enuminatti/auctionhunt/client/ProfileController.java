package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.academiadecodigo.enuminatti.auctionhunt.server.Item;

public class ProfileController {

    @FXML
    private Label Photo;

    @FXML
    private Button MyFundsButton;

    @FXML
    private Label Money;

    @FXML
    private Label NumberOfItems;

    @FXML
    private Button LogOutButton;

    @FXML
    private Button GoToAuctionButton;

    @FXML
    private Pane DepositWithdrawMoey;

    @FXML
    private Button DepositButton;

    @FXML
    private Button WithdrawButton;

    @FXML
    private TextField InsertWithdrawMoney;

    @FXML
    private Button UploadItemButton;

    @FXML
    private Label readHistory1;

    @FXML
    private Label readHistory2;

    @FXML
    private Label readHistory3;

    @FXML
    private Label readHistory4;

    @FXML
    private Label readHistory5;

    @FXML
    private AnchorPane UploadPhotoButton;

    @FXML
    private Button UploadPhoto;

    @FXML
    void onDepositButtonPressed(ActionEvent event) {

    }

    @FXML
    void onGoToAuctionButtonPressed(ActionEvent event) {
        String dataHead = HandleClient.getInstance().setDataServer("item", GoToAuctionButton.getText());
        HandleClient.getInstance().sendData(dataHead);
        String receiveHead = HandleClient.getInstance().readData();
        String decodeMessage = HandleClient.getInstance().receiveDataServer(receiveHead);

        if(decodeMessage.equals("item")){

            Navigation.getInstance().loadScreen("bidAuction");
            return;
        }

        System.out.println("Something wrong happen");

    }

    @FXML
    void onLogoutButtonPressed(ActionEvent event) {
        Navigation.getInstance().back();

    }

    @FXML
    void onMyFundsButtonPressed(ActionEvent event) {

    }

    @FXML
    void onUploadButtonPressed(ActionEvent event) {

    }

    @FXML
    void onUploadPhotoButtonPressed(ActionEvent event) {

    }

    @FXML
    void onWithdrawButtonPressed(ActionEvent event) {

    }

}

