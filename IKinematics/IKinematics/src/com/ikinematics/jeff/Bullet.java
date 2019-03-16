package com.ikinematics.jeff;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
	
public Vector2 position,velocity;
	
	
	public Bullet() {
		position = new Vector2();
		velocity = new Vector2();
	}
	
	public void updateBullet() {
		position.add(velocity);
	}
	
	public void renderBullet(ShapeRenderer shape) {
		shape.rect(position.x, position.y, 4, 4);
		
	}

}
