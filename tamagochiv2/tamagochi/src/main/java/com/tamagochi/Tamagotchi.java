package com.tamagochi;

import java.util.ArrayList;
import java.util.List;

public class Tamagotchi {
    private EtatVie etatVie;
    private int bonheur;
    private int tempsDepuisDerniereAction;
    private boolean environnementSale;
    private List<Runnable> tamagotchiUpdateListeners;
    private boolean estMalade;


    public Tamagotchi() {
        etatVie = EtatVie.OEUF;
        bonheur = 15;
        tempsDepuisDerniereAction = 0;
        environnementSale = false;
        tamagotchiUpdateListeners = new ArrayList<>();
    }

    public void setOnTamagotchiUpdate(Runnable listener) {
        tamagotchiUpdateListeners.add(listener);
    }

    private void notifyTamagotchiUpdateListeners() {
        tamagotchiUpdateListeners.forEach(Runnable::run);
    }

    public void incrementerTemps() {
        tempsDepuisDerniereAction++;
        
        // Vérifier les conditions pour changer l'état de vie
        if (etatVie == EtatVie.OEUF && tempsDepuisDerniereAction == 1) {
            etatVie = EtatVie.BEBE;
            tempsDepuisDerniereAction = 0;
        } else if (etatVie == EtatVie.BEBE && tempsDepuisDerniereAction >= 4 && bonheur >= 40) {
            etatVie = EtatVie.ADULTE;
            tempsDepuisDerniereAction = 0;
        } else if (etatVie == EtatVie.ADULTE && tempsDepuisDerniereAction >= 15) {
            etatVie = EtatVie.VIEILLARD;
            tempsDepuisDerniereAction = 0;
            
            // Le Tamagotchi devient malade avec une chance de 1 sur 3
            if (Math.random() < 1.0 / 3.0) {
                estMalade = true;
            }
        } else if (etatVie == EtatVie.VIEILLARD) {
            if (tempsDepuisDerniereAction >= 1) {
                etatVie = EtatVie.MORT;
                tempsDepuisDerniereAction = 0;
                bonheur = 0;
            }
        }
    
        // Mettre à jour l'environnement si le Tamagotchi est vivant
        if (isAlive()) {
            if (tempsDepuisDerniereAction == 1) {
                environnementSale = true;
            } else if (tempsDepuisDerniereAction > 1) {
                bonheur -= 3;
                if (bonheur < 0) {
                    bonheur = 0;
                }
            }
        }
    
        notifyTamagotchiUpdateListeners();
    
        // Réinitialiser l'état et le bonheur si le Tamagotchi est mort
        if (etatVie == EtatVie.MORT) {
            etatVie = EtatVie.OEUF;
            bonheur = 15;
            environnementSale = false;
            nombreActionsNourrir = 0; // Réinitialiser le nombre d'actions de nourrir
            nombreJoursOublies = 0; // Réinitialiser le nombre de jours d'oubli
            nombreActionsNettoyer = 0;
        }
    
        nombreActionsNourrir = 0; // Réinitialiser le nombre d'actions de nourrir à chaque unité de temps
        nombreActionsJouer = 0;
        nombreActionsNettoyer = 0; // Variable pour compter le nombre d'actions de nettoyer
    }
    
    

    private boolean isAlive() {
        return etatVie != EtatVie.OEUF && etatVie != EtatVie.MORT;
    }

    private int nombreActionsJouer = 0; // Variable pour compter le nombre d'actions de jouer

    public void jouer() {
        if (isAlive() && nombreActionsJouer < 3) { // Vérifier si le Tamagotchi est en vie et le nombre d'actions de jouer est inférieur à 3
            bonheur += 3;
            tempsDepuisDerniereAction = 0;
            if (bonheur > 50) {
                bonheur = 50;
            }
            nombreActionsJouer++; // Incrémenter le nombre d'actions de jouer
            notifyTamagotchiUpdateListeners();
        }
    }

    private int nombreActionsNourrir = 0; // Variable pour compter le nombre d'actions de nourrir
    private int nombreJoursOublies = 0; // Variable pour compter le nombre de jours d'oubli
    private int nombreActionsNettoyer = 0; // Variable pour compter le nombre d'actions de nettoyer

    public void nourrir() {
        if (isAlive() && nombreActionsNourrir == 0) { // Vérifier si le Tamagotchi est en vie et si l'action de nourrir n'a pas encore été effectuée
            if (environnementSale && !nourriDepuisDernierNettoyage()) { // Vérifier si l'environnement est sale et si le Tamagotchi n'a pas été nourri depuis le dernier nettoyage
                bonheur -= (tempsDepuisDerniereAction * 5); // Diminuer le bonheur en fonction du nombre de jours d'oubli
            } else {
                bonheur -= 3; // Le Tamagotchi perd 3 points de bonheur s'il est nourri et que l'environnement est sale ou s'il est nourri après un nettoyage
            }
            tempsDepuisDerniereAction = 0;
            if (bonheur < 0) {
                bonheur = 0;
            }
            nombreActionsNourrir++; // Incrémenter le nombre d'actions de nourrir
            notifyTamagotchiUpdateListeners();
        }
    }
    
    private boolean nourriDepuisDernierNettoyage() {
        return nombreActionsNourrir > 0 && nombreActionsNourrir < nombreActionsNettoyer;
    }

    public void nettoyer() {
        if (isAlive() && environnementSale) { // Vérifier si le Tamagotchi est en vie et si l'environnement est sale
            if (nombreActionsNourrir == 0) { // Vérifier si le Tamagotchi n'a pas été nourri dans la même unité de temps
                bonheur -= 3;
            }
            tempsDepuisDerniereAction = 0;
            environnementSale = false;
            if (bonheur < 0) {
                bonheur = 0;
            }
            nombreActionsNettoyer++; // Incrémenter le nombre d'actions de nettoyer
            notifyTamagotchiUpdateListeners();
        }
    }
    public void soigner() {
        if (etatVie == EtatVie.VIEILLARD && isAlive() && etatVie != EtatVie.MORT) { // Vérifier si le Tamagotchi est en vie et n'est pas déjà mort et au stade de vieillard
            if (Math.random() < 1.0 / 3.0) { // Une chance sur trois de tomber malade
                etatVie = EtatVie.MORT; // Si le Tamagotchi n'est pas malade et qu'on oublie de le soigner, il meurt
                bonheur = 0;
                if (etatVie == EtatVie.MORT) {
                // Réinitialiser l'état et le bonheur pour recommencer le jeu
                etatVie = EtatVie.OEUF;
                bonheur = 15;
                environnementSale = false;
                nombreActionsNourrir = 0; // Réinitialiser le nombre d'actions de nourrir
                nombreJoursOublies = 0; // Réinitialiser le nombre de jours d'oubli
                nombreActionsNettoyer = 0;
                }
                notifyTamagotchiUpdateListeners();
            } else {
                etatVie = EtatVie.VIEILLARD; // Si le Tamagotchi n'est pas malade, il reste à l'état de Vieillard
            }
    
            tempsDepuisDerniereAction = 0;
            notifyTamagotchiUpdateListeners();
        }
    }
    

    public EtatVie getEtatVie() {
        return etatVie;
    }

    public int getBonheur() {
        return bonheur;
    }

    public String getStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("État du Tamagotchi:\n");
        sb.append("Vie: ").append(etatVie.toString()).append("\n");
        sb.append("Bonheur: ").append(bonheur).append("\n");
        sb.append("Temps depuis dernière action: ").append(tempsDepuisDerniereAction).append("\n");
        sb.append("Environnement sale: ").append(environnementSale ? "Oui" : "Non").append("\n");
        sb.append("Malade: ").append(estMalade ? "Oui" : "Non").append("\n");
        return sb.toString();
    }
    

    public void play() {
        jouer();
    }

    public void feed() {
        nourrir();
    }

    public void clean() {
        nettoyer();
    }
    public boolean isMalade() {
        return estMalade;
    }
    
    public int getTempsDepuisDerniereAction() {
        return tempsDepuisDerniereAction;
    }

    public String afficherEtat() {
        return getStatus();
    }

    public enum EtatVie {
        OEUF,
        BEBE,
        ADULTE,
        VIEILLARD,
        MALADE,
        MORT
    }

    public String getPropreteEnvironnement() {
        if (environnementSale == true  && nombreActionsNourrir > 0) {
            return "sale";
        } else {
            return "propre";
        }
    }
    
}
