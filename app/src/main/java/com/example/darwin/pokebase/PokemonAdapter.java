package com.example.darwin.pokebase;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Darwin on 6/30/2017.
 */

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

	private static class ViewHolder {
		public TextView tvId;
		public TextView tvName;
		public ImageView ivSprite;
	}

	public PokemonAdapter(Context context, ArrayList<Pokemon> pokemonList) {
		super(context, 0, pokemonList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final Pokemon pokemon = getItem(position);
		ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_pokemon, parent, false);
			viewHolder.tvId = (TextView) convertView.findViewById(R.id.pokedexNumber);
			viewHolder.tvName = (TextView) convertView.findViewById(R.id.pokemonName);
			viewHolder.ivSprite = (ImageView) convertView.findViewById(R.id.pokemonSprite);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvId.setText(pokemon.getDexNum());
		viewHolder.tvName.setText(pokemon.getName());
		AssetManager assetManager = getContext().getAssets();
		try {
			InputStream img = assetManager.open("sprites/" + pokemon.getDexNum() + ".png" );
			Drawable d = Drawable.createFromStream(img, null);
			viewHolder.ivSprite.setImageDrawable(d);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

		return convertView;
	}
}
