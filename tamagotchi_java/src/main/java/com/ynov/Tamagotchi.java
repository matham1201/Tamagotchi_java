package com.ynov;

public class Tamagotchi {
    private String name;
    private int hunger;
    private int happiness;

    public void setName(String name) {
        this.name = name;
    }

    public Tamagotchi(String name) {
        this.name = name;
        this.hunger = 0;
        this.happiness = 0;
    }

    public void feed() {
        hunger -= 1;
        if (hunger < 0) {
            hunger = 0;
        }
        System.out.println(name + " a été nourri.");
    }

    public void play() {
        happiness += 1;
        System.out.println(name + " a joué.");
    }

    public void checkStatus() {
        System.out.println("État de " + name + ":");
        System.out.println("Faim : " + hunger);
        System.out.println("Bonheur : " + happiness);
    }

    // Autres méthodes et logique du Tamagotchi
    
    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }
}
