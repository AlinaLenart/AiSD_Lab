import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        test1();
    }
    private static void test1(){
        Comparator<Integer> comp = Comparator.naturalOrder();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(comp);

        bst.insert(50);//             50
        bst.insert(30);//           /    \
        bst.insert(20);//         30      70
        bst.insert(40);//        /  \    /  \
        bst.insert(70);//      20   40  60   80
        bst.insert(60);//
        bst.insert(80);//

        System.out.println("Search Test:");
        System.out.println("Search for 40: " + bst.search(40)); // return true
        System.out.println("Search for 90: " + bst.search(90)); // return false

        System.out.println("\nMin and Max Test:");
        System.out.println("Min: " + bst.findMin()); // return 20
        System.out.println("Max: " + bst.findMax()); // return 80

        System.out.println("\nPre-order Traversal:");
        IntegerToStringExec executor = new IntegerToStringExec();
        bst.preOrderWalk(executor);
        String traversalResult = executor.getResult();
        System.out.println(traversalResult);


        System.out.println("\nSuccessor Test:");
        System.out.println("Successor of 30: " + bst.successor(30)); // return 40
        System.out.println("Successor of 40: " + bst.successor(40)); // return 50
        System.out.println("Successor of 60: " + bst.successor(60)); // return 70
        System.out.println("Successor of 80: " + bst.successor(80)); // return null

    }
}