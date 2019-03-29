package frontendExample;

import java.util.List;

public class checkIfProjectileHit {
    private GameGrid grid;

    checkIfProjectileHit(GameGrid grid, Projectile projectile){
        this.grid=grid;
        Cell projectileCell = grid.getCell(projectile.getX(), projectile.getY());
        for (Enemy enemy:projectileCell.getEnemies()){
            enemy.setHealth(enemy.getHealth-projectile.getKillPower);
        }
    }
}
