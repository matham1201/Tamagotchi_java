package com.tamagochi;

import java.io.*;

public class Main {
    private static final String SAVE_FILE_PATH = "tamagotchi.ser";

    public static void main(String[] args) {
        Tamagotchi tamagotchi;

        try {
            tamagotchi = loadTamagotchi();
            System.out.println("Tamagotchi chargé !");
        } catch (IOException | ClassNotFoundException e) {
            tamagotchi = new Tamagotchi();
            System.out.println("Nouveau tamagotchi créé !");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Passer du temps");
            System.out.println("2. Jouer avec le tamagotchi");
            System.out.println("3. Nourrir le tamagotchi");
            System.out.println("4. Nettoyer l'environnement du tamagotchi");
            System.out.println("5. Quitter");

            try {
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        tamagotchi.passTime();
                        System.out.println("Le temps passe...");
                        break;
                    case 2:
                        tamagotchi.play();
                        break;
                    case 3:
                        tamagotchi.feed();
                        break;
                    case 4:
                        tamagotchi.clean();
                        break;
                    case 5:
                        saveTamagotchi(tamagotchi);
                        System.out.println("Tamagotchi enregistré. Au revoir !");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix invalide !");
                        break;
                }

                System.out.println("Bonheur du tamagotchi : " + tamagotchi.getHappiness());
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveTamagotchi(Tamagotchi tamagotchi) {
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILE_PATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(tamagotchi);
            objectOut.close();
            fileOut.close();
            System.out.println("Tamagotchi sauvegardé !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Tamagotchi loadTamagotchi() throws IOException, ClassNotFoundException {
        File file = new File(SAVE_FILE_PATH);

        if (file.exists()) {
            FileInputStream fileIn = new FileInputStream(SAVE_FILE_PATH);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Tamagotchi tamagotchi = (Tamagotchi) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            return tamagotchi;
        } else {
            throw new FileNotFoundException();
        }
    }
}
