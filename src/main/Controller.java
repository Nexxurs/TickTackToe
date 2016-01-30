package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Label player1_name, player2_name, player1_score, player2_score, ties_score;
    @FXML
    private Button playerChange;
    @FXML
    private GridPane gameGrid;


    public void initialize(URL url, ResourceBundle rb){

    }

    @FXML
    private void onPlayerChange(ActionEvent ae){
        System.out.println("Player Change");
    }

    @FXML
    private void onReset(ActionEvent ae){
        System.out.println("Reset");
    }

}
