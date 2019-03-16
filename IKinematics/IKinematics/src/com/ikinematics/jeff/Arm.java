package com.ikinematics.jeff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Arm {
	
	public float x,y,length;
	public float angle;
	public Arm parent;
	
	public Arm(float x,float y,float length,float angle) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.angle = angle;
		parent = null;
	}
	
	public float getEndX() {
		return (this.x + MathUtils.cos(this.angle) * this.length);
	}
	
	public float getEndY() {
		return (this.y + MathUtils.sin(this.angle) * this.length);
	}
	
	public void pointAt(float x,float y) {
		float dx = x - this.x;
		float dy = y - this.y;
		this.angle = MathUtils.atan2(dy, dx);
		
//		System.out.println(this.x + "-" + x + "=" + (this.x - x));
//		System.out.println(this.y + "-" + y + "=" + (this.y - y));
//		System.out.println(dx + " " + dy);
	}
	
	public void drag(float x,float y) {
		pointAt(x,y);
		this.x = (x - MathUtils.cos(this.angle) * this.length);
		this.y = (y - MathUtils.sin(this.angle) * this.length);
		if(this.parent != null) {
			this.parent.drag(this.x, this.y);
			this.x = this.parent.getEndX();
			this.y = this.parent.getEndY();
		} else {
			//this.x = Gdx.graphics.getWidth()*0.5f;
			//this.y = 300;
		}
		
	}
	
	
	
	public void renderArm(ShapeRenderer shape) {
		shape.begin(ShapeType.Line);
		shape.line(this.x, this.y, getEndX(), getEndY());
		shape.end();
	}

}
