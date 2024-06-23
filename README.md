# Algorithms and Data Structures Laboratory Classes

Welcome to the repository for the Algorithms and Data Structures Laboratory classes. This repository contains all the materials and assignments for the laboratory sessions. Each lab focuses on different algorithms and data structures, providing both theoretical explanations and practical coding exercises.

## Table of Contents

1. [Introduction](#introduction)
2. [Lab List](#lab-list)
    - [Lab 1: Introduction to Algorithms](#lab-1-introduction-to-algorithms)
    - [Lab 2: Array and Iterator](#lab-2-array-and-iterator)
    - [Lab 3: One-Way Lists](#lab-3-one-way-lists)
    - [Lab 4: Two-Way Linked Lists](#lab-4-two-way-linked-lists)
    - [Lab 5: Basic Sorting Algorithms](#lab-5-basic-sorting-algorithms)
    - [Lab 6: Effective Sorting Algorithms](#lab-6-effective-sorting-algorithms)
    - [Lab 7: Heaps and other structures](#lab-7-heaps-and-other-structures)
    - [Lab 8: Tree structure part 1](#lab-8-tree-structure-part-1)
    - [Lab 9: Tree structure part 2](#lab-9-tree-structure-part-2)
    - [Lab 10: Graphs](#lab-10-graphs)
3. [Setup and Installation](#setup-and-installation)

## Introduction

This repository contains the laboratory exercises for a course on Algorithms and Data Structures. Each lab focuses on a different topic within this field, providing practical examples and coding exercises to help you understand and implement various algorithms and data structures.

## Lab List

### Lab 1: Introduction to Algorithms
- **Description:** drawHourglass, drawBridge, longestNaturalSubstring 
- **Topics Covered:** Loops.

### Lab 2: Array and Iterator
- **Description:** 
1. Zaimplementuj i przetestuj:
  a. Napisz klasę TableIterator<E> implementującą iterator zwracający kolejne elementy tablicy typu E,
  b. Zdefiniuj klasę Table<E> reprezentującą tablicę typu E. Klasa musi umożliwiać iterowanie po elementach tablicy z wykorzystaniem pętli foreach,
2. Zdefiniuj klasę PascalIterator przyjmującą liczbę N i generującą kolejne elementy N-tego wiersza trójkąta Pascala. W implementacji nie wykorzystuj definicji opartej o silnię!
- **Topics Covered:** Arrays, lists, strings, basic operations such as iterator and generic types.

### Lab 3: One-Way Lists
- **Description:** Zaimplementuj i przetestuj klasę OneWayArrayList<E> zgodną z interfejsem IList<E> podanym na wykładzie. Podczas implementacji pomiń metodę iterator().
Podczas sprawdzania poprawności uwzględnij przypadki:
a. pustej listy, 
b. listy jednoelementowej,
c. listy o parzystej oraz listy o nieparzystej liczbie elementów
Pamiętaj, że projekt klasy ma mieć na uwadze potrzeby potencjalnego użytkownika tzn. mają być zgłaszane informatywne komunikaty o błędach oraz obsługiwane dowolne, poprawne wartości np. null jako elementy na liście.
Zwróć uwagę, że w opisie powyżej, węzły „zwykłe” i „tablicowe” nigdy nie są przemieszane.
- **Topics Covered:** one-way lists, exceptions

### Lab 4: Two-Way Linked Lists
- **Description:** 
Zaimplementuj i przetestuj klasę TwoWayLinkedList<E> zgodną z interfejsem IList<E> podanym na wykładzie, reprezentującą listę:
• dwukierunkową,
• z głową i ogonem (referencją na ostatni element),
• bez strażników,
• prostą (tzn. nie cykliczną).
W implementacji pomiń iteratory.
- **Topics Covered:** Two-Way Linked Lists, Queue, Stack

### Lab 5: Basic Sorting Algorithms
- **Description:** 
Wykorzystując przygotowaną paczkę kodu zaimplementuj i przetestuj następujące warianty poznanych algorytmów:
a. Sortowanie przez wstawianie z przeszukiwaniem binarnym (ang. binary search),
b. Sortowanie przez wybór z jednoczesnym wyszukiwaniem minimum i maksimum,
c. Sortowanie koktajlowe (ang. Shaker sort) jako modyfikacja sortowania bąbelkowego.
- **Topics Covered:** Basic sorting algorithms, their complexity, statistics in MS Excel
- **Additional Files:**
  - SortingTester package
  - MS Excel metrics
    
### Lab 6: Effective Sorting Algorithms
- **Description:** 
Wykorzystując paczkę kodu z listy 5 zaimplementuj i przetestuj następujące warianty poznanych algorytmów:
a. Sortowanie przez scalanie iteracyjne z kolejką (Wykład 5 slajd 8),
b. Sortowanie szybkie zoptymalizowane pod kątem list dowiązaniowych:
  i. z wyborem pivota jako pierwszego elementu,
  ii. z wyborem pivota jako losowego elementu,
Algorytmy przetestuj na wariantach kolekcji z poprzedniej listy. Sprawdź, czy implementacje są tak samo efektywne dla list i tablic. Przygotuj wykresy metryk jak dla listy 5.
- **Topics Covered:** Effective sorting algorithms, their complexity and differences, tested on lists and arrays, statistics in MS Excel
- **Additional Files:**
  - SortingTester package
  - MS Excel metrics

### Lab 7: Heaps and other structures
- **Description:** 
Zdefiniuj klasę implementujące strukturę ternarnego (węzły z trojgiem dzieci) kopca maksymalnego (ang. ternary max-heap) Array3Heap<T> zaimplementowanego na tablicy.
Interfejs kopca powinien udostępniać trzy operacje:
• void clear() – czyszczącą kopiec (usuwającą wszystkie elementy),
• void add(T element) – wstawiającą nowy element do kopca,
• T maximum() – zwracającą maksymalny element kopca wraz z jego usunięciem.
Opis budowy kopca opisano na wykładzie 6.
Podczas konstrukcji kopca przekaż podstawową pojemność, a gdy w kopcu nie ma miejsca, należy rozmiar podwoić. Załóż, że null nie jest poprawną wartością.
- **Topics Covered:** Searching algorithms, priority queues, minHeap, maxHeap and their basic operations
![Screenshot 2024-06-23 at 12 53 24](https://github.com/AlinaLenart/AiSD_Lab/assets/147208016/da72b025-ead2-40ae-a737-3022d3323f37)


### Lab 8: Tree structure part 1
- **Description:** 
Zdefiniuj klasę BST<T> implementującą strukturę binarnego drzewa poszukiwań (ang. binary search tree, BST) bez referencji na rodzica.
Klasa ma definiować następujące operacje:
• implementacja rekurencyjna:
    o wyszukania elementu,
    o znalezienie minimum,
    o znalezienie maksimum,
    o przejścia po drzewie w porządku pre-order,
• implementacja iteracyjna:
    o znalezienie następnika,
    o wstawienia elementu,
    o usunięcia elementu.
Implementacja operacji przejścia po drzewie ma stosować wzorzec projektowy wizytator (na wykładzie nazwany „egzekutorem”).
Należy pamiętać, by operacje adekwatnie rozdzielić między klasą drzewa, a klasą węzła. Użytkownik klasy BST<T> nie może uzyskiwać bezpośredniego dostępu do szczegółów implementacyjnych tj. obiektów klasy węzła.
**Modification**:
Zdefiniuj klasę IntBST dziedziczącą po BST<Integer> zawierającą metodę IntBST maxSumSubtree(int n), zwracającą poddrzewo, którego suma wartości jest możliwie największa, ale mniejsza od wartości n.
- **Topics Covered:** Tree structure, iterative and recursive operations, creating subtrees and time complexity of those structures, tree traversal methods.

### Lab 9: Tree structure part 2
- **Description:** 
Zdefiniuj klasę BTree<T> implementujące strukturę B-drzewa o następujących cechach:
• Drzewo może posiadać dowolną wartość minimalnego stopnia węzła t ≥ 2,
• Każdy węzeł drzewa przechowuje:
    ▪ uporządkowaną tablicę kluczy,
    ▪ tablicę dzieci,
    ▪ aktualną liczbę kluczy/dzieci,
    ▪ wartość logiczną mówiącą, czy węzeł jest liściem,
    ▪ jeśli trzeba – wartość parametru t,
• Klucze należy przeszukiwać przeszukiwaniem binarnym,
• Drzewo udostępnia operacje:
    ▪ Wyszukiwanie,
    ▪ Wstawiania węzła z naprawianiem (omówione na zajęciach),
    ▪ Usuwanie węzła z naprawianiem (omówione na zajęciach).
Przyjmij, że wartości null są niepoprawne. W przypadku wstawiania istniejącej wartości nie zmieniaj drzewa, ale poinformuj użytkownika (zgłoszenie wyjątku lub zwrócenie wartości logicznej).
- **Topics Covered:** Principles of **balanced** tree data structures, b-tree as a good choice for databases

### Lab 10: Graphs
- **Description:** 
W kuchni pewnej firmy cateringowej przyszykowana została uczta. Niestety, ktoś zapomniał sprawdzić ile produktów zostało zakupionych przed przygotowaniem i wysłaniem posiłków do klienta, co może oznaczać problemy finansowe podczas rozliczeń podatkowych. Na szczęście, wiadomo jest ile jakich dań przygotowano, znane są dokładne proporcje dane w przepisach oraz wiadomo ile jakich półproduktów zostało po gotowaniu.

Napisz program, który poda ile podstawowych składników zostało zużytych podczas gotowania. Przyjmij, że przepisy są grafami, w których ukazano powiązania między składnikami bazowymi, półproduktami powstałymi w czasie gotowania oraz gotowymi daniami.
- **Topics Covered:** Graph representation (adjacency matrix), depth-first search (DFS), breadth-first search (BFS), Dijkstra's algorithm.
- **Additional Files:**
  - Graph diagram based on my testing examples

## Setup and Installation

To set up this repository on your local machine, follow these steps:

**Clone the repository:**
   ```bash
   git clone git@github.com:AlinaLenart/AiSD_Lab.git
   cd AiSD_Lab
