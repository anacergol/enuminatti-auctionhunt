package org.academiadecodigo.enuminatti.auctionhunt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LogicController implements Initializable{

    @FXML
    private Button logOutButton;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernameField;

    @FXML
    private Text usernameText;

    @FXML
    private Text passwordText;

    @FXML
    private Text welcomeText;

    @FXML
    private Text auctionText;

    @FXML
    private Hyperlink dontHaveAccount;

    @FXML
    private Text succesfullLog;

    @FXML
    private Text couldNotLogIn;

    @FXML
    private Text emailText;

    @FXML
    private Hyperlink alreadyHaveAccount;

    @FXML
    private Button logInButton;

    @FXML
    private Text succesfullRegister;

    @FXML
    private Text couldNotRegister;

    @FXML
    private TextField emailfield;

    @FXML
    void changeToLogin(ActionEvent event) {
       showLogin();
    }

    @FXML
    void changeToRegister(ActionEvent event) {
        showRegister();
    }

    @FXML
    void onLogin(ActionEvent event) {
        System.out.println("cenas");
    }

    @FXML
    void onRegister(ActionEvent event) {
        System.out.println(usernameField.getText());
        System.out.println(passwordfield.getText());
        System.out.println(emailfield.getText());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showLogin();
    }

    private void showLogin() {
        emailfield.setVisible(false);
        emailText.setVisible(false);
        logOutButton.setVisible(false);
        alreadyHaveAccount.setVisible(false);
        logInButton.setVisible(true);
        dontHaveAccount.setVisible(true);

    }

    private void showRegister() {
        emailfield.setVisible(true);
        emailText.setVisible(true);
        logOutButton.setVisible(true);
        alreadyHaveAccount.setVisible(true);
        logInButton.setVisible(false);
        dontHaveAccount.setVisible(false);
    }
}

