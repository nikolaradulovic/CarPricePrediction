package rs.fon.is.carPricePrediction.dataMining;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.M5P;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Created by Nikola on 6/10/2015.
 */
public class WekaModelTree {

    private M5P m5p;
    private FilteredClassifier filteredClassifier;


    public WekaModelTree(){
        m5p = new M5P();


    }

    public void classifyWithModel(Instances data) throws Exception{
        m5p.buildClassifier(data);
        System.out.println(m5p.toString());

    }

    public void classifyWithoutModel(Instances data) throws Exception{
        Remove remove = new Remove();
        remove.setAttributeIndices("1");

        this.filteredClassifier = new FilteredClassifier();
        this.filteredClassifier.setClassifier(m5p);
        this.filteredClassifier.setFilter(remove);
        this.filteredClassifier.buildClassifier(data);
        System.out.println(filteredClassifier.toString());




    }

//    public void classiftyWithoutStandardization(Instances data) throws Exception {
//        m5p.buildClassifier(data);
//        System.out.println(m5p.toString());
//    }
//
//    private Standardize buildStandardizedFilter(Instances data) throws Exception {
//        Standardize standardizeFilter = new Standardize();
//        standardizeFilter.setInputFormat(data);
//
//        return standardizeFilter;
//
//    }
//
//    public void classifyWithStandardization(Instances data) throws Exception {
//        Standardize standardize = buildStandardizedFilter(data);
//        this.filteredClassifier = new FilteredClassifier();
//        this.filteredClassifier.setFilter(standardize);
//        this.filteredClassifier.setClassifier(m5p);
//        this.filteredClassifier.buildClassifier(data);
//        System.out.println(m5p.toString());
//
//
//
//    }





}
