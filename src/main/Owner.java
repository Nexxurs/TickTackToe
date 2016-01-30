package main;

/**
 * Created by puebe on 30.01.2016.
 */
public enum Owner {
    Player1("X"),
    Player2("O"),
    Unowned(" ");

    private String symbol;
    Owner(String symbol){
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
