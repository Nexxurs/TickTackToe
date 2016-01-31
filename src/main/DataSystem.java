package main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;

/**
 * Created by puebe on 30.01.2016.
 */
public class DataSystem {

    public static final int NOEND = 0;
    public static final int TIE=1;
    public static final int PLAYER1WIN =2;
    public static final int PLAYER2WIN =3;

    private static final DataSystem instance = new DataSystem();
    private Owner turn;
    private IntegerProperty scorePlayer1,scorePlayer2,scoreTies;
    public ObjectProperty<BoardPiece>[][] board = new ObjectProperty[3][3];

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
        if(turn==Owner.Player1) turn = Owner.Player2;
        else turn = Owner.Player1;
        return turn;
    }

    public boolean claim(int x,int y){
        if(x>3||y>3) return false;
        if(board[x][y].get().getOwner().equals(Owner.Unowned)){
            board[x][y].get().setOwner(turn);
            return true;
        }
        return false;
    }

    public void resetScores(){
        scorePlayer1.set(0);
        scorePlayer2.set(0);
        scoreTies.set(0);
        turn = Owner.Player1;
        restartBoard();
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

    public void restartBoard(){

        for(int i = 0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                board[i][j].get().setOwner(Owner.Unowned);
            }
        }
        dehighlighAll();
    }

    public boolean checkForEnd(){
        boolean noend = false;
        for (int i = 0; i < 3; i++) {
            switch (checkRow(i)){
                case PLAYER1WIN:
                    increaseScorePlayer1();
                    highlightRow(i);
                    return true;
                case PLAYER2WIN:
                    highlightRow(i);
                    increaseScorePlayer2();
                    return true;
                case NOEND:
                    noend=true;
            }
            switch (checkColumn(i)){
                case PLAYER1WIN:
                    increaseScorePlayer1();
                    highlightColumn(i);
                    return true;
                case PLAYER2WIN:
                    highlightColumn(i);
                    increaseScorePlayer2();
                    return true;
                case NOEND:
                    noend=true;
            }
        }
        switch (checkDiagnoal1()){
            case PLAYER1WIN:
                increaseScorePlayer1();
                highlightDiagonal1();
                return true;
            case PLAYER2WIN:
                increaseScorePlayer2();
                highlightDiagonal1();
                return true;
            case NOEND:
                noend=true;
        }
        switch (checkDiagonal2()){
            case PLAYER1WIN:
                increaseScorePlayer1();
                highlightDiagonal2();
                return true;
            case PLAYER2WIN:
                increaseScorePlayer2();
                highlightDiagonal2();
                return true;
            case NOEND:
                noend=true;
        }
        if(noend) return false;
        else {
            increaseScoreTies();
            return true;
        }
    }

    private void highlightRow(int row){
        highlightCell(row,0);
        highlightCell(row,1);
        highlightCell(row,2);
    }
    private void highlightColumn(int column){
        highlightCell(0,column);
        highlightCell(1,column);
        highlightCell(2,column);
    }
    private void highlightDiagonal1(){
        highlightCell(0,0);
        highlightCell(1,1);
        highlightCell(2,2);
    }
    private void highlightDiagonal2(){
        highlightCell(2,0);
        highlightCell(1,1);
        highlightCell(0,2);
    }
    private void highlightCell(int x, int y){
        board[x][y].get().setHighlight(true);
    }
    public void dehighlighAll(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].get().setHighlight(false);
            }
        }
    }

    private int checkRow(int row){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[row][i].get().getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[row][i].get().getOwner();
            else {
                if(!board[row][i].get().getOwner().equals(owner)){
                    returnValue = TIE;
                }
            }
        }

        if(returnValue!=TIE){
            if(owner.equals(Owner.Player1)) returnValue=PLAYER1WIN;
            else if(owner.equals(Owner.Player2)) returnValue=PLAYER2WIN;
        }
        return returnValue;

    }
    private int checkColumn(int column){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[i][column].get().getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][column].get().getOwner();
            else {
                if(!board[i][column].get().getOwner().equals(owner)){
                    returnValue = TIE;
                }
            }
        }

        if(returnValue!=TIE){
            if(owner.equals(Owner.Player1)) returnValue=PLAYER1WIN;
            else if(owner.equals(Owner.Player2)) returnValue=PLAYER2WIN;
        }
        return returnValue;
    }
    private int checkDiagnoal1(){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[i][i].get().getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][i].get().getOwner();
            else {
                if(!board[i][i].get().getOwner().equals(owner)){
                    returnValue = TIE;
                }
            }
        }

        if(returnValue!=TIE){
            if(owner.equals(Owner.Player1)) returnValue=PLAYER1WIN;
            else if(owner.equals(Owner.Player2)) returnValue=PLAYER2WIN;
        }
        return returnValue;
    }

    private int checkDiagonal2(){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[i][2-i].get().getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][2-i].get().getOwner();
            else {
                if(!board[i][2-i].get().getOwner().equals(owner)){
                    returnValue = TIE;
                }
            }
        }

        if(returnValue!=TIE){
            if(owner.equals(Owner.Player1)) returnValue=PLAYER1WIN;
            else if(owner.equals(Owner.Player2)) returnValue=PLAYER2WIN;
        }
        return returnValue;
    }

    private void initBoard(){
        for(int i = 0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                board[i][j] = new SimpleObjectProperty<>(new BoardPiece(i,j));
            }
        }
    }
}
