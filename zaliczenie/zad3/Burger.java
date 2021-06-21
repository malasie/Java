import java.util.List;

public class Burger{
    public double cena;
    public static List<Składniki> skladniki;
    public Waga waga;

    public Burger(List<Składniki> s, double c, Waga w ){skladniki=s; cena=c; waga=w;}
}
