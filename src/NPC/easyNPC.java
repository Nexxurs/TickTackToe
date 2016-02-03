package NPC;

import main.BoardPiece;
import main.Coordinate;
import main.DataSystem;
import java.util.Random;

/**
 * Created by puebe on 31.01.2016.
 */
public class easyNPC implements NPC {
    @Override
    public Coordinate nextTurn(BoardPiece[][] board) {
        DataSystem data = DataSystem.getInstance();
        Random rnd = new Random();
        while(true){
            int x = rnd.nextInt(3);
            int y = rnd.nextInt(3);
            Coordinate currCoordinate = new Coordinate(x,y);
            if(data.claimable(currCoordinate)) return currCoordinate;
        }
    }

    @Override
    public String toString() {
        return "Easy NPC";
    }
}
