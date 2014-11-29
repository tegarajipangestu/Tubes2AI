package classificator;

//~--- non-JDK imports --------------------------------------------------------

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;

import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;

//~--- JDK imports ------------------------------------------------------------

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.functions.SMO;
import weka.core.Instance;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.Add;

/**
 *
 * @author tegar
 */
public class news_classificator {
    public static Classifier mode;
    public static Instances source;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        try {
            Learn(1);
        } catch (Exception ex) {
            Logger.getLogger(news_classificator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Learn
    public static Classifier Learn(int pilSkema) throws Exception {
        source = DataSource.read("dataset\\labelled_artikel.arff");
        
        if (source.classIndex() == -1) {
            source.setClassIndex(source.numAttributes() - 1);
        }
        Instances  train = source;
        train.setClassIndex(1);
        MultiFilter multiFilter = new MultiFilter();
        String[] options = weka.core.Utils.splitOptions("weka.filters.MultiFilter -F \"weka.filters.unsupervised.attribute.NominalToString -C 1,3\" -F \"weka.filters.unsupervised.attribute.StringToWordVector -R first-last -W 1000 -prune-rate -1.0 -T -I -N 1 -L -S -stemmer weka.core.stemmers.NullStemmer -M 1 -stopwords src\\\\classificator\\\\stopwords\\\\stopwords.txt -tokenizer \\\"weka.core.tokenizers.WordTokenizer -delimiters \\\\\\\" \\\\\\\\r \\\\\\\\t.,;:\\\\\\\\\\\\\\'\\\\\\\\\\\\\\\"()?!0123456789\\\\\\\"\\\"\" -F \"weka.filters.unsupervised.attribute.Reorder -R last-first\"");
        multiFilter.setOptions(options);
        multiFilter.setInputFormat(source);
        Instances filteredTrain = Filter.useFilter(train, multiFilter);
        
        Classifier cls;
        SMO smo = new SMO();
        String[] options1 = weka.core.Utils.splitOptions("-C 1.0 -L 0.001 -P 1.0E-12 -N 0 -V -1 -W 4 -K \"weka.classifiers.functions.supportVector.PolyKernel -E 1.0 -C 250007\"");
        smo.setOptions(options1);
        cls = smo;
        //Classify
            //set class attribute
//        filteredTrain.setClassIndex(1);
//        filteredTrain.classIndex();
        cls.buildClassifier(filteredTrain);
        
        Evaluation eval = new Evaluation(filteredTrain);

        // Full training
        if (pilSkema == 0) {
            eval.evaluateModel(cls, filteredTrain);
        }

        // 10-fold
        else 
        {
            eval.crossValidateModel(cls, filteredTrain, 10, new Random(1));
        }
        
        System.out.println(eval.toSummaryString("\n--Result--\n", false));
        System.out.println(eval.toMatrixString("\n--Matrix--\n"));

        return cls;
    }
}

    /**
     *
     * @param Model
     * @return
     * @throws java.lang.Exception
     */

//    // Read model
//    public static Classifier read_to_model(String Model) throws Exception {
//        Classifier read = (Classifier) SerializationHelper.read(Model);
//
//        return read;
//    }
//
//    // Write model
//    public static void write_to_model(Classifier write, String model) throws Exception {
//        SerializationHelper.write(model, write);
//    }
//
//    // Classifying
//    public static void Classify(Classifier cls) throws Exception {
//
//        // loading data
//        Instances unlabeled = DataSource.read("weather.unlabeled.nominal.arff");
//
//        unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
//
//        // copy
//        Instances labeled = new Instances(unlabeled);
//
//        // label instance
//        for (int i = 0; i < unlabeled.numInstances(); i++) {
//            double clsLabel = cls.classifyInstance(unlabeled.instance(i));
//
//            labeled.instance(i).setClassValue(clsLabel);
//        }
//
//        DataSink.write("weather.labeled_nominal.arff", labeled);
//    }
//}


//~ Formatted by Jindent --- http://www.jindent.com
