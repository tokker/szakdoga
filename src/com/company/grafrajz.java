package com.company;

import java.io.BufferedWriter;
import java.io.IOException;

public interface grafrajz {

    public static void kirajzol(BufferedWriter writer, int sorokSzama, graf g, int csucsokSzama) throws IOException {
        int sorok = sorokSzama;
        if(sorokSzama %2 == 0)
            ++sorok;
        for (int i = 0; i < sorok; ++i){
            for(int j = 0; j < (csucsokSzama-2)/sorokSzama + 2; ++j) {
                writer.write(g.csucsok[i][j]);
                if(j != 0 && j != (csucsokSzama-2)/sorokSzama)
                    writer.write(" ");
                else if(sorokSzama % 2 == 1)
                    writer.write(" ");

            }
            writer.write("\n");
        }

    }
}
