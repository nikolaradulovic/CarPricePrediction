package rs.fon.is.carPricePrediction.main;

import rs.fon.is.carPricePrediction.dataMining.WekaLinearRegression;
import rs.fon.is.carPricePrediction.dataMining.WekaModelTree;
import rs.fon.is.carPricePrediction.dataMining.WekaSMOreg;
import rs.fon.is.carPricePrediction.util.DataLoader;
import weka.core.Instances;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by Nikola on 3/4/2015.
 */
public class Main {

    private static String bmw = "http://www.polovniautomobili.com/putnicka-vozila/pretraga?page=1&sort=renewDate_desc&brand=37&city_distance=0&showOldNew=all&without_price=1";
    private static String renault = "http://www.polovniautomobili.com/putnicka-vozila/pretraga?page=2&sort=renewDate_desc&brand=192&city_distance=0&showOldNew=all&without_price=1";

    public static void main (String[] args) throws Exception {

        //Stari kod, pisem novi
//        HTMLParser htmlParser = new HTMLParser(renault);
//        int numOfPages = htmlParser.calculateNumberOfPages();
//
//        htmlParser.generateData(numOfPages);
//
//        System.out.println("Podaci su preuzeti");
//
//        DataSorter ds = new DataSorter();
//        String[][] matrix = ds.loadDataFromFile("data/unsortedData.csv");
//        String[][] rotatedMatrix = ds.rotateMatrix(matrix);
//
//        ds.writeInFile(rotatedMatrix);
//
//        System.out.println("Podaci su uspesno upisani");

//        upisujem ceo output direktno u file results.txt
        PrintStream out = new PrintStream(new FileOutputStream("data/results.csv"));
        System.setOut(out);

        Instances data = DataLoader.loadTrainingData("data/dataSet_renault.arff");
        WekaLinearRegression lr = new WekaLinearRegression();
        WekaModelTree m5p = new WekaModelTree();
        WekaSMOreg smoReg = new WekaSMOreg();

        System.out.println("\n Linear Regression with car model \n");
        lr.classifyWithModel(data);
        System.out.println("\n Linear Regression without car model \n");
        lr.classifyWithoutModel(data);

        System.out.println("\n SMO with car model \n");
        smoReg.classifyWithModel(data);
        System.out.println("\n SMO without car model \n");
        smoReg.classifyWithoutModel(data);

        System.out.println("\n Model tree with car model \n");
        m5p.classifyWithModel(data);
        System.out.println("\n Model tree without car model \n");
       m5p.classifyWithoutModel(data);





    }
}
