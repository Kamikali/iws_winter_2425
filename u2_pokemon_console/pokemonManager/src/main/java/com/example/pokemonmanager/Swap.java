package com.example.pokemonmanager;

import java.util.Date;


public class Swap {
    private Trainer trainer1;
    private Trainer trainer2;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private String id; // Unique identifier for the swap
    private Date date; // Date of the swap


    //defining custom exceptions as inner classes because they only occur in methods of Swap
    public class SwapNotAllowedException extends Exception {
        public SwapNotAllowedException(String message) {
            super(message);
        }
    }

    public class SameTrainerException extends Exception {
        public SameTrainerException(String message) {
            super(message);
        }
    }

    public Swap(Trainer trainer1, Trainer trainer2, Pokemon pokemon1, Pokemon pokemon2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.id = generateSwapId(); // Generate a unique ID for the swap
        this.date = new Date(); // Set the current date and time
    }

    private String generateSwapId() {
        return "SWAP-" + System.currentTimeMillis(); // Simple unique ID based on current time
    }

    public void execute() throws SameTrainerException, SwapNotAllowedException {
        if (!pokemon1.isSwapAllowed() || !pokemon2.isSwapAllowed()) {
            throw new SwapNotAllowedException("Pokemon " + (pokemon1.isSwapAllowed() ? pokemon2.getName() : pokemon1.getName()) + " ist nicht zum Tauschen freigegeben.");
        }

        if (trainer1 == trainer2) {
            throw new SameTrainerException("Pokemon " + pokemon1.getName() + " kann nicht mit " + pokemon2.getName() + " getauscht werden, da beide den/die TrainerIn '" + trainer1.getFirstName() + " " + trainer1.getLastName() + "' haben.");
        }

        // Perform the swap
        Trainer tempTrainer = pokemon1.getTrainer();
        pokemon1.linkToTrainer(trainer2);
        pokemon2.linkToTrainer(tempTrainer);

        // Record the swap in each Pokemon's swap history
        pokemon1.addSwap(this);
        pokemon2.addSwap(this);

        System.out.println("Swap executed between " + pokemon1.getName() + " and " + pokemon2.getName());
    }
}