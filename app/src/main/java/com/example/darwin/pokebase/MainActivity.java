package com.example.darwin.pokebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
	private ArrayList<Pokemon> pokemonList;
	private HashMap<String, String> idToPokemon;
	private PokemonAdapter pokemonAdapter;
	private ListView lvPokemon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String json = loadJSON("dexnumbers.json");
		JSONArray jarr = getJSONArray(json, "dexnumbers");

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
	}

	public String loadJSON(String file) {
		String json = null;
		try {
			InputStream is = MainActivity.this.getAssets().open("data/" + file);
			int size = is.available();
			byte[] buff = new byte[size];
			is.read(buff);
			is.close();
			json = new String(buff, "UTF-8");
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

	public JSONArray getJSONArray(String json, String key) {
		JSONArray jarr = null;
		try {
			JSONObject obj = new JSONObject(json);
			jarr = obj.getJSONArray(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jarr;
	}
}
