package com.company;

import java.util.ArrayList;
import java.util.Random;

public interface vagas {

    public static void vagast_sorsol(graf g, int csucsokSzama, int sorokSzama){
        int sorok = sorokSzama;
        Random r = new Random();
        ArrayList<Character> vagas = new ArrayList();
        if(sorokSzama %2 == 0)
            ++sorok;
        int vagasSzam = 1;
        vagas.add('s');
        boolean joVagas = false;
        while (!joVagas) {
            if (vagasSzam == 2){
                vagas.remove(1);
            }
            vagasSzam = 1;
            for (int i = 0; i < sorok; ++i) {
                for (int j = 0; j < (csucsokSzama - 2) / sorokSzama + 2; ++j) {
                    if (g.csucsok[i][j] != ' ' && g.csucsok[i][j] != 's' && g.csucsok[i][j] != 't') {
                        int x = r.nextInt(5);
                        if (x < 2) {
                            vagas.add(g.csucsok[i][j]);
                            ++vagasSzam;
                        }
                    }
                }
            }
            if(vagas.size() > 2){
                joVagas = true;
            }
        }

        g.vagasCsucsSzam = vagasSzam;
        g.vagas = new char[vagasSzam];
        for (int i = 0; i < vagasSzam; ++i) {
            g.vagas[i] = vagas.get(i);
        }
    }
}
