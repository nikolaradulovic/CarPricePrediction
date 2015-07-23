package rs.fon.is.carPricePrediction.main;

import rs.fon.is.carPricePrediction.dataMining.WekaLinearRegression;
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

//        this line of code collects data for regression
//        DataScraper.generateDataSet(renault);

        PrintStream out = new PrintStream(new FileOutputStream("data/results.txt"));

        Instances data = DataLoader.loadTrainingData("data/dataSet_renault.arff");

        WekaLinearRegression lr = new WekaLinearRegression();
        WekaSMOreg smoReg = new WekaSMOreg();

        out.print("\n Linear Regression with car model \n");
        out.print(lr.classifyWithModelStandardizatied(data));
        out.print("\n Linear Regression without car model \n");
        out.print(lr.classifyWithoutModelStandardized(data));

        out.print("\n SMO Regression with car model \n");
        out.print(smoReg.classifyWithModel(data));
        out.print("\n SMO Regression without car model \n");
        out.print(smoReg.classifyWithoutModel(data));







    }
}
