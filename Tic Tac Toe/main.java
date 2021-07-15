import javax.swing.*;

import static java.lang.Boolean.TRUE;

class Game {
    boolean O=true;
    boolean X=false;

    void nextPlayer()
    {
        if (O == true) {
            X = false;
        } else {
            X = true;
        }
    }

    int klikniecia=0;
}

class TicTacToe extends JFrame {
    Game gra=new Game();
    JPanel board=new JPanel();
    JPanel menu= new JPanel();
    JTextField t= new JTextField();
    JButton start=new JButton();

}
