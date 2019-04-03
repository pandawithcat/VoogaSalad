package weaponsConfigPackage;

import java.util.List;
import java.util.function.Consumer;

public class WeaponConfig {


    List<Behavior> behaviors;
    private boolean isPathPlaceable;
    private double myHealth;

    public void setIsPathPlaceable(boolean placeable) {
        isPathPlaceable = placeable;
    }

    public void setHealth(int health) {
        myHealth = health;
    }

    public void changeHealth(Consumer<Double> healthChangeMode) {
        healthChangeMode.accept(myHealth);
    }
}