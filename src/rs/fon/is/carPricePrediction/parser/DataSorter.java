package rs.fon.is.carPricePrediction.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Nikola on 3/4/2015.
 */
public class DataSorter {

    public int[][] loadDataFromFile(String filePath) throws FileNotFoundException {

        int[][] matrix = { { 1 }, { 2 } };

        File inFile = new File(filePath);

        Scanner in = new Scanner(inFile);

        int intLength = 0;
        String[] length = in.nextLine().trim().split(" ");
        for (int i = 0; i < length.length; i++) {
            intLength++;
        }

        in.close();

        matrix = new int[8][intLength];
        in = new Scanner(inFile);

        int lineCount = 0;
        while (in.hasNextLine()) {
            String[] currentLine = in.nextLine().trim().split(" ");
            for (int i = 0; i < currentLine.length; i++) {
                matrix[lineCount][i] = Integer.parseInt(currentLine[i]);
            }
            lineCount++;

        }

        return matrix;
    }

    public int[][] rotateMatrix(int[][] matrix){
        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = matrix.length - 1; j >= 0; j--) {
                rotatedMatrix[i][j] = matrix[j][i];
            }
        }

        return rotatedMatrix;
    }

    public void writeInFile(int[][] rotatedMatrix){


        PrintWriter writer = null;

        try {
            writer = new PrintWriter("data/dataSet.txt");
            mainloop: for (int i = 0; i < rotatedMatrix.length; i++) {


                for (int j = 0; j < rotatedMatrix[0].length; j++) {
                    if (rotatedMatrix[i][j] == 1122334455) {
                        continue mainloop;
                    }
                }

                String text = "";
                for (int j = 0; j < rotatedMatrix[0].length; j++) {
                    text+=rotatedMatrix[i][j] + ",";
                }

                writer.println(text.substring(0, text.length()-1));

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        writer.close();

    }

}
