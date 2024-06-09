package copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        int t = 3;
        int elements = 50;
        Comparator<Integer> comparator = Comparator.naturalOrder();

        System.out.println("---Test Sorted---");
        testAdd(comparator, elements, t);

        System.out.println("---Test Random---");
        testAddRandom(comparator, elements, t);

        System.out.println("---Test Deletion in Leaf---");
        testDeletionLeaf(comparator, t);

        System.out.println("---Test Deletion Internal---");
        testDeletionInternal(comparator, t);

        System.out.println("Degree (t): "+ t +"\tMin keys(t-1): "+(t-1)+"\tMax keys(2t-1): "+(2*t-1));
    }

    private static void testDeletionLeaf(Comparator<Integer> comparator, int t) {//10, 20, 5, 6, 12, 30, 7, 17, 3, 4, 2, 1, 15, 25, 35, 22
        BTree<Integer> btree = new BTree<>(comparator, t);
        btree.add(10);btree.add(20);btree.add(5);btree.add(6);btree.add(12);btree.add(30);btree.add(7);btree.add(17);btree.add(4);btree.add(2);btree.add(1);btree.add(15);btree.add(25);btree.add(35);btree.add(22);btree.add(13);btree.add(16);btree.add(21);btree.add(33);btree.add(71);btree.add(19);btree.add(34);btree.add(28);btree.add(11);btree.add(98);btree.add(65);btree.add(56);btree.add(36);
        btree.printTree();

        System.out.println("---Usuwanie z liscia:---");
        System.out.println("Usuwanie 30 ==> zwykly lisc ");
        btree.remove(30);
        btree.printTree();

        System.out.println("Usuwanie 28 ==> ponizej minimum, pozycz z lewego (i.1): ");
        btree.remove(28);
        btree.printTree();

        System.out.println("Usuwanie 25 ==> ponizej minimum, pozycz z prawego bo lewy minimum (i.2): ");
        btree.remove(25);
        btree.printTree();

        System.out.println("Usuwanie 22 ==> laczenie dwoch minimalnych, wziecie z rodzica (i.3) :");
        btree.remove(22);
        btree.printTree();

        System.out.println("Usuwanie 21, 33 i 98 (liscie zeby byly minimalne):");
        btree.remove(21);
        btree.remove(33);
        btree.remove(98);
        btree.printTree();

        System.out.println("Usuwanie 36 ==> przypadek minimalnego rodzica (i.3 naprawa) :");
        btree.remove(36);
        btree.printTree();

    }

    private static void testDeletionInternal(Comparator<Integer> comparator, int t) {//10, 20, 5, 6, 12, 30, 7, 17, 3, 4, 2, 1, 15, 25, 35, 22
        BTree<Integer> btree = new BTree<>(comparator, t);
        btree.add(8);btree.add(10);btree.add(20);btree.add(5);btree.add(6);btree.add(12);btree.add(30);btree.add(7);btree.add(17);btree.add(4);btree.add(2);btree.add(1);btree.add(15);btree.add(25);btree.add(35);btree.add(22);btree.add(13);btree.add(16);btree.add(21);btree.add(33);btree.add(71);btree.add(19);btree.add(34);btree.add(28);btree.add(11);btree.add(98);btree.add(65);btree.add(56);btree.add(36);
        btree.printTree();

        System.out.println("---Usuwanie z wnetrza: ---");
        System.out.println("Usuwanie 8 ==> minimalny wezel, wziecie poprzednika (ii.1)");
        btree.remove(8);
        btree.printTree();

        System.out.println("Usuwanie 7 ==> minimalny wezel, wziecie nastepnika (ii.1)");
        btree.remove(7);
        btree.printTree();

        System.out.println("Usuwanie 15 ==> wartosc z korzenia (wezmie nastepnika z liscia!)");
        btree.remove(15);
        btree.printTree();

    }


    private static BTree<Integer> testAdd(Comparator<Integer> comparator, int elements, int t) {
        BTree<Integer> btree = new BTree<>(comparator, t);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= elements; i++) {
            list.add(i);
        }

        for (int newElem : list) {
            btree.add(newElem);
        }

        btree.printTree();

        return btree;
    }

    private static BTree<Integer> testAddRandom(Comparator<Integer> comparator, int elements, int t) {
        BTree<Integer> btree = new BTree<>(comparator, t);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= elements; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        for (int newElem : list) {
            btree.add(newElem);
        }

        btree.printTree();
        return btree;
    }
}

