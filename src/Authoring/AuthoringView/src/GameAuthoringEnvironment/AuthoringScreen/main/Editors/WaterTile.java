package GameAuthoringEnvironment.AuthoringScreen.main.Editors;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class WaterTile extends SquareCell{
    Paint myColor = Color.BLUE;
    public WaterTile(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }
}

