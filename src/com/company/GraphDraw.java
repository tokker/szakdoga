package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class GraphDraw extends JFrame {
    int width;
    int height;

    boolean megoldas = false;

    ArrayList<Node> nodes;
    ArrayList<edge> edges;

    public GraphDraw() { //Constructor
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    public GraphDraw(String name) { //Construct with label
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
        JButton button = new JButton("Megoldás");
        button.addActionListener(new GombNyomas(this, button));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(button);
        this.getContentPane().add(panel);
    }

    private class GombNyomas implements ActionListener {
        GraphDraw g;
        JButton button;

        public GombNyomas(GraphDraw gr, JButton b){
            g = gr;
            button = b;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            megoldas = !megoldas;
            g.repaint();
            if(megoldas)
                button.setText("Feladat");
            else
                button.setText("Megoldás");
        }
    }

    class Node {
        int x, y;
        char name;
        boolean vagas;
        public Node(char myName, int myX, int myY, boolean v) {
            x = myX;
            y = myY;
            name = myName;
            vagas = v;
        }
    }

    class edge {
        char x,y;
        int kapacitas,max;

        public edge(char xx, char yy, int k, int m) {
            x = xx;
            y= yy;
            kapacitas = k;
            max = m;
        }
    }

    public void addNode(char name, int x, int y, boolean vagas) {
        //add a node at pixel (x,y)
        nodes.add(new Node(name,x,y, vagas));
        this.repaint();
    }
    public void addEdge(char x, char y, int kapacitas, int max) {
        //add an edge between nodes i and j
        edges.add(new edge(x,y, kapacitas, max));
        this.repaint();
    }

    public void paint(Graphics g) { // draw the nodes and edges
        super.paint(g);
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        g.setColor(Color.black);
        for (edge e : edges) {
            int i = 0, j = 0;
            for(Node n : nodes) {
                if (n.name == e.x)
                    i = nodes.indexOf(n);
                if (n.name == e.y)
                    j = nodes.indexOf(n);
            }
            int x0 = nodes.get(i).x;
            int y0 = nodes.get(i).y;
            int x1 = nodes.get(j).x;
            int y1 = nodes.get(j).y;

            double lineLength = Math.sqrt(Math.pow(x0-x1, 2) + Math.pow(y0-y1, 2));
            int x2 = (int) (x1 - (width/lineLength/2 * (x1-x0)));
            int y2 = (int) (y1 - (width/lineLength/2 * (y1-y0)));
            double offs = 28 * Math.PI / 180.0;
            double angle = Math.atan2(y0 - y2, x0 - x2);
            int[] xs = { x2 + (int) (13 * Math.cos(angle + offs)), x2,
                    x2 + (int) (13 * Math.cos(angle - offs)) };
            int[] ys = { y2 + (int) (13 * Math.sin(angle + offs)), y2,
                    y2 + (int) (13 * Math.sin(angle - offs)) };

            g.drawLine(x0, y0, x1, y1);
            g.drawPolyline(xs, ys, 3);
            if (megoldas)
                g.drawString("(" + e.max + ")", x0 + (x1-x0)/2,
                        y0 + (y1-y0)/2);
            else
                g.drawString("(" + e.kapacitas + ")", x0 + (x1-x0)/2,
                    y0 + (y1-y0)/2);
        }

        for (Node n : nodes) {
            int nodeWidth = Math.max(width, 1+width/2);
            if(!megoldas || n.vagas == false)
                g.setColor(Color.lightGray);
            else
                g.setColor(Color.orange);
            g.fillOval(n.x-nodeWidth/2, n.y-nodeHeight/2,
                    nodeWidth, nodeHeight);
            g.setColor(Color.black);
            g.drawOval(n.x-nodeWidth/2, n.y-nodeHeight/2,
                    nodeWidth, nodeHeight);

            g.drawString(String.valueOf(n.name), n.x-3,
                    n.y+f.getHeight()/2-3);
        }
    }
}

class testGraphDraw {
    //Here is some example syntax for the GraphDraw class
    public static void draw(int sorokSzama, graf g, int csucsokSzama) {
        GraphDraw frame = new GraphDraw("Test Window");

        int sorok = sorokSzama;
        if(sorokSzama %2 == 0)
            ++sorok;

        int szelesseg =(csucsokSzama - 2)/sorokSzama * 100 + 200;
        int magassag = sorok * 100;
        frame.setSize(szelesseg, magassag);

        frame.setVisible(true);

        for (int i = 0; i < sorok; ++i){
            for(int j = 0; j < (csucsokSzama-2)/sorokSzama + 2; ++j) {
                if(g.csucsok[i][j] != ' ') {
                    boolean vagas = false;
                    for(int k = 0; k < g.vagasCsucsSzam; ++k){
                        if(g.vagas[k] == g.csucsok[i][j])
                            vagas = true;
                    }
                    frame.addNode(g.csucsok[i][j], 50 + 100 * j, 50 + i * 100, vagas);
                }
            }
        }
        for (int i = 0; i < g.elekSzama; ++i) {
            if(g.elek.get(i).kapacitas != 0)
                frame.addEdge(g.elek.get(i).kezdo, g.elek.get(i).veg, g.elek.get(i).kapacitas, g.elek.get(i).max);
        }
    }
}
