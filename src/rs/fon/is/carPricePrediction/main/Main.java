package rs.fon.is.carPricePrediction.main;

import rs.fon.is.carPricePrediction.dataMining.WekaLinearRegression;
import rs.fon.is.carPricePrediction.dataMining.WekaModelTree;
import rs.fon.is.carPricePrediction.util.DataLoader;
import weka.core.Instances;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Nikola on 3/4/2015.
 */
public class Main {

    private static String bmw = "http://www.polovniautomobili.com/putnicka-vozila/pretraga?page=1&sort=renewDate_desc&brand=37&city_distance=0&showOldNew=all&without_price=1";
    private static String renault_megane = "http://www.polovniautomobili.com/putnicka-vozila/pretraga?submit_1=&brand=192&model=1824&price_from=&price_to=&year_from=&year_to=&door_num=&without_price=1&date_limit=&showOldNew=all&modeltxt=&engine_volume_from=&engine_volume_to=&power_from=&power_to=&mileage_from=&mileage_to=&emission_class=&layout=&seat_num=&wheel_side=&registration=&country=&city=&page=&sort=";
    private static String renault_modus = "http://www.polovniautomobili.com/putnicka-vozila/pretraga?submit_1=&brand=192&model=1825&price_from=&price_to=&year_from=&year_to=&door_num=&without_price=1&date_limit=&showOldNew=all&modeltxt=&engine_volume_from=&engine_volume_to=&power_from=&power_to=&mileage_from=&mileage_to=&emission_class=&layout=&seat_num=&wheel_side=&registration=&country=&city=&page=&sort=";
    public static void main (String[] args) throws Exception {

        //Stari kod, pisem novi
//        HTMLParser htmlParser = new HTMLParser(renault_megane);
//        int numOfPages = htmlParser.calculateNumberOfPages();
//
//        htmlParser.generateData(numOfPages);
//
//        System.out.println("Podaci su preuzeti");
//
//        DataSorter ds = new DataSorter();
//        int[][] matrix = ds.loadDataFromFile("data/unsortedData.txt");
//        int[][] rotatedMatrix = ds.rotateMatrix(matrix);
//
//        ds.writeInFile(rotatedMatrix);
//
//        System.out.println("Podaci su uspesno upisani");

        //upisujem ceo output direktno u file results.txt
        PrintStream out = new PrintStream(new FileOutputStream("data/results.csv"));
        System.setOut(out);

        Instances data = DataLoader.loadTrainingData("data/dataSet_megane.arff");
        WekaLinearRegression lr = new WekaLinearRegression();
        WekaModelTree m5p = new WekaModelTree();

        System.out.println("\n Linear Regression without standardization \n");
        lr.classifyWithoutStandardization(data);
        System.out.println("\n Linear Regression with standardization \n");
        lr.classifyWithStandardization(data);

        System.out.println("\n Model tree without standardization \n");
        m5p.classiftyWithoutStandardization(data);
        System.out.println("\n Model tree with standardization \n");
        m5p.classifyWithStandardization(data);





    }
}
