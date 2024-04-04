public class Main {
    public static void main(String[] args) {


        System.out.println("First Test (Strings): ");
        firstTest(3);

        System.out.println("\nSecond Test (Integers): ");
        secondTest(5);

        System.out.println("\nThird Test (Empty List): ");
        emptyListTest(3);

        System.out.println("\nFourth Test (Single Element List): ");
        singleElementListTest(3);




    }

    private static void firstTest(int M){

        OneWayArrayList list = new OneWayArrayList(M);

        System.out.println("Initial size: " + list.size());
        list.add("Alina");
        list.add("Bartosz");
        list.add("Czeslaw");
        list.add("Dorota");
        list.add("Ewelina");

        displayList(list);


        System.out.println("Size after adding: " + list.size());
        System.out.println("Element [0]: " + list.get(0));
        list.add(2,"Beata");
        System.out.println("List after adding person on index 2:");

        displayList(list);

        list.remove("Alina");
        System.out.println("List after removing \"Alina\":");

        displayList(list);

        System.out.println("Does list contains \"Alina\" : " +  list.contains("Alina"));

        list.remove(4);
        System.out.println("List after removing element on index 4: ");
        displayList(list);

        list.clear();
        System.out.println("List after clearing isEmpty? "+ list.isEmpty());

    }
    private static void secondTest(int M){

        OneWayArrayList list = new OneWayArrayList(M);

        System.out.println("Initial size: " + list.size());
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(11);
        list.add(13);
        list.add(17);

        displayList(list);

        System.out.println("Size after adding: " + list.size());
        System.out.println("Element [4]: " + list.get(4));
        list.add(1,0);
        System.out.println("List after adding 0 on index 1:");

        displayList(list);


        System.out.println("Does list contains 5? : " +  list.contains(5));

        System.out.println("Removed element: " + list.remove(7));
        System.out.println("List after removing element on index 7: ");

        displayList(list);

        System.out.println("List isEmpty? "+ list.isEmpty());

    }
    private static void emptyListTest(int M) {

        OneWayArrayList<Integer> list = new OneWayArrayList<>(M);

        System.out.println("Initial size: " + list.size());
        System.out.println("Is the empty list really empty? " + list.isEmpty());

    }
    private static void singleElementListTest(int M) {

        OneWayArrayList<Integer> list = new OneWayArrayList<>(M);

        list.add(44);
        System.out.println("Initial size: " + list.size());
        System.out.println("Is the single element list empty? " + list.isEmpty());
        System.out.println("Element [0]: " + list.get(0));
        /*System.out.println("Element [2]: " + list.get(2)); */



    }

    private static <E> void displayList(OneWayArrayList<E> list) {

        System.out.println("------ List ------");
        Node<E> currentNode = list.getHead();
        while (currentNode != null) {
            for (int i = 0; i < currentNode.getCounter(); i++) {
                System.out.print(currentNode.getElements()[i] + " ");
            }
            currentNode = currentNode.getNextNode();
            System.out.println();
        }
        System.out.println("-----------------");
    }
}