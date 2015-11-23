package com.subzero.spacecaptain;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.subzero.entities.Enemy;

public class Formations {
	private ArrayList<ArrayList<Enemy>> formations;
	private ArrayList<Vector2> formationStartPosition; // May need to put all these as ArrayList<ArrayList<T>>
	private ArrayList<Integer> enemiesPerRow;
	private ArrayList<Vector2> positionInFormation;
	private ArrayList<Boolean> isArrived;
	private boolean isAllArrived = false;

	public Formations() {
		formations = new ArrayList<ArrayList<Enemy>>();
		formationStartPosition = new ArrayList<Vector2>();
		enemiesPerRow = new ArrayList<Integer>();
		positionInFormation = new ArrayList<Vector2>();
		isArrived = new ArrayList<Boolean>();
	}

	public void addFormation() {
		Random rand = new Random();
		formations.add(new ArrayList<Enemy>());
		formationStartPosition.add(new Vector2(rand.nextInt(20), rand.nextInt(20)));
		enemiesPerRow.add(rand.nextInt(3) + 2);
		System.out.println(formationStartPosition.get(0));
	}

	public void addMember(int index, Enemy member) {
		setPositioninFormation(getPlaceInFormation(member, index)); // This needs to be called first otherwise it will think there's already a member in the destination
		formations.get(index).add(member);
		isArrived.add(false);
	}

	public void setPositioninFormation(Vector2 position) {
		positionInFormation.add(position);
	}

	public ArrayList<ArrayList<Enemy>> getFormations() {
		return formations;
	}

	public ArrayList<Enemy> getMembers(int index) {
		return formations.get(index);
	}

	public boolean isEnemyInFormation(int index, Enemy member) {
		return formations.get(index).contains(member);
	}

	public int getFormationSize(int index) {
		return formations.get(index).size();
	}

	public int getEnemiesPerRow(int index) {
		return enemiesPerRow.get(0);
	}

	public void render(SpriteBatch batch) {
		int count, x;
		for (int i = 0; i < formations.size(); i++) {
			if (isArrived.get(i)) {
				count = 0;
				x = 0;
				for (int j = 0; j < formations.get(i).size(); j++) {
					if (j % enemiesPerRow.get(i) == 0) { // Change the value here to set how many ships per row
						count++;
						x = 0;
					}
					x++;
					formations.get(i).get(j).setX(formationStartPosition.get(i).x + (x * 30));
					formations.get(i).get(j).setY(formationStartPosition.get(i).y + (count * 30));
					formations.get(i).get(j).update(batch, false);
				}
			}
		}
		if(!isAllArrived){
			for(int i = 0; i < isArrived.size(); i++){
				// Travel to destination
				if(!isArrived.get(i)){
					formations.get(i).get
				}
			}
		}
	}

	public Vector2 getPlaceInFormation(Enemy enemy, int index) {
		int row = 1;
		int col = 0;
		for (int i = 0; i < getFormationSize(index); i++) {
			if (i % getEnemiesPerRow(index) == 0) {
				row++;
				col = 0;
			}
			col++;
		}
		return new Vector2(col, row);
	}
	// Get formations

	// Get formation members
}
