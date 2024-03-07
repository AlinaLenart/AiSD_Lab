public class LongestNaturalSubstrings {

    public int[][] longestNaturalSubstrings(int[] numbers){

        int[][] outputArray = new int[numbers.length][];

        for (int i = 0; i < numbers.length; i++) {

            if (numbers[i] == 0) {

                int k = 0;

                for (int j = i; j < numbers.length - 1; j++) {

                    if (numbers[j + 1] == numbers[j] + 1)
                        k++;

                    else
                        break;
                }

                int[] substring = new int[k + 1];

                for (int j = 0; j <= k; j++) {

                    substring[j] = numbers[i + j];
                }

                outputArray[i] = substring;
                i += k + 1;

            }
        }

        return outputArray;
    }



}
