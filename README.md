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
    - [Lab 8: Hashing](#lab-8-hashing)
    - [Lab 9: Dynamic Programming](#lab-9-dynamic-programming)
    - [Lab 10: Advanced Topics](#lab-10-advanced-topics)
3. [Setup and Installation](#setup-and-installation)
4. [Contribution Guidelines](#contribution-guidelines)
5. [License](#license)

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


### Lab 8: Hashing
- **Description:** Exploration of hashing techniques and hash table implementations.
- **Topics Covered:** Hash functions, collision resolution strategies (chaining, open addressing), performance analysis.
- **Files:**
  - `Lab8_Hashing.ipynb`
  - `Lab8_Exercises.md`

### Lab 9: Dynamic Programming
- **Description:** Introduction to dynamic programming and its applications in solving complex problems.
- **Topics Covered:** Principles of dynamic programming, memoization, common dynamic programming problems (e.g., Fibonacci sequence, knapsack problem).
- **Files:**
  - `Lab9_Dynamic_Programming.ipynb`
  - `Lab9_Exercises.md`

### Lab 10: Advanced Topics
- **Description:** Exploration of advanced topics in algorithms and data structures.
- **Topics Covered:** Topics may include but are not limited to, advanced graph algorithms, string matching algorithms, computational geometry.
- **Files:**
  - `Lab10_Advanced_Topics.ipynb`
  - `Lab10_Exercises.md`

## Setup and Installation

To set up this repository on your local machine, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/algorithms-data-structures-labs.git
   cd algorithms-data-structures-labs
