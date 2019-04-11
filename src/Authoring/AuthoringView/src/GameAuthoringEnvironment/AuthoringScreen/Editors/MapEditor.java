package GameAuthoringEnvironment.AuthoringScreen.Editors;

import GameAuthoringEnvironment.AuthoringScreen.Screen;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class MapEditor extends Screen {

    public MapEditor(Group myRoot, int width, int height, String moduleName){
        super(myRoot, width, height, moduleName, true);
        setLayout(300, 100);
        setContentColor(Color.LIGHTBLUE);
    }

    public void setContent(){
    }

}
