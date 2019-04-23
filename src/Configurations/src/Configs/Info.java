package Configs;

public class Info {

    private String name;
    private String image;
    private double width;
    private double height;

    public Info(String name, String image, double width, double height) {
        this.name = name;
        this.image = image;
        this.width=width;
        this.height = height;
    }

    public String getImage(){
        return image;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
