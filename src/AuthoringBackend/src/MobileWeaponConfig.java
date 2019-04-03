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


}
