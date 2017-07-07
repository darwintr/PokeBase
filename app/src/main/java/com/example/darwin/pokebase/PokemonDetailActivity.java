package com.example.darwin.pokebase;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PokemonDetailActivity extends AppCompatActivity {

	private ImageView ivSprite;
	private TextView tvId;
	private TextView tvName;
	private TextView tvHeight;
	private TextView tvWeight;

	private TextView tvHP;
	private TextView tvAtk;
	private TextView tvDef;
	private TextView tvSpAtk;
	private TextView tvSpDef;
	private TextView tvSpd;

	private TextView tvHPval;
	private TextView tvAtkval;
	private TextView tvDefval;
	private TextView tvSpAtkval;
	private TextView tvSpDefval;
	private TextView tvSpdval;

	private ImageView ivTypeOne;
	private ImageView ivTypeTwo;
	private Pokemon pokemon;
	private ArrayList<TextView> statsArr;
	private ArrayList<TextView> statsValArr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pokemon_detail);

		ivSprite = (ImageView) findViewById(R.id.pokemonSpriteDetail);
		tvName = (TextView) findViewById(R.id.pokemonNameDetail);
		tvId = (TextView) findViewById(R.id.pokedexNumberDetail);
		tvHeight = (TextView) findViewById(R.id.pokemonWeight);
		tvWeight = (TextView) findViewById(R.id.pokemonHeight);
		ivTypeOne = (ImageView) findViewById(R.id.pokemonTypeOne);
		ivTypeTwo = (ImageView) findViewById(R.id.pokemonTypeTwo);
		tvHP = (TextView) findViewById(R.id.HP);
		tvAtk = (TextView) findViewById(R.id.Atk);
		tvDef = (TextView) findViewById(R.id.Def);
		tvSpAtk = (TextView) findViewById(R.id.SpAtk);
		tvSpDef = (TextView) findViewById(R.id.SpDef);
		tvSpd = (TextView) findViewById(R.id.Spd);

		tvHPval = (TextView) findViewById(R.id.HPval);
		tvAtkval = (TextView) findViewById(R.id.Atkval);
		tvDefval = (TextView) findViewById(R.id.Defval);
		tvSpAtkval = (TextView) findViewById(R.id.SpAtkval);
		tvSpDefval = (TextView) findViewById(R.id.SpDefval);
		tvSpdval = (TextView) findViewById(R.id.Spdval);

		statsArr =  new ArrayList<>(
				Arrays.asList(tvSpd, tvSpDef, tvSpAtk, tvDef, tvAtk, tvHP)
		);
		statsValArr = new ArrayList<>(
				Arrays.asList(tvSpdval, tvSpDefval, tvSpAtkval, tvDefval, tvAtkval, tvHPval)
		);

		pokemon = (Pokemon) getIntent().getSerializableExtra(MainActivity.POKEMON_DETAIL_KEY);
		loadPokemon();
	}

	private void loadPokemon() {
		String id = pokemon.getDexNum();
		String json = FileManager.loadJSON(id + ".json", this);
		try {
			JSONObject obj = new JSONObject(json);
			tvId.setText(id);
			pokemon.setName(obj.getString("name"));
			tvName.setText(pokemon.getName());

			Drawable d = FileManager.openDrawable(id, this);
			ivSprite.setImageDrawable(d);

			JSONArray types = FileManager.getJSONArray(json, "types");
			JSONObject typeOne;
			JSONObject typeTwo = null;
			if (types.length() < 2) {
				typeOne = (JSONObject) types.get(0);
			} else {
				typeTwo = (JSONObject) types.get(0);
				typeOne = (JSONObject) types.get(1);
			}
			d = FileManager.openDrawable(typeOne.getString("name"), this);
			ivTypeOne.setImageDrawable(d);
			if (typeTwo != null) {
				d = FileManager.openDrawable(typeTwo.getString("name"), this);
				ivTypeTwo.setImageDrawable(d);
			}

			pokemon.setHeight(obj.getDouble("height"));
			tvHeight.setText(Double.toString(pokemon.getHeight()) + "m");
			pokemon.setWeight(obj.getDouble("weight"));
			tvWeight.setText(Double.toString(pokemon.getWeight()) + "kg");

			loadStats(obj);
			displayStats();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void loadStats(JSONObject obj) throws JSONException{
		JSONArray jarr = obj.getJSONArray("stats");

		ArrayList<PokemonStat> statsList = new ArrayList<>();
		for (int i = 0; i < jarr.length(); i++) {
			JSONObject statObj = jarr.getJSONObject(i);
			PokemonStat stat = new PokemonStat();
			stat.setName(statObj.getString("name"));
			stat.setBaseXp(statObj.getInt("base"));
			stat.setEv(statObj.getInt("ev"));
			statsList.add(stat);
		}
		pokemon.setStats(statsList);
	}

	private void displayStats() {

		HashMap<String, String> statColors = new HashMap<>();
		statColors.put("HP", "#FF5959");
		statColors.put("Attack", "#F5AC78");
		statColors.put("Defense", "#FAE078");
		statColors.put("Sp.Atk", "#9DB7F5");
		statColors.put("Sp.Def", "#A7DB8D");
		statColors.put("Speed", "#FA92B2");

		ArrayList<PokemonStat> statsList = pokemon.getStats();
		for (int i = 0; i < statsList.size(); i++) {
			PokemonStat curr = statsList.get(i);
			statsArr.get(i).setText(curr.getName());
			statsArr.get(i).setTextColor(Color.parseColor(statColors.get(curr.getName())));
			statsValArr.get(i).setText(Integer.toString(curr.getBaseXp()));
		}
	}
}
