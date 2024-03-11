public class LongestNaturalSubstrings {

    public int[][] longestNaturalSubstrings(int[] numbers){

        int[][] outputArray = new int[numbers.length][];

        for (int i = 0; i < numbers.length; i++) {

            if (numbers[i] == 0) {

                int length = 1;

                while (i + length < numbers.length && numbers[i + length] == numbers[i] + length) {

                    length++;
                }

                int[] substring = new int[length];

                for (int j = 0; j < length; j++) {

                    substring[j] = numbers[i + j];
                }

                outputArray[i] = substring;
                i += length;

            }
        }

        return outputArray;
    }


}
