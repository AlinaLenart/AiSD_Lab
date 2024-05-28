package proby;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comp = Comparator.naturalOrder();
        BTree<Integer> btree = new BTree<>(3, comp);

        btree.insert(59);
        btree.insert(23);
        btree.insert(7);
        btree.insert(97);
        btree.insert(73);
        btree.insert(67);
        btree.insert(19);
        btree.insert(79);
        btree.insert(61);
        btree.insert(101);

        System.out.println("B-tree structure:");
        btree.printTree(btree.getRoot(), 0);

        System.out.println("\nB-tree have 19? " + btree.search(19, btree.getRoot()));
        System.out.println("B-tree have 10? " + btree.search(10, btree.getRoot()));

        btree.delete(7);
        System.out.println("\nB-tree after deleting 7:");
        btree.printTree(btree.getRoot(), 0);

        btree.delete(10);
        System.out.println("\nB-tree after attempting to delete non-existing 10:");
        btree.printTree(btree.getRoot(), 0);

        btree.delete(19);
        System.out.println("\nB-tree after deleting 19 (one node reaches minimum):");
        btree.printTree(btree.getRoot(), 0);

        btree.delete(73);
        System.out.println("\nB-tree after deleting 73 (from root):");
        btree.printTree(btree.getRoot(), 0);

    }
}
