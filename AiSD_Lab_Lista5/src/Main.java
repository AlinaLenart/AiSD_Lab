import java.util.Comparator;
import java.io.FileWriter;
import java.io.PrintWriter;

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



        /*AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm = new BubbleSort<>(markedComparator);

        testing.results.swapping.Result result = Tester.runNTimes(algorithm, randomGenerator, 1000, 200);
        printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
        printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
        printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

        System.out.println("always sorted: " + result.sorted());
        System.out.println("always stable: " + result.stable());*/




        testSort("InsertSort", markedComparator, orderedGenerator, arraySizes, "OrderedGenerator");
        testSort("InsertSort", markedComparator, reversedGenerator, arraySizes, "ReversedGenerator");
        testSort("InsertSort", markedComparator, randomGenerator, arraySizes, "RandomGenerator");
        testSort("InsertSort", markedComparator, shuffledGenerator, arraySizes, "ShuffledGenerator");

        testSort("SelectSort", markedComparator, orderedGenerator, arraySizes, "OrderedGenerator");
        testSort("SelectSort", markedComparator, reversedGenerator, arraySizes, "ReversedGenerator");
        testSort("SelectSort", markedComparator, randomGenerator, arraySizes, "RandomGenerator");
        testSort("SelectSort", markedComparator, shuffledGenerator, arraySizes, "ShuffledGenerator");

        testSort("ShakerSort", markedComparator, orderedGenerator, arraySizes, "OrderedGenerator");
        testSort("ShakerSort", markedComparator, reversedGenerator, arraySizes, "ReversedGenerator");
        testSort("ShakerSort", markedComparator, randomGenerator, arraySizes, "RandomGenerator");
        testSort("ShakerSort", markedComparator, shuffledGenerator, arraySizes, "ShuffledGenerator");



    }

    private static void testSort(String sortType, Comparator<MarkedValue<Integer>> markedComparator, Generator<MarkedValue<Integer>> generator, int[] arraySizes, String generatorType) {

        String fileName = sortType + generatorType + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("-- " + sortType + " test; numbers generated by: " + generatorType + " --");
            writer.println("Size\tTime\t\tStddev\t\tCompare\t\tStddev\t\tSwaps\t\tStddev");

            for (int size : arraySizes) {
                AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> algorithm;
                if (sortType.equals("InsertSort"))
                    algorithm = new ModifiedInsertSort<>(markedComparator);
                else if (sortType.equals("SelectSort"))
                    algorithm = new ModifiedSelectSort<>(markedComparator);
                else // Assume ShakerSort
                    algorithm = new ModifiedInsertSort<>(markedComparator);

                testing.results.swapping.Result result = Tester.runNTimes(algorithm, generator, size, 50);
                String formattedOutput = String.format("%5d\t%f\t%f\t%f\t%f\t%f\t%f", size,
                        result.averageTimeInMilliseconds(), result.timeStandardDeviation(),
                        result.averageComparisons(), result.comparisonsStandardDeviation(),
                        result.averageSwaps(), result.swapsStandardDeviation());

                writer.print(formattedOutput.replace('.', ',') + "\n");
            }

            System.out.println("Zapisano wyniki do pliku " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printStatistic(String label, double average, double stdDev) {
        System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
    }

    private static String double2String(double value) {
        return String.format("%.12f", value);
    }

}