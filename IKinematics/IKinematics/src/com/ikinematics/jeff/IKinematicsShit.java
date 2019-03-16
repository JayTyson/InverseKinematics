package com.ikinematics.jeff;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class IKinematicsShit implements ApplicationListener {
	private OrthographicCamera camera;
	public ShapeRenderer shape;
	/*public Arm arm;
	public Arm arm1;
	public Arm arm2;*/
	IKSystem ik1;
	/*IKSystem ik2;
	IKSystem ik3;
	IKSystem ik4;*/
	Vector3 temp;
	
	float speed = 0;
	
	ArrayList<Bullet> bullets;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		shape = new ShapeRenderer();
		temp = new Vector3();
		
		/*arm = new Arm(200,200,100,0);
		arm1 = new Arm(arm.getEndX(),arm.getEndY(),100,0);
		arm2 = new Arm(arm1.getEndX(),arm1.getEndY(),100,0);
		
		arm1.parent = arm;
		arm2.parent = arm1;*/
		bullets = new ArrayList<Bullet>();
		
		ik1 = new IKSystem(200,350);
		ik1.addArm(100);
		ik1.addArm(100);
		ik1.addArm(100);
		
		/*ik2 = new IKSystem(324,223);
		ik2.addArm(100);
		ik2.addArm(100);
		ik2.addArm(100);
		
		ik3 = new IKSystem(523,300);
		ik3.addArm(100);
		ik3.addArm(100);
		ik3.addArm(100);
		
		ik4 = new IKSystem(257,278);
		ik4.addArm(100);
		ik4.addArm(100);
		ik4.addArm(100);*/
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {	
		updateInput();
		for(Bullet b:bullets) {
			b.updateBullet();
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(temp);
		
		ik1.reach(temp.x,temp.y);
		/*ik2.reach(temp.x,temp.y);
		ik3.reach(temp.x,temp.y);
		ik4.reach(temp.x,temp.y);*/
		
		
		shape.setProjectionMatrix(camera.combined);
		//shape.setColor(Color.RED);
		ik1.renderArms(shape);
		/*ik2.renderArms(shape);
		ik3.renderArms(shape);
		ik4.renderArms(shape);*/
		/*shape.setColor(Color.BLUE);
		arm1.renderArm(shape);
		shape.setColor(Color.GREEN);
		arm2.renderArm(shape);*/
		
		//arm1.x = arm.getEndX();
		//arm1.y = arm.getEndY();
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.RED);
		for(Bullet b:bullets) {
			b.renderBullet(shape);
		}
		shape.end();
	}
	
	public Bullet shootBullet() {
		Bullet bullet = new Bullet();
		bullet.position.set(ik1.arms.get(ik1.arms.size()-1).getEndX(), ik1.arms.get(ik1.arms.size()-1).getEndY());
		bullet.velocity.x = 2 * MathUtils.cosDeg(ik1.arms.get(ik1.arms.size()-1).angle * 180/MathUtils.PI);
		bullet.velocity.y = 2 * MathUtils.sinDeg(ik1.arms.get(ik1.arms.size()-1).angle * 180/MathUtils.PI);
		return bullet;
	}
	
	private void updateInput() {
		if(Gdx.input.justTouched()) {
			temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			float x = temp.x;
			float y = temp.y;
			//float vx = x - arm1.getEndX();
			//float vy = y - arm1.getEndY();
			
			//float angle = (float) (MathUtils.atan2(vy, vx));
			//arm1.angle = angle;
			
			bullets.add(shootBullet());
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
