import java.util.*;

class Student {
    int id;
    int ocena;
    String imie;
    ArrayList<Integer> oceny;

    Student(int a, String b, ArrayList<Integer> c) {
        id = a;
        imie = b;
        oceny = c;
    }
}

class CompA implements Comparator<Student>{
    public int compare(Student a, Student b) {
        String aI = a.imie;
        String bI = b.imie;
        return aI.compareTo(bI);
    }
}
class CompW implements Comparator<Student>{
    public int compare(Student a, Student b) {
        int liczba_a=0;
        int liczba_b=0;
        for (int i=0; i<a.oceny.size();i++){
            if(a.oceny.get(i)>=4){liczba_a++;}
        }
        for (int i=0; i<b.oceny.size();i++){
            if(b.oceny.get(i)>=4){liczba_b++;}
        }
        return Integer.compare(liczba_a,liczba_b);
    }
}

class CompS implements Comparator<Student> {
    public int compare(Student a, Student b) {
        float sr_a;
        float sr_b;
        int as=0;
        int bs=0;
        for (int i=0; i<a.oceny.size();i++){
            as=as+a.oceny.get(i);
        }
        for (int i=0; i<b.oceny.size();i++){
            bs=bs+b.oceny.get(i);
        }
        sr_a=as/a.oceny.size();
        sr_b=bs/b.oceny.size();
        return Float.compare(sr_a,sr_b);
    }
}

class ZlaOcenaException extends Exception{
    int ocena;
    ZlaOcenaException(int ocena){this.ocena=ocena;}
}

class WykazS {
    ArrayList<Student> studenci=new ArrayList<Student>();

    void wstawStudenta(int nr, String imie) {
        ArrayList<Integer> oceny = new ArrayList<Integer>();
        Student student = new Student(nr, imie, oceny);
        studenci.add(student);
    }

    void wstawOcene(int nr, int ocena) throws ZlaOcenaException {
        Student student;
        if (ocena>5) throw new ZlaOcenaException(ocena);
        else if(ocena<2) throw new ZlaOcenaException(ocena);
        else {
            for (int i = 0; i < studenci.size(); i++) {
                student = studenci.get(i);
                if (student.id == nr) {
                    student.oceny.add(ocena);
                }
            }
        }
    }

    void wypisz(int nr){
        Student student;
        for (int i=0; i< studenci.size(); i++){
            student= studenci.get(i);
            if (student.id == nr) {
                System.out.println(student.id+" "+student.imie+"\n"+ student.oceny);
            }
        }
    }

    void wypisz(){
        Student student;
        for (int i=0; i< studenci.size(); i++) {
            student = studenci.get(i);
            System.out.println(student.id + " " + student.imie + "\n" + student.oceny);
            System.out.println();
        }
    }

    void sortujA(){
      Collections.sort(studenci, new CompA());
    }
    void sortujS(){
        Collections.sort(studenci, new CompS());
    }
    void sortuj() { Collections.sort(studenci, new CompW()); }

    void srednia(){
        double suma=0;
        int l=0;
        double sr;
        for (Student a : studenci ){
            for(int ocena : a.oceny){
                suma+=ocena;
                l+=1;
            }
        }
        sr=suma/l;
        System.out.println(sr);
    }
}


class TestWykazS{
    public static void main(String[] a){
        WykazS ws = new WykazS();

        ws.wstawStudenta(199200,"Kazik");
        ws.wstawStudenta(199201,"Kazik");
        ws.wstawStudenta(199204,"Nikodem");
        ws.wstawStudenta(199205,"Jan");
        ws.wstawStudenta(189557,"Wiesiek");

        try {
            ws.wstawOcene(199200, 5);
            ws.wstawOcene(199200, 4);
            ws.wstawOcene(199200, 3);
            ws.wstawOcene(199200, 3);
            ws.wstawOcene(199200, 2);

            ws.wstawOcene(199201, 5);
            ws.wstawOcene(199201, 4);

            ws.wstawOcene(199204, 4);
            ws.wstawOcene(199204, 5);
            ws.wstawOcene(199204, 5);
            ws.wstawOcene(199201, 4);

            ws.wstawOcene(199205, 3);
            ws.wstawOcene(189557, 5);
            ws.wstawOcene(199201, 1);
        } catch (ZlaOcenaException e){
            System.out.println("Zla wartość oceny!!! "+e.ocena + "\n" );
        }




        ws.wypisz();
        //ws.wypisz(199200);

        /*
    System.out.println();
    System.out.println();
    ws.sortujA();
    ws.wypisz();

    System.out.println();
    System.out.println();
    ws.sortujS();

         */


    System.out.println();
    System.out.println();
    ws.sortuj();

    ws.wypisz();

    //ws.srednia();
    }
}
