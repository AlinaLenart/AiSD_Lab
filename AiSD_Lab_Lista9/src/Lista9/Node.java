package Lista9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Node<T> {
    List<T> keys;
    List<Node<T>> children;
    boolean isLeaf;
    final Comparator<T> comp;
    final int t;
    int size;

    public Node (boolean isLeaf, int t, Comparator<T> comp) {
        this.isLeaf = isLeaf;
        this.comp = comp;
        this.t = t;
        this.size = 0;
        this.keys = new ArrayList<>(2 * t - 1);
        this.children = new ArrayList<>(2 * t);
    }

    //modul SEARCH
    public Node<T> search(T elem) {
        int i = searchIndex(elem);
        if (i < size && comp.compare(elem, keys.get(i)) == 0) {
            return this;
        }
        return isLeaf ? null : children.get(i).search(elem); //rekurencja gdy nie jest lisciem
    }

    private int searchIndex(T elem) {
        if (size > 0) {
            int i = binarySearch(elem, 0, keys.size() - 1);
            if(i < size && comp.compare(elem, keys.get(i)) > 0) {
                i++;
            }
            return i;
        }
        return 0;
    }
    private int binarySearch(T elem, int low, int high) {
        if (low == high) {
            return low;
        }
        int mid = low + (high - low) / 2;
        int cmp = comp.compare(elem, keys.get(mid));
        if (cmp > 0) {
            return binarySearch(elem, mid + 1, high);
        }
        else if (cmp < 0){
            return binarySearch(elem, low, mid);
        }
        else {
            return mid;
        }
    }

    //modul ADD
    public void add(Stack<Node<T>> stack, T elem) {
        int index = searchIndex(elem);

        if (isLeaf) { //konczy na przejsciu do liscia i dodaniu MIMO przekroczenia rozmiaru
            addToLeaf(index, elem);
            stack.push(this);
        } else {
            stack.push(this);
            children.get(index).add(stack, elem);
        }
    }
    public void addToLeaf(int index, T key) {
        keys.add(index, key);
        size++;
    }
    public void split(Node<T> parent, Node<T> fullChild) {
        Node<T> splitProduct = new Node<>(fullChild.isLeaf, t, comp);
        T median = fullChild.keys.get(t - 1); //na pol i w lewo

        int insertIndex = parent.children.indexOf(fullChild) + 1; //zabieram mediane i wstawiam do rodzica (nawet jesli pelny)
        parent.children.add(insertIndex, splitProduct);
        parent.keys.add(parent.searchIndex(median), median);
        parent.size++;

        splitProduct.keys.addAll(fullChild.keys.subList(t, fullChild.keys.size())); //nowy: (srodek, prawo>
        fullChild.keys.subList(t - 1, fullChild.keys.size()).clear();               //pelny: <lewo, srodek)

        if (!fullChild.isLeaf) { //dostanie tez czesc jego dzieci
            splitProduct.children.addAll(fullChild.children.subList(t, fullChild.children.size()));
            fullChild.children.subList(t, fullChild.children.size()).clear();
        }

        splitProduct.size = splitProduct.keys.size();
        fullChild.size = fullChild.keys.size();

    }

    //modul REMOVE
    public boolean remove(T elem) {
        boolean removed = false;
        int i = searchIndex(elem);

        // element is within that node
        if (i < size && comp.compare(elem, keys.get(i)) == 0) { //znaleziony element

            if (isLeaf) {
                deleteFromNode(i); //z liscia odrazu usuwam
            } else {
                if(children.get(i).size >= t){
                    keys.set(i, children.get(i).removePredecessor(null)); //zamiana z poprzednikiem
                }
                else {
                    keys.set(i, children.get(i+1).removeSuccessor(null)); //zamiana z nastepnikiem
                }
            }
            removed = true;
            return removed;
        } else if (isLeaf) { //element nieznaleziony, return false
            return removed;
        } else {
            removed = children.get(i).remove(elem); //przejscie w glab drzewa
        }

        if (children.get(i).isInsufficient()) {
            fixChildren(i); //rekurencja
        }

        return removed;
    }

    private void fixChildren(int index) { //dzialam na nodzie parenta
        Node<T> child = children.get(index);
        Node<T> leftSibling = index > 0 ? children.get(index - 1) : null;
        Node<T> rightSibling = index < size ? children.get(index + 1) : null;

        if (leftSibling != null && leftSibling.size >= t) { //pozycz od lewego brata (o ile istnieje i nie jest Min)
            child.keys.addFirst(keys.remove(index - 1));  //lewy z rodzica
            child.size++;
            keys.add(index - 1, leftSibling.deleteFromNode(leftSibling.size - 1)); //z lewego dziecka ostatni do gory

            if (!leftSibling.isLeaf) {
                child.children.addFirst(leftSibling.children.removeLast()); //przekaze dzieci jesli trzeba
            }

        } else if (rightSibling != null && rightSibling.size >= t) { //pozycz od prawego brata (o ile to mozliwe)
            child.keys.add(keys.remove(index));
            child.size++;
            keys.add(index, rightSibling.deleteFromNode(0));

            if (!rightSibling.isLeaf) {
                child.children.add(rightSibling.children.removeFirst());
            }

        } else {
            if (leftSibling != null) { //polaczenie z lewym bratem (jesli istnieje), zostawiam lewy, usuwam child
                leftSibling.keys.add(this.deleteFromNode(index - 1));
                leftSibling.keys.addAll(child.keys);
                leftSibling.children.addAll(child.children);
                leftSibling.size = leftSibling.keys.size();
                this.children.remove(index);
            }

            else if (rightSibling != null) { //polaczenie z prawym bratem (jesli istnieje), zostawiam dziecko, usuwam prawy
                child.keys.addFirst(this.deleteFromNode(index));
                child.keys.addAll(rightSibling.keys);
                child.children.addAll(rightSibling.children);
                child.size = child.keys.size();
                this.children.remove(index + 1);
            }
        }
    }

    private T removePredecessor(Node<T> parent) { //predecessor = najwiekszy klucz w lewym dziecku
        T predecessor;
        if (isLeaf) {
            predecessor = deleteFromNode(size - 1); //najwiekszy z dziecka
        } else {
            predecessor = children.getLast().removePredecessor(this); //idziemy wglab drzewa az do liscia
        }

        if (parent != null && isInsufficient()) { //jesli zbyt maly naprawiam
            parent.fixChildren(parent.children.size() - 1);
        }

        return predecessor;
    }

    private T removeSuccessor(Node<T> parent) { //successor = najmniejszt klucz w prawym dziecku
        T successor;
        if (isLeaf) {
            successor = deleteFromNode(0); //mniejszy z dziecka
        } else {
            successor = children.getFirst().removeSuccessor(this); //idziemy wglab drzewa az do liscia
        }

        if (parent != null && isInsufficient()) { //jesli zbyt maly naprawiam
            parent.fixChildren(0);
        }

        return successor;
    }

    private T deleteFromNode(int index) {
        T retElem = keys.remove(index);
        size--;
        return retElem;
    }
    public boolean isFull(){
        return keys.size() > 2 * t - 1;
    }
    public boolean isInsufficient(){
        return keys.size() < t - 1 ;
    }

}