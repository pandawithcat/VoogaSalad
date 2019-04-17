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
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.imageView=new ImageView(image);
        isPath=false;
        tileImString= "grass.jpg";


//        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                System.out.println("HELLO I AM COL");
//                imageView=new ImageView(getNewImage(type));
//            }
//        });
    }

    public Image getNewImage(String type){
        if(type.equals("Grass")){
            return new Image(this.getClass().getClassLoader().getResourceAsStream("grass.jpg"));
        }
        else if(type.equals("Water")){
            return new Image(this.getClass().getClassLoader().getResourceAsStream("water.jpg"));

        }
        else if(type.equals("Dirt")){
            return new Image(this.getClass().getClassLoader().getResourceAsStream("dirt.jpg"));
        }
        else{
            return null;
        }
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

    public void changeToGrass(){
        try {

            this.setImage(new Image(new FileInputStream("resources/grass.jpg")));
        }
        catch(FileNotFoundException f){
            System.out.println(f);
        }
        isPath=false;
        tileImString="grass.jpg";
    }

    public void changeToWater(){
        try {
            this.setImage(new Image(new FileInputStream("resources/water.jpg")));
        }
        catch(FileNotFoundException f){
            System.out.println(f);
        }
        isPath=true;
        tileImString="water.jpg";

    }
    public void changeToDirt(){
        try {
            this.setImage(new Image(new FileInputStream("resources/dirt.jpg")));
        }
        catch(FileNotFoundException f){
            System.out.println(f);
        }
        isPath=true;
        tileImString="dirt.jpg";
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


