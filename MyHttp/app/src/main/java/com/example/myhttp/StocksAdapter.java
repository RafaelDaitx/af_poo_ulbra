package com.example.myhttp;

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

    public StocksAdapter(@NonNull Context context,  @NonNull List<Stocks> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Stocks coin= getItem(position);
        View listaStocks=convertView;
        if(convertView==null){
            listaStocks = LayoutInflater.from(getContext()).inflate(R.layout.currencies_item,null);
        }
        TextView name = listaStocks.findViewById(R.id.txtCoin2);
        TextView txtBuy = listaStocks.findViewById(R.id.txtCompra);
        TextView txtSell = listaStocks.findViewById(R.id.txtVenda);
        TextView variacao = listaStocks.findViewById(R.id.txtVariacao);
        name.setText(coin.getName());
        txtBuy.setText(coin.getLocation());
        txtSell.setText(coin.getLocation());
        variacao.setText(coin.getVariation());


        return listaStocks;
    }
}
