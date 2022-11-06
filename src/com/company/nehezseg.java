package com.company;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public interface nehezseg {
    public static double nehezseget_szamol(graf g, int csucsokSzama, int sorokSzama){
        double nehezseg = 0;
        double elekSzama = g.elekSzama;
        double nullElek = 0;
        double visszaElek = 0;
        double vagasErosseg = 0;
        double szoras = 0;
        double[] kapacitasok = new double[g.elekSzama];
        double atlag = 0;
        double hosszuElek = 0;
        int sorok = sorokSzama;
        if(sorokSzama %2 == 0)
            ++sorok;
        for(int i = 0; i < g.elekSzama; ++i){
            kapacitasok[i] = g.elek.get(i).kapacitas;
            atlag += g.elek.get(i).kapacitas;
            if(g.elek.get(i).max == 0 && g.elek.get(i).kapacitas != 0)
                nullElek++;
            if(g.elek.get(i).kapacitas == 0)
                elekSzama--;
            int kezdo = 0, veg = 0;
            for(int j = 0; j < sorok; ++j){
                for(int k = 0; k < (csucsokSzama-2)/sorokSzama + 2; ++k) {
                    if(g.elek.get(i).kezdo == g.csucsok[j][k])
                        kezdo = k;
                    if(g.elek.get(i).veg == g.csucsok[j][k])
                        veg = k;
                }
            }
            if(kezdo > veg && g.elek.get(i).kapacitas != 0)
                visszaElek++;
            if(abs(kezdo-veg) == 2)
                hosszuElek++;

        }
        for(int i = 0; i < sorok; ++i){
            for(int j = 2; j < (csucsokSzama-2)/sorokSzama + 2; ++j) {
                int erosseg = 0;
                for(int k = 0; k < g.vagasCsucsSzam; ++k){
                    if(g.csucsok[i][j] == g.vagas[k])
                        ++erosseg;
                }
                for(int k = 0; k < g.vagasCsucsSzam; ++k){
                    if (g.csucsok[i][j - 1] == g.vagas[k]) {
                        erosseg = 0;
                        break;
                    }
                }
                for(int k = 0; k < g.vagasCsucsSzam; ++k){
                    if(g.csucsok[i][j-2] == g.vagas[k] && erosseg ==1)
                        ++erosseg;
                }
                vagasErosseg += erosseg;
            }
        }
        atlag /= g.elekSzama;
        for(int i = 0; i < g.elekSzama; ++i){
            szoras += (kapacitasok[i] - atlag) * (kapacitasok[i] - atlag);
        }
        szoras /= g.elekSzama;
        szoras = sqrt(szoras);
        szoras /= 8;
        if(szoras < 1)
            szoras = 1;
        if(szoras > 1.25)
            szoras = 1.25;
        double maxEl = (maxElek(csucsokSzama, sorokSzama));
        double maxVagasErosseg = sorokSzama*((csucsokSzama-2)/sorokSzama - 1) * 0.35;
        if(vagasErosseg > maxVagasErosseg)
            maxVagasErosseg = vagasErosseg;
        double maxVisszaElek = maxEl - (csucsokSzama-2)/sorokSzama*(sorokSzama-1);
        maxEl *= 0.5;
        if(elekSzama- csucsokSzama - sorokSzama - 2 - nullElek/2 > maxEl)
            maxEl = elekSzama - csucsokSzama - sorokSzama - 2- nullElek/2;
        if(nullElek > (elekSzama - csucsokSzama - sorokSzama - 2)/2)
            nullElek = (elekSzama - csucsokSzama - sorokSzama - 2)/2;
        double csSzam = csucsokSzama;
        if(csSzam > 20)
            csSzam = 20;
        double maxHosszuelek = (4 + ((csucsokSzama-2)/sorokSzama*2-4)* (sorokSzama-1)) * 0.7;
        if(hosszuElek > maxHosszuelek)
            maxHosszuelek = hosszuElek;
        nehezseg = ((elekSzama - csucsokSzama - sorokSzama - 2 - nullElek/2)/maxEl*3 + hosszuElek/maxHosszuelek + visszaelNehezseg(maxVisszaElek, visszaElek)*2 + csSzam/20*2 + vagasErosseg/maxVagasErosseg*2) / szoras;
        //System.out.println(nehezseg + " " + (elekSzama - csucsokSzama - sorokSzama - 2 - nullElek/2)/maxEl + " " + hosszuElek/maxHosszuelek + " " + visszaelNehezseg(maxVisszaElek, visszaElek) + " " + vagasErosseg/maxVagasErosseg + " " + szoras);
        return nehezseg;
    }

    public static double maxElek(int csucsokSzama, int sorokSzama){
        int maxel = 0;
        int csucsSorban = (csucsokSzama-2)/sorokSzama;
        maxel += csucsSorban * (sorokSzama-1);
        if(csucsSorban > 1)
            maxel += (csucsSorban*2-2) * (sorokSzama-1);
        if(csucsSorban > 2)
            maxel += (csucsSorban*2-4) * (sorokSzama-1);
        return maxel;
    }

    public static double visszaelNehezseg(double maxVisszaElek, double visszaElek){
        double kul = abs(maxVisszaElek/3-visszaElek);
        if(kul > maxVisszaElek/3)
            kul = maxVisszaElek/3;
        double nehezseg = 1 - (kul / maxVisszaElek * 3);
        nehezseg *= 1.8;
        if(nehezseg > 1)
            nehezseg = 1;
        return nehezseg;
    }
}
