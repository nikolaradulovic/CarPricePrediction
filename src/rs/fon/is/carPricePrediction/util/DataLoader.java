package rs.fon.is.carPricePrediction.util;

import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 * Created by Nikola on 6/10/2015.
 */
public class DataLoader {

    public static Instances loadTrainingData(String arrFileName) throws Exception{

        ConverterUtils.DataSource loader = new ConverterUtils.DataSource(arrFileName);
        Instances loadedData = loader.getDataSet();
        loadedData.setClassIndex(2);

        return loadedData;
    }
}
