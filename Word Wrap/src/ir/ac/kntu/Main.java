package ir.ac.kntu;

import java.util.Scanner;

public class Main{

    static final int MAX = Integer.MAX_VALUE;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input String : ");
        String input = scanner.nextLine(); //she is my friend
        System.out.print("Maximum width of each line : ");
        int lineWidth = scanner.nextInt(); //(maximum no. of characters that can fit in a line) //6

        String[] wordArray = input.split(" ");
        int[] lengthOfWordArray = new int[wordArray.length];
        for(int i = 0 ; i < wordArray.length ; i++ ){
            lengthOfWordArray[i] = wordArray[i].length(); // lengthOfWordArray[] = {3, 2, 2, 5}
        }

        solveWordWrap (lengthOfWordArray, lineWidth);
    }



    //n = size of lengthOfWordArray
    public static void solveWordWrap (int lengthOfWordArray[], int lineWidth){
        int n = lengthOfWordArray.length;

        int extras[][] = new int[n+1][n+1];
        for (int i = 1; i <= n; i++){
            extras[i][i] = lineWidth - lengthOfWordArray[i-1];
            for (int j = i+1; j <= n; j++)
                extras[i][j] = extras[i][j-1] - lengthOfWordArray[j-1] - 1;
        }

        int costOfLine[][]= new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (extras[i][j] < 0)
                    costOfLine[i][j] = MAX;
                else if (j == n && extras[i][j] >= 0)
                    costOfLine[i][j] = 0;
                else
                    costOfLine[i][j] = extras[i][j]*extras[i][j];
            }
        }

        int p[] =new int[n+1];
        int totalCost[] = new int[n+1];
        totalCost[0] = 0;
        for (int j = 1; j <= n; j++) {
            totalCost[j] = MAX;
            for (int i = 1; i <= j; i++) {
                if (totalCost[i-1] != MAX && costOfLine[i][j] != MAX &&
                        (totalCost[i-1] + costOfLine[i][j] < totalCost[j])) {
                    totalCost[j] = totalCost[i-1] + costOfLine[i][j];
                    p[j] = i;
                }
            }
        }

        printSolution(p, n);
    }

    public static int printSolution (int p[], int n) {
        int k;
        if (p[n] == 1)
            k = 1;
        else
            k = printSolution (p, p[n]-1) + 1;
        System.out.println("Line number" + " " + k + ": " +
                "From word no." +" "+ p[n] + " " + "to" + " " + n);
        return k;
    }
}
