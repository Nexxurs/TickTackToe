package main;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Created by puebe on 31.01.2016.
 */
public class BoardPiece extends StackPane {
    private ObjectProperty<Owner> ownerProperty = new SimpleObjectProperty<>(Owner.Unowned);
    private Label label;
    private int x,y;

    public BoardPiece(int x,int y){
        super();
        label = new Label();
        label.textProperty().bind(Bindings.select(ownerProperty,"Symbol"));
        label.setFont(Font.font(50));
        this.getChildren().add(label);
        this.x =x;
        this.y=y;
    }

    public BoardPiece(Owner owner,int x, int y) {
        this(x,y);
        this.ownerProperty = new SimpleObjectProperty<>(owner);

    }

    public Owner getOwner() {
        return ownerProperty.get();
    }

    public ObjectProperty<Owner> ownerProperty() {
        return ownerProperty;
    }

    private void setOwner(Owner owner){
        ownerProperty.set(owner);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
