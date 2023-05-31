package com.ynov;

public class Tamagotchi {
    private String nom;
    private int vie;
    private int vieMax;
    private String couleur;
    private String nomJoueur;

    public Tamagotchi(String nom) {
        this.nom = nom;
        this.vie = 50;
        this.vieMax = 100;
    }

    public String getNom() {
        return nom;
    }

    public int getVie() {
        return vie;
    }

    public int getVieMax() {
        return vieMax;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public void nourrir() {
        if (vie < vieMax) {
            vie += 5;
            if (vie > vieMax) {
                vie = vieMax;
            }
        }
    }

    public void laver() {
        vie += 5;
        if (vie < 0) {
            vie = 0;
        }
    }

    public void faireToilette() {
        if (vie >= 100) {
            vie = vieMax;
        }
    }
}
