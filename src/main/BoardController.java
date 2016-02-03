package main;

/**
 * Created by puebe on 02.02.2016.
 */
public class BoardController {

    public static final int NOEND = 0;
    public static final int TIE=1;
    public static final int PLAYER1WIN =2;
    public static final int PLAYER2WIN =3;

    public static boolean claimable(BoardPiece[][] board, Coordinate c){
        if(c.getX()>3||c.getY()>3) return false;
        if(board[c.getX()][c.getY()].getOwner().equals(Owner.Unowned)){
            return true;
        }
        return false;
    }

    public static boolean claim(BoardPiece[][] board, Coordinate c, Owner player){
        if(claimable(board, c)) {
            board[c.getX()][c.getY()].setOwner(player);
            return true;
        }
        return false;
    }
    public static void restartBoard(BoardPiece[][] board){

        for(int i = 0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                board[i][j].setOwner(Owner.Unowned);
            }
        }
        dehighlighAll(board);
    }
    public static void dehighlighAll(BoardPiece[][] board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setHighlight(false);
            }
        }
    }


    public static int checkForEnd(BoardPiece[][] board){
        boolean noend = false;
        for (int i = 0; i < 3; i++) {

            int res = checkRow(board, i);
            if(res==PLAYER1WIN||res==PLAYER2WIN){
                highlightRow(board, i);
                return res;
            } else if(res == NOEND) noend=true;

            res = checkColumn(board, i);
            if(res==PLAYER1WIN||res==PLAYER2WIN){
                highlightColumn(board, i);
                return res;
            } else if(res == NOEND) noend=true;
        }

        int res = checkDiagnoal1(board);
        if(res==PLAYER1WIN||res==PLAYER2WIN){
            highlightDiagonal1(board);
            return res;
        } else if(res == NOEND) noend=true;

        res = checkDiagonal2(board);
        if(res==PLAYER1WIN||res==PLAYER2WIN){
            highlightDiagonal2(board);
            return res;
        } else if(res == NOEND) noend=true;


        if(noend) return NOEND;
        else {
            return TIE;
        }
    }

    private static void highlightRow(BoardPiece[][] board, int row){
        highlightCell(board, row,0);
        highlightCell(board, row,1);
        highlightCell(board, row,2);
    }
    private static void highlightColumn(BoardPiece[][] board, int column){
        highlightCell(board, 0,column);
        highlightCell(board, 1,column);
        highlightCell(board, 2,column);
    }
    private static void highlightDiagonal1(BoardPiece[][] board){
        highlightCell(board, 0,0);
        highlightCell(board, 1,1);
        highlightCell(board, 2,2);
    }
    private static void highlightDiagonal2(BoardPiece[][] board){
        highlightCell(board, 2,0);
        highlightCell(board, 1,1);
        highlightCell(board, 0,2);
    }
    private static void highlightCell(BoardPiece[][] board, int x, int y){
        board[x][y].setHighlight(true);
    }

    private static int checkRow(BoardPiece[][] board, int row){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[row][i].getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[row][i].getOwner();
            else {
                if(!board[row][i].getOwner().equals(owner)){
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
    private static int checkColumn(BoardPiece[][] board, int column){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[i][column].getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][column].getOwner();
            else {
                if(!board[i][column].getOwner().equals(owner)){
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
    private static int checkDiagnoal1(BoardPiece[][] board){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[i][i].getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][i].getOwner();
            else {
                if(!board[i][i].getOwner().equals(owner)){
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

    private static int checkDiagonal2(BoardPiece[][] board){
        int returnValue = -1;
        Owner owner = null;
        for(int i = 0;i<3;i++){
            if(board[i][2-i].getOwner().equals(Owner.Unowned)) return NOEND;
            if(owner == null) owner = board[i][2-i].getOwner();
            else {
                if(!board[i][2-i].getOwner().equals(owner)){
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

}
