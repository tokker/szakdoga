package com.company;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public interface folyamgeneralas {
    public static void general(int elekSzama, int csucsokSzama, int sorokSzama, graf graf, int random, int feladatNehezseg, int vagasCsucsSzam) throws IOException {
        graf g2 = null;
        assert graf != null;
        for(int i = 0; i < 1000; ++i){
            graf.vagast_csokkent();
            for (int j = 0; j < graf.elekSzama*1.3; ++j)
                graf.utat_novel(csucsokSzama, 's', 't');
            for (int j = 0; j < graf.elekSzama; ++j){
                if(graf.elek.get(j).alap && graf.elek.get(j).kapacitas == 0){
                    graf.rossz = true;
                }
            }
            if(graf.rossz)
                --i;
            else if(i == 0 || Math.abs(nehezseg.nehezseget_szamol(graf, csucsokSzama, sorokSzama) - feladatNehezseg) < Math.abs(nehezseg.nehezseget_szamol(g2, csucsokSzama, sorokSzama) - feladatNehezseg)){
                g2 = graf;
            }
            if(random == 0){
                for (int j = 0; j < elekSzama; ++j) {
                    graf = new graf(csucsokSzama, vagasCsucsSzam, elekSzama, sorokSzama);
                    for (int k = 0; k < elekSzama; ++k) {
                        graf.elek.add(new el(g2.elek.get(k).kezdo, g2.elek.get(k).veg, false));
                    }
                    for (int k = 0; k < vagasCsucsSzam; ++k) {
                        graf.vagas[k] = g2.vagas[k];
                    }
                    Random r = new Random();
                    graf.elek.get(j).kapacitas = r.nextInt(4) + 1;
                    graf.elek.get(j).max = 0;
                }
            }
            else{
                graf = new graf(csucsokSzama, sorokSzama);
                vagas.vagast_sorsol(graf, csucsokSzama, sorokSzama);
            }
        }
        graf = g2;

        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        grafrajz.kirajzol(writer,sorokSzama, graf, csucsokSzama);
        writer.write("\n\n");

        for (int i = 0; i < graf.elekSzama; ++i){
            if(graf.elek.get(i).kapacitas != 0)
                writer.write(String.valueOf(graf.elek.get(i).kezdo) + " " + String.valueOf(graf.elek.get(i).veg) + " (" + graf.elek.get(i).kapacitas + ") " + graf.elek.get(i).max + "\n");
        }
        writer.write("\nMinimális vágás: ");
        for (int i = 0; i < graf.vagasCsucsSzam; ++i){
            writer.write(graf.vagas[i] + " ");
        }
        int vagasErtek = 0;
        for (int i = 0; i < graf.elekSzama; ++i){
            boolean vagasEl = false;
            for (int j = 0; j < graf.vagasCsucsSzam; ++j) {
                if (graf.elek.get(i).kezdo == graf.vagas[j]) {
                    vagasEl = true;
                    break;
                }
            }
            for (int j = 0; j < graf.vagasCsucsSzam; ++j) {
                if (graf.elek.get(i).veg == graf.vagas[j]) {
                    vagasEl = false;
                    break;
                }
            }
            if(vagasEl)
                vagasErtek += graf.elek.get(i).kapacitas;
        }
        writer.write("\nMinimális vágás értéke: " + vagasErtek);
        writer.close();
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("latex.tex"));
        latex.kiir(writer2,sorokSzama, graf, csucsokSzama);
        writer2.close();
        testGraphDraw.draw(elekSzama, csucsokSzama, sorokSzama, graf, random, feladatNehezseg, vagasCsucsSzam);
    }
}
