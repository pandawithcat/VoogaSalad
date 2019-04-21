package Configs;

import ActiveConfigs.ActiveLevel;
import Configs.GamePackage.Game;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    private double heightInGridUnits;
    private double widthInGridUnits;


    public MapFeature(int gridXPos, int gridYPos, double displayDirection, View view) {
        setImage(view);
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        setGridPos(gridXPos,gridYPos,displayDirection);
        displayState = DisplayState.NEW;

    }

    public MapFeature(double pixelXPos, double pixelYPos, double direction, View view) {
        setImage(view);
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        setPixelPos(pixelXPos,pixelYPos,direction);
        displayState = DisplayState.NEW;
    }

    private void setImage(View view) {
        try {
            myImageView = new TransferImageView(new Image(new FileInputStream("resources/"+view.getImage())));
            myImageView.setFitHeight(view.getHeight());
            myImageView.setFitWidth(view.getWidth());
        }
        catch (FileNotFoundException e) {
            throw new IllegalStateException();
        }
    }

    public double getPixelXPos() {
        return myImageView.getX();
    }

    public double getPixelYPos() {
        return myImageView.getY();
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
        myImageView.setX(pixelXPos);
        myImageView.setY(pixelYPos);
        gridXPos = (int) (pixelXPos*Game.gridPixelWidth/widthInGridUnits);
        gridYPos = (int) (pixelYPos*Game.gridPixelHeight/heightInGridUnits);
    }

    private void setPixelPos(double pixelXPos, double pixelYPos, double direction) {
        this.pixelYPos = pixelYPos;
        this.pixelXPos = pixelXPos;
        this.displayDirection = direction;
        this.gridXPos = (int) (pixelXPos/(widthInGridUnits/Game.gridPixelWidth));
        this.gridYPos = (int) (pixelYPos/(heightInGridUnits/Game.gridPixelHeight));
        setImageView(pixelXPos,pixelYPos,direction);
    }

    private void setImageView(double pixelXPos, double pixelYPos, double direction) {
        myImageView.setX(pixelXPos);
        myImageView.setY(pixelYPos);
        myImageView.setRotate(direction);
    }

    public void setGridPos(int gridXPos, int gridYPos, double direction) {
        this.gridXPos = gridXPos;
        this.gridYPos = gridYPos;
        this.displayDirection = direction;
        pixelXPos = (Game.gridPixelWidth/widthInGridUnits)*gridXPos;
        pixelYPos = (Game.gridPixelHeight/heightInGridUnits)*gridYPos;
        System.out.println("HIDNFNLDJFLKJL");
        System.out.println(pixelXPos);
        System.out.println(pixelYPos);
        setImageView(pixelXPos,pixelYPos,direction);
    }


    public TransferImageView getImageView() {
//        myImageView.setX(pixelXPos)
        myImageView.setX(pixelXPos);
        myImageView.setY(pixelYPos);
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
