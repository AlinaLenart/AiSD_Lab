import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        testInt();
        testString();
        testObjects();

    }
    private static void testInt() {
        System.out.println("Test on Integer Heap:");
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Array3Heap<Integer> heap = new Array3Heap<>(10, comparator);

        heap.add(5);
        heap.add(8);
        heap.add(3);
        heap.add(12);
        heap.add(6);

        System.out.println("Trzeci max: " + heap.nthElement(3));

        int maxIndex = 1;
        while (!heap.isEmpty()) {
            System.out.println(maxIndex + ". " + heap.maximum());
            maxIndex++;
        }
    }
    private static void testString(){
        System.out.println("Test on String Heap:");
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        Array3Heap<String> heap = new Array3Heap<>(10, lengthComparator);

        heap.add("Kuba");
        heap.add("Janusz");
        heap.add("Genowefa");
        heap.add("Kamil");
        heap.add("Bartlomiej");
        heap.add("Jan");

        System.out.println("Czwarty max: " + heap.nthElement(4));

        int maxIndex = 1;
        while (!heap.isEmpty()) {
            System.out.println(maxIndex + ". " + heap.maximum());
            maxIndex++;
        }
    }

    private static void testObjects(){
        System.out.println("Test on Object Heap:");
        Comparator<Artist> ageComparator = Comparator.comparingInt(Artist::getAge);
        Array3Heap<Artist> heap = new Array3Heap<>(10, ageComparator);

        heap.add(new Artist("Johnny", "Depp", 60));
        heap.add(new Artist("Leonardo", "DiCaprio", 49));
        heap.add(new Artist("Jennifer", "Lawrence", 33));
        heap.add(new Artist("Robert", "Pattinson", 37));
        heap.add(new Artist("Morgan", "Freeman", 86));

        while (!heap.isEmpty()) {
            Artist artist = heap.maximum();
            System.out.println(artist.getName() +" "+ artist.getSurname() + " - " + artist.getAge() + " years old");
        }
    }

}