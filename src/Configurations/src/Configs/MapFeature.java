package Configs;

import ActiveConfigs.ActiveEnemy;
import ActiveConfigs.ActiveWeapon;
import ActiveConfigs.Cell;
import Configs.GamePackage.Game;
import ExternalAPIs.AuthoringData;
import ExternalAPIs.Data;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
    private transient TransferImageView myImageView;
    private DisplayState displayState;
    private double heightInGridUnits;
    private double widthInGridUnits;
    private MapFeaturable parent;

    @Deprecated
    public MapFeature(int gridXPos, int gridYPos, double displayDirection, View view) {
        setImage(view);
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        setGridPos(gridXPos,gridYPos,displayDirection);
        displayState = DisplayState.NEW;
    }

    @Deprecated
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

    public MapFeature(int gridXPos, int gridYPos, double displayDirection, View view, double paneWidth, double paneHeight,int gridXSize, int gridYSize, MapFeaturable parent) {
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.gridXSize = gridXSize;
        this.gridYSize = gridYSize;
        displayState = DisplayState.NEW;
        this.gridYPos = gridYPos;
        this.gridXPos = gridXPos;
        this.parent = parent;
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

    @Deprecated
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

    public MapFeature(double pixelXPos, double pixelYPos, double direction, View view, double paneWidth, double paneHeight,int gridXSize, int gridYSize, MapFeaturable parent) {
        this.heightInGridUnits = view.getHeight();
        this.widthInGridUnits = view.getWidth();
        displayState = DisplayState.NEW;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.gridXSize = gridXSize;
        this.gridYSize = gridYSize;
        this.gridXPos = (int) (pixelXPos*(gridXSize/paneWidth));
        this.gridYPos = (int) (pixelYPos*(gridYSize/paneHeight));
        this.parent = parent;
        setImage(view);
        setPixelPos(pixelXPos,pixelYPos,direction);
    }

    private void setImage(View view) throws IllegalStateException {
        Game game = parent.getActiveLevel().getGame();
        int imageId = view.getImage();
        Image image;
        System.out.println("HERE:" + imageId);
        if (game.hasImage(imageId)) image = game.getImage(imageId);
        else image = Data.getImageStatic(imageId);
        myImageView = new TransferImageView(image);
        myImageView.setFitHeight(paneHeight/gridYSize*heightInGridUnits);
        myImageView.setFitWidth(paneWidth/gridXSize* widthInGridUnits);

    }

    public double getPixelHeight(){
        return myImageView.getFitHeight();
    }

    public double getPixelWidth(){
        return myImageView.getFitWidth();
    }

    private void setInCell(int yPos, int xPos) {
        for(int x = 0 ;x<widthInGridUnits;x++) {
            for(int y = 0; y<heightInGridUnits;y++) {
                Cell cell = parent.getActiveLevel().getMyGrid()[xPos+x][yPos+y];
                if(parent instanceof ActiveEnemy) {
                    cell.addEnemy((ActiveEnemy) parent);

                }
                else if (parent instanceof ActiveWeapon) {
                    cell.setWeapon((ActiveWeapon) parent);
                }
            }
        }
    }

    private void removeFromCell() {
        for(int x = 0 ;x<widthInGridUnits;x++) {
            for(int y = 0; y<heightInGridUnits;y++) {
                Cell cell = parent.getActiveLevel().getMyGrid()[gridXPos+x][gridYPos+y];
                if(parent instanceof ActiveWeapon) {
                    cell.removeWeapon();
                }
                else if (parent instanceof ActiveEnemy) {
                    cell.removeEnemy((ActiveEnemy) parent);
                }
            }
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
        setPixelPos(pixelXPos, pixelYPos, displayDirection);
    }

    private boolean isOutOfBounds(int x, int y) {
        return x<0||x>=gridXSize||y<0||y>=gridYSize;
    }

    private boolean isOutOfBoundsPixel(double xPixel, double yPixel) {
        return (xPixel>paneWidth||xPixel<0||yPixel>paneHeight||yPixel<0);
    }


    private void setPixelPos(double pixelXPos, double pixelYPos, double direction) {
        removeFromCell();
        if(isOutOfBoundsPixel(pixelXPos,pixelYPos)) displayState = DisplayState.DIED;
        else {
            this.pixelYPos = pixelYPos;
            this.pixelXPos = pixelXPos;
            this.displayDirection = direction;
            this.gridXPos = (int) (pixelXPos*(gridXSize/paneWidth));
            this.gridYPos = (int) (pixelYPos*(gridYSize/paneHeight));
            setImageView(pixelXPos,pixelYPos,direction);
            setInCell(gridYPos, gridXPos);
        }
    }

    private void setImageView(double pixelXPos, double pixelYPos, double direction) {
        myImageView.setX(pixelXPos);
        myImageView.setY(pixelYPos);
        myImageView.setRotate(direction);
    }

    public void setGridPos(int gridXPos, int gridYPos, double direction) {
        removeFromCell();
        if(isOutOfBounds(gridXPos,gridYPos)) {
            displayState = DisplayState.DIED;
        }
        else {
            this.gridXPos = gridXPos;
            this.gridYPos = gridYPos;
            this.displayDirection = direction;
            pixelXPos = (paneWidth/gridXSize)*gridXPos;
            pixelYPos = (paneHeight/gridYSize)*gridYPos;
            setImageView(pixelXPos,pixelYPos,direction);
            setInCell(gridYPos, gridXPos);
        }
    }


    public TransferImageView getImageView() {
        setImageView(pixelXPos, pixelYPos, displayDirection);
        return myImageView;
    }

    public double getDirection() {
        return myImageView.getRotate();
    }

    public double getTrigDirection() {
        trigDirection = (360-getDirection()+90)%360;
        System.out.println(trigDirection);
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
        if(displayState == DisplayState.DIED) {
            removeFromCell();
        }
        this.displayState = displayState;
    }
}
