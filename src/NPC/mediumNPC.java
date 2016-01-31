package NPC;

import main.DataSystem;
import main.Owner;

import java.util.Random;

/**
 * Created by puebe on 31.01.2016.
 */
public class mediumNPC implements NPC {
    @Override
    public void nextTurn() {
        DataSystem data = DataSystem.getInstance();
        Owner opponent = data.getTurn().getOpponent();

        if(checkBoardForOwner(data.getTurn())) return;
        if(checkBoardForOwner(opponent)) return;

        Random rnd = new Random();
        while(true){
            int x = rnd.nextInt(3);
            int y = rnd.nextInt(3);
            if(data.claim(x,y)) return;
        }
    }

    private boolean checkBoardForOwner(Owner owner){
        DataSystem data = DataSystem.getInstance();
        for (int i = 0; i < 3; i++) {
            if((data.board[i][0].getOwner().equals(owner)&&data.board[i][1].getOwner().equals(owner)&&data.board[i][2].getOwner().equals(Owner.Unowned)) ||
                    (data.board[i][0].getOwner().equals(owner)&&data.board[i][1].getOwner().equals(Owner.Unowned)&&data.board[i][2].getOwner().equals(owner)) ||
                    (data.board[i][0].getOwner().equals(Owner.Unowned)&&data.board[i][1].getOwner().equals(owner)&&data.board[i][2].getOwner().equals(owner))){
                for (int j = 0; j < 3; j++) {
                    if(data.claim(i,j)) return true;
                }
            }

            if((data.board[0][i].getOwner().equals(owner)&&data.board[1][i].getOwner().equals(owner)&&data.board[2][i].getOwner().equals(Owner.Unowned)) ||
                    (data.board[0][i].getOwner().equals(owner)&&data.board[1][i].getOwner().equals(Owner.Unowned)&&data.board[2][i].getOwner().equals(owner)) ||
                    (data.board[0][i].getOwner().equals(Owner.Unowned)&&data.board[1][i].getOwner().equals(owner)&&data.board[2][i].getOwner().equals(owner))){
                for (int j = 0; j < 3; j++) {
                    if(data.claim(j,i)) return true;
                }
            }

        }

        if((data.board[0][0].getOwner().equals(owner)&&data.board[1][1].getOwner().equals(owner)&&data.board[2][2].getOwner().equals(Owner.Unowned)) ||
                (data.board[0][0].getOwner().equals(owner)&&data.board[1][1].getOwner().equals(Owner.Unowned)&&data.board[2][2].getOwner().equals(owner)) ||
                (data.board[0][0].getOwner().equals(Owner.Unowned)&&data.board[1][1].getOwner().equals(owner)&&data.board[2][2].getOwner().equals(owner))){
            for (int i = 0; i < 3; i++) {
                if(data.claim(i,i)) return true;
            }
        }

        if((data.board[0][2].getOwner().equals(owner)&&data.board[1][1].getOwner().equals(owner)&&data.board[2][0].getOwner().equals(Owner.Unowned)) ||
                (data.board[0][2].getOwner().equals(owner)&&data.board[1][1].getOwner().equals(Owner.Unowned)&&data.board[2][0].getOwner().equals(owner)) ||
                (data.board[0][2].getOwner().equals(Owner.Unowned)&&data.board[1][1].getOwner().equals(owner)&&data.board[2][0].getOwner().equals(owner))){
            for (int i = 0; i < 3; i++) {
                if (data.claim(i,2-i)) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Medium NPC";
    }
}
