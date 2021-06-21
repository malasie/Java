public class Zwierze {
    String nazwa;
    int wiek;
    double waga;
    public Zwierze(String s,int w, double wg){nazwa=s; wiek=w; waga=wg;}

}

class Lew extends Zwierze{
    public Lew(String s, int w, double wg) {
        super(s, w, wg);
    }
    boolean biegnie = false;
    void biegnij(){
        biegnie=true;
    }
    void zatrzymajSie(){
        biegnie=false;
    }
    void wypisz(){
        String odp;
        if (biegnie){
            odp="tak";
        }
        else{ odp="nie";}
        System.out.println("Czy biegnie? " + odp );
    }
}

class Orzel extends Zwierze {
    public Orzel(String s, int w, double wg) {
        super(s, w, wg);
    }

    boolean leci = false;

    void lec() {
        leci = true;
    }

    void wyladuj() {
        leci = false;
    }

    void wypisz() {
        String odp;
        if (leci) {
            odp = "tak";
        } else {
            odp = "nie";
        }
        System.out.println("Czy leci? " + odp);
    }
}

class wywolaj{
    public static void main(String[] args) {
        Orzel orzel= new Orzel("Olaf",1,5.4);
        Lew lew= new Lew("Tedek",6,180.2);
        lew.biegnij();
        orzel.lec();
        orzel.wypisz();
        lew.wypisz();
    }
}