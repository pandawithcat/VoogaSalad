package GameAuthoringEnvironment.AuthoringScreen;

import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TerrainTile extends ImageView {
    private Map<String, String> typeToImageMap;
    private ImageView imageView;
    private boolean isPath;
    private String tileImString;
    private String type;

    public TerrainTile(int x, int y, Image image, String type, Map<String, String> map){
        super(image);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(25);
        this.setFitHeight(30);
        this.imageView=new ImageView(image);
        this.type="Grass";
        isPath=false;
        tileImString="resources/grass.jpg";
//        Tooltip tooltip = new Tooltip(tileImString+""+getPathString());
//        Tooltip.install(this,tooltip);
        typeToImageMap=map;
    }
    public TerrainTile(Image image){
        super(image);
        this.imageView=new ImageView(image);
    }

    public Image getNewImage(String type){
        return new Image(this.getClass().getClassLoader().getResourceAsStream(type.toLowerCase() + ".jpg"));
    }



    public void changeImage(String myType){
//        if(type.equals("Grass")){
//            changeToGrass();
//        }
//        else if(type.equals("Water")){
//            changeToWater();
//        }
//        else if(type.equals("Dirt")){
//            changeToDirt();
//        }
        try {
            this.setImage(new Image(new FileInputStream(typeToImageMap.get(myType))));
        }
        catch(FileNotFoundException f){
            System.out.println(f);
        }
        if(!type.equals("Grass")){
            setPath();
        }
        type=myType;
        tileImString=typeToImageMap.get(myType);



    }

    public void changeToWater(){
        try {
            this.setImage(new Image(new FileInputStream("resources/water.jpg")));
        }
        catch(FileNotFoundException f){
            System.out.println(f);

        }
        tileImString="water.jpg";
        isPath=true;
        type="Water";

    }
    public void changeToDirt(){
        try {
            this.setImage(new Image(new FileInputStream("resources/dirt.jpg")));
        }
        catch(FileNotFoundException f){
            System.out.println(f);

        }
        tileImString="dirt.jpg";
        isPath=true;
        type="Dirt";
    }



    public void changeToGrass() {
        try {

            this.setImage(new Image(new FileInputStream("resources/grass.jpg")));
        } catch (FileNotFoundException f) {
            //TODO Exception! NO such file found
        }
        tileImString = "grass.jpg";
        isPath=false;
        type="Grass";
    }

    public ImageView getImageView(){
        return imageView;
    }

    public String getTileImString(){
        return tileImString;
    }
    public boolean getIsPath(){
        return isPath;
    }
    public void setPath(){
        isPath=true;
    }
    public String getPathString(){
        if(this.isPath){
            return "Is a path";
        }
        else{
            return "Is not a path";
        }
    }
    public String getType(){
        return type;
    }

}


