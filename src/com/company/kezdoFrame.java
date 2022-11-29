package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class kezdoFrame extends JFrame {

    JFrame f;

    public kezdoFrame() throws IOException {
        f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel elso = new JLabel("Gráf generálása");
        elso.setBounds(350, 50, 100, 30);
        f.add(elso);
        JRadioButton r2=new JRadioButton("Fájlból");
        JRadioButton r1=new JRadioButton("Paraméterek megadásával", true);
        r1.setBounds(200,100,200,30);
        r2.setBounds(450,100,200,30);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);bg.add(r2);
        f.add(r1);f.add(r2);
        JLabel masodik = new JLabel("Gráf sorainak száma");
        masodik.setBounds(335, 200, 200, 30);
        f.add(masodik);
        JRadioButton s1=new JRadioButton("2", true);
        JRadioButton s2=new JRadioButton("3");
        JRadioButton s3=new JRadioButton("4");
        JRadioButton s4=new JRadioButton("5");
        s1.setBounds(150,250,100,30);
        s2.setBounds(300,250,100,30);
        s3.setBounds(450,250,100,30);
        s4.setBounds(600,250,100,30);
        ButtonGroup bg2=new ButtonGroup();
        bg2.add(s1);bg2.add(s2);bg2.add(s3);bg2.add(s4);
        f.add(s1);f.add(s2);f.add(s3);f.add(s4);
        JLabel harmadik = new JLabel("Gráf csúcsainak száma");
        harmadik.setBounds(335, 350, 200, 30);
        f.add(harmadik);
        JRadioButton cs1=new JRadioButton("6", true);
        JRadioButton cs2=new JRadioButton("8");
        JRadioButton cs3=new JRadioButton("10");
        JRadioButton cs4=new JRadioButton("12");
        cs1.setBounds(150,400,100,30);
        cs2.setBounds(300,400,100,30);
        cs3.setBounds(450,400,100,30);
        cs4.setBounds(600,400,100,30);
        ButtonGroup bg3=new ButtonGroup();
        bg3.add(cs1);bg3.add(cs2);bg3.add(cs3);bg3.add(cs4);
        f.add(cs1);f.add(cs2);f.add(cs3);f.add(cs4);
        JLabel negyedik = new JLabel("Feladat nehézsége");
        negyedik.setBounds(350, 500, 200, 30);
        f.add(negyedik);
        JSlider nehezseg = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        nehezseg.setMajorTickSpacing(1);
        nehezseg.setPaintTicks(true);
        nehezseg.setPaintLabels(true);
        nehezseg.setBounds(250, 550, 300, 60);
        f.add(nehezseg);
        JButton indit = new JButton("Feladat generálása");
        indit.setBounds(325, 650, 150, 50);
        f.add(indit);
        r2.addActionListener(new Fajlbol(true, masodik, s1, s2, s3, s4, harmadik, cs1, cs2, cs3, cs4, negyedik, nehezseg));
        r1.addActionListener(new Fajlbol(false, masodik, s1, s2, s3, s4, harmadik, cs1, cs2, cs3, cs4, negyedik, nehezseg));
        s1.addActionListener(new Sorszam(2, cs1, cs2, cs3, cs4));
        s2.addActionListener(new Sorszam(3, cs1, cs2, cs3, cs4));
        s3.addActionListener(new Sorszam(4, cs1, cs2, cs3, cs4));
        s4.addActionListener(new Sorszam(5, cs1, cs2, cs3, cs4));
        indit.addActionListener(new Indit(f, r2, s1, s2, s3, s4, cs1, cs2, cs3, cs4, nehezseg));
        f.setSize(800,800);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
    }

    private class Fajlbol implements ActionListener {
        boolean fajlbol;
        JLabel masodik;
        JRadioButton s1;JRadioButton s2;JRadioButton s3;JRadioButton s4;
        JLabel harmadik;
        JRadioButton cs1;JRadioButton cs2;JRadioButton cs3;JRadioButton cs4;
        JLabel negyedik;
        JSlider nehezseg;

        public Fajlbol(boolean fajlbol, JLabel masodik, JRadioButton s1, JRadioButton s2, JRadioButton s3, JRadioButton s4, JLabel harmadik, JRadioButton cs1, JRadioButton cs2, JRadioButton cs3, JRadioButton cs4, JLabel negyedik, JSlider nehezseg){
            this.masodik = masodik;
            this.fajlbol = fajlbol;
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            this.s4 = s4;
            this.harmadik = harmadik;
            this.cs1 = cs1;
            this.cs2 = cs2;
            this.cs3 = cs3;
            this.cs4 = cs4;
            this.negyedik = negyedik;
            this.nehezseg = nehezseg;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            masodik.setVisible(!fajlbol);
            s1.setVisible(!fajlbol);
            s2.setVisible(!fajlbol);
            s3.setVisible(!fajlbol);
            s4.setVisible(!fajlbol);
            harmadik.setVisible(!fajlbol);
            cs1.setVisible(!fajlbol);
            cs2.setVisible(!fajlbol);
            cs3.setVisible(!fajlbol);
            cs4.setVisible(!fajlbol);
            negyedik.setVisible(!fajlbol);
            nehezseg.setVisible(!fajlbol);
        }
    }

    private class Sorszam implements ActionListener {
        int sorszam;
        JRadioButton cs1;JRadioButton cs2;JRadioButton cs3;JRadioButton cs4;

        public Sorszam(int sorszam, JRadioButton cs1, JRadioButton cs2, JRadioButton cs3, JRadioButton cs4){
            this.sorszam = sorszam;
            this.cs1 = cs1;
            this.cs2 = cs2;
            this.cs3 = cs3;
            this.cs4 = cs4;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            cs1.setText(String.valueOf(sorszam*2+2));
            cs2.setText(String.valueOf(sorszam*3+2));
            cs3.setText(String.valueOf(sorszam*4+2));
            cs4.setText(String.valueOf(sorszam*5+2));
        }
    }

    private class Indit implements ActionListener {
        JFrame f;
        JRadioButton fajlbol;
        JRadioButton s1;JRadioButton s2;JRadioButton s3;JRadioButton s4;
        JRadioButton cs1;JRadioButton cs2;JRadioButton cs3;JRadioButton cs4;
        JSlider nehezseg;

        public Indit(JFrame f, JRadioButton fajlbol, JRadioButton s1, JRadioButton s2, JRadioButton s3, JRadioButton s4, JRadioButton cs1, JRadioButton cs2, JRadioButton cs3, JRadioButton cs4, JSlider nehezseg){
            this.f = f;
            this.fajlbol = fajlbol;
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            this.s4 = s4;
            this.cs1 = cs1;
            this.cs2 = cs2;
            this.cs3 = cs3;
            this.cs4 = cs4;
            this.nehezseg = nehezseg;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(fajlbol.isSelected()){
                try {
                    fajlt_olvas.olvas();
                    f.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else{
                int sorSzam = 0;
                int csucsSzam = 0;
                int feladatNehezseg = 0;
                if(s1.isSelected())
                    sorSzam = 2;
                else if(s2.isSelected())
                    sorSzam = 3;
                else if(s3.isSelected())
                    sorSzam = 4;
                else
                    sorSzam = 5;
                if(cs1.isSelected())
                    csucsSzam = 2*sorSzam+2;
                else if(cs2.isSelected())
                    csucsSzam = 3*sorSzam+2;
                else if(cs3.isSelected())
                    csucsSzam = 4*sorSzam+2;
                else
                    csucsSzam = 5*sorSzam+2;
                feladatNehezseg = nehezseg.getValue();
                graf graf = new graf(csucsSzam, sorSzam);
                vagas.vagast_sorsol(graf, csucsSzam, sorSzam);
                try {
                    folyamgeneralas.general(0, csucsSzam, sorSzam, graf, 1, feladatNehezseg, 0);
                    f.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
