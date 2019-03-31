package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import GUI.GameAuthoringEnvironment.AuthoringComponents.CloseButton;
import GUI.GameAuthoringEnvironment.AuthoringScreen.AuthoringVisualization;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class Module {

    private final VBox module;
    private Pane content, toolbarPane;
    private int moduleWidth;
    private int moduleHeight;
    private CloseButton close;
    private final double toolbarHeight = 20.0;

    public Module(int width, int height, String moduleName) {

        this.module = new VBox();
        this.content = new Pane();
        this.moduleWidth = width;
        this.moduleHeight = height;

        module.setMinSize(moduleWidth, moduleHeight);
        module.setMaxSize(moduleWidth, moduleHeight);
        module.setId("module");
        addToolbar(moduleName);
        content.prefHeightProperty().bind(module.heightProperty());
        content.prefWidthProperty().bind(module.widthProperty());
        module.getChildren().addAll(content, toolbarPane);
    }


    protected void addToolbar(String moduleName) {
        this.toolbarPane = new Pane();
        System.out.println(moduleWidth);
        toolbarPane.setPrefWidth(moduleWidth);
        toolbarPane.setMinHeight(toolbarHeight);
        toolbarPane.setId(moduleName);
        Text title = new Text(moduleName);
        title.setLayoutY(500);
        title.setLayoutX(100);
        title.setVisible(true);
        System.out.println("title exists");

        /*close = new CloseButton();
        close.getButton().setLayoutX(0);
        close.getButton().setLayoutY(-2);
        System.out.println("Ran");*/
        toolbarPane.getChildren().addAll(title);
    }

    //public abstract void setContent();

    public void setBackGroundColor(String style){
        module.setStyle(style);
    }

    public VBox getContent() {
        return module;
    }

    protected Pane getPane() {
        return content;
    }

    protected int getModuleWidth() {
        return moduleWidth;
    }

    protected int getModuleHeight() { return moduleHeight; }


}
