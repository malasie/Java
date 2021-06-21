public class Składniki {
   public boolean PODWÓJNA_WOŁOWINA,PODWÓJNY_SER,SER,BUŁKA,POMIDOR,OGÓREK, KURCZAK, WOŁOWINA, SAŁATA, CEBULA, PAPRYKA, SOS_MAJONEZOWY, SOS_ARABSKI, SOS_ŁAGODNY, SOS_TLAZIKI;
   public Składniki valueOf(String s);
   public Składniki[] values();
}
public class Waga{
    public boolean G_300, G_500, G_1000;
    public Waga valueOf(String);
    public Waga[] values();
}

public class Burger{
    public double cena;
    public List<Składniki> skladniki;
    public Waga waga;

    public Burger(List<Składniki> s, double c, Waga w ){skladniki=s; cena=c; waga=w;}
}

public class BurgerTyp{
    public boolean CHEESE_BURGER, BIG_MAC, CHICKEN_BURGER,MC_ROYAL,WIES_MAC,ZINGER,GRANDER;
}

public class McDonald{
    public Burger zamów(BurgerTyp bt);
}

public class Subway{
    public Burger zamów(BurgerTyp bt);
}

public class Kfc{
    public Burger zamów(BurgerTyp bt);
}

public class TypRestauracji {
    public boolean MC_DONALD, KFC,SUBWAY;
}

public class RestauracjaFabryka{
    public Restauracje wybierzRestauracje(TypRestauracje r);
}


public class TestApp {
    private List<Burger> zamówioneBurgery;
    private void wyświetlZamowienie(){
        System.out.println(zamówioneBurgery)
    }
    public static void main(String[] args) {
    }
}

public interface Restauracje {
    public Burger zamów(BurgerType bt);
}