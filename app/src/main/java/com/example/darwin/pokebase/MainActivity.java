package com.example.darwin.pokebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
	public static final String POKEMON_DETAIL_KEY = "pokemon";
	private ArrayList<Pokemon> pokemonList;
	private HashMap<String, String> idToPokemon;
	private PokemonAdapter pokemonAdapter;
	private ListView lvPokemon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String json = FileManager.loadJSON("dexnumbers.json", this);
		JSONArray jarr = FileManager.getJSONArray(json, "dexnumbers");

		try {
			idToPokemon = new HashMap<String, String>();
			pokemonList = new ArrayList<Pokemon>();
			for (int i = 0; i < jarr.length(); i++) {
				JSONObject inside = jarr.getJSONObject(i);
				Pokemon p = new Pokemon();
				String dex_num = inside.getString("id");
				String name = inside.getString("name");
				p.setDexNum(dex_num);
				p.setName(name);
				idToPokemon.put(dex_num, name);
				pokemonList.add(p);
			}
		} catch (JSONException ex) {
			ex.printStackTrace();
			return;
		}

		Collections.sort(pokemonList, new Comparator<Pokemon>() {
			@Override
			public int compare(Pokemon a, Pokemon b) {
				return Integer.parseInt(a.getDexNum()) < Integer.parseInt(b.getDexNum()) ? -1 : 1;
			}
		});

		lvPokemon = (ListView) findViewById(R.id.lvPokemon);
		pokemonAdapter = new PokemonAdapter(this, pokemonList);
		lvPokemon.setAdapter(pokemonAdapter);

		setupPokemonSelectedListener();
	}

	public void setupPokemonSelectedListener() {
		lvPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, PokemonDetailActivity.class);
				intent.putExtra(POKEMON_DETAIL_KEY, pokemonAdapter.getItem(position));
				startActivity(intent);
			}
		});
	}

}
