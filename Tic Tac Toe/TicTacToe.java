import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Boolean.TRUE;

class Game {
    boolean O=false;
    boolean X=true;
    int scoreX=0;
    int scoreO=0;
    ArrayList<Integer> o= new ArrayList<Integer>();
    ArrayList<Integer> x= new ArrayList<Integer>();


    void nextPlayer()
    {
        if (O) {
            X = true;
            O=false;
        } else {
            X = false;
            O= true;
        }
    }

    int klikniecia=0;
}

class TicTacToe extends JFrame {
    public static Game gra=new Game();
    JPanel board=new JPanel();
    JPanel menu= new JPanel();
    JPanel game= new JPanel();
    JTextField t= new JTextField("Rozpocznij grę klikając przycisk 'START'");
    JTextField scores= new JTextField();
    static JButton start=new JButton("START");
    JButton end=new JButton("End the Game");
    JButton tab[][] = new JButton[3][3];

    public TicTacToe() {
        int i,j;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(2,1));
        cp.add(game);
        cp.add(board);

        game.setLayout(new GridLayout(4,1));
        game.add(menu);
        menu.setLayout(new GridLayout(2,2));
        menu.add(end);
        menu.add(start);
        start.addActionListener(new Start());
        end.addActionListener(new End());
        menu.add(new JLabel(""));
        game.add(t);
        t.setBackground(new Color(255,255,204));
        t.setHorizontalAlignment(JTextField.CENTER);
        t.setFont(new Font("Tahoma", Font.BOLD, 13));
        game.add(scores);
        scores.setBackground(new Color(225, 245, 252));
        game.add(new JLabel("", 2));

        board.setLayout(new GridLayout(4,5));
        for(j=0; j<4; j++){
            for(i=0;i<5;i++){
                if(i==0 | i==4){
                    board.add(new JLabel(""));
                }
                else{
                    if(j==3) {
                        board.add(new JLabel(""));
                    }
                    else{
                        tab[j][i-1] = new JButton("");
                        tab[j][i-1].setBackground(Color.WHITE);
                        tab[j][i-1].setEnabled(false);
                        tab[j][i-1].addActionListener(new Click(i-1,j));
                        board.add(tab[j][i-1]);
                    }
                }
            }
        }
    }
    void Disable(){
        int i, j;
        for(j=0; j<3; j++) {
            for (i = 0; i < 3; i++) {
                tab[j][i].setEnabled(false);
            }
        }
    }

    boolean victoryX() {
        int a, b, c;
        boolean v = false;
        if (gra.klikniecia < 4) {
            v = false;
        } else {
            Collections.sort(gra.x);
            if (gra.x.size() > 2) {
                for (a = 0; a < gra.x.size() - 2; a++) {
                    int e1 = gra.x.get(a);
                    if (e1 == 2) {
                        for (b = 0; b < gra.x.size() - 1; b++) {
                            int e2 = gra.x.get(b);
                            if (e2 == 11) {
                                for (c = 0; c < gra.x.size(); c++) {
                                    int e3 = gra.x.get(c);
                                    if (e3 == 20) {
                                        v = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (e1 == 0) {
                        if (gra.x.get(gra.x.size() - 1) == 22) {
                            for (b = 0; b < gra.x.size(); b++) {
                                int e2 = gra.x.get(b);
                                if (e2 == 11) {
                                    v = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (e1 % 10 == 0) {
                        if (gra.x.get(a + 1) == e1 + 1) {
                            if (gra.x.get(a + 2) == e1 + 2) {
                                v = true;
                                break;
                            }
                        }
                    }
                    if (e1 < 10) {
                        for (b = 0; b < gra.x.size() - 1; b++) {
                            int e2 = gra.x.get(b);
                            if (e2 == e1 + 10) {
                                for (c = 0; c < gra.x.size(); c++) {
                                    int e3 = gra.x.get(c);
                                    if (e3 == e1 + 20) {
                                        v = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        if(v)break;
                    }
                }
            }
        }
        return v;
    }

    boolean victoryO() {
        int a,b,c;
        boolean v=false;
        if (gra.klikniecia < 4) {
            v=false;
        }
        else {
            Collections.sort(gra.o);
            if (gra.o.size() > 2) {
                for (a = 0; a < gra.o.size() - 2; a++) {
                    int e1 = gra.o.get(a);

                    if (e1 == 0) {
                        if (gra.o.get(gra.o.size() - 1) == 22) {
                            for (b = 0; b < gra.o.size(); b++) {
                                int e2 = gra.o.get(b);
                                if (e2 == 11) {
                                    v = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (e1 == 2) {
                        for (b = 0; b < gra.o.size(); b++) {
                            int e2 = gra.o.get(b);
                            if (e2 == 11) {
                                for (c = 0; c < gra.o.size(); c++) {
                                    int e3 = gra.o.get(c);
                                    if (e3 == 20) {
                                        v = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (e1 % 10 == 0) {
                        if (gra.o.get(a + 1) == e1 + 1) {
                            if (gra.o.get(a + 2) == e1 + 2) {
                                v = true;
                                break;
                            }
                        }
                    }
                    if (e1 < 10) {
                        for (b = 0; b < gra.o.size() - 1; b++) {
                            int e2 = gra.o.get(b);
                            if (e2 == e1 + 10) {
                                for (c = 0; c < gra.o.size(); c++) {
                                    int e3 = gra.o.get(c);
                                    if (e3 == e1 + 20) {
                                        v = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        if(v)break;
                    }
                }
            }
        }
        return v;
    }


    boolean remis(){
        return gra.klikniecia == 9;
    }

    class Click implements ActionListener{
        int i, j;

        Click(int i, int j) {                            //pobieramy współrzędne klikniętego przycisku
            this.i = i;
            this.j = j;
        }

        public void actionPerformed(ActionEvent e) {
            gra.klikniecia++;
            if (gra.O) {
                tab[j][i].setBackground(Color.blue);
                gra.o.add(j*10+i);

                if (victoryO()){
                    Disable();
                    gra.scoreO++;
                    t.setText("O WON");
                    t.setBackground(Color.blue);
                    start.setEnabled(true);
                } else if(remis()){
                    t.setText("Remis");
                    start.setEnabled(true);
                } else{
                    gra.nextPlayer();
                    t.setText("X's TURN");
                }

            } else {
                tab[j][i].setBackground(Color.red);
                gra.x.add(j*10+i);
                if (victoryX()){
                    Disable();
                    gra.scoreX++;
                    t.setText("X WON");
                    t.setBackground(Color.red);
                    start.setEnabled(true);
                } else if(remis()){
                    t.setText("Remis");
                    start.setEnabled(true);
                } else {
                    gra.nextPlayer();
                    t.setText("O's TURN");
                }
            }

            tab[j][i].setEnabled(false);
            Collections.sort(gra.o);
        }
    }

    class Start implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int i,j;
            gra.o.clear();
            gra.x.clear();
            t.setBackground(new Color(255,255,204));

            gra.klikniecia=0;
            gra.nextPlayer();
            scores.setText("X: "+gra.scoreX+"             O: "+gra.scoreO);
            for(j=0; j<3; j++) {
                for (i = 0; i < 3; i++) {
                    tab[j][i].setEnabled(true);
                    tab[j][i].setBackground(Color.WHITE);
                }
            }
            if (gra.O) {
                t.setText("O's TURN");
            } else t.setText("X's TURN");
            start.setEnabled(false);
        }
    }

    class End implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame f = new Scores();
            f.setSize(300, 230);
            f.setLocation(250, 250);
            f.setVisible(true);
        }
    }

    static void close(){
        System.exit(0);
    }


    public static void main(String[] args) {
        JFrame f = new TicTacToe();
        f.setSize(600, 900);
        f.setLocation(100, 100);
        f.setVisible(true);

    }

}
