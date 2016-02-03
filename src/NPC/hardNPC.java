package NPC;

import main.BoardPiece;
import main.Coordinate;

/**
 * Created by puebe on 31.01.2016.
 */
public class hardNPC implements NPC {
    @Override
    public Coordinate nextTurn(BoardPiece[][] board) {
        return null;
    }

    @Override
    public String toString() {
        return "Hard NPC";
    }
}
