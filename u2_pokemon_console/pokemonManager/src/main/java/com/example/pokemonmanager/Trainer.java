package com.example.pokemonmanager;

import java.util.ArrayList;

public class Trainer {
    private String lastName;
    private String firstName;

    private ArrayList<Pokemon> pokemons;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Trainer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pokemons = new ArrayList<>();
    }

    public static void main(String[] args) {
        trainer = new Trainer("John", "Mayer");
    }
}