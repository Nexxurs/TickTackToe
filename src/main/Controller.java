package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import NPC.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Label player1_name, player2_name, player1_score, player2_score, ties_score;
    @FXML
    private ChoiceBox playerChoice;
    @FXML
    private GridPane gameGrid;

    private DataSystem data;
    private boolean end = false;
    private NPC npc;
    public void initialize(URL url, ResourceBundle rb){
        data = DataSystem.getInstance();
        player1_score.textProperty().bind(data.scorePlayer1Property().asString());
        player2_score.textProperty().bind(data.scorePlayer2Property().asString());
        ties_score.textProperty().bind(data.scoreTiesProperty().asString());

        ObservableList<String> playerChoiceList = FXCollections.observableArrayList();
        playerChoiceList.addAll("Player", "Easy", "Medium","Hard");

        playerChoice.setStyle("-fx-font: 20px \"System\";");
        playerChoice.setItems(playerChoiceList);
        playerChoice.getSelectionModel().select(0);
        playerChoice.setOnAction(this::onPlayerChoiceAction);

        npc = getNPCfromString((String)playerChoice.getSelectionModel().getSelectedItem());


        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                BoardPiece piece = data.board[i][j].get();
                //piece.ownerProperty().bind(data.board[i][j].get().ownerProperty());
                piece.setOnMouseClicked(this::onMouseClicked);
                gameGrid.add(piece,i,j);
            }
        }
    }

    @FXML
    private void onReset(ActionEvent ae){
        data.resetScores();
        data.restartBoard();
    }

    private void onPlayerChoiceAction(Event event){
        String selectedString = ((ChoiceBox<String>)((ActionEvent)event).getSource()).getSelectionModel().getSelectedItem();
        npc = getNPCfromString(selectedString);
        System.out.println(npc);
    }

    private void onMouseClicked(MouseEvent me){
        if(!end){
            BoardPiece piece = (BoardPiece) me.getSource();
            if(data.claim(piece.getX(),piece.getY())) {
                end = data.checkForEnd();
                if(!end) {
                    data.changeTurn();
                    if (npc != null) {
                        npc.nextTurn();
                        data.changeTurn();
                        end = data.checkForEnd();
                    }
                }

            }
        }
        else {
            end = false;
            data.restartBoard();
        }
    }

    private NPC getNPCfromString(String str){
        if(str==null||str.length()==0||str.equals("Player")) return null;
        if(str.equals("Easy")) return new easyNPC();
        if(str.equals("Medium")) return new mediumNPC();
        if(str.equals("Hard")) return new hardNPC();
        return null;
    }
}
