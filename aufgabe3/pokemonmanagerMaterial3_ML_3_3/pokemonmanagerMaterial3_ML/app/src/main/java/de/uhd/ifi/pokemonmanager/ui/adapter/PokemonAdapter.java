package de.uhd.ifi.pokemonmanager.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;
import java.util.Locale;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;

public class PokemonAdapter extends Adapter<PokemonHolder> {

    private LayoutInflater inflater;
    private List<Pokemon> originalData;

    public PokemonAdapter(Context context, List<Pokemon> originalData) {
        this.inflater = LayoutInflater.from(context);
        this.originalData = originalData;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.listitem_pokemon, parent, false);
        return new PokemonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PokemonHolder holder, int position) {
        holder.setPokemon(originalData.get(position));
    }

    @Override
    public int getItemCount() {
        return originalData.size();
    }
}

class PokemonHolder extends ViewHolder {

    private TextView pokemonName;
    private TextView pokemonType;
    private TextView pokemonId;
    private TextView trainerText;
    private TextView pokemonSwaps;
    private TextView pokemonCompetitions;

    PokemonHolder(View itemView) {
        super(itemView);
        pokemonName = itemView.findViewById(R.id.pokemonName);
        pokemonType = itemView.findViewById(R.id.pokemonType);
        pokemonId = itemView.findViewById(R.id.pokemonId);
        trainerText = itemView.findViewById(R.id.trainerText);
        pokemonSwaps = itemView.findViewById(R.id.pokemonSwaps);
        pokemonCompetitions = itemView.findViewById(R.id.pokemonCompetitions);
        itemView.setTag(this);
    }

    void setPokemon(Pokemon pokemon) {
        this.pokemonName.setText(pokemon.getName());
        this.pokemonType.setText(pokemon.getType().toString());
        this.pokemonId.setText(String.format(Locale.getDefault(),"# %d", pokemon.getNumber()));
        this.trainerText.setText(pokemon.getTrainer().toString());
        this.pokemonSwaps.setText(String.format(Locale.getDefault(),"Swaps: %d", pokemon.getSwaps().size()));
        this.pokemonCompetitions.setText(String.format(Locale.getDefault(),"Competitions: %d", pokemon.getCompetitions().size()));
    }
}
