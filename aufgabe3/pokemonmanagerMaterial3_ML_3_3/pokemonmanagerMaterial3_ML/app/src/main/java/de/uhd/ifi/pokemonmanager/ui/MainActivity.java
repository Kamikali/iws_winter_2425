package de.uhd.ifi.pokemonmanager.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Competition;
import de.uhd.ifi.pokemonmanager.data.Trainer;
import de.uhd.ifi.pokemonmanager.data.Type;
import de.uhd.ifi.pokemonmanager.ui.adapter.PokemonAdapter;

import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private RecyclerView pokemonRecycler;

    private static final String TAG = "main_app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "This is an info message.");
        pokemonRecycler = findViewById(R.id.pokemonList);
        initializeList();

    }

    private void initializeList() {
        List<Pokemon> sourceData = generateSampleData();
        PokemonAdapter pokemonAdapter = new PokemonAdapter(this, sourceData);

        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        pokemonRecycler.setLayoutManager(layoutManager);
        pokemonRecycler.setAdapter(pokemonAdapter);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);

        return linearManager;
    }

    private static List<Pokemon> generateSampleData() {
        List<Pokemon> pokemonSample = new ArrayList<>();
        Trainer trainerA = new Trainer("Lyra", "Limone");
        Trainer trainerB = new Trainer("Kai", "Havertz");
        Trainer trainerC = new Trainer("Nova", "Curious");

        // Erstelle Pokemons
        Pokemon pokemon1 = new Pokemon("Aquana", Type.WATER);
        Pokemon pokemon2 = new Pokemon("Gengar", Type.POISON);
        Pokemon pokemon3 = new Pokemon("Flamara", Type.FIRE);
        Pokemon pokemon4 = new Pokemon("Zapdos", Type.ELECTRIC);
        Pokemon pokemon5 = new Pokemon("Gorillaz", Type.GRASS);
        Pokemon pokemon6 = new Pokemon("Blizzy", Type.ICE);

        // Trainern Pokemons zuweisen
        trainerA.addPokemon(pokemon1);
        trainerA.addPokemon(pokemon2);
        trainerB.addPokemon(pokemon3);
        trainerB.addPokemon(pokemon4);
        trainerC.addPokemon(pokemon5);
        trainerC.addPokemon(pokemon6);

        // Create a competition and use it to simulate a few battles
        // battle results are logged with tag "competition"
        Competition competition = new Competition();
        competition.execute(pokemon1, pokemon3);
        competition.execute(pokemon2, pokemon3);
        competition.execute(pokemon3, pokemon4);
        competition.execute(pokemon5, pokemon2);

        // Add pokemons to the list
        pokemonSample.add(pokemon1);
        pokemonSample.add(pokemon2);
        pokemonSample.add(pokemon3);
        pokemonSample.add(pokemon4);
        pokemonSample.add(pokemon5);
        pokemonSample.add(pokemon6);

        return pokemonSample;
    }
}