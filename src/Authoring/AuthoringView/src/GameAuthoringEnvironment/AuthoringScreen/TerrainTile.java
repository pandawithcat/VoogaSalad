package GameAuthoringEnvironment.AuthoringScreen;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TerrainTile extends ImageView {
    ImageView imageView;
    boolean isPath;
    String tileImString;

    public TerrainTile(int x, int y, Image image, String type){
        super(image);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(25);
        this.setFitHeight(30);
        this.imageView=new ImageView(image);
        isPath=false;
        tileImString="grass.jpg";

    }

    public Image getNewImage(String type){
        return new Image(this.getClass().getClassLoader().getResourceAsStream(type.toLowerCase() + ".jpg"));
    }



    public void changeImage(String type){
        if(type.equals("Grass")){
            changeToGrass();
        }
        else if(type.equals("Water")){
            changeToWater();
        }
        else if(type.equals("Dirt")){
            changeToDirt();
        }


    }

    public void changeToWater(){
        try {
            this.setImage(new Image(new FileInputStream("resources/water.jpg")));
        }
        catch(FileNotFoundException f){
            System.out.println(f);

        }
        tileImString="resources/water.jpg";
        isPath=true;

    }
    public void changeToDirt(){
        try {
            this.setImage(new Image(new FileInputStream("resources/dirt.jpg")));
        }
        catch(FileNotFoundException f){
            System.out.println(f);

        }
        tileImString="resources/dirt.jpg";
        isPath=true;
    }



    public void changeToGrass() {
        try {

            this.setImage(new Image(new FileInputStream("resources/" + "grass" + ".jpg")));
        } catch (FileNotFoundException f) {
            //TODO Exception! NO such file found
        }
        tileImString = "resources/grass.jpg";
        isPath=false;
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

}


