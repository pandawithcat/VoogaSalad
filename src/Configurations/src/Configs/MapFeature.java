package Configs;

public class MapFeature {
    private int gridXPos;
    private int gridYPos;
    private double pixelXPos;
    private double pixelYPos;

    public MapFeature(int gridXPos, int gridYPos) {
        setGridPos(gridXPos,gridYPos);


    }

    public void setPixelPos(double x, double y) {
        pixelXPos = x;
        pixelYPos = y;
        //TODO: CALCULATE GRID POSITION FROM THIS
    }

    public void setGridPos(int gridXPos, int gridYPos) {
        this.gridXPos = gridXPos;
        this.gridYPos = gridYPos;
        //TODO: CALCULATE PIXEL POSITION FROM THIS

    }



}
