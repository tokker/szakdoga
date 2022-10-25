package com.company;

import java.io.BufferedWriter;
import java.io.IOException;

public interface latex {
    public static void kiir(BufferedWriter writer, int sorokSzama, graf g, int csucsokSzama) throws IOException {
        writer.write("\\documentclass{article}\n" +
                "\\usepackage{tikz}\n" +
                "\\usetikzlibrary{arrows}\n" +
                "\\tikzset{>=triangle 45}\n" +
                "\n" +
                "\\begin{document}\n" +
                "\n" +
                "\\begin{center}\n" +
                "\t\n" +
                "\t\\tikz[->, csucs/.style={draw, fill=black!10, circle, minimum size={0.6cm}, inner sep=0cm, align=center, scale=0.9},\n" +
                "\tscale=3]\n" +
                "\t{\n" +
                "\t\t");
        int sorok = sorokSzama;
        double osszenyomas = 0.8;
        if(sorokSzama %2 == 0) {
            ++sorok;
            osszenyomas = 0.6;
        }
        for (int i = 0; i < sorok; ++i){
            for(int j = 0; j < (csucsokSzama-2)/sorokSzama + 2; ++j) {
                if(g.csucsok[i][j] != ' ')
                    writer.write("\\node [csucs] (" + g.csucsok[i][j] + ") at (" + j + "," + (sorok-i) * osszenyomas + ") {$" + g.csucsok[i][j] + "$};\n\t\t");
            }
        }
        writer.write("\n" +
                "\t\t\\path\n" +
                "\t\t\n" +
                "\t\t");
        for(int i = 0; i < g.elekSzama; ++i){
            if(g.elek.get(i).kapacitas != 0)
                writer.write("(" + g.elek.get(i).kezdo + ") edge node [above=-2pt, pos=0.5, sloped] {(" + g.elek.get(i).kapacitas + ")} (" + g.elek.get(i).veg + ")\n\t\t");
        }
        writer.write("\n" +
                "\t\t;}\n" +
                "\\end{center}\n\n" +
                "\\end{document}");
    }
}
