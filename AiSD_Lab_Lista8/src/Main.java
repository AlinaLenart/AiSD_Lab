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

        System.out.println("Search for 40: " + bst.search(40)); // return true
        System.out.println("Search for 90: " + bst.search(90)); // return false

        System.out.println("Min: " + bst.findMin()); // return 20
        System.out.println("Max: " + bst.findMax()); // return 80

        System.out.println("\nPre-order Traversal:");
        IntegerToStringExec executor = new IntegerToStringExec();
        bst.preOrderWalk(executor);
        String traversalResult = executor.getResult();
        System.out.println(traversalResult); //50, 30, 20, 40, 70, 60, 80


        System.out.println("Successor of 30: " + bst.successor(30)); // return 40
        System.out.println("Successor of 40: " + bst.successor(40)); // return 50
        System.out.println("Successor of 60: " + bst.successor(60)); // return 70
        System.out.println("Successor of 80: " + bst.successor(80)); // return null

        int elementToDelete = 30;
        System.out.println("Deleting element: " + elementToDelete);
        bst.delete(elementToDelete);

        IntegerToStringExec executor2 = new IntegerToStringExec();
        bst.preOrderWalk(executor2);
        String traversalResult2 = executor2.getResult();
        System.out.println(traversalResult2); //50; 40; 20; 70; 60; 80

        int rootElementToDelete = 50;
        System.out.println("Deleting root element: " + rootElementToDelete);
        bst.delete(rootElementToDelete);

        IntegerToStringExec executor3 = new IntegerToStringExec();
        bst.preOrderWalk(executor3);
        String traversalResult3 = executor3.getResult();
        System.out.println(traversalResult3);//60; 40; 20; 70; 80

        int nonExistentElement = 90;
        try {
            System.out.println("Deleting non-existent element: " + nonExistentElement);
            bst.delete(nonExistentElement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}