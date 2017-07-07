package com.example.darwin.pokebase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Darwin on 6/24/2017.
 */

public class Pokemon implements Serializable{
	private String name;
	private String dexNum;
	private double weight;
	private double height;
	private String typeOne;
	private String typeTwo;
	private ArrayList<PokemonStat> stats;

	public void setName(String name) {
		this.name = name;
	}

	public void setDexNum(String dexNum) {
		this.dexNum = dexNum;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setStats(ArrayList<PokemonStat> stats) {
		this.stats = stats;
	}

	public void setTypeOne(String typeOne) {
		this.typeOne = typeOne;
	}

	public void setTypeTwo(String typeTwo) {
		this.typeTwo = typeTwo;
	}

	public String getName() {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public String getDexNum() {
		return dexNum;
	}

	public double getWeight() {
		return weight/10;
	}

	public double getHeight() {
		return height/10;
	}

	public ArrayList<PokemonStat> getStats() {
		return stats;
	}

	public String getTypeOne() {
		return typeOne;
	}

	public String getTypeTwo() {
		return typeTwo;
	}


}

