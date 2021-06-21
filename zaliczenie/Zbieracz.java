import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

class  Suma {
    int suma=0;
}

class Zbieracz extends JFrame {
    Suma zbieracz= new Suma();
    JLabel inst=new JLabel("  Wprowadź liczbę i naciśnij enter");
    JLabel rowne= new JLabel("  Skumulowana suma wynosi: ");
    JTextField liczba=new JTextField();
    private JTextField wynik=new JTextField();
    Font smallFont=liczba.getFont().deriveFont(Font.PLAIN,12);

    public Zbieracz(){
        JPanel label = new JPanel(new GridLayout(2, 1));
        JPanel text = new JPanel(new GridLayout(6,1));
        JPanel p=new JPanel(new BorderLayout());
        liczba.setPreferredSize(new Dimension(250,40));
        liczba.setMaximumSize(new Dimension(250,40));

        label.add(inst);
        label.add(rowne);

        text.add(new JLabel());
        text.add(liczba);
        text.add(new JLabel());
        text.add(new JLabel());
        text.add(wynik);
        wynik.setBackground(Color.cyan);
        wynik.setForeground(Color.BLACK);
        liczba.addKeyListener(new SubmitButton(liczba));


        p.add(label, BorderLayout.WEST);
        p.add(text, BorderLayout.EAST);
        setContentPane(p);
        setVisible(true);
        liczba.setFont(smallFont);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    class SubmitButton implements KeyListener {

        JTextField l;
        int suma= zbieracz.suma;
        JTextField w=wynik;


        public SubmitButton(JTextField textfield){
            l = textfield;
        }


        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                try{
                    Integer.parseInt(l.getText());
                    int L=Integer.parseInt(l.getText());
                    suma=suma+L;
                    wynik.setText(Integer.toString(suma));
                    wynik.setBackground(Color.cyan);
                } catch (NumberFormatException exc){
                    wynik.setText("Wprowadź liczbę całkowitą");
                    wynik.setBackground(Color.RED);
                }
            }


        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyTyped(KeyEvent arg0) {

        }
    }

    public static void main(String[] args) {
        JFrame f = new Zbieracz() ;
        f.setSize(500, 200);
        f.setLocation(100, 100);
        f.setVisible(true);
    }
}

