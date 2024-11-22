package de.uhd.ifi.pokemonmanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;

/**
 * Enables to show a list of {@link Swap}s in the UI in a so called {@link
 * androidx.recyclerview.widget.RecyclerView}.
 */
public class SwapAdapter extends RecyclerView.Adapter<SwapHolder> {
    private LayoutInflater inflater;
    private List<Swap> swaps;
    private Pokemon pokemon;

    public SwapAdapter(final Context context, final List<Swap> swaps, final Pokemon pokemon) {
        this.inflater = LayoutInflater.from(context);
        this.swaps = swaps;
        this.pokemon = pokemon;
    }

    @NonNull
    @Override
    public SwapHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SwapHolder(inflater.inflate(R.layout.listitem_swap, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SwapHolder holder, int position) {
        holder.setSwap(swaps.get(position), pokemon);
    }

    @Override
    public int getItemCount() {
        return swaps.size();
    }

    public void setList(List<Swap> swaps) {
        this.swaps = swaps;
        notifyDataSetChanged();
    }
}

/**
 * Responsible to show a single {@link Swap} in the UI.
 */
class SwapHolder extends RecyclerView.ViewHolder {

    private final TextView swapDateText;
    private final TextView swapSourceTrainer;
    private final TextView swapTargetTrainer;
    private final TextView swapOtherPokemon;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);

    SwapHolder(@NonNull View itemView) {
        super(itemView);
        swapDateText = itemView.findViewById(R.id.swapDateText);
        swapSourceTrainer = itemView.findViewById(R.id.swapSourceTrainer);
        swapTargetTrainer = itemView.findViewById(R.id.swapTargetTrainer);
        swapOtherPokemon = itemView.findViewById(R.id.swapOtherPokemon);
        itemView.setTag(this);
    }

    void setSwap(Swap swap, @NonNull Pokemon pokemon) {
        this.swapDateText.setText(formatter.format(swap.getDate()));
        if (pokemon.equals(swap.getSourcePokemon())) {
            this.swapSourceTrainer.setText(swap.getSourceTrainer().toString());
            this.swapTargetTrainer.setText(swap.getTargetTrainer().toString());
            if (swap.getTargetPokemon() == null) {
                this.swapOtherPokemon.setText(R.string.deleted_pokemon);
            } else {
                this.swapOtherPokemon.setText(swap.getTargetPokemon().getName());
            }
        } else {
            this.swapSourceTrainer.setText(swap.getTargetTrainer().toString());
            this.swapTargetTrainer.setText(swap.getSourceTrainer().toString());
            if (swap.getSourcePokemon() == null) {
                this.swapOtherPokemon.setText(R.string.deleted_pokemon);
            } else {
                this.swapOtherPokemon.setText(swap.getSourcePokemon().getName());
            }
        }
    }
}
