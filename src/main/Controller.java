package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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

    private DataSystem data;
    private boolean end = false;

    public void initialize(URL url, ResourceBundle rb){
        data = DataSystem.getInstance();
        player1_score.textProperty().bind(data.scorePlayer1Property().asString());
        player2_score.textProperty().bind(data.scorePlayer2Property().asString());
        ties_score.textProperty().bind(data.scoreTiesProperty().asString());

        playerChange.setText("VS Computer");

        for(int i =0;i<3;i++){
            for(int j =0;j<3;j++){
                BoardPiece piece = new BoardPiece(i,j);
                piece.ownerProperty().bind(data.board[i][j]);
                piece.setOnMouseClicked(this::onMouseClicked);
                gameGrid.add(piece,i,j);
            }
        }
        //gameGrid.setOnMouseClicked(this::onMouseClicked);
    }

    @FXML
    private void onPlayerChange(ActionEvent ae){
        System.out.println("Player Change");
    }

    @FXML
    private void onReset(ActionEvent ae){
        data.resetScores();
    }

    private void onMouseClicked(MouseEvent me){
        if(!end){
            BoardPiece piece = (BoardPiece) me.getSource();
            if(data.claim(piece.getX(),piece.getY())) {
                data.changeTurn();
                switch (data.checkForEnd()){
                    case DataSystem.NOEND:
                        break;
                    case DataSystem.PLAYER1WIN:
                        data.increaseScorePlayer1();
                        end = true;
                        break;
                    case DataSystem.PLAYER2WIN:
                        data.increaseScorePlayer2();
                        end = true;
                        break;
                    case DataSystem.TIE:
                        data.increaseScoreTies();
                        end = true;
                        break;
                }
            }
        }
        else {
            end = false;
            data.restartBoard();
        }
    }


}
