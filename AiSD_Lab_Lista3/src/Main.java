public class Main {
    public static void main(String[] args) {

        OneWayArrayList owa = new OneWayArrayList(3);
        System.out.println(owa.size());
        owa.add("Alina");
        owa.add("Alicja");
        owa.add("Agnieszka");
        owa.add("Maciej");
        owa.add("Marcin");
        System.out.println(owa.size());
        System.out.println(owa.get(0));
        //System.out.println(owa.get(5));
//        owa.add(2,"Laura");
//        System.out.println(owa.get(2));
        //owa.remove(0);
        System.out.println(owa.contains("Alina"));
        displayList(owa);
    }

    private static <E> void displayList(OneWayArrayList<E> list) {
        System.out.println("One Way Arraylist: ");
        Node<E> currentNode = list.getHead();
        while (currentNode != null) {
            for (int i = 0; i < currentNode.getCounter(); i++) {
                System.out.print(currentNode.getElements()[i] + " ");
            }
            currentNode = currentNode.getNextNode();
            System.out.println();
        }
        System.out.println();
    }
}