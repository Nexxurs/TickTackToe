package NPC;

import main.DataSystem;
import java.util.Random;

/**
 * Created by puebe on 31.01.2016.
 */
public class easyNPC implements NPC {
    @Override
    public void nextTurn() {
        DataSystem data = DataSystem.getInstance();
        Random rnd = new Random();
        while(true){
            int x = rnd.nextInt(3);
            int y = rnd.nextInt(3);
            if(data.claim(x,y)) return;
        }
    }

    @Override
    public String toString() {
        return "Easy NPC";
    }
}
