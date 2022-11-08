package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public interface fajlt_olvas {
    public static void olvas() throws IOException {
        int sorokSzama = 0;
        int csucsokSzama = 0;
        int feladatNehezseg = 0;
        int elekSzama = 0;
        int vagasCsucsSzam = 0;
        int random = 0;
        graf graf = null;
        File file = new File("in.txt");
        try {
            Scanner sc = new Scanner(file);
            random = sc.nextInt();
            sorokSzama = sc.nextInt();
            csucsokSzama = sc.nextInt();
            int csucsSorSzam = (csucsokSzama-2)/sorokSzama;
            csucsokSzama = csucsSorSzam*sorokSzama + 2;
            feladatNehezseg = sc.nextInt();
            if(random == 0) {
                elekSzama = sc.nextInt();
                vagasCsucsSzam = sc.nextInt();
                graf = new graf(csucsokSzama, vagasCsucsSzam, elekSzama, sorokSzama);
                for (int i = 0; i < elekSzama; ++i) {
                    graf.elek.add(new el(sc.next().charAt(0), sc.next().charAt(0), false));
                }
                for (int i = 0; i < vagasCsucsSzam; ++i) {
                    graf.vagas[i] = sc.next().charAt(0);
                }
            }
            else {
                graf = new graf(csucsokSzama, sorokSzama);
                vagas.vagast_sorsol(graf, csucsokSzama, sorokSzama);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        folyamgeneralas.general(elekSzama, csucsokSzama, sorokSzama, graf, random, feladatNehezseg, vagasCsucsSzam);
    }
}
