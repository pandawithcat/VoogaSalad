package GameAuthoringEnvironment.AuthoringScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class TerrainTile extends ImageView {
    ImageView imageView;
    boolean isPath;

    public TerrainTile(double x, double y, Image image, String type){
        super(image);
        this.setX(x);
        this.setY(y);
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.imageView=new ImageView(image);
        isPath=false;


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
        this.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("grass.jpg")));
        isPath=false;
    }

    public void changeToWater(){
        this.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("water.jpg")));
        isPath=true;

    }
    public void changeToDirt(){
        this.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream("dirt.jpg")));
        isPath=true;
    }

}
