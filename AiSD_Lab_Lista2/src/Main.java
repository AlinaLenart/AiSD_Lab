
public class Main {
    public static void main(String[] args) {

        //zadanie 1

        System.out.println("--Test z tablica Integer: --");

        Integer[] numbers1 = {1, 2, 3, 4, 5, 6, 7};
        Table<Object> table1 = new Table<>(numbers1);

        for (Object element : table1) {
            System.out.println(element);
        }

        System.out.println("--Test z tablica Double: --");

        Double[] numbers2 = {1.1, 2.2, 3.3, 4.4};
        Table<Object> table2 = new Table<>(numbers2);

        for (Object element : table2) {
            System.out.println(element);
        }

        System.out.println("--Test z tablica String: --");

        String[] names = {"Anna", "Barbara", "Czeslaw"};
        Table<Object> table3 = new Table<>(names);

        for (Object element : table3) {
            System.out.println(element);
        }

        System.out.println("--Test z tablica Person: --");

        Person[] people = {new Person("Kasia", 12), new Person("Mateusz", 16)};
        Table<Object> table4 = new Table<>(people);

        for (Object element : table4) {
            System.out.println(element);
        }

        //zadanie 2

        int N = 7;
        System.out.println("--Wiersz numer " + N + " w trojkacie Pascala--");

        PascalIterator iter = new PascalIterator(N);
        while (iter.hasNext()){
            System.out.print(iter.next() + " ");
        }

        int M = 7;
        System.out.println("--Wiersz numer " + M + " w trojkacie Pascala--");

        PascalIterator iter2 = new PascalIterator(M);
        while (iter2.hasNext()){
            System.out.print(iter2.next() + " ");
        }

        //modyfikacja

        int m = 150;
        System.out.println("\n--Dzielniki liczby "+ m + " to: ");

        DivisorIterator divIter1 = new DivisorIterator(m);

        while (divIter1.hasNext()) {
            System.out.print(divIter1.next() + " ");
        }

        int o = 30;
        System.out.println("\n--Dzielniki liczby "+ o + " to: ");

        DivisorIterator divIter2 = new DivisorIterator(o);

        while (divIter2.hasNext()) {
            System.out.print(divIter2.next() + " ");
        }

    }
}

class Person {
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Osoba o imieniu: "+ name + " i wieku: " + age;
    }
}