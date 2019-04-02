package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import GUI.GameAuthoringEnvironment.AuthoringComponents.CloseButton;
import GUI.GameAuthoringEnvironment.AuthoringScreen.AuthoringVisualization;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

    private String colorToHex(Paint color) {
        String hex1;
        String hex2;

        hex1 = Integer.toHexString(color.hashCode()).toUpperCase();

        switch (hex1.length()) {
            case 2:
                hex2 = "000000";
                break;
            case 3:
                hex2 = String.format("00000%s", hex1.substring(0,1));
                break;
            case 4:
                hex2 = String.format("0000%s", hex1.substring(0,2));
                break;
            case 5:
                hex2 = String.format("000%s", hex1.substring(0,3));
                break;
            case 6:
                hex2 = String.format("00%s", hex1.substring(0,4));
                break;
            case 7:
                hex2 = String.format("0%s", hex1.substring(0,5));
                break;
            default:
                hex2 = hex1.substring(0, 6);
        }
        return hex2;
    }

    public void setColor(Paint color) {
        String hexColor = colorToHex(color);
        content.setStyle("-fx-background-color: #" + hexColor);
    }

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
