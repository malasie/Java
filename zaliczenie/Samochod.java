public class Samochod {
    int predkosc;
    int dystans;
    public void jedz(int d){
        dystans=d;
    }
    public void przyspiesz(int p){
        predkosc=p;
    }
    public void wyswietlStan(){
        System.out.println("Obecny stan");
        System.out.println("Prędkość wynosi:"+predkosc);
        System.out.println("Samochód pokonał dystans: "+dystans);
    }
}
class Main{
    public static void main(String[] a) {
        Samochod wilk= new Samochod();
        Samochod owieczka= new Samochod();
        wilk.przyspiesz(80);
        wilk.jedz(20);
        owieczka.przyspiesz(90);
        owieczka.jedz(30);
        wilk.wyswietlStan();
        owieczka.wyswietlStan();
    }
}