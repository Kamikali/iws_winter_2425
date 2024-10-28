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

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trainer: ");
        sb.append(firstName).append(" ").append(lastName);
        sb.append("\nPokemons: ");

        if (pokemons.isEmpty()) {
            sb.append("None");
        } else {
            for (Pokemon pokemon : pokemons) {
                sb.append("\n- ").append(pokemon);
            }
        }

        return sb.toString();
    }

    // List all Pokemons for Trainer in Terminal
    public void listPokemons() {
        System.out.println("Listing all Pokemons for " + firstName + " " + lastName + ":");
        if (pokemons.isEmpty()) {
            System.out.println("None");
        } else {
            for (Pokemon pokemon : pokemons) {
                System.out.println(pokemon);
            }
        }
    }

    // List Pokemons for Trainer by Type in Terminal
    public void listPokemonsByType(Type type) {
        System.out.println("Listing all " + type + " type Pokemons for " + firstName + " " + lastName + ":");
        if (pokemons.isEmpty()) {
            System.out.println("None");
        } else {
            for (Pokemon pokemon : pokemons) {
                if(pokemon.getType() == type){
                    System.out.println(pokemon);
                }
            }
        }
    }

    // Show i-th Pokemon for Trainer in Terminal
    public void showPokemon(int index) {
        if (index < 0 || index >= pokemons.size()) {
            System.out.println("Invalid index. Please provide a valid index between 0 and " + (pokemons.size() - 1));
        } else {
            System.out.println("Pokemon at index " + index + " for " + firstName + " " + lastName + ":");
            System.out.println(pokemons.get(index));
        }
    }
}