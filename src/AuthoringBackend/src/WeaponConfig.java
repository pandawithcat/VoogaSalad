import java.util.function.Consumer;

public class WeaponConfig implements WeaponBehavior {

    private boolean isPathPlaceable;
    private double myHealth;

    @Override
    public void setIsPathPlaceable(boolean placeable) {
        isPathPlaceable = placeable;
    }

    @Override
    public void setHealth(int health) {
        myHealth = health;
    }

    @Override
    public void changeHealth(Consumer<Double> healthChangeMode) {
        healthChangeMode.accept(myHealth);
    }

}
