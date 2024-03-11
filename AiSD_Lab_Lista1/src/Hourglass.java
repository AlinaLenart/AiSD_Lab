public class Hourglass {

    public void drawHourglass(int n){

        if(n < 0)
            System.out.println("Ujemny wymiar, popraw wartosc n");

        else {

            drawEnd(n);

            for (int i = 1; i < n ; i++) {

                for (int j = 0; j < (2*n - 1); j++) {

                    if (j == i || j == (2*n - 2 - i))
                        System.out.print("X");

                    else if (j > (i + 1)  && j < (2*n - 3 - i))
                        System.out.print("O");

                    else
                        System.out.print(" ");

                }
                System.out.println();
            }

            for (int i = n - 2; i > 0 ; i--) {

                for (int j = (2*n - 1); j > 0 ; j--) {

                    if (j == (i + 1) || j == (2*n - 1 - i))
                        System.out.print("X");

                    else if (j > (i + 2)   && j < (2*n - 2 - i))
                        System.out.print("O");

                    else
                        System.out.print(" ");

                    }
                System.out.println();
            }

            drawEnd(n);
        }

    }

    private void drawEnd(int n){

        for (int i = 0; i < (2*n - 1); i++) {
            System.out.print("X");
        }
        System.out.println();

    }

}
