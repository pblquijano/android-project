package com.pabloquijano.loginpokedex.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pabloquijano.loginpokedex.R;
import com.pabloquijano.loginpokedex.models.Pokemon_data;
import com.pabloquijano.loginpokedex.utils.Singleton;

import java.util.List;

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonsAdapter.PokemonsViewHolder> {
    private List<Pokemon_data> items;
    public Context context;
    public PokemonsAdapter(List<Pokemon_data> items, Context context) {
        this.items = items;
        this.context = context;
    }


    public static class PokemonsViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtNumber;
        private View mView;

        public PokemonsViewHolder(View itemView, Context context) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.tvName);
            txtNumber = (TextView)itemView.findViewById(R.id.tvNumber);
            mView = itemView.findViewById(R.id.cLayout);
            txtName.setTypeface(Singleton.getInstance(context).getFontSBold());
            txtNumber.setTypeface(Singleton.getInstance(context).getFontSBold());
        }

        public void bindPokemon(Pokemon_data pd) {
            txtName.setText(pd.getName());
            String id = ""+pd.getId();
            txtNumber.setText(id);

        }

    }


    @Override
    public PokemonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        //android:background="?android:attr/selectableItemBackground"

        PokemonsViewHolder tvh = new PokemonsViewHolder(itemView, context);

        return tvh;
    }

    @Override
    public void onBindViewHolder(PokemonsViewHolder holder, int position) {
        Pokemon_data item = items.get(position);
        item.setId(position+1);
        holder.bindPokemon(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Pokemon_data> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }
}
