package com.deivid.boff.stonks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StocksAdapter extends ArrayAdapter<Stocks> {

    public StocksAdapter(@NonNull Context context, @NonNull List<Stocks> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Stocks stonks= getItem(position);
        View listaStocks=convertView;
        if(convertView==null){
            listaStocks= LayoutInflater.from(getContext()).inflate(R.layout.stocks_item,null);
        }
        TextView name = listaStocks.findViewById(R.id.txtName);
        TextView txtPoints = listaStocks.findViewById(R.id.txtPoints);
        TextView txtLocation = listaStocks.findViewById(R.id.txtLocation);
        TextView variacao = listaStocks.findViewById(R.id.txtVariacao);
            name.setText(stonks.getnName());
            txtPoints.setText(stonks.getnPoints());
            txtLocation.setText(stonks.getnLocation());
            variacao.setText(stonks.getnVariation().toString());


        return listaStocks;
    }
}
