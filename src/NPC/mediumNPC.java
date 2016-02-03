package NPC;

import main.*;

import java.util.Random;

/**
 * Created by puebe on 31.01.2016.
 */
public class mediumNPC implements NPC {
    @Override
    public Coordinate nextTurn(BoardPiece[][] board) {
        DataSystem data = DataSystem.getInstance();
        Owner opponent = data.getTurn().getOpponent();

        Coordinate c;

        c = checkBoardForOwner(board, data.getTurn());
        if(c!=null) return c;

        c=checkBoardForOwner(board, opponent);
        if(c!=null) return c;

        Random rnd = new Random();
        while(true){
            int x = rnd.nextInt(3);
            int y = rnd.nextInt(3);
            c = new Coordinate(x,y);
            if(data.claimable(c)) return c;
        }
    }

    private Coordinate checkBoardForOwner(BoardPiece[][] board, Owner owner){
        DataSystem data = DataSystem.getInstance();
        for (int i = 0; i < 3; i++) {
            if((board[i][0].getOwner().equals(owner)&&board[i][1].getOwner().equals(owner)&&board[i][2].getOwner().equals(Owner.Unowned)) ||
                    (board[i][0].getOwner().equals(owner)&&board[i][1].getOwner().equals(Owner.Unowned)&&board[i][2].getOwner().equals(owner)) ||
                    (board[i][0].getOwner().equals(Owner.Unowned)&&board[i][1].getOwner().equals(owner)&&board[i][2].getOwner().equals(owner))){
                for (int j = 0; j < 3; j++) {
                    Coordinate c = new Coordinate(i,j);
                    if(BoardController.claimable(board,c)) return c;
                }
            }

            if((board[0][i].getOwner().equals(owner)&&board[1][i].getOwner().equals(owner)&&board[2][i].getOwner().equals(Owner.Unowned)) ||
                    (board[0][i].getOwner().equals(owner)&&board[1][i].getOwner().equals(Owner.Unowned)&&board[2][i].getOwner().equals(owner)) ||
                    (board[0][i].getOwner().equals(Owner.Unowned)&&board[1][i].getOwner().equals(owner)&&board[2][i].getOwner().equals(owner))){
                for (int j = 0; j < 3; j++) {
                    Coordinate c = new Coordinate(j,i);
                    if(BoardController.claimable(board,c)) return c;
                }
            }

        }

        if((board[0][0].getOwner().equals(owner)&&board[1][1].getOwner().equals(owner)&&board[2][2].getOwner().equals(Owner.Unowned)) ||
                (board[0][0].getOwner().equals(owner)&&board[1][1].getOwner().equals(Owner.Unowned)&&board[2][2].getOwner().equals(owner)) ||
                (board[0][0].getOwner().equals(Owner.Unowned)&&board[1][1].getOwner().equals(owner)&&board[2][2].getOwner().equals(owner))){
            for (int i = 0; i < 3; i++) {
                Coordinate c = new Coordinate(i,i);
                if(BoardController.claimable(board,c)) return c;
            }
        }

        if((board[0][2].getOwner().equals(owner)&&board[1][1].getOwner().equals(owner)&&board[2][0].getOwner().equals(Owner.Unowned)) ||
                (board[0][2].getOwner().equals(owner)&&board[1][1].getOwner().equals(Owner.Unowned)&&board[2][0].getOwner().equals(owner)) ||
                (board[0][2].getOwner().equals(Owner.Unowned)&&board[1][1].getOwner().equals(owner)&&board[2][0].getOwner().equals(owner))){
            for (int i = 0; i < 3; i++) {
                Coordinate c = new Coordinate(i,2-i);
                if(BoardController.claimable(board,c)) return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Medium NPC";
    }
}
