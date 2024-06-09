package version;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        BTree<Integer> b = new BTree<>(3, comp);
        b.add(59);
        b.add(23);
        b.add(7);
        b.add(91);
        b.add(73);
        b.add(97);
        b.add(19);
        b.add(79);
        b.add(61);
        b.add(101);


        if (b.search(23) != null) {
            System.out.println("\nfound");
        } else {
            System.out.println("\nnot found");
        }

        b.delete(101);
        b.delete(59);
    }



}