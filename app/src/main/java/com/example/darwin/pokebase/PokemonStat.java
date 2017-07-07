package com.example.darwin.pokebase;

public class PokemonStat {
	private int baseXp;
	private int ev;
	private String name;

	public void setBaseXp(int baseXp) {
		this.baseXp = baseXp;
	}

	public void setEv(int ev) {
		this.ev = ev;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBaseXp() {
		return baseXp;
	}

	public int getEv() {
		return ev;
	}

	public String getName() {
		// by default return stat name with first character capitalized.
		switch (this.name) {
			case "hp": return "HP";
			case "special-attack": return "Sp.Atk";
			case "special-defense": return "Sp.Def";
			default: return this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		}

	}

}
