package com.example.myfirebaseapphit.dbstorage;

import android.os.StrictMode;

import com.example.myfirebaseapphit.models.State;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataService {
private ArrayList<State> arrState =new ArrayList<>();
public ArrayList<State> getArrState() {
    String sUrl = "https://restcountries.com/v2/all?fields=name,nativeName,borders,flag";
    URL url = null;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy); // ייצור טריידים על ידי המערכת עצמה
    try{
        url = new URL(sUrl);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    try{
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser js = new JsonParser();
        JsonElement root = js.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonArray rootObj = root.getAsJsonArray();

        for(JsonElement je : rootObj){
            JsonObject obj = je.getAsJsonObject();
            JsonElement name = obj.get("name");
            JsonElement nativeName = obj.get("nativeName");
            JsonElement flag = obj.get("flag");

            String nameString = name.toString().replace("\"", "").trim();
            String nativeString = nativeName.toString().replace("\"", "").trim();
            String flagString = flag.getAsString();
            /*if(flag!=null){
                flagString=flag.getAsString();
            }
            else {`
                flagString = " ";
            }
            flagString = flagString.substring(1, flagString.length() - 1);*/

            ArrayList<String> arrBorders = new ArrayList<>();
            JsonElement borders = obj.get("borders");
            if(borders != null)
            {
                JsonArray bordersArray = borders.getAsJsonArray();
                for(JsonElement j : bordersArray)
                {
                    String s = j.toString().replace("\"", "").trim();
                    arrBorders.add(s);
                }
            }
            arrState.add(new State(nameString, arrBorders, nativeString, flagString));
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    return arrState;
    }
}
