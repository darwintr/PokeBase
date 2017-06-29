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

	public String getName() {
		return name;
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

