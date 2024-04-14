import java.util.Comparator;

import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;


public class Main {
    public static void main(String[] args) {

        int[] arraySizes = {0, 5, 10, 15, 20, 30, 40, 50, 60, 70, 80, 100, 150, 200, 250, 500, 1000, 2000, 5000, 10000};


        Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> orderedGenerator = new MarkingGenerator<Integer>(new OrderedIntegerArrayGenerator());
        Generator<MarkedValue<Integer>> reversedGenerator = new MarkingGenerator<Integer>(new ReversedIntegerArrayGenerator());
        Generator<MarkedValue<Integer>> randomGenerator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));
        Generator<MarkedValue<Integer>> shuffledGenerator = new MarkingGenerator<Integer>(new ShuffledIntegerArrayGenerator(10));

        /*AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ModifiedInsertSort<>(markedComparator);

        testing.results.swapping.Result result = Tester.runNTimes(algorithm, orderedGenerator, 1000, 50);
        printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
        printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
        printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

        System.out.println("always sorted: " + result.sorted());
        System.out.println("always stable: " + result.stable());*/



        //testInsertSort(markedComparator, orderedGenerator, arraySizes, "Ordered Generator");
        //testInsertSort(markedComparator, reversedGenerator, arraySizes, "Reversed Generator");
        testInsertSort(markedComparator, randomGenerator, arraySizes, "Random Generator");
        //testInsertSort(markedComparator, shuffledGenerator, arraySizes, "Shuffled Generator");


    }

    private static void testInsertSort(Comparator<MarkedValue<Integer>> markedComparator, Generator<MarkedValue<Integer>> generator, int[] arraySizes, String generatorType){

        System.out.println("--Sortowanie przez wstawianie z przeszukiwaniem binarnym; liczby: "+ generatorType +"--");


        for (int number : arraySizes) {

            AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ModifiedInsertSort<>(markedComparator);
            System.out.println("Rozmiar tablicy: "+ number);
            testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, number, 50);
            printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
            printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
            printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

            System.out.println("always sorted: " + result.sorted());
            System.out.println("always stable: " + result.stable());

        }

    }

    private void testSelectSort(Comparator<MarkedValue<Integer>> markedComparator, Generator<MarkedValue<Integer>> generator, int[] arraySizes, String generatorType){

        System.out.println("--Sortowanie przez wybÃ³r z jednoczesnym wyszukiwaniem minimum i maksimum; liczby: "+ generatorType +"--");



        for (int number : arraySizes) {

            AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ModifiedSelectSort<>(markedComparator);
            System.out.println("Rozmiar tablicy: "+ number);
            testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, number, 50);
            printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
            printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
            printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());
            System.out.println("always sorted: " + result.sorted());
            System.out.println("always stable: " + result.stable());
        }

    }

    private void testShakerSort(Comparator<MarkedValue<Integer>> markedComparator, Generator<MarkedValue<Integer>> generator, int[] arraySizes, String generatorType){

        System.out.println("--Sortowanie koktajlowe jako modyfikacja sortowania babelkowego; liczby: "+ generatorType +"--");

        AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ShakerSort<>(markedComparator);

        for (int number : arraySizes) {

            System.out.println("Rozmiar tablicy: "+ number);
            testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, number, 50);
            printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
            printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
            printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

            System.out.println("always sorted: " + result.sorted());
            System.out.println("always stable: " + result.stable());
            System.out.println();
        }
        System.out.println();
    }

    private static void printStatistic(String label, double average, double stdDev) {
        System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
    }

    private static String double2String(double value) {
        return String.format("%.12f", value);
    }
}