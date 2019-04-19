package Configs;

public class Info {

    private String name;
    private String image;

    public Info(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getImage(){
        return image;
    }

    public String getName(){
        return name;
    }

}
