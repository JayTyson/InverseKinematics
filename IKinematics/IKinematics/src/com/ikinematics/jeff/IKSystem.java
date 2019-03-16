package com.ikinematics.jeff;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class IKSystem {
	
	public int x,y;
	public ArrayList<Arm> arms;
	public Arm lastArm;
	
	
	public IKSystem(int x,int y) {
		this.x = x;
		this.y = y;
		arms = new ArrayList<Arm>();
		lastArm = null;
	}
	
	public void addArm(int length) {
		Arm arm = new Arm(0,0,length,0);
		if(lastArm != null) {
			arm.x = lastArm.getEndX();
			arm.y = lastArm.getEndY();
			arm.parent = lastArm;
		} else {
			arm.x = this.x;
			arm.y = this.y;
		}
		arms.add(arm);
		lastArm = arm;
		
	}
	public void drag(float x,float y) {
		lastArm.drag(x, y);
	}
	
	public void renderArms(ShapeRenderer shape) {
		for(int i = 0;i < arms.size();i++) {
			arms.get(i).renderArm(shape);
		}
	}
	
	public void reach(float x,float y) {
		drag(x,y);
		for(int i = 0;i < arms.size();i++) {
			if(arms.get(i).parent != null) {
				arms.get(i).x = arms.get(i).parent.getEndX();
				arms.get(i).y = arms.get(i).parent.getEndY();
			} else {
				arms.get(i).x = this.x;
				arms.get(i).y = this.y;
			}
		}
	}
	
	public void rotate(float angle) {
		/*for(int i = 0;i < arms.size();i++) {
			arms.get(i).angle = MathUtils.sin(angle);
			if(arms.get(i).parent != null) {
				arms.get(i).x = arms.get(i).parent.getEndX();
				arms.get(i).y = arms.get(i).parent.getEndY();
			} else {
				arms.get(i).x = this.x;
				arms.get(i).y = this.y;
			}
		}*/
		
		int lowerLegIndex = arms.size()-1;
		arms.get(lowerLegIndex).angle = MathUtils.sin(angle);
		if(arms.get(lowerLegIndex).parent != null) {
			arms.get(lowerLegIndex).x = arms.get(lowerLegIndex).parent.getEndX();
			arms.get(lowerLegIndex).y = arms.get(lowerLegIndex).parent.getEndY();
		} else {
			arms.get(lowerLegIndex).x = this.x;
			arms.get(lowerLegIndex).y = this.y;
		}
		
		
	}

}
