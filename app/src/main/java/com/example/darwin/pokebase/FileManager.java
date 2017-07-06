package com.example.darwin.pokebase;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Darwin on 7/6/2017.
 */

public class FileManager {

		public static String loadJSON(String file, Activity context) {
			String json = null;
			try {

				InputStream is = context.getAssets().open("data/" + file);
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

		public static JSONArray getJSONArray(String json, String key) {
			JSONArray jarr = null;
			try {
				JSONObject obj = new JSONObject(json);
				jarr = obj.getJSONArray(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return jarr;
		}

		public static Drawable openDrawable(String id, Context context) {
			try {
				InputStream img = context.getAssets().open("sprites/" + id + ".png");
				Drawable d = Drawable.createFromStream(img, null);
				return d;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

}
