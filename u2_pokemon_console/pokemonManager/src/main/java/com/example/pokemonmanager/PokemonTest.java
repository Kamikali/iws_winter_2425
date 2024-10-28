package com.example.pokemonmanager;

public class PokemonTest {
    public static void main(String[] args) {
        test21(); //method names are shitty because of sonar lint. would have called them task_2_1x etc
        test22();
    }
    private static void test21(){
        // Test 1: Create a trainer and add Pokémon
        Trainer trainer = new Trainer("John", "Mayer");

        Pokemon pikachu = new Pokemon("Pikachu", Type.ELECTRIC);
        Pokemon charmander = new Pokemon("Charmander", Type.FIRE);
        Pokemon bulbasaur = new Pokemon("Bulbasaur", Type.POISON);

        // Link Pokémon to trainer
        pikachu.link_to_trainer(trainer);
        charmander.link_to_trainer(trainer);
        bulbasaur.link_to_trainer(trainer);

        // Display trainer details
        trainer.listPokemons();

        // Test 2: List Pokémon by type
        trainer.listPokemonsByType(Type.FIRE);

        // Test 3: Show Pokémon at a specific index
        trainer.showPokemon(0); // pikachu

        // Test 4: Show Pokémon at an invalid index
        trainer.showPokemon(3); // invalid index

        // Test 5: List Pokémon of a type that doesn't exist
        trainer.listPokemonsByType(Type.WATER); // None
    }

    private static void test22(){
        // Test 1: Create trainers and Pokémon
        Trainer trainer1 = new Trainer("John", "Mayer");
        Trainer trainer2 = new Trainer("Jane", "Doe");

        Pokemon pikachu = new Pokemon("Pikachu", Type.ELECTRIC);
        Pokemon charmander = new Pokemon("Charmander", Type.FIRE);
        Pokemon bulbasaur = new Pokemon("Bulbasaur", Type.GRASS);

        // Link Pokémon to trainers
        pikachu.link_to_trainer(trainer1);
        charmander.link_to_trainer(trainer2);
        bulbasaur.link_to_trainer(trainer2);

        pikachu.setSwapAllowed(true);
        charmander.setSwapAllowed(true);

        // Test 2: Swap Pokémon that are allowed to swap
        System.out.println("Test 1: Swap Pikachu with Charmander:");
        try {
            Swap swap1 = new Swap(trainer1, trainer2, pikachu, charmander);
            swap1.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Test 3: Attempt to swap Pokémon that are not allowed to swap
        System.out.println("\nTest 2: Attempt to swap Bulbasaur (not allowed) with Charmander:");
        bulbasaur.setSwapAllowed(false); // Bulbasaur is not allowed to swap
        try {
            Swap swap2 = new Swap(trainer2, trainer1, bulbasaur, charmander);
            swap2.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Should show the not allowed message
        }

        // Test 4: Attempt to swap Pokémon from the same trainer
        bulbasaur.setSwapAllowed(true);
        System.out.println("\nTest 3: Attempt to swap Pikachu with Bulbasaur (same trainer):");
        try {
            Swap swap3 = new Swap(trainer2, trainer2, bulbasaur, pikachu);
            swap3.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Should show the same trainer message
        }
    }
}