package Configs;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


public class View implements Configurable {
    private Configuration myConfiguration;

    @Configure
    private String myLabel;
    @Configure
    private String imagePath;
//    @Configure
//    private String imageName;
    @Configure
    private double width;
    @Configure
    private double height;
    private Configurable myConfigurable;


    public View(Configurable configurableParent) {
        myConfigurable = configurableParent;
        myConfiguration = new Configuration(this);
    }

    //this constructor is for the special case for the terrain blocks in the map
    public View(String file, double width, double height) {
        imagePath = file;
        this.width = width;
        this.height = height;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }


    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    //    public View(String imageName, int x, int y, int width, int height){
//        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName));//classloader probably has to be set
//        ImageView myImageView = new TransferImageView(image);
//        myImageView.setX(x);
//        myImageView.setY(y);
//        myImageView.setFitWidth(width);
//        myImageView.setFitWidth(height);
//        myImageView.getFitWidth();
//    }

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public String getImage() {
        return imagePath;
    }




}
