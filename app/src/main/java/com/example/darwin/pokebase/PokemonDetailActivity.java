package com.example.darwin.pokebase;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDetailActivity extends AppCompatActivity {

	private ImageView ivSprite;
	private TextView tvId;
	private TextView tvName;
	private TextView tvHeight;
	private TextView tvWeight;
	private ImageView ivTypeOne;
	private ImageView ivTypeTwo;
	private Pokemon pokemon;

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
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


}
