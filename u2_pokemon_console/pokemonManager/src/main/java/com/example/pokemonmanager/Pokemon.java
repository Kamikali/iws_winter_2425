package com.example.pokemonmanager;
import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private String name;
    private Type type;
    private Trainer trainer = null;
    private final int number;
    private static int nextNumber;
    private ArrayList<Swap> swaps; // List of swaps the Pokemon is involved in
    private boolean isSwapAllowed; // Indicates if the Pokemon can be swapped


    //implements SF: Create Pokemon in Terminal
    public Pokemon(String name, Type type) {
        this.name = name;
        this.type = type;
        this.isSwapAllowed = false;
        this.number = nextNumber;
        this.swaps = new ArrayList<>();
        nextNumber++;
    }

    //return false if pokemon is already linked to a trainer.
    public boolean link_to_trainer(Trainer trainer){
        if (this.trainer == null){
            this.trainer = trainer;
            trainer.addPokemon(this);
            return true;
        }
        return false;
    }

    public boolean isSwapAllowed() {
        return isSwapAllowed;
    }

    public void setSwapAllowed(boolean isSwapAllowed) {
        this.isSwapAllowed = isSwapAllowed;
    }

    public void addSwap(Swap swap) {
        this.swaps.add(swap);
    }

    public String getName() {
        return name;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setName(String name) {
        // this references the actual object instance
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Trainer getTrainer(){
        return trainer;
    }

    //implements SF: Edit Pokemon in Terminal
    public void setType(Type type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType() + "'";
    }
}
