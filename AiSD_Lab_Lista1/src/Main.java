//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Hourglass hourglass = new Hourglass();
        hourglass.drawHourglass(10);

        System.out.println();

        Bridge bridge = new Bridge();
        bridge.drawBridge(7);

        System.out.println();

        int[] numbers = {0, 2, 8, -3, -3, -2, 0, 1, 2, 7, 10, 0};

        LongestNaturalSubstrings lns = new LongestNaturalSubstrings();
        int[][] output = lns.longestNaturalSubstrings(numbers);
        displayArrayOfSubstrings(output);



    }

    private static void displayArrayOfSubstrings(int[][] outputArray) {

        for (int i = 0; i < outputArray.length; i++) {

            if(outputArray[i] != null) {

                for (int j = 0; j < outputArray[i].length; j++) {

                    System.out.print(outputArray[i][j]+" ");
                }

                System.out.println();
            }

        }

    }
}