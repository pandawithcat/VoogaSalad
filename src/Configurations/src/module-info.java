module Configurations {
    requires java.desktop;
    requires javafx.graphics;
<<<<<<< HEAD
    exports Configs;
    exports Configs.GamePackage;
}
=======

    requires xstream;
    opens Configs to xstream;
    exports Configs;
    
    exports Configs.ArsenalConfig.WeaponBehaviors;
    exports Configs.ArsenalConfig;
    exports Configs.Behaviors;
    exports Configs.EnemyPackage.EnemyBehaviors;
    exports Configs.EnemyPackage;
    exports Configs.GamePackage;
//    exports Configs.GamePackage.GameBehaviors;
    exports Configs.LevelPackage.LevelBehaviors;
    exports Configs.LevelPackage;
    exports Configs.MapPackage;
    exports Configs.MapPackage.TerrainBehaviors;
    exports Configs.ProjectilePackage.ProjectileBehaviors;
    exports Configs.ProjectilePackage;
    exports Configs.Waves.WaveBehaviors;
    exports Configs.Waves;
    exports ActiveConfigs;
}
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
