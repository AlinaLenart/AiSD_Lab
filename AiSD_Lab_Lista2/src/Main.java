
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

        PascalIterator pi = new PascalIterator(2);




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