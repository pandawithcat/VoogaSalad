import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GamePlayLeftSide extends VBox {
    public GamePlayMap myMap;
    public GamePlaySettingsBar mySettings;
    public static final int padding = 10;

    public GamePlayLeftSide(double width, double height){
        setPrefWidth(width);
        setPrefHeight(height);
        myMap = new GamePlayMap(width,height * 9 / 10);
        mySettings = new GamePlaySettingsBar(width,height / 10);
        this.getChildren().addAll(myMap, mySettings);
        setPadding(new Insets(padding,padding,padding,padding));
        setSpacing(padding);
    }
}
