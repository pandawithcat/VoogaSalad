import java.util.function.Consumer;

public interface WeaponBehavior {
    void setIsPathPlaceable(boolean placeable);
    void setHealth(int health);
    void changeHealth(Consumer<Double> healthChangeMode);
}
