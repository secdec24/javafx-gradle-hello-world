package app.helloworld.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloWorldController implements Initializable {

    @FXML private Button helloWorldButton;
    @FXML private Button clearButton;
    @FXML private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {}
}
