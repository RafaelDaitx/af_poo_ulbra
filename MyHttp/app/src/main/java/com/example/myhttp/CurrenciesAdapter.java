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

public class CurrenciesAdapter extends ArrayAdapter<Currencies> {

    public CurrenciesAdapter(@NonNull Context context,  @NonNull List<Currencies> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Currencies coin= getItem(position);
        View listaCurrencies=convertView;
        if(convertView==null){
            listaCurrencies= LayoutInflater.from(getContext()).inflate(R.layout.currencies_item,null);
        }
        TextView name2 = listaCurrencies.findViewById(R.id.txtCoin2);
        TextView txtBuy2 = listaCurrencies.findViewById(R.id.txtCompra);
        TextView txtSell2 = listaCurrencies.findViewById(R.id.txtVenda);
        TextView variacao2 = listaCurrencies.findViewById(R.id.txtVariacao);
            name2.setText(coin.getName());
            txtBuy2.setText(coin.getBuy());
            txtSell2.setText(coin.getSell());
            variacao2.setText(coin.getVariation());


        return listaCurrencies;
    }
}
