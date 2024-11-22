package de.uhd.ifi.pokemonmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import de.uhd.ifi.pokemonmanager.R;
import de.uhd.ifi.pokemonmanager.data.Competition;
import de.uhd.ifi.pokemonmanager.data.Pokemon;
import de.uhd.ifi.pokemonmanager.data.Swap;
import de.uhd.ifi.pokemonmanager.data.Trainer;
import de.uhd.ifi.pokemonmanager.data.Type;
import de.uhd.ifi.pokemonmanager.storage.SerialStorage;
import de.uhd.ifi.pokemonmanager.ui.adapter.CompetitionAdapter;
import de.uhd.ifi.pokemonmanager.ui.adapter.SwapAdapter;

import static de.uhd.ifi.pokemonmanager.ui.MainActivity.DETAIL_POKEMON;
import static de.uhd.ifi.pokemonmanager.ui.util.RecyclerViewUtil.createLayoutManager;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView type;
    private TextView trainer;
    private TextView swapAllowed;
    private RecyclerView swapList;
    private RecyclerView competitionList;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bindViews();
        getPokemonFromIntent();
        setupSwapList();
        setupCompetitionList();
        initPokemonDetailView();

        setupSaveButton();
    }

    private void setupSaveButton() {
        Button saveButton = findViewById(R.id.saveChangesButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePokemonDetails();
            }
        });
    }


    private void savePokemonDetails() {
        // Update Pokemon name
        String updatedName = name.getText().toString();
        String updateTrainer = trainer.getText().toString();
        String updateType = type.getText().toString();
        pokemon.setName(updatedName);
        pokemon.getTrainer().setFirstName(updateTrainer);
        pokemon.getTrainer().setLastName("");

        // Handle updating the type
        try {
            Type newType = Type.valueOf(updateType);  // Convert the string to a Type enum
            pokemon.setType(newType);  // Update the pokemon's type
        } catch (IllegalArgumentException e) {
            // If the string is not a valid Type, show a toast and don't update the type
            Toast.makeText(this, "Invalid type entered. Please use FIRE, WATER, or POISON.", Toast.LENGTH_SHORT).show();
            return;  // Exit method to avoid saving invalid data
        }


        // Save the updated Pokemon object back into storage
        SerialStorage.getInstance().save(pokemon);

        // Persist the changes by saving all data
        SerialStorage.getInstance().saveAll(this); // Ensure all data is saved to the file
        // Notify user
        Toast.makeText(this, "Pokemon details saved successfully!", Toast.LENGTH_SHORT).show();
    }


    private void bindViews() {
        name = findViewById(R.id.nameText);
        type = findViewById(R.id.type);
        trainer = findViewById(R.id.trainer);
        swapAllowed = findViewById(R.id.swapAllowed);
        swapList = findViewById(R.id.swapList);
        competitionList = findViewById(R.id.competitionList);
    }

    private void getPokemonFromIntent() {
        Intent intent = getIntent();
        Parcelable parcelable = intent.getParcelableExtra(DETAIL_POKEMON);
        pokemon = SerialStorage.getInstance().getPokemonById(((Pokemon) parcelable).getId());
    }

    private void setupSwapList() {
        List<Swap> swapsOfPokemon = pokemon.getSwaps();
        SwapAdapter swapAdapter = new SwapAdapter(this, swapsOfPokemon, pokemon);
        RecyclerView.LayoutManager manager = createLayoutManager(this);

        swapList.setLayoutManager(manager);
        swapList.setAdapter(swapAdapter);
    }

    private void setupCompetitionList() {
        List<Competition> competitionsOfPokemon = pokemon.getCompetitions();
        CompetitionAdapter competitionAdapter = new CompetitionAdapter(this, competitionsOfPokemon, pokemon);
        RecyclerView.LayoutManager manager = createLayoutManager(this);

        competitionList.setLayoutManager(manager);
        competitionList.setAdapter(competitionAdapter);
    }

    private void initPokemonDetailView() {
        name.setText(pokemon.getName());
        type.setText(pokemon.getType().toString());
        trainer.setText(pokemon.getTrainer().toString());
        swapAllowed.setText(String.valueOf(pokemon.isSwapAllowed()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

}
