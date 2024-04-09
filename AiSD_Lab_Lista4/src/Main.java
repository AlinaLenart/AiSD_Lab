public class Main {
    public static void main(String[] args) {

        System.out.println("\nSTRING TEST: ");
        stringTest();

        System.out.println("\nEMPTY TEST: ");
        emptyTest();

        System.out.println("\nNULLS TEST: ");
        nullTest();

    }

    private static void stringTest() {

        TwoWayLinkedList<String> list = new TwoWayLinkedList<>();

        list.add("Barbara");
        list.add("Czeslaw");
        list.add(0, "Alina");
        list.add(2, "Borys");

        System.out.println("Size: " + list.size());
        System.out.println("Is it Empty? "+ list.isEmpty());

        displayLinkedList(list);

        list.reverse();
        System.out.println("Lista po odwroceniu: ");
        displayLinkedList(list);

        System.out.println("Element on index 2: " + list.get(2));

        list.set(0, "XYZ");
        System.out.println("Does it contains XYZ? " + list.contains("XYZ"));
        System.out.println("XYZ is on index: "+ list.indexOf("XYZ"));

        displayLinkedList(list);

        list.remove("Borys");
        list.remove(0);

        displayLinkedList(list);

        list.reverse();
        System.out.println("Lista po odwroceniu: ");
        displayLinkedList(list);
    }

    private static void emptyTest() {

        TwoWayLinkedList list = new TwoWayLinkedList<>();

        System.out.println("Size: " + list.size());
        System.out.println("Is it Empty? "+ list.isEmpty());

        displayLinkedList(list);

        System.out.println("Does it contains XYZ? " + list.contains("XYZ"));

        displayLinkedList(list);

        list.remove("Borys");

        displayLinkedList(list);
    }

    private static void nullTest() {

        TwoWayLinkedList list = new TwoWayLinkedList<>();

        list.add(null);
        list.add(null);
        list.add(0, null);
        list.add(2, null);

        System.out.println("Size: " + list.size());
        System.out.println("Is it Empty? "+ list.isEmpty());

        displayLinkedList(list);

        System.out.println("Element on index 2: " + list.get(2));

        list.set(0, "XYZ");
        System.out.println("Does it contains XYZ? " + list.contains("XYZ"));
        System.out.println("XYZ is on index: "+ list.indexOf("XYZ"));

        displayLinkedList(list);

        list.reverse();
        System.out.println("Lista po odwroceniu: ");

        displayLinkedList(list);
    }

    private static void displayLinkedList(TwoWayLinkedList list){

        System.out.println("---Your list: ----");
        Element current = list.getHead();
        for (int i = 0; i < list.size(); i++) {

            if (current != null) {

                System.out.println("["+ i +"] "+current);
                current = current.getNext();
            }
        }
    }

}