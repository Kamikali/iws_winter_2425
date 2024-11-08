package de.uhd.ifi.pokemonmanager.data;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private String name;
    private Type type;
    private Trainer trainer;
    private final int number;
    private static int nextNumber;
    private boolean isSwapAllowed = true;
    private List<Swap> swaps = new ArrayList<>();
    private List<Competition> competitions = new ArrayList<>();

    //implements SF: Create Pokemon in Terminal
    public Pokemon(String name, Type type) {
        this.name = name;
        this.type = type;
        this.number = nextNumber;
        nextNumber++;
    }

    public String getName() {
        return name;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setName(String name) {
        // this references the actual object instance
        this.name = name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    //implements SF: Link Pokemon to Trainer in Terminal
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Type getType() {
        return type;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setType(Type type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public List<Swap> getSwaps() {
        return swaps;
    }

    public void setSwaps(List<Swap> swaps) {
        this.swaps = swaps;
    }

    public void addSwap(Swap swap) {
        getSwaps().add(swap);
    }

    public boolean isSwapAllowed() {
        return isSwapAllowed;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setSwapAllowed(boolean isSwapAllowed) {
        this.isSwapAllowed = isSwapAllowed;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public void addCompetition(Competition competition) {
        getCompetitions().add(competition);
    }

    //implements SF: Show Pokemon Details in Terminal
    @Override
    public String toString() {
        return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType() +
                "' has trainer '" + getTrainer() + "'";
    }
}
