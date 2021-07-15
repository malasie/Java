import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

class Game {
    boolean O=false;
    boolean X=true;
    ArrayList<String> o= new ArrayList<String>();
    ArrayList<String> x= new ArrayList<String>();

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
    Game gra=new Game();
    JPanel board=new JPanel();
    JPanel menu= new JPanel();
    JPanel game= new JPanel();
    JTextField t= new JTextField("Rozpocznij grę klikając przycisk 'START'");
    JTextField scores= new JTextField();
    JButton start=new JButton("START");
    JButton restart=new JButton("RESTART");
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
        menu.add(restart);
        menu.add(start);
        start.addActionListener(new Start());
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

    class Click implements ActionListener{
        int i, j;

        Click(int i, int j) {                            //pobieramy współrzędne klikniętego przycisku
            this.i = i;
            this.j = j;
        }

        public void actionPerformed(ActionEvent e) {
            if (gra.O) {
                tab[j][i].setBackground(Color.blue);
                gra.nextPlayer();
                t.setText("X's TURN");
            } else {
                tab[j][i].setBackground(Color.red);
                gra.nextPlayer();
                t.setText("X's TURN");
            }
            tab[j][i].setEnabled(false);
        }
    }

    class Start implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int i,j;
            gra.klikniecia=0;
            gra.nextPlayer();
            for(j=0; j<3; j++) {
                for (i = 0; i < 3; i++) {
                    tab[j][i].setEnabled(true);
                    tab[j][i].setBackground(Color.WHITE);
                }
            }
            if (gra.O) {
                t.setText("O's TURN");
            } else t.setText("X's TURN");
        }
    }



    public static void main(String[] args) {
        JFrame f = new TicTacToe();
        f.setSize(600, 900);
        f.setLocation(100, 100);
        f.setVisible(true);
    }

}
