package GameAuthoringEnvironment.AuthoringScreen.Map;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GrassTile extends SquareCell{
    Paint myColor = Color.GREEN;
    public GrassTile(double x, double y, double width, double height){
        super(x,y,width,height);
        this.setFill(myColor);
    }
}
