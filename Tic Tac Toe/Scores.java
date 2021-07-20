import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

class Scores extends JFrame {
    Game gra= TicTacToe.gra;
    static boolean end=true;
    JPanel Winner=new JPanel();
    JPanel menu= new JPanel();
    JPanel Score= new JPanel();
    JLabel finalscoreX= new JLabel("X:  "+gra.scoreX+ " wins");
    JLabel finalscoreO= new JLabel("O:  "+gra.scoreO +" wins");
    JLabel winner= new JLabel();
    JButton close=new JButton("Close");
    JButton restart=new JButton("New Game");

    public Scores() {
        Container cp=getContentPane();
        cp.setLayout(new GridLayout(3,1));
        menu.setBackground(new Color(253, 205, 159));
        Score.setBackground(new Color(253, 205, 159));
        Winner.setBackground(new Color(253, 205, 159));
        cp.add(Winner);
        cp.add(Score);
        cp.add(menu);

        Winner.add(winner);
        Winner.setLayout(new GridLayout(1,1));
        winner.setFont(new Font("Tahoma", Font.BOLD, 27));
        winner.setHorizontalAlignment(JTextField.CENTER);
        if(gra.scoreO> gra.scoreX) {
            winner.setText(" The winner is O !!");
        }
        else if(gra.scoreO< gra.scoreX){
            winner.setText(" The winner is X !!");
        }
        else winner.setText(" Remis !!");

        Score.setLayout(new GridLayout(1,2));
        Score.add(finalscoreO);
        Score.add(finalscoreX);
        finalscoreO.setHorizontalAlignment(JTextField.CENTER);
        finalscoreX.setHorizontalAlignment(JTextField.CENTER);
        finalscoreO.setFont(new Font("Tahoma", Font.PLAIN, 16));
        finalscoreX.setFont(new Font("Tahoma", Font.PLAIN, 16));

        menu.setLayout(new GridLayout(2,2));
        menu.add(new JLabel());
        menu.add(new JLabel());
        menu.add(restart);
        restart.addActionListener(new Restart());
        menu.add(close);
        close.addActionListener(new Close());

    }

    class Restart implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToe.gra.o.clear();
            TicTacToe.gra.x.clear();
            TicTacToe.gra.scoreO=0;
            TicTacToe.gra.scoreX=0;
            TicTacToe.gra.O=true;
            TicTacToe.gra.X=false;
            TicTacToe.start.doClick();
            setVisible(false); //you can't see me!
            dispose();
        }
    }

    class Close implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToe.close();
            setVisible(false); //you can't see me!
            dispose();
        }
    }

    public static void main(String[] args) {
        JFrame f = new Scores();
        f.setSize(300, 230);
        f.setLocation(100, 100);
        f.setVisible(true);
    }
}
