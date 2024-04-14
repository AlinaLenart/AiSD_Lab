import java.util.Comparator;

import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;


public class Main {
    public static void main(String[] args) {

        System.out.println("--Sortowanie przez wstawianie z przeszukiwaniem binarnym--");

        Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> randomGenerator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(10));
        Generator<MarkedValue<Integer>> reversedGenerator = new MarkingGenerator<Integer>(new ShuffledIntegerArrayGenerator());



        AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new ModifiedInsertSort<>(markedComparator);

        testing.results.swapping.Result result = Tester.runNTimes(algorithm, reversedGenerator, 1000, 50);

        printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
        printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
        printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

        System.out.println("always sorted: " + result.sorted());
        System.out.println("always stable: " + result.stable());

        /*

        System.out.println("\n--Sortowanie przez wybór z jednoczesnym wyszukiwaniem minimum i maksimum--");

        AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm2 = new ModifiedSelectSort<>(markedComparator);

        testing.results.swapping.Result result2 = Tester.runNTimes(algorithm2, generator, 1000, 20);

        printStatistic("time [ms]", result2.averageTimeInMilliseconds(), result2.timeStandardDeviation());
        printStatistic("comparisons", result2.averageComparisons(), result2.comparisonsStandardDeviation());
        printStatistic("swaps", result2.averageSwaps(), result2.swapsStandardDeviation());

        System.out.println("always sorted: " + result2.sorted());
        System.out.println("always stable: " + result2.stable());



        System.out.println("\n--Sortowanie koktajlowe jako modyfikacja sortowania babelkowego--");

        AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm3 = new ShakerSort<>(markedComparator);

        testing.results.swapping.Result result3 = Tester.runNTimes(algorithm3, generator, 1000, 20);

        printStatistic("time [ms]", result3.averageTimeInMilliseconds(), result3.timeStandardDeviation());
        printStatistic("comparisons", result3.averageComparisons(), result3.comparisonsStandardDeviation());
        printStatistic("swaps", result3.averageSwaps(), result3.swapsStandardDeviation());

        System.out.println("always sorted: " + result3.sorted());
        System.out.println("always stable: " + result3.stable());


        System.out.println("\n--Sortowanie babelkowe--");

        AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm4 = new BubbleSort<MarkedValue<Integer>>(markedComparator);

        testing.results.swapping.Result result4 = Tester.runNTimes(algorithm4, generator, 1000, 20);

        printStatistic("time [ms]", result4.averageTimeInMilliseconds(), result4.timeStandardDeviation());
        printStatistic("comparisons", result4.averageComparisons(), result4.comparisonsStandardDeviation());
        printStatistic("swaps", result4.averageSwaps(), result4.swapsStandardDeviation());

        System.out.println("always sorted: " + result4.sorted());
        System.out.println("always stable: " + result4.stable());
        */

    }



    private static void printStatistic(String label, double average, double stdDev) {
        System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
    }

    private static String double2String(double value) {
        return String.format("%.12f", value);
    }
}