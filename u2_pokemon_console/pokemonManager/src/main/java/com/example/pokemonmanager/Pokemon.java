package com.example.pokemonmanager;


public class Pokemon {

    private String name;
    private Type type;
    private final int number;
    private static int nextNumber;

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

    //implements SF: Show Pokemon Details in Terminal
    @Override
    public String toString() {
        return "Pokemon(" + getNumber() + ") '" + getName() + "' of type '" + getType();
    }

    public static void main(String[] args) {
        Pokemon p;
        p = new Pokemon("Glurak", Type.FIRE);
        System.out.println(p);
    }
}
