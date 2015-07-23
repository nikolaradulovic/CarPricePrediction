package rs.fon.is.carPricePrediction.parser;

import java.io.IOException;

/**
 * Created by Nikola on 7/23/2015.
 */
public class DataScraper {



     public static void generateDataSet(String carLink) throws IOException {

         HTMLParser htmlParser = new HTMLParser(carLink);
         int numOfPages = htmlParser.calculateNumberOfPages();

         htmlParser.generateData(numOfPages);

         System.out.println("Scraping finished");

         DataSorter ds = new DataSorter();
         String[][] matrix = ds.loadDataFromFile("data/unsortedData.csv");
         String[][] rotatedMatrix = ds.rotateMatrix(matrix);

         ds.writeInFile(rotatedMatrix);

         System.out.println("DataSet completed");


     }



}

