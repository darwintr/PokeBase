package com.example.darwin.pokebase;

import java.util.ArrayList;

/**
 * Created by Darwin on 6/24/2017.
 */

public class Pokemon {
	private String name;
	private String dexNum;
	private float weight;
	private float height;
	private ArrayList<PokemonType> types;
	private ArrayList<PokemonStat> stats;


	public void setName(String name) {
		this.name = name;
	}

	public void setDexNum(String dexNum) {
		this.dexNum = dexNum;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setTypes(ArrayList<PokemonType> types) {
		this.types = types;
	}

	public void setStats(ArrayList<PokemonStat> stats) {
		this.stats = stats;
	}


	public String getName() {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public String getDexNum() {
		return dexNum;
	}

	public float getWeight() {
		return weight;
	}

	public float getHeight() {
		return height;
	}

	public ArrayList<PokemonType> getTypes() {
		return types;
	}

	public ArrayList<PokemonStat> getStats() {
		return stats;
	}

	public String getSpriteFilename() { return dexNum + ".png"; }

	public static String getFormattedStat(String stat) {
		// by default return stat name with first character capitalized.
		switch (stat) {
			case "hp": return "HP";
			case "special-attack": return "Sp.Atk";
			case "special-defense": return "Sp.Def";
			default: return stat.substring(0, 1).toUpperCase() + stat.substring(1);
		}

	}
}

