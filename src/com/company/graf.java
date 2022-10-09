package com.company;

import java.util.ArrayList;
import java.util.Random;

public class graf {
    public static char[][] csucsok;
    public ArrayList<el> elek;
    public char[] vagas;
    public int elekSzama;
    public int vagasCsucsSzam;
    public boolean rossz;

    public graf(int csucsokSzama, int vagasCsucsSzam, int elekSzama, int sorokSzama){
        rossz = false;
        int sorSzam = sorokSzama;
        if(csucsokSzama %2 == 0)
            ++sorSzam;
        csucsok = new char[sorSzam][(csucsokSzama-2)/sorokSzama + 2];
        elek = new ArrayList<el>();
        vagas = new char[vagasCsucsSzam];
        this.elekSzama = elekSzama;
        this.vagasCsucsSzam = vagasCsucsSzam;
        int szamlalo = 0;
        for (int i = 0; i < sorSzam; ++i) {
            for(int j = 0; j < (csucsokSzama-2)/sorokSzama + 2; ++j) {
                if(j == 0 || j == (csucsokSzama-2)/sorokSzama + 1){
                    if (i != (sorSzam-1)/2){
                        csucsok[i][j] = ' ';
                    }
                    else if (j == 0)
                        csucsok[i][j] = 's';
                    else
                        csucsok[i][j] = 't';
                }
                else if (i == (sorSzam-1)/2 && sorokSzama %2 == 0)
                    csucsok[i][j] = ' ';
                else {
                    csucsok[i][j] = (char) ('a' + szamlalo);
                    ++szamlalo;
                }
            }
        }
    }

    public graf(int csucsokSzama, int sorokSzama){
        rossz = false;
        int sorSzam = sorokSzama;
        if(csucsokSzama %2 == 0)
            ++sorSzam;
        csucsok = new char[sorSzam][(csucsokSzama - 2)/sorokSzama + 2];
        int szamlalo = 0;
        for (int i = 0; i < sorSzam; ++i) {
            for(int j = 0; j < (csucsokSzama-2)/sorokSzama + 2; ++j) {
                if(j == 0 || j == (csucsokSzama-2)/sorokSzama + 1){
                    if (i != (sorSzam-1)/2 ){
                        csucsok[i][j] = ' ';
                    }
                    else if (j == 0)
                        csucsok[i][j] = 's';
                    else
                        csucsok[i][j] = 't';
                }
                else if (i == (sorSzam-1)/2  && sorokSzama %2 == 0)
                    csucsok[i][j] = ' ';
                else {
                    csucsok[i][j] = (char) ('a' + szamlalo);
                    ++szamlalo;
                }
            }
        }
        elek = new ArrayList<el>();
        elekSzama = 0;
        for (int i = 0; i < sorSzam; ++i) {
            for (int j = 0; j < (csucsokSzama - 2) / sorokSzama + 1; ++j) {
                if(csucsok[i][j] != ' ' && csucsok[i][j+1] != ' ') {
                    elek.add(new el(csucsok[i][j], csucsok[i][j + 1], true));
                    elekSzama++;
                }
            }
            Random r = new Random();
            int x;
            if(i != (sorSzam-1)/2){
                elek.add(new el(csucsok[(sorSzam-1)/2][0], csucsok[i][1], true));
                elekSzama++;
                elek.add(new el(csucsok[i][(csucsokSzama - 2) / sorokSzama], csucsok[(sorSzam-1)/2][(csucsokSzama - 2) / sorokSzama + 1], true));
                elekSzama++;
                x = r.nextInt(3);
                if(x == 0){
                    elek.add(new el(csucsok[(sorSzam-1)/2][0], csucsok[i][2], false));
                    elekSzama++;
                }
                x = r.nextInt(3);
                if(x == 0){
                    elek.add(new el(csucsok[i][(csucsokSzama - 2) / sorokSzama - 1], csucsok[(sorSzam-1)/2][(csucsokSzama - 2) / sorokSzama + 1], false));
                    elekSzama++;
                }
            }
            for (int j = 1; j < (csucsokSzama - 2) / sorokSzama + 1; ++j) {
                if(csucsok[i][j] != ' ' && i < sorSzam - 1) {
                    int a = 1;
                    if(csucsok[i+1][j] == ' ') ++a;
                    if(j > 2){
                        x = r.nextInt(8);
                        if(x == 0) {
                            elek.add(new el(csucsok[i][j], csucsok[i+a][j-2], false));
                            elekSzama++;
                        }
                        else if(x < 3) {
                            elek.add(new el(csucsok[i+a][j-2], csucsok[i][j], false));
                            elekSzama++;
                        }
                    }
                    if(j > 1){
                        x = r.nextInt(6);
                        if(x == 0) {
                            elek.add(new el(csucsok[i][j], csucsok[i+a][j-1], false));
                            elekSzama++;
                        }
                        else if(x < 3) {
                            elek.add(new el(csucsok[i+a][j-1], csucsok[i][j], false));
                            elekSzama++;
                        }
                    }
                    x = r.nextInt(4);
                    if(x == 0) {
                        elek.add(new el(csucsok[i][j], csucsok[i+a][j], false));
                        elekSzama++;
                    }
                    if(x == 1) {
                        elek.add(new el(csucsok[i+a][j], csucsok[i][j], false));
                        elekSzama++;
                    }
                    if(j < (csucsokSzama - 2) / sorokSzama){
                        x = r.nextInt(6);
                        if(x < 2) {
                            elek.add(new el(csucsok[i][j], csucsok[i+a][j+1], false));
                            elekSzama++;
                        }
                        else if(x == 2) {
                            elek.add(new el(csucsok[i+a][j+1], csucsok[i][j], false));
                            elekSzama++;
                        }
                    }
                    if(j < (csucsokSzama - 2) / sorokSzama - 1){
                        x = r.nextInt(8);
                        if(x < 2) {
                            elek.add(new el(csucsok[i][j], csucsok[i+a][j+2], false));
                            elekSzama++;
                        }
                        else if(x == 2) {
                            elek.add(new el(csucsok[i+a][j+2], csucsok[i][j], false));
                            elekSzama++;
                        }
                    }
                }
            }
        }
    }

    public void vagast_csokkent(){
        for (int i = 0; i < vagasCsucsSzam; ++i) {
            for (int j = 0; j < elekSzama; ++j) {
                if (elek.get(j).kezdo == vagas[i]) {
                    elek.get(j).kapacitas = 0;
                    elek.get(j).vagas = true;
                }
            }
        }
        Random r = new Random();
        for (int i = 0; i < vagasCsucsSzam; ++i) {
            for (int j = 0; j < elekSzama; ++j) {
                if (elek.get(j).veg == vagas[i]) {
                    elek.get(j).kapacitas = r.nextInt(4) + 1;
                    elek.get(j).vagas = false;
                }
            }
        }
    }

    void utat_novel(int csucsokSzama, char elso, char utolso){
        int[] utvonal = new int[elekSzama];
        for (int i=0; i< elekSzama; ++i)
            utvonal[i] = 0;
        char[] bejartCsucsok = new char[csucsokSzama];
        for (int i=1; i< csucsokSzama; ++i)
            bejartCsucsok[i] = ' ';
        el[] rosszElek = new el[elekSzama];
        for (int i=0; i< elekSzama; ++i)
            rosszElek[i] = new el(' ', ' ', false);
        bejartCsucsok[0] = elso;
        char utolsoCsucs = elso;
        int bejartSzam = 0;
        boolean vege = false;
        Random r = new Random();
        int noveles;
        if(elso == 's' && utolso == 't')
            noveles = r.nextInt(3)+1;
        else
            noveles = 1;
        char[] rosszCsucsok = new char[csucsokSzama];
        for (int i=0; i< csucsokSzama; ++i)
            rosszCsucsok[i] = ' ';
        while (!vege){
            int[] joElek = new int[elekSzama];
            int joElekSzama = 0;
            for(int i = 0; i < elekSzama; ++i){
                if(this.elek.get(i).kezdo == utolsoCsucs || this.elek.get(i).veg == utolsoCsucs){
                    boolean vissza = false;
                    boolean vissza2 = false;
                    if(this.elek.get(i).kezdo == utolsoCsucs) {
                        for (int j = 0; j < vagasCsucsSzam; ++j){
                            if(vagas[j] == this.elek.get(i).veg)
                                vissza = true;
                            if(vagas[j] == utolsoCsucs)
                                vissza2 = true;
                        }
                    }
                    if(!vissza || vissza2) {
                        joElek[joElekSzama] = i;
                        ++joElekSzama;
                    }
                }
            }
            Random rand = new Random();
            boolean ok = false;
            for (int i = 0; i < joElekSzama && !ok; ){
                int valasztott = rand.nextInt(joElekSzama);
                char kov = elso;
                if(this.elek.get(joElek[valasztott]).kezdo == utolsoCsucs)
                    kov = this.elek.get(joElek[valasztott]).veg;
                else if(this.elek.get(joElek[valasztott]).kapacitas > noveles + 3)
                    kov = this.elek.get(joElek[valasztott]).kezdo;
                boolean voltMar = false;
                for(int j = 0; j < csucsokSzama; ++j){
                    if(bejartCsucsok[j] == kov || rosszCsucsok[j] == kov)
                        voltMar = true;
                }
                if(!voltMar){
                    utolsoCsucs = kov;
                    if(this.elek.get(joElek[valasztott]).kezdo == utolsoCsucs) {
                        this.elek.get(joElek[valasztott]).kapacitas -= noveles;
                        this.elek.get(joElek[valasztott]).max -= noveles;
                    }
                    else {
                        this.elek.get(joElek[valasztott]).kapacitas += noveles;
                        this.elek.get(joElek[valasztott]).max += noveles;
                    }
                    utvonal[bejartSzam]=joElek[valasztott];
                    ++bejartSzam;
                    bejartCsucsok[bejartSzam] = kov;
                    if(kov==utolso)
                        vege = true;
                    ok = true;
                }
                else{
                    int[] masolat = new int[elekSzama];
                    for (int j = 0; j < elekSzama; ++j){
                        if(j<valasztott)
                            masolat[j] = joElek[j];
                        else if(j>valasztott){
                            masolat[j-1] = joElek[j];
                        }
                    }
                    for(int j = 0; j < elekSzama; ++j){
                        joElek[j] = masolat[j];
                    }
                    --joElekSzama;
                }
            }
            if(!ok){
                if(bejartSzam < 1){
                    rossz = true;
                    return;
                }
                if(this.elek.get(utvonal[bejartSzam-1]).kezdo == utolsoCsucs) {
                    this.elek.get(utvonal[bejartSzam - 1]).kapacitas += noveles;
                    this.elek.get(utvonal[bejartSzam - 1]).max += noveles;
                }
                else {
                    this.elek.get(utvonal[bejartSzam - 1]).kapacitas -= noveles;
                    this.elek.get(utvonal[bejartSzam - 1]).max -= noveles;
                }
                for(int i = 0; i < elekSzama; ++i){
                    for(int j = 0; j < csucsokSzama; ++j) {
                        if ((this.elek.get(i).kezdo == utolsoCsucs && this.elek.get(i).veg == rosszCsucsok[j]) || (this.elek.get(i).veg == utolsoCsucs && this.elek.get(i).kezdo == rosszCsucsok[j])) {
                            for(int k = 0; k < elekSzama; ++k) {
                                if ((utolsoCsucs == rosszElek[k].kezdo && rosszCsucsok[j] == rosszElek[k].veg) || (utolsoCsucs == rosszElek[k].veg && rosszCsucsok[j] == rosszElek[k].kezdo)) {
                                    for(int l = 0; l < elekSzama; ++l) {
                                        if (rosszElek[l].kezdo == rosszCsucsok[j] || rosszElek[l].veg == rosszCsucsok[j])
                                            rosszElek[l].kezdo = ' ';
                                    }
                                    rosszCsucsok[j] = ' ';
                                }
                            }
                        }
                    }
                }
                for(int i = 0; i < csucsokSzama; ++i) {
                    if(rosszCsucsok[i] == ' ') {
                        rosszCsucsok[i] = utolsoCsucs;
                        i = csucsokSzama;
                    }
                }
                for(int i = 0; i < elekSzama; ++i) {
                    if(rosszElek[i].kezdo == ' ') {
                        rosszElek[i].kezdo = this.elek.get(utvonal[bejartSzam-1]).kezdo;
                        rosszElek[i].veg = this.elek.get(utvonal[bejartSzam-1]).veg;
                        i = elekSzama;
                    }
                }
                utvonal[bejartSzam-1] = 0;
                bejartCsucsok[bejartSzam] = ' ';
                --bejartSzam;
                utolsoCsucs = bejartCsucsok[bejartSzam];
            }
        }
    }
}
