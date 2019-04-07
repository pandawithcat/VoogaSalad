package GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import GameAuthoringEnvironment.AuthoringScreen.Modules.Module;

public class MapEditor extends Module {

    public MapEditor(Group myRoot, int width, int height, String moduleName){
        super(myRoot, width, height, moduleName, true);
        setLayout(300, 100);
        setContentColor(Color.LIGHTBLUE);
    }

    public void setContent(){

    }

}
