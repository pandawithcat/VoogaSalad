package Configs.weaponsConfigPackage;

import Configs.Shooter;

public class Shootable implements Behavior {
    Shooter myShooter;
    Shootable(Shooter shooter){
        myShooter = shooter;
    }
}
