package com.company;

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
        nehezseg = (elekSzama - nullElek*2 + visszaElek*4) / (double)csucsokSzama * (vagasErosseg + 1) / szoras * 20;
        return nehezseg;
    }
}
