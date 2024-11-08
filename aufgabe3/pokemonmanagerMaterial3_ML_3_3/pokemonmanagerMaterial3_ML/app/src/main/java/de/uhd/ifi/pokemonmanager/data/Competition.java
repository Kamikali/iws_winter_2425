package de.uhd.ifi.pokemonmanager.data;

import android.util.Log;
import java.util.Date;

public class Competition extends Swap {
    private static final String TAG = "competition";
    private Pokemon winner = null;
    private Pokemon loser = null;

    // Implements SF: Execute Competition in Logcat
    @Override
    public void execute(Pokemon p1, Pokemon p2) {
        if (p1.getTrainer() != p2.getTrainer()) {
            setDate(new Date());

            // Game logic: Generate scores based on type and random factor
            double score1 = (getEffectivity(p1.getType(), p2.getType())) * Math.random();
            double score2 = (getEffectivity(p1.getType(), p2.getType())) * Math.random();

            Log.i(TAG, String.format("Pokemon '%s' has score: %f", p1, score1));
            Log.i(TAG, String.format("Pokemon '%s' has score: %f", p2, score2));

            if (score1 > score2) {
                // p1 wins -> assign p2 to p1's trainer
                p2.getTrainer().getPokemons().remove(p2);
                p1.getTrainer().addPokemon(p2);
                setWinner(p1);
                setLoser(p2);
                Log.i(TAG, String.format("Pokemon '%s' wins!", p1));
            } else if (score1 < score2) {
                // p2 wins -> assign p1 to p2's trainer
                p1.getTrainer().getPokemons().remove(p1);
                p2.getTrainer().addPokemon(p1);
                setWinner(p2);
                setLoser(p1);
                Log.i(TAG, String.format("Pokemon '%s' wins!", p2));
            } else {
                // Draw case
                Log.i(TAG, "Draw, no Pokemon wins!");
            }

            // Store in competition history
            p2.addCompetition(this);
            p1.addCompetition(this);

        } else {
            Log.e(TAG, String.format("No competition: Trainers '%s' == '%s' are identical!", p1.getTrainer(), p2.getTrainer()));
        }
    }

    public double getEffectivity(Type myType, Type opponentType){
        switch (myType) {
            case NORMAL:
                return (opponentType == Type.ROCK || opponentType == Type.STEEL) ? 0.5 : (opponentType == Type.GHOST) ? 0 : 1;

            case FIRE:
                return (opponentType == Type.GRASS || opponentType == Type.ICE || opponentType == Type.BUG || opponentType == Type.STEEL) ? 2 : (opponentType == Type.FIRE || opponentType == Type.WATER || opponentType == Type.ROCK || opponentType == Type.DRAGON) ? 0.5 : 1;

            case WATER:
                return (opponentType == Type.FIRE || opponentType == Type.GROUND || opponentType == Type.ROCK) ? 2 : (opponentType == Type.WATER || opponentType == Type.GRASS || opponentType == Type.DRAGON) ? 0.5 : 1;

            case GRASS:
                return (opponentType == Type.WATER || opponentType == Type.GROUND || opponentType == Type.ROCK) ? 2 : (opponentType == Type.FIRE || opponentType == Type.GRASS || opponentType == Type.POISON || opponentType == Type.FLYING || opponentType == Type.BUG || opponentType == Type.DRAGON || opponentType == Type.STEEL) ? 0.5 : 1;

            case ELECTRIC:
                return (opponentType == Type.WATER || opponentType == Type.FLYING) ? 2 : (opponentType == Type.ELECTRIC || opponentType == Type.GRASS || opponentType == Type.DRAGON) ? 0.5 : (opponentType == Type.GROUND) ? 0 : 1;

            //Ähnliche Fälle für die übrigen Typen...
            default:
                return 1;  // Falls kein Typ passt
        }
    }

    public Pokemon getWinner() {
        return winner;
    }

    public void setWinner(Pokemon winner) {
        this.winner = winner;
    }

    public Pokemon getLoser() {
        return loser;
    }

    public void setLoser(Pokemon loser) {
        this.loser = loser;
    }
}