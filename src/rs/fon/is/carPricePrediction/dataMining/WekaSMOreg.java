package rs.fon.is.carPricePrediction.dataMining;

import weka.classifiers.functions.SMOreg;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Created by Nikola on 6/11/2015.
 */
public class WekaSMOreg {

    private FilteredClassifier filteredClassifier;
    private SMOreg smOreg;

    public WekaSMOreg(){
        smOreg = new SMOreg();

    }

    public void classifyWithModel(Instances data) throws Exception{
        smOreg.buildClassifier(data);
        System.out.println(smOreg.toString());

    }

    public void classifyWithoutModel(Instances data) throws Exception{

        Remove remove = new Remove();
        remove.setAttributeIndices("1");

        this.filteredClassifier = new FilteredClassifier();
        this.filteredClassifier.setClassifier(smOreg);
        this.filteredClassifier.setFilter(remove);
        this.filteredClassifier.buildClassifier(data);
        System.out.println(filteredClassifier.toString());

    }

}
