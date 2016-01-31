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
    public ObjectProperty<Owner>[][] board = new ObjectProperty[3][3];

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
        if(board[x][y].get().equals(Owner.Unowned)){
            board[x][y].set(turn);
            return true;
        }
        return false;
    }

    public void resetScores(){
        scorePlayer1.set(0);
        scorePlayer2.set(0);
        scoreTies.set(0);
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
                board[i][j].set(Owner.Unowned);
            }
        }
    }

    public int checkForEnd(){
        boolean noend = false;
        for (int i = 0; i < 3; i++) {
            switch (checkRow(i)){
                case PLAYER1WIN:
                    return PLAYER1WIN;
                case PLAYER2WIN:
                    return PLAYER2WIN;
                case NOEND:
                    noend=true;
            }
            switch (checkColumn(i)){
                case PLAYER1WIN:
                    return PLAYER1WIN;
                case PLAYER2WIN:
                    return PLAYER2WIN;
                case NOEND:
                    noend=true;
            }
        }
        switch (checkDiagnoal1()){
            case PLAYER1WIN:
                return PLAYER1WIN;
            case PLAYER2WIN:
                return PLAYER2WIN;
            case NOEND:
                noend=true;
        }
        switch (checkDiagonal2()){
            case PLAYER1WIN:
                return PLAYER1WIN;
            case PLAYER2WIN:
                return PLAYER2WIN;
            case NOEND:
                noend=true;
        }
        if(noend) return NOEND;
        else return TIE;
    }

    private int checkRow(int row){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[row][i].get().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[row][i].get();
            else {
                if(!board[row][i].get().equals(owner)){
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
            if(board[i][column].get().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][column].get();
            else {
                if(!board[i][column].get().equals(owner)){
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
            if(board[i][i].get().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][i].get();
            else {
                if(!board[i][i].get().equals(owner)){
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
            if(board[i][2-i].get().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][2-i].get();
            else {
                if(!board[i][2-i].get().equals(owner)){
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
                board[i][j] = new SimpleObjectProperty<>(Owner.Unowned);
            }
        }
    }
}
