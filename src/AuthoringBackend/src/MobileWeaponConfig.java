import weaponsConfigPackage.WeaponConfig;

public class MobileWeaponConfig extends WeaponConfig implements MobileBehavior {
    private double myVelocity;
    private Motion myMotionType;
    private String imageFile;
    private int width;
    private int height;

    @Override
    public void setVelocity(double velocity) {
        myVelocity = velocity;
    }

    @Override
    public void setMotion(Motion type) {
        myMotionType = type;
    }

//    @Override
//    public void setImage(String imageFile, int width, int height) {
//        this.imageFile = imageFile;
//        this.width = width;
//        this.height = height;
//
//    }

}
