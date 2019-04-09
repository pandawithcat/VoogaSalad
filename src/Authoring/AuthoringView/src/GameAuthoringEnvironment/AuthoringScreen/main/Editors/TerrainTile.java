package GameAuthoringEnvironment.AuthoringScreen.main.Editors;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TerrainTile extends ImageView {
    ImageView imageView;
    String type;

    public TerrainTile(double x, double y, Image image){
        super(image);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.imageView=new ImageView(image);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //imageView=new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream()))
            }
        });
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



//    public void changeImage(String type){
//        if(type.equals("Grass")){
//            changeToGrass();
//        }
//        else if(type.equals("Water")){
//            changeToWater();
//        }
//        else if(type.equals("Dirt")){
//            changeToDirt();
//        }
//
//    }
//
//    public void changeToGrass(){
//        this.imageView=new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("grass.jpg")));
//    }
//
//    public void changeToWater(){
//        this.imageView=new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("water.jpg")));
//
//    }
//    public void changeToDirt(){
//        this.imageView=new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("dirt.jpg")));
//    }

}
