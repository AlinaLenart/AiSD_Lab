public interface IList<E> {
    public boolean add(E e); // dodanie elementu na koniec listy
    public void add(int index, E element); // dodanie elementu na podanej pozycji
    public void clear(); // skasowanie wszystkich elementów
    public boolean contains(E element); // czy lista zawiera podany element (equals())
    public E get(int index); // pobranie elementu z podanej pozycji
    public E set(int index, E element); // ustawienie nowej wartości na pozycji
    public int indexOf(E element); // pozycja szukanego elementu (equals())
    public boolean isEmpty(); // czy lista jest pusta
    public E remove(int index); // usuwa element z podanej pozycji
    public boolean remove(E element); // usuwa element (equals())
    public int size(); // rozmiar listy

}
