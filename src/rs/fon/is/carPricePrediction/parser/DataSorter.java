package rs.fon.is.carPricePrediction.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Nikola on 3/4/2015.
 */
public class DataSorter {

    public String[][] loadDataFromFile(String filePath) throws FileNotFoundException {

        String[][] matrix;
       // int[][] matrix = { { 1 }, { 2 } };

        File inFile = new File(filePath);

        Scanner in = new Scanner(inFile);

        int intLength = 0;
        String[] length = in.nextLine().trim().split(" ");
        for (int i = 0; i < length.length; i++) {
            intLength++;
        }

        in.close();

        matrix = new String[9][intLength];
        in = new Scanner(inFile);

        int lineCount = 0;
        while (in.hasNextLine()) {
            String[] currentLine = in.nextLine().trim().split(" ");
            for (int i = 0; i < currentLine.length; i++) {
                try{

               // matrix[lineCount][i] = Integer.parseInt(currentLine[i]);
                    matrix[lineCount][i] = currentLine[i];
                }
                catch (Exception ex){
                   ex.printStackTrace();
                }
            }
            lineCount++;

        }

        return matrix;
    }

    public String[][] rotateMatrix(String[][] matrix){
        String[][] rotatedMatrix = new String[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = matrix.length - 1; j >= 0; j--) {
                rotatedMatrix[i][j] = matrix[j][i];
            }
        }

        return rotatedMatrix;
    }

    public void writeInFile(String[][] rotatedMatrix){


        PrintWriter writer = null;

        try {
            writer = new PrintWriter("data/dataSet.txt");
            mainloop: for (int i = 0; i < rotatedMatrix.length; i++) {


                for (int j = 0; j < rotatedMatrix[0].length; j++) {
                    if (rotatedMatrix[i][j] == ("1122334455") || rotatedMatrix[i][j] ==null || rotatedMatrix[i][j].equals("R")) {
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
