
public class Main {

    public static void main(String[] args) {

        Hourglass hourglass = new Hourglass();
        hourglass.drawHourglass(9);

        System.out.println();

        Bridge bridge = new Bridge();
        bridge.drawBridge(7);

        System.out.println();

        int[] numbers = {0, 1, 8, -3, -3, -2, 0, 1, 2, 7, 10, 0};

        LongestNaturalSubstrings lns = new LongestNaturalSubstrings();
        int[][] output = lns.longestNaturalSubstrings(numbers);
        displayArrayOfSubstrings(output);



    }

    private static void displayArrayOfSubstrings(int[][] outputArray) {

        System.out.println("{");

        for (int i = 0; i < outputArray.length; i++) {

            if (outputArray[i] != null) {

                System.out.print(" { ");

                for (int j = 0; j < outputArray[i].length; j++) {

                    if (j == (outputArray[i].length - 1)){
                        System.out.print(outputArray[i][j]+" ");
                    }
                    else {
                        System.out.print(outputArray[i][j]+", ");
                    }
                }

                System.out.println("},");

            }
        }
        System.out.println("}");
    }
}