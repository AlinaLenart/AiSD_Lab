public class Bridge {

    public void drawBridge(int n){

        if (n < 0)
            System.out.println("Ujemny wymiar, popraw wartosc n");

        else {

            for (int i = 0; i < n; i++) {

                if (i == n - 2){

                    for (int j = 0; j < (2*n - 1); j++) {

                        System.out.print("X");
                    }
                }

                else {

                    for (int j = 0; j < (2*n - 1); j++) {

                        if (j == 0 || j == 2*n - 2)
                            System.out.print("I");

                        else if (i > 0 && i < n - 2 && j == i)
                            System.out.print("\\");

                        else if (i > 0 && i < n - 2 && j == 2*n - 2 - i)
                            System.out.print("/");

                        else
                            System.out.print(" ");

                    }
                }

                System.out.println();

            }
        }
    }
}
