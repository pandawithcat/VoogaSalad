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
    private int gridXSize;
    private int gridYSize;
    private double pixelXPos;
    private double pixelYPos;
    private double paneWidth;
    private double paneHeight;
    private double displayDirection;
    private double trigDirection;
    @XStreamOmitField
    private TransferImageView myImageView;
    private View view;
    private DisplayState displayState;
    private double heightInGridUnits;
    private double widthInGridUnits;



    @Deprecated
    public MapFeature(int gridXPos, int gridYPos, double displayDirection, View view) {
        setImage(view);
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        setGridPos(gridXPos,gridYPos,displayDirection);
        displayState = DisplayState.NEW;
    }

    public MapFeature(int gridXPos, int gridYPos, double displayDirection, View view, double paneWidth, double paneHeight,int gridXSize, int gridYSize) {
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.gridXSize = gridXSize;
        this.gridYSize = gridYSize;
        displayState = DisplayState.NEW;
        setImage(view);
        setGridPos(gridXPos, gridYPos, displayDirection);
    }

    @Deprecated
    public MapFeature(double pixelXPos, double pixelYPos, double direction, View view) {
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        setImage(view);
        setPixelPos(pixelXPos,pixelYPos,direction);
        displayState = DisplayState.NEW;
    }

    public MapFeature(double pixelXPos, double pixelYPos, double direction, View view, double paneWidth, double paneHeight,int gridXSize, int gridYSize) {
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        displayState = DisplayState.NEW;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.gridXSize = gridXSize;
        this.gridYSize = gridYSize;
        setImage(view);
        setPixelPos(pixelXPos,pixelYPos,direction);
    }

    private void setImage(View view) throws IllegalStateException {
        try {
            myImageView = new TransferImageView(new Image(new FileInputStream("resources/"+view.getImage())));
            myImageView.setFitHeight(paneHeight/gridYSize*heightInGridUnits);
            myImageView.setFitWidth(paneWidth/gridXSize* widthInGridUnits);
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

    //TODO: this needs to be changed once enemy is moving based on pixel
    public boolean isOutOfBounds(int x, int y) {
        return (x<0||x>gridXSize||y<0||y>gridYPos);
    }

    public boolean isOutOfBoundsRelative(double deltaX, double deltaY) {
        double currentX = deltaX+pixelXPos;
        double currentY = deltaX+pixelXPos;
        return (currentX>paneWidth||currentX<0||currentY>paneHeight||currentY<0);
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
        pixelXPos = (paneWidth/gridXSize)*gridXPos;
        pixelYPos = (paneHeight/gridYSize)*gridYPos;
        setImageView(pixelXPos,pixelYPos,direction);
    }


    public TransferImageView getImageView() {
//        myImageView.setX(pixelXPos)
        setImageView(pixelXPos, pixelYPos, displayDirection);
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
