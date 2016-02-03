package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by puebe on 30.01.2016.
 */
public class DataSystem {



    private static final DataSystem instance = new DataSystem();
    private Owner turn;
    private IntegerProperty scorePlayer1,scorePlayer2,scoreTies;
    public BoardPiece[][] board = new BoardPiece[3][3];

    private DataSystem(){
        turn = Owner.Player1;
        scorePlayer1 = new SimpleIntegerProperty(0);
        scorePlayer2 = new SimpleIntegerProperty(0);
        scoreTies = new SimpleIntegerProperty(0);

        initBoard();
    }

    public static DataSystem getInstance(){
        return instance;
    }

    public IntegerProperty scorePlayer1Property() {
        return scorePlayer1;
    }

    public IntegerProperty scorePlayer2Property() {
        return scorePlayer2;
    }

    public IntegerProperty scoreTiesProperty() {
        return scoreTies;
    }

    public Owner changeTurn(){
        turn = turn.getOpponent();
        return turn;
    }

    public boolean claimable(Coordinate coordinate){
        return BoardController.claimable(board, coordinate);
    }

    public boolean claim(Coordinate c){
        return BoardController.claim(board,c,turn);
    }

    public void resetScores(){
        scorePlayer1.set(0);
        scorePlayer2.set(0);
        scoreTies.set(0);
        turn = Owner.Player1;
        BoardController.restartBoard(board);
    }
    public void increaseScorePlayer1(){
        scorePlayer1.set(scorePlayer1.get()+1);
    }
    public void increaseScorePlayer2(){
        scorePlayer2.set(scorePlayer2.get()+1);
    }
    public void increaseScoreTies(){
        scoreTies.set(scoreTies.get()+1);
    }

    public Owner getTurn(){
        return turn;
    }

    public int checkForEnd(){
        return BoardController.checkForEnd(board);
    }

    public void restartBoard(){
        BoardController.restartBoard(board);
    }

    public boolean npcTurn(){
        if(turn.getNPC() == null) return false;
        claim(turn.getNPC().nextTurn(board));
        changeTurn();
        return true;
    }

    private void initBoard(){
        for(int i = 0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                board[i][j] = new BoardPiece(i,j);
            }
        }
    }
}
