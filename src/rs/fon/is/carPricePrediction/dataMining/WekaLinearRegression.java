package rs.fon.is.carPricePrediction.dataMining;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Standardize;

/**
 * Created by Nikola on 6/9/2015.
 */
public class WekaLinearRegression {

        private FilteredClassifier filteredClassifier;
        private LinearRegression linearRegression;

        public WekaLinearRegression(){
                linearRegression = new LinearRegression();
                linearRegression.setEliminateColinearAttributes(false);

        }



        public void classifyWithStandardization(Instances data) throws Exception{
                Standardize standardize = buildStandardizeFilter(data);
                this.filteredClassifier = new FilteredClassifier();
                this.filteredClassifier.setClassifier(linearRegression);
                this.filteredClassifier.setFilter(standardize);
                this.filteredClassifier.buildClassifier(data);
                System.out.println(filteredClassifier.toString());

        }

        public void classifyWithoutStandardization(Instances data) throws Exception {
                linearRegression.buildClassifier(data);
                System.out.println(linearRegression.toString());


        }

        private Standardize buildStandardizeFilter(Instances data) throws Exception{
                Standardize standardizeFilter = new Standardize();
                standardizeFilter.setInputFormat(data);
              //  standardizeFilter.
                return standardizeFilter;

        }






}
