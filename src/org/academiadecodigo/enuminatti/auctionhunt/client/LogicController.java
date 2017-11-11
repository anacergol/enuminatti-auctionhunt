package org.academiadecodigo.enuminatti.auctionhunt.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.academiadecodigo.enuminatti.auctionhunt.server.Server;
import org.academiadecodigo.enuminatti.auctionhunt.server.UserService;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LogicController implements Initializable {

    private UserService userService;

    @FXML
    private Button logOutButton;

    @FXML
    private PasswordField passwordField;

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
    private TextField emailField;

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

        if (usernameField.getText().isEmpty()) {
            couldNotLogIn.setVisible(true);
            return;
        }

        if (passwordField.getText().isEmpty()) {
            couldNotLogIn.setVisible(true);
            return;
        }

        String data = usernameField.getText() + " " + passwordField.getText();

        System.out.println(logInButton.getText());
        String dataAndHead = ParseClient.getInstance().setDataServer(data, logInButton.getText());
        ParseClient.getInstance().sendData(dataAndHead);

        String string = ParseClient.getInstance().readData();

        if (ParseClient.getInstance().decodeServerMessage(string)) {

            succesfullLog.setVisible(true);
            Navigation.getInstance().loadScreen("Profile");

            return;
        }
        couldNotLogIn.setVisible(true);

    }

    @FXML
    void onRegister(ActionEvent event) {

        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        System.out.println(emailField.getText());

        if (usernameField.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return;
        }

        if (passwordField.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return;
        }

        if (emailField.getText().isEmpty()) {
            couldNotRegister.setVisible(true);
            return;
        }

        String registerData = usernameField.getText() + " " + emailField.getText() + " " + passwordField.getText();
        System.out.println(logInButton.getText());

        String register = ParseClient.getInstance().setDataServer(registerData, logOutButton.getText());

        ParseClient.getInstance().sendData(register);

        String readData = ParseClient.getInstance().readData();

        if (ParseClient.getInstance().decodeServerMessage(readData)) {

            System.out.println("bem-vindo");
            succesfullRegister.setVisible(true);
            showLogin();

            return;
        }
        couldNotRegister.setVisible(true);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Socket clientSocket = null;

        try {

            clientSocket = new Socket(Server.HOST, Server.PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ParseClient.getInstance().setClientSocket(clientSocket);
        showLogin();

    }

    private void showLogin() {
        if (couldNotRegister.isVisible()) {
            couldNotRegister.setVisible(false);
        }
        succesfullRegister.setVisible(false);
        emailField.setVisible(false);
        emailText.setVisible(false);
        logOutButton.setVisible(false);
        alreadyHaveAccount.setVisible(false);
        logInButton.setVisible(true);
        dontHaveAccount.setVisible(true);

    }

    private void showRegister() {
        if (couldNotLogIn.isVisible()) {
            couldNotLogIn.setVisible(false);
        }

        emailField.setVisible(true);
        emailText.setVisible(true);
        logOutButton.setVisible(true);
        alreadyHaveAccount.setVisible(true);
        logInButton.setVisible(false);
        dontHaveAccount.setVisible(false);
    }
}

