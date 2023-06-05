package com.tamagochi;

import java.io.Serializable;

class Tamagotchi implements Serializable {
    private static final long serialVersionUID = 1L;
    private int happiness;
    private int age;
    private int state;
    private int dirtyLevel;
    private int feedCount;

    public Tamagotchi() {
        happiness = 15;
        age = 0;
        state = 0;
        dirtyLevel = 0;
        feedCount = 0;
    }

    public void passTime() {
        age++;

        // Check the tamagotchi's state and update accordingly
        if (state == 0 && age == 1) {
            state = 1; // Egg hatches into baby
        } else if (state == 1 && feedCount >= 4 && happiness >= 40) {
            state = 2; // Baby becomes adult
        } else if (state == 2 && age >= 15) {
            state = 3; // Adult becomes old
        } else if (state == 3 && age >= 20) {
            state = 0; // Old tamagotchi dies
            resetTamagotchi();
        }

        // Decrease happiness based on dirty level
        if (dirtyLevel > 0) {
            happiness -= 3;
        }

        // Decrease happiness if not fed
        if (state != 0 && feedCount == 0) {
            int hungerMultiplier = (feedCount / 4) + 1;
            int happinessDecrease = hungerMultiplier * 5;
            happiness -= happinessDecrease;
        }

        // Reset feed count and dirty level
        feedCount = 0;
        dirtyLevel++;

        // Check if happiness reaches 0
        if (happiness <= 0) {
            state = 0;
            System.out.println("Votre tamagotchi est mort !");
            resetTamagotchi();
        }
    }

    public void play() {
        if (state == 2) {
            happiness += 3;
            System.out.println("Vous jouez avec votre tamagotchi !");
        } else {
            System.out.println("Impossible de jouer avec le tamagotchi dans cet état !");
        }
    }

    public void feed() {
        if (state != 0) {
            happiness += 5;
            feedCount++;
            System.out.println("Vous nourrissez votre tamagotchi !");
        } else {
            System.out.println("Impossible de nourrir le tamagotchi dans cet état !");
        }
    }

    public void clean() {
        if (dirtyLevel > 0) {
            happiness += 2;
            dirtyLevel = 0;
            System.out.println("Vous nettoyez l'environnement du tamagotchi !");
        } else {
            System.out.println("L'environnement du tamagotchi est déjà propre !");
        }
    }

    public void resetTamagotchi() {
        happiness = 15;
        age = 0;
        state = 0;
        dirtyLevel = 0;
        feedCount = 0;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getState() {
        return state;
    }
}
