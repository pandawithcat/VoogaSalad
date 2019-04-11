package GameAuthoringEnvironment.AuthoringScreen.main.Editors;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public abstract class SquareCell extends Rectangle {
    Paint myColor;

    public SquareCell(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
        this.setStroke(Color.BLACK);
    }

    public SquareCell(){
        this.setFill(myColor);
        this.setStroke(Color.BLACK);
    }
}

