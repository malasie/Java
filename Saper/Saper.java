import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

class Model{
    int wiel= 10;
    int kliknięcia=0;
    ArrayList<ArrayList> Współrzędne= new ArrayList<ArrayList>();
    Random liczba= new Random();
    int los= liczba.nextInt(6)+5;
    int miny;                                        //liczba min
    int pozycja=0;
    boolean czyponów;
    ArrayList<Integer> X= new ArrayList<>();        //listy współrzędnych min
    ArrayList<Integer> Y= new ArrayList<>();

    void Miny(){
        miny= wiel*los/10;                          // liczba min ustalona na podstawie wielkośći planszy i losowej liczby podzielonej na 10 np. dla wielkośći 4x4 ilość min wypada między [2,4], a dla 10x10 [5,10]
        X.clear();
        Y.clear();
        for (int a = 0; a < miny; a++) {            //losujemy współrzędne
            Random l1 = new Random();
            int x = l1.nextInt(wiel);
            Random l2 = new Random();
            int y = l2.nextInt(wiel);
            int spr=1;
            for (int b = 0; b < X.size(); b++){   //sprawdzamy czy współrzędne się nie powtarzają
                if(X.get(b)!=x & Y.get(b)!=y){
                    spr++;                       //jeśli nie to spr+1
                }
            }
            if(spr<X.size()){a--;}               //jeśli spr jest mniejsze od wielkośći list współrzędnych, to zmniejszamy a o 1 by powtórzyć pętle
            else{                               //jeśli nie jest mniejsze
                X.add(x);
                Y.add(y);
            }
        }
        miny=X.size();
    }

    char  tab[][] = new char[wiel][wiel];
    boolean sprawdz(int w, int k) {
        for( int i=0; i< X.size(); i++) {
            int x=X.get(i);
            int y=Y.get(i);
            if (w==x & k==y) return true;
        }
            return false;
    }

}


class Saper extends JFrame {
    Model model = new Model();
    JButton start= new JButton("Start");
    JButton cofnij= new JButton("<<");
    JButton ponow= new JButton(">>");
    JButton zapisz= new JButton("zapisz");
    JButton wczytaj= new JButton("wczytaj");
    JButton tab[][] = new JButton[model.wiel][model.wiel];
    JPanel plansza = new JPanel();                          //tworzymy pare paneli: plansza i boczny, które będą służyć jako "główne" oraz
    JPanel boczny= new JPanel();                            //oraz 3 panele, które będą zawarte w panelu bocznym
    JPanel Komendy= new JPanel();                           // każdy o innym zastosowaniu
    JPanel sterowanie = new JPanel();
    JPanel zapis= new JPanel();
    JTextField t = new JTextField(10);
    JTextField wielkość = new JTextField(1);
    JTextField nazwa = new JTextField(2);

    public Saper() {
        int i, j;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(1, 2));
        cp.add(plansza);
        cp.add(boczny);

        boczny.add(Komendy);
        boczny.setLayout(new GridLayout(4, 1));
        Komendy.setLayout(new GridLayout(2,1));
        Komendy.add(t);

        boczny.add(sterowanie);
        sterowanie.setLayout(new GridLayout(4,2));
        sterowanie.add(new JLabel("Wielkość (4-10):")) ;
        sterowanie.add(new JLabel("")) ;
        sterowanie.add(wielkość);
        sterowanie.add(start);
        start.addActionListener(new Start());
        sterowanie.add(new JLabel("")) ;
        sterowanie.add(new JLabel("")) ;
        sterowanie.add(cofnij);
        cofnij.addActionListener(new Anuluj());
        sterowanie.add(ponow);
        ponow.addActionListener(new Ponów());

        boczny.add(new JPanel());

        boczny.add(zapis);
        zapis.setLayout(new GridLayout(2,2));
        zapis.add(new JLabel("nazwa pliku"));
        zapis.add(nazwa);
        zapis.add(wczytaj);
        zapis.add(zapisz);
        //wczytaj.addActionListener(new Wczytaj());
        zapisz.addActionListener(new Zapisz());


        plansza.setLayout(new GridLayout(model.wiel, model.wiel));
        for (i = 0; i < model.wiel; i++)
            for (j = 0; j < model.wiel; j++) {
                tab[i][j] = new JButton("");
                plansza.add(tab[i][j]);
                (tab[i][j]).addActionListener(new B(i, j));
                tab[i][j].setEnabled(false);
                tab[i][j].setBackground(Color.LIGHT_GRAY);

            }
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    int dajLiczbe(JTextField tf) throws ZlaWielkość {               // funkcja zwracająca liczbę z pola tekstowego
        if (tf.getText().isEmpty()) {                               //gdy nie wprowadzimy wartości; plansza ustawia się 8x*
            tf.setText("8");
            return 8;
        }
        try {
            int wielkosc = Integer.parseInt(tf.getText());
            if (wielkosc > 10) throw new ZlaWielkość(wielkosc);             //przy wprowadzeniu liczby poza zakres [4,10] błąd ZlaWielkość
            else if (wielkosc < 4) throw new ZlaWielkość(wielkosc);
            else{ return wielkosc;}
        } catch (NumberFormatException e) {                      //przy wprowadzeniu innej wartości innego rodzaju (double, String), automatycznie 8x8
            t.setText("Należało wprowadzić liczbę - Auomatycznie 8X8");
            return 8;
        }
    }

    boolean wygrana(int w, int k) {                             // sprawdzamy czy wszystkie pola poza minami zostały kliknięte
        int l = 0;
        for (int a = 0; a < model.wiel; a++) {                  //liczymy pola odblokowane
            for (int b = 0; b < model.wiel; b++) {
                if (tab[a][b].isEnabled()) l++;
            }
        }
        if (model.sprawdz(w, k) == false) {                     //jeśli nie odkryto miny
            if (l == model.miny) {                              //i liczba min jest równa liczbie odblokowanych przycisków
                return true;                                    // zwraca true (wygrana)
            }
            else return false;
        }
        return false;

    }


    class Start implements ActionListener {                     // funkcja ustawiająca plansze (wielkość i miny na planszy)
        public void actionPerformed(ActionEvent e) {
            int ii=0;
            if (model.kliknięcia==0) {                          //kliknęcia będą sprawdzać czy plansza nie została wczytana
                model.Współrzędne.clear();                     //czyścimy listę i zerujemy pozycję (mogły nie być puste/ równe 0 jeśli przed chwilą graliśmy)
                model.pozycja = 0;
                model.czyponów = false;


                t.setBackground(Color.WHITE);                 //ponieważ przy wygranej i przegranej kolor pola tekstowego się zmienia przywracamy do
                model.kliknięcia = 0;
                try {                                        //ustawiamy wielkość, sprawdzając czy nie przekracza zakresu (4-10), jeśli tak to 8x8
                    model.wiel = dajLiczbe(wielkość);
                } catch (ZlaWielkość e2) {
                    ii=1;
                    t.setText("Liczba przekracza zakres (4-10)  - Auomatycznie 8X8");
                    model.wiel = 8;
                }
                model.Miny();                                        //wywołujemy funkcję Miny (ustalającą ilość i współrzędne min)
                for (int a = 0; a < 10; a++) {
                    for (int b = 0; b < 10; b++) {
                        if (a < model.wiel & b < model.wiel) {      //"czyścimy" i odblolkowujemy przyciski (ustawiamy kolor i usuwamy tekst (jesli był po poprzedniej grze))
                            tab[a][b].setEnabled(true);
                            tab[a][b].setBackground(Color.LIGHT_GRAY);
                            tab[a][b].setText("");
                        } else {
                            tab[a][b].setBackground(Color.white);   //jeśli przyciski znajdują się poza ustaloną wielkością planszy zmieniamy ich kolor na biały
                        }
                    }
                }
            }

            start.setEnabled(false);                //wyłączamy możliwość ponownego rozpoczęcia gry przed zakończeniem rozgrywki
            if (ii==0){
                t.setText("liczba min: "+Integer.toString(model.miny));
            }
            else { t.setText("Liczba przekracza zakres (4-10)  - Auomatycznie 8X8  -   liczba min: "+Integer.toString(model.miny));}
        }
    }

    class B implements ActionListener {               // funkcja gdy klikniemy na pole na planszy
        int i, j;

        B(int i, int j) {                            //pobieramy współrzędne klikniętego przycisku
            this.i = i;
            this.j = j;
        }

        public void actionPerformed(ActionEvent e) {
            ArrayList<Integer> wsp= new ArrayList<Integer>();             //lista ("podlista") współrzędnych klikniętego przycisku
            wsp.add(i);
            wsp.add(j);

            if (model.Współrzędne.isEmpty()) model.pozycja++;
            else model.pozycja=model.Współrzędne.size();

            if (model.pozycja<model.Współrzędne.size()) {           //sprawdza czy nie próbujemy ponowić akcji czy nie cofneliśmy akcji, jeśli tak usuwa je i wstawia teraźniejszy ruch do listy
                for(int a=0; a<=model.Współrzędne.size()-model.pozycja+5;a++){
                    model.Współrzędne.remove(model.pozycja);
                    model.Współrzędne.add(wsp);
                }
            }
            else model.Współrzędne.add(wsp);            //dodajemy "podlistę" współrzędnych do listy wszystkich ruchów




            if (model.sprawdz(i, j)) {                  //sprawdzamy czy natrafiliścmy na minę, jeśli tak to:
                t.setText("Game Over");
                t.setBackground(Color.RED);
                tab[i][j].setText("M");
                tab[i][j].setBackground(Color.red);
                for (int a = 0; a < model.wiel; a++) {
                    for (int b = 0; b < model.wiel; b++) {
                        tab[a][b].setEnabled(false);                //blokujemy wszystkie przyciski
                    }
                }
                for (int a = 0; a < model.miny; a++) {              //zmieniamy kolor i tekst przycisków pod którymi znajduja się miny
                    int x=model.X.get(a);
                    int y=model.Y.get(a);
                    tab[x][y].setText("M");
                    tab[x][y].setBackground(Color.RED);
                }
                start.setEnabled(true);                                     //umożliwiamy rozpoczęcie nowej rozgrywki
            }
            else {  //jeśli nie mina
                int m = 0;
                for (int a = 0; a < model.X.size(); a++) {              //sprawdzamy ile min jest w okolicy przycisku
                    int x = model.X.get(a);
                    int y = model.Y.get(a);
                    if (x == i - 1 & y == j - 1) {
                        m++;
                    }
                    if (x == i - 1 & y == j) {
                        m++;
                    }
                    if (x == i - 1 & y == j + 1) {
                        m++;
                    }
                    if (x == i & y == j - 1) {
                        m++;
                    }
                    if (x == i & y == j + 1) {
                        m++;
                    }
                    if (x == i + 1 & y == j - 1) {
                        m++;
                    }
                    if (x == i + 1 & y == j) {
                        m++;
                    }
                    if (x == i + 1 & y == j + 1) {
                        m++;
                    }
                }
                if (m == 0) {                    //jeśli nie ma min w otoczniu to "pole puste" (ciemno szare) i odkrywa swoje otoczenie (automatycznie klikając (akcja będzie odgrywać się aż nie stworzy się brzeg))
                    tab[i][j].setBackground(Color.gray);
                    tab[i][j].setEnabled(false);
                    int xmin = -1, xmax = 2, ymin = -1, ymax = 2;
                    if(i==model.wiel-1) xmax=1;
                    if (i==0) xmin=0;
                    if(j==model.wiel-1) ymax=1;
                    if (j==0) ymin=0;
                    for (int x = xmin; x < xmax; x++) {
                        for (int y = ymin; y < ymax; y++) {
                            tab[i+x][j+y].doClick();
                        }
                    }
                } else {                                //jeśli jest mina/y w okolicy, to kolor niebieski i liczba min w okolicy
                    tab[i][j].setText(Integer.toString(m));
                    tab[i][j].setBackground(Color.blue);
                    tab[i][j].setEnabled(false);
                }
            }
            if (wygrana(i,j)==true){                 //jeśli wygrana to komunikat, zablokowanie przycisków i umożliwienie startu
                t.setText("Wygrana!!!");
                t.setBackground(Color.YELLOW);
                for (int a = 0; a < model.wiel; a++) {
                    for (int b = 0; b < model.wiel; b++) {
                        tab[a][b].setEnabled(false);
                    }
                }
                start.setEnabled(true);
            }
        }
    }

    class Anuluj implements ActionListener {                         // cofanie ruchów
        public void actionPerformed(ActionEvent e) {
            if(model.Współrzędne.isEmpty()|| model.pozycja==0 ){     //jesli lista pusta to wyswietla tekst w rezultacie
                t.setText("Nie ma operacji do cofnięcia");
            }
            else {
                model.czyponów=false;
                if(model.Współrzędne.size()<model.pozycja) model.pozycja=model.Współrzędne.size();  //jeśli pozycja przekracza wielkość listy to "wyrównujemy"
                ArrayList wsp= model.Współrzędne.get(model.pozycja);                                //"pobieramy podlistę" współrzędnych o danej pozycji
                int a=Integer.parseInt(wsp.get(0).toString());
                int b=Integer.parseInt(wsp.get(1).toString());
                JButton guzik = tab[a][b];                                                          //przypisujemy nazwę "guzik" przyciskowi o danych współrzędnych
                guzik.setBackground(Color.LIGHT_GRAY);                                              //"czyścimy go"
                guzik.setText("");
                model.pozycja-=1;                                                                   //zmniejszamy pozycję (cofamy się w liście)
                t.setText("Cofnięto operacje");
                t.setBackground(Color.WHITE);                                                       //zmieniamy kolor pola komunikatów (mógł byc czerwony lub żółty jeśli była wygrana lub natrafiono na minę)
                for (int i=0; i<model.wiel; i++) {
                    for (int j = 0; j < model.wiel; j++) {
                        if (tab[i][j].getBackground() == Color.RED) {                               //sprawdzamy każdy przycisk czy jego kolor jest czerwony (natrafiono na minę) i jeśli tak to "czyścimy go"
                            tab[i][j].setBackground(Color.LIGHT_GRAY);
                            tab[i][j].setText("");
                        }
                        if (tab[i][j].getBackground() == Color.LIGHT_GRAY) {                        //jeśli przycisk jest jasno szary (został "wyczyszczony") to go odblokowujemy
                            tab[i][j].setEnabled(true);
                        }
                    }
                }
            }
        }
    }

    class Ponów implements ActionListener {                                     //ponawia akcje
        public void actionPerformed(ActionEvent e) {
            if(model.Współrzędne.isEmpty() || model.pozycja>=model.Współrzędne.size()){
                t.setText("Nie ma operacji do ponowienia");
            }
            else {
                model.czyponów=true;
                ArrayList wsp= model.Współrzędne.get(model.pozycja);         //bierze listę o naszej pozycji z listy i ponownie go klika
                int a=Integer.parseInt(wsp.get(0).toString());
                int b=Integer.parseInt(wsp.get(1).toString());
                JButton guzik = tab[a][b];
                guzik.doClick();
                model.pozycja+=1;
                t.setText("Ponowiono operacje");
                t.setBackground(Color.WHITE);
            }
        }
    }
    /*class Wczytaj implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.kliknięcia=1;
            if (zapisz.getText()=="") zapisz.setText("Automatyczny");
            try{
                ObjectInputStream is = new ObjectInputStream( new FileInputStream(zapisz.getText()));
                model.Współrzędne = (ArrayList<ArrayList>) is.readObject() ;
                model.wiel = (int) is.readObject();
                model.X= (ArrayList<Integer>) is.readObject();
                model.Y= (ArrayList<Integer>) is.readObject();
                wielkość.setText(Integer.toString(model.wiel));
                is.close();
            } catch (IOException e2){System.out.println("--wyjatek!");}
            catch (ClassNotFoundException e2){}

            for(int a=0; a<model.Współrzędne.size(); a++){
                ArrayList wsp= model.Współrzędne.get(a);
                int i=Integer.parseInt(wsp.get(0).toString());
                int j=Integer.parseInt(wsp.get(1).toString());
                JButton guzik = tab[i][j];
                guzik.doClick();
            }
        }
    }

     */
    class Zapisz implements ActionListener {                                        //zapisywanie stanu gry
        public void actionPerformed(ActionEvent e) {
            if (zapisz.getText()=="") zapisz.setText("Automatyczny");               //jeśli nie ustawiono nazwy pliku to w pole wprowadzania nazwy wyświetla "Automatyczny" (taka będzie nazwa pliku)
            try{
                FileOutputStream f = new FileOutputStream(zapisz.getText());        //tworzymy plik o wprowadzonej nazwie
                ObjectOutputStream os = new ObjectOutputStream(f);
                os.writeObject(model.Współrzędne);                                 //zapisujemy listę ruchów, położenie min oraz wielkość planszy
                os.writeObject(model.X);
                os.writeObject(model.Y);
                os.writeObject(model.wiel);
                f.close();
                t.setText("Zapisano grę");                                          //zwracamy komunikat że udało się zapisać grę
            } catch (IOException e2){}
        }
    }


    public static void main(String[] args) {
        JFrame f = new Saper();
        f.setSize(1200, 700);
        f.setLocation(100, 100);
        f.setVisible(true);
    }
}

class ZlaWielkość extends Exception{       //funkcja sprawdzająca wyjątki
    int wielkość;
    ZlaWielkość(int wielkość){this.wielkość=wielkość;}
}
