package com.deivid.boff.stonks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Stocks> mStocks;
    ArrayAdapter<Stocks> mAdapter;
    ListView mList;
    CurrenciesTask mTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.Lista);

        buscar();
    }
    private void buscar(){
        if(mStocks==null){
            mStocks= new ArrayList<Stocks>();
        }
        mAdapter= new StocksAdapter(getApplicationContext(),mStocks);
        mList.setAdapter(mAdapter);

        if(mTask==null){
            if (StocksHttp.hasConexao(this)){
                start();
            }else{
                Toast.makeText(getApplicationContext(),"Erro a buscar....",Toast.LENGTH_SHORT).show();
            }
        }else if(mTask.getStatus()==AsyncTask.Status.RUNNING){
            Toast.makeText(getApplicationContext(),"....",Toast.LENGTH_SHORT).show();
        }

    }



    public void start(){
        if(mTask ==null || mTask.getStatus()!= AsyncTask.Status.RUNNING){
            mTask = new CurrenciesTask();
            mTask.execute();
        }
    }

    class CurrenciesTask extends AsyncTask<Void,Void, ArrayList<Stocks>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Pronto....",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected ArrayList<Stocks> doInBackground(Void... voids) {
            ArrayList<Stocks> coinsList= StocksHttp.loadStocks();
            return coinsList;
        }

        @Override
        protected void onPostExecute(ArrayList<Stocks> currencies) {
            super.onPostExecute(currencies);
            if (currencies!=null){
                mStocks.clear();
                mStocks.addAll(currencies);
                mAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getApplicationContext(),"Buscando...", Toast.LENGTH_LONG).show();
            }
        }
    }
}