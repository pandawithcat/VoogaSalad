package Configs;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.image.Image;

public class MapFeature {

    private int gridXPos;
    private int gridYPos;
//    private double pixelXPos;
//    private double pixelYPos;
//    private double displayDirection;
    private double trigDirection;
    @XStreamOmitField
    private TransferImageView myImageView;
    private View view;
    private DisplayState displayState;


    public MapFeature(int gridXPos, int gridYPos, double displayDirection, View view) {
        this.view = view;
        myImageView = new TransferImageView(new Image(view.getImage()));
        setGridPos(gridXPos,gridYPos,displayDirection);
        displayState = DisplayState.NEW;
    }

    public MapFeature(double pixelXPos, double pixelYPos, double direction, View view) {
        this.view = view;
        myImageView = new TransferImageView(new Image(view.getImage()));
        myImageView.setFitHeight(view.getHeight());
        myImageView.setFitWidth(view.getWidth());
        setImageView(pixelXPos,pixelYPos, direction);
        displayState = DisplayState.NEW;
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
//        pixelXPos+=deltaPixelX;
//        pixelYPos+=deltaPixelY;
        double newX = getPixelXPos()+deltaPixelX;
        double newY = getPixelYPos()+deltaPixelY;
        myImageView.setTranslateX(newX);
        myImageView.setTranslateY(newY);

        gridXPos = Math.floorDiv(newX, view.width);


        //TODO; calculate grid position
    }

    private void setImageView(double pixelXPos, double pixelYPos, double direction) {
        myImageView.setTranslateX(pixelXPos);
        myImageView.setTranslateY(pixelYPos);
        myImageView.setRotate(direction);
    }

    public void setGridPos(int gridXPos, int gridYPos, double direction) {
        this.gridXPos = gridXPos;
        this.gridYPos = gridYPos;

        //TODO: CALCULATE PIXEL POSITION FROM THIS
        //set imageview x and y in this

    }

    public TransferImageView getImageView() {
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
