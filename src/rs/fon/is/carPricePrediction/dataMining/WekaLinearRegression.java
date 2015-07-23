package rs.fon.is.carPricePrediction.dataMining;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.Standardize;

import java.util.Random;

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

        public String classifyWithModel(Instances data) throws Exception{
                linearRegression.buildClassifier(data);
                System.out.println(linearRegression.toString());

                Evaluation evaluation = new Evaluation(data);
                evaluation.crossValidateModel(linearRegression,data,10,new Random());

                return evaluation.toSummaryString();


        }

        public String classifyWithoutModel(Instances data) throws Exception{
                Remove remove = new Remove();
                remove.setAttributeIndices("1");

                this.filteredClassifier = new FilteredClassifier();
                this.filteredClassifier.setClassifier(linearRegression);
                this.filteredClassifier.setFilter(remove);
                this.filteredClassifier.buildClassifier(data);
                System.out.println(filteredClassifier);

                Evaluation evaluation = new Evaluation(data);
                evaluation.crossValidateModel(this.filteredClassifier,data,10,new Random());

                return evaluation.toSummaryString();

        }


        public String classifyWithoutModelStandardized(Instances data) throws Exception{

                Standardize standardize = buildStandardizeFilter(data);
                Remove remove = new Remove();
                remove.setAttributeIndices("1");
                Filter[] filters = new Filter[]{standardize, remove};

                MultiFilter mf = new MultiFilter();
                mf.setFilters(filters);

                this.filteredClassifier = new FilteredClassifier();
                this.filteredClassifier.setClassifier(linearRegression);
                this.filteredClassifier.setFilter(mf);
                this.filteredClassifier.buildClassifier(data);

               // return filteredClassifier.toString();
                Evaluation evaluation = new Evaluation(data);
                evaluation.crossValidateModel(this.filteredClassifier,data,10,new Random());

                return evaluation.toSummaryString();
        }

        public String classifyWithModelStandardizatied(Instances data) throws Exception {
                Standardize standardize = buildStandardizeFilter(data);

                this.filteredClassifier = new FilteredClassifier();
                this.filteredClassifier.setClassifier(linearRegression);
                this.filteredClassifier.setFilter(standardize);
                this.filteredClassifier.buildClassifier(data);

                //return filteredClassifier.toString();
                Evaluation evaluation = new Evaluation(data);
                evaluation.crossValidateModel(this.filteredClassifier,data,10,new Random());

                return evaluation.toSummaryString();

        }

        private Standardize buildStandardizeFilter(Instances data) throws Exception{
                Standardize standardizeFilter = new Standardize();
                standardizeFilter.setInputFormat(data);
                return standardizeFilter;

        }






}
