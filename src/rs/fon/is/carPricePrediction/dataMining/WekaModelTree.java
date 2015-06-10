package rs.fon.is.carPricePrediction.dataMining;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.M5P;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Standardize;

/**
 * Created by Nikola on 6/10/2015.
 */
public class WekaModelTree {

    private M5P m5p;
    private FilteredClassifier filteredClassifier;


    public WekaModelTree(){
        m5p = new M5P();


    }

    public void classiftyWithoutStandardization(Instances data) throws Exception {
        m5p.buildClassifier(data);
        System.out.println(m5p.toString());
    }

    private Standardize buildStandardizedFilter(Instances data) throws Exception {
        Standardize standardizeFilter = new Standardize();
        standardizeFilter.setInputFormat(data);

        return standardizeFilter;

    }

    public void classifyWithStandardization(Instances data) throws Exception {
        Standardize standardize = buildStandardizedFilter(data);
        this.filteredClassifier = new FilteredClassifier();
        this.filteredClassifier.setFilter(standardize);
        this.filteredClassifier.setClassifier(m5p);
        this.filteredClassifier.buildClassifier(data);
        System.out.println(m5p.toString());



    }





}
