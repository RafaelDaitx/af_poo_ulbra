package com.example.myhttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CurrenciesHttp {

    //public static String URL="https://api.hgbrasil.com/finance/quotations?format=json&key=4aaa9ac3";
    public static String URL="https://api.hgbrasil.com/finance?format=json-cors&key=93be48f8";

    private  static HttpURLConnection conectar(String urlWeb){
        final  int SEGUNDOS =10000;
        try{
            java.net.URL url = new URL(urlWeb);
            HttpURLConnection conexao= (HttpURLConnection) url.openConnection();
            conexao.setReadTimeout(10*10000);
            conexao.setConnectTimeout(15*10000);
            conexao.setRequestMethod("GET");
            conexao.setDoInput(true);
            conexao.setDoOutput(false);
            conexao.connect();
            return  conexao;
        } catch (Exception e) {
            Log.d("URL ERROR", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static boolean hasConexao(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info= cm.getActiveNetworkInfo();
        return (info!=null &&info.isConnected());
    }


    public static ArrayList<Currencies> loadCurrencies(){
        try {
            HttpURLConnection connection= conectar(URL);
            int response=connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
         //       InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                JSONObject jsonObject = new JSONObject(bytesPraString(inputStream));
                //chegou JSON
                ArrayList<Currencies> currencies= readJsonCurrencie(jsonObject);
                return currencies;
            }
        } catch (IOException | JSONException e) {

            Log.d("JSON", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public  static ArrayList<Currencies> readJsonCurrencie(JSONObject json){
        ArrayList<Currencies> array = new ArrayList<>();
        try {
            JSONObject results= json.getJSONObject("results");
            JSONObject jsonCurrencies= results.getJSONObject("currencies");
            JSONObject btc=jsonCurrencies.getJSONObject("BTC");
            JSONObject eur=jsonCurrencies.getJSONObject("EUR");
            JSONObject usd=jsonCurrencies.getJSONObject("USD");
            array.add(getCurrenciesFromJson(usd));
            array.add(getCurrenciesFromJson(eur));
            array.add(getCurrenciesFromJson(btc));


        }catch (JSONException e){
            Log.d("JSON", e.getMessage());
        }
        return array;
    }


    public static Currencies getCurrenciesFromJson(JSONObject json){
        String name2;
        Double buy2;
        Double sell2;
        double variation2;
        Currencies coin= null;
        try {
            name2=json.getString("name");
            buy2=json.getDouble("buy");
            sell2=json.getDouble("sell");
            variation2=json.getDouble("variation");
            coin= new Currencies(name2,buy2,sell2,variation2);
        }catch (JSONException e){
            Log.e("Log", e.getMessage());
        }
        return coin;

    }

    private static String bytesPraString(InputStream inputStream){
        byte[] buffer= new byte[1024];
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        int bytesLidos;
        try {
            while ((bytesLidos=inputStream.read(buffer))!=-1){
                dados.write(buffer,0,bytesLidos);
            }
            return new String(dados.toByteArray(),"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


}
