package NPC;

import main.BoardPiece;
import main.Coordinate;

/**
 * Created by puebe on 31.01.2016.
 */
public interface NPC {
    Coordinate nextTurn(BoardPiece[][] board);
}
