package com.company;

import java.util.Random;

public class el {
    public char kezdo;
    public char veg;
    public int kapacitas;
    public int max;
    boolean vagas;
    boolean alap;

    public el(char kezdo, char veg, boolean alap){
        this.kezdo = kezdo;
        this.veg = veg;
        Random r = new Random();
        kapacitas = r.nextInt(4) + 1;
        vagas = false;
        max = 0;
        this.alap = alap;
    }
}
