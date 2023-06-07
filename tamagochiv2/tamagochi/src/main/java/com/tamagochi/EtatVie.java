package com.tamagochi;

public class EtatVie {
    private String nom;
    private int duree;
    private EtatVie etatSuivant;

    public EtatVie(String nom, int duree) {
        this.nom = nom;
        this.duree = duree;
        this.etatSuivant = null;
    }

    public String getNom() {
        return nom;
    }

    public int getDuree() {
        return duree;
    }

    public EtatVie getEtatSuivant() {
        return etatSuivant;
    }

    public void setEtatSuivant(EtatVie etatSuivant) {
        this.etatSuivant = etatSuivant;
    }
    
}
