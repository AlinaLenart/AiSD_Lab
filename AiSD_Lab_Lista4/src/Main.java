public class Main {
    public static void main(String[] args) {

        TwoWayLinkedList<String> list1 = new TwoWayLinkedList<>();
        list1.add("Barbara");
        list1.add("Czeslaw");
        list1.add(0, "Alina");
        list1.add(2, "Borys");

        list1.displayLinkedList();

    }
}