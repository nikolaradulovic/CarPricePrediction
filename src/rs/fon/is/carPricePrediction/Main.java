package rs.fon.is.carPricePrediction;

import java.io.FileNotFoundException;

/**
 * Created by Nikola on 3/4/2015.
 */
public class Main {

    private static String bmw = "http://www.polovniautomobili.com/putnicka-vozila/pretraga?page=1&sort=renewDate_desc&brand=37&city_distance=0&showOldNew=all&without_price=1";

    public static void main (String[] args) throws FileNotFoundException {

        HTMLParser htmlParser = new HTMLParser(bmw);
        int numOfPages = htmlParser.calculateNumberOfPages();

        htmlParser.generateData(numOfPages);

        System.out.println("Podaci su preuzeti");

        DataSorter ds = new DataSorter();
        int[][] matrix = ds.loadDataFromFile("data/unsortedData.txt");
        int[][] roatedMatrix = ds.rotateMatrix(matrix);

        ds.writeInFile(roatedMatrix);

        System.out.println("Podaci su uspesno upisani");



    }
}
