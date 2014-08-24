package game.level.entity;

import game.level.Animation;
import game.level.map.LevelMap;

import java.io.IOException;

import javax.imageio.ImageIO;

public class EntityEnemyMars extends EntityEnemy {

	protected Animation animationRun;
	protected Animation animationFight;

	private boolean walkLeft;

	public EntityEnemyMars(double x, double y, double width, double height) {
		super(x, y, width, height, null);

		walkLeft = true;
		animationRun = new Animation();
		animationFight = new Animation();
		animationRun.load("/model/enemys/MarsKnight-RunAnimation.png", 2, 150);
		animationFight.load("/model/enemys/MarsKnight-FightAnimation.png", 2, 150);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/model/enemys/MarsKnight-Model.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update(LevelMap map) {
		if (walkLeft) {
			xMovement = 1;
		} else {
			xMovement = -1;
		}
		super.update(map);
		if (xMovement == 0) {
			walkLeft = !walkLeft;
		}
	}
}