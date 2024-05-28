//import java.util.Comparator;
//
//public class Main {
//    public static void main(String[] args) {
//        Comparator<Integer> comp = Comparator.naturalOrder();
//        BTree<Integer> b = new BTree<>(3, comp);
//        b.insert(59);
//        b.insert(23);b.print();
//        b.insert(7);b.print();
//        b.insert(97);b.print();
//        b.insert(73);b.print();
//        b.insert(67);b.print();
//        b.insert(19);b.print();
//        b.insert(79);b.print();
//        b.insert(61);
//        b.insert(101);
//        b.print();
//
//        if (b.search(140) != null) {
//            System.out.println("\nfound");
//        } else {
//            System.out.println("\nnot found");
//        }
//    }
//}