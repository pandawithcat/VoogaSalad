package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Module;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MapEditor extends Module {

    public MapEditor(int width, int height, String moduleName){
        super(width, height, moduleName);
        setColor(Color.LIGHTBLUE);
    }

}
