package Configs;

import ActiveConfigs.ActiveLevel;
import Configs.GamePackage.Game;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.image.Image;

import java.io.FileInputStream;

public class MapFeature {

    private int gridXPos;
    private int gridYPos;
    private double pixelXPos;
    private double pixelYPos;
    private double displayDirection;
    private double trigDirection;
    @XStreamOmitField
    private TransferImageView myImageView;
    private View view;
    private DisplayState displayState;
    private int gridHeight;
    private int gridWidth;


    public MapFeature(int gridXPos, int gridYPos, double displayDirection, View view, int gridHeight, int gridWeight) {
        this.view = view;
        try {
            System.out.println(view.getImage());
            FileInputStream fileInputStream = new FileInputStream("resources/" + view.getImage());
            myImageView = new TransferImageView(new Image(fileInputStream));
        }catch (Exception e) {
        }
        setGridPos(gridXPos,gridYPos,displayDirection);
        displayState = DisplayState.NEW;
        myImageView.setFitHeight(view.getHeight());
        myImageView.setFitWidth(view.getWidth());
        this.gridHeight = gridHeight;
        this.gridWidth = gridWeight;
        }

    public MapFeature(double pixelXPos, double pixelYPos, double direction, View view, int gridHeight, int gridWeight) {
        this.view = view;
        myImageView = new TransferImageView(new Image(view.getImage()));
        myImageView.setFitHeight(view.getHeight());
        myImageView.setFitWidth(view.getWidth());
        setPixelPos(pixelXPos,pixelYPos,direction);
        displayState = DisplayState.NEW;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWeight;
    }

    public double getPixelXPos() {
        return myImageView.getTranslateX();
    }

    public double getPixelYPos() {
        return myImageView.getTranslateY();
    }

    public int getGridXPos() {
        return gridXPos;
    }

    public int getGridYPos() {
        return gridYPos;
    }

    public void moveRelatively(double deltaPixelX, double deltaPixelY) {
        pixelXPos+=deltaPixelX;
        pixelYPos+=deltaPixelY;
        myImageView.setTranslateX(pixelXPos);
        myImageView.setTranslateY(pixelYPos);
        gridXPos = (int) (pixelXPos*Game.gridPixelWidth/gridWidth);
        gridYPos = (int) (pixelYPos*Game.gridPixelHeight/gridHeight);
    }

    private void setPixelPos(double pixelXPos, double pixelYPos, double direction) {
        this.pixelYPos = pixelYPos;
        this.pixelXPos = pixelXPos;
        this.displayDirection = direction;
        this.gridXPos = (int) (pixelXPos/(gridWidth/Game.gridPixelWidth));
        this.gridYPos = (int) (pixelYPos/(gridHeight/Game.gridPixelHeight));
        setImageView(pixelXPos,pixelYPos,displayDirection);
    }

    private void setImageView(double pixelXPos, double pixelYPos, double direction) {
        myImageView.setTranslateX(pixelXPos);
        myImageView.setTranslateY(pixelYPos);
        myImageView.setRotate(direction);
    }

    public void setGridPos(int gridXPos, int gridYPos, double direction) {
        this.gridXPos = gridXPos;
        this.gridYPos = gridYPos;
        this.displayDirection = direction;
        pixelXPos = (Game.gridPixelWidth/gridWidth)*gridXPos;
        pixelYPos = (Game.gridPixelHeight/gridWidth)*gridYPos;
        setImageView(pixelXPos,pixelYPos,direction);
    }



    public TransferImageView getImageView() {
        myImageView.setTranslateX(pixelXPos);
        myImageView.setTranslateY(pixelYPos);
        myImageView.setRotate(displayDirection);
        return myImageView;
    }

    public double getDirection() {
        return myImageView.getRotate();
    }

    public double getTrigDirection() {
        trigDirection = (360-getDirection()+90)%360;
        return trigDirection;
    }

    public void setTrigDirection(double trigDirection) {
        this.trigDirection = trigDirection;
        myImageView.setRotate((-trigDirection+450)%360);

    }

    public DisplayState getDisplayState() {
        return displayState;
    }

    public void setDisplayState(DisplayState displayState) {
        this.displayState = displayState;
    }
}
