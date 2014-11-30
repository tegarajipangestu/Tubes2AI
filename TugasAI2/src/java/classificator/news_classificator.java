package classificator;

//~--- non-JDK imports --------------------------------------------------------

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;

import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;

import weka.filters.Filter;
import weka.filters.MultiFilter;

//~--- JDK imports ------------------------------------------------------------

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tegar
 */
public class news_classificator {
    public static String[]   CompleteResult;
    public static Classifier model;
    public static Instances  source;

    public news_classificator() throws Exception {
        model          = read_to_model("Model");
        CompleteResult = null;
        source         = null;
    }

    public void Classify(int i) throws Exception {
        if (i == 1) {}
        else {}
    }

    public static void main(String args[]) {
        try {

//          news_classificator nc = new news_classificator();
//          String[] ses = new String[2];
//          ses[0] = "Tender Naskah UN Diduga Bermasalah Sejak Awal";
//          ses[1] = "TEMPO.CO, Jakarta - Forum Indonesia untuk Transparansi Anggaran (Fitra) telah menduga PT Ghalia Indonesia Printing tak akan berhasil menyelesaikan tender naskah ujian nasional. Koordinator Investigasi dan Advokasi Uchok Sky Khadafi menilai proses tender perusahaaan tersebut ganjil. \\\"Dari awal saya sudah menduga ini bermasalah,\\\" katanya saat dihubungi, Ahad, 14 April 2013.\\nUchok menyebutkan, dalam lelang, Ghalia menawarkan harga yang lebih tinggi, Rp 22,8 miliar. Sedangkan perusahaan lainnya, PT Aneka Ilmu memberi menawarkan Rp 17 miliar, PT Jasuindo Tiga Perkasa Rp 21,1 miliar, dan PT Balebat Dedikasi Prima Rp 21,6 miliar. Namun kementerian Pendidikan dan Kebudayaan tetap memenangkan perusahaan tersebut.\\nSelain itu, Ghalia ternyata tak hanya mengikuti satu paket lelang. Perusahaan itu juga ikut dalam lelang tiga paket lainnya. Menurut Uchok, ini merupakan bukti Ghalia tak mempertimbangkan kapasitas perusahannya. \\\"Yang penting menang, dan akhirnya bermasalah,\\\" ujarnya.\\nPT Ghalia Indonesia Printing adalah perusahaan yang mencetak naskah ujian untuk 11 provinsi. Provinsi tersebut yakni Kalimantan Selatan, Kalimantan Timur, Sulawesi Utara, Sulawesi Tengah, Sulawesi Selatan, Sulawesi Tenggara, Bali, Nusa Tenggara Barat, Nusa Tenggara Timur, Gorontalo, dan Sulawesi Barat.\\nGhalia mengaku kesulitan memasukkan naskah ke boks per sekolah hingga membuat ujian nasional tingkat SMA, MA, dan SMK untuk ke-11 provinisi tersebut ditunda. \\\"Kalau mencetak, kami sudah selesai, tapi ketika memasukan ke boks per sekolah, itu yang kami kesulitan,\\\" kata Direktur Ghalia, Hamzah Lukman.\\nUjian nasional awalnya akan diselenggarakan serentak Senin besok, 15 April 2013. Karena terlambat, jadwal untuk Senin, yakni ujian Bahasa Indonesia, dipindah pekan depan. Untuk Selasa, yakni Bahasa Inggris dan Fisika/Ekonomi, ditunda 23 April 2013. Sementara itu, untuk mata pelajaran Matematika yang seharusnya Rabu, 17 April, digeser ke hari Jumat, 19 April 2013.'\n" +
//"'TNI AU Dilibatkan untuk Percepat Distribusi Soal UN',Pendidikan,'TEMPO.CO, Jakarta - Kementerian Pendidikan dan Kebudayaan meminta bantuan dari Tentara Nasional Indonesia Angkatan Udara untuk mendistribusikan naskah ujian nasional sebelas provinsi yang jadwalnya mundur. Menteri Pendidikan dan Kebudayaan Mohammad Nuh beralasan pengiriman menggunakan bantuan Angkatan Udara bisa dilaksanakan sewaktu-waktu, berbeda dengan penerbangan biasa.\\n\\\"Sehingga selesai pengepakan bisa langsung dibawa ke Lanud Halim Perdanakusuma,\\\" ucap Nuh ketika ditemui di kantornya, Ahad, 14 April 2013. Nuh menjelaskan, TNI AU sudah bersedia menyiapkan empat Hercules, satu Foker, dan satu pesawat jenis Boeing 737.\\nMenurut Ketua Badan Standar Badan Nasional Pendidikan Muhammad Aman Wirakartakusuma, naskah ujian nasional SMA sudah diterima daerah paling lambat tiga hari sebelumnya. Sedangkan untuk SMP harus sudah dikirim sejak enam hari sebelum ujian berlangsung. Namun, naskah ujian untuk sebelas provinsi ini belum terkirim sampai sekarang.\\nPT Ghalia Indonesia Printing mengalami kesulitan dalam pendistribusian naskah ujian nasional ke sebelas provinsi. Terlambatnya pengiriman ini menyebabkan pemunduran jadwal ujian nasional dari Senin besok menjadi Kamis, 18 April 2013. Sebelas provinsi itu adalah Kalimantan Selatan, Kalimantan Timur, Sulawesi Utara, Gorontalo, Sulawesi Tengah, Sulawesi Barat, Sulawesi Selatan, Sulawesi Tenggara, Bali, Nusa Tenggara Barat, dan Nusa Tenggara Timur. Kebijakan ini berimbas kepada 3.601 sekolah SMA/MA, 1.508 SMK, dan 1,1 juta anak.\\nSebelumnya, sesuai jadwal Kementerian Pendidikan, ujian nasional untuk tingkat SMA/MA/SMALB dan Paket C akan berlangsung pada 15-18 April, untuk jenjang SMK 15-17 April. Sedangkan jenjang SMP/MTs/SMLB dan Paket B pada 22-25 April 2013, dan SD/MI dan Paket A pada 6-8 Mei 2013.";
//          WriteStringToArff(ses);
//            LearnFromDataset(1);
//          ClassifyFromUser(model);
            CSVToArff("C:\\Users\\toshibapc\\Documents\\NetBeansProjects\\EksplorasiWeka\\hoya hoya.csv");
        } catch (Exception ex) {
            Logger.getLogger(news_classificator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Learn
    public static void LearnFromDataset(int pilSkema) throws Exception {
        DataSource dataSource =
            new DataSource(
                "C:\\Users\\toshibapc\\Documents\\NetBeansProjects\\TugasAI2\\dataset\\labelled_artikel_updated.arff");
        Instances train = dataSource.getDataSet();

        train.setClassIndex(1);

        MultiFilter multiFilter = new MultiFilter();
        String[]    options     =
            weka.core.Utils.splitOptions(
                "weka.filters.MultiFilter -F \"weka.filters.unsupervised.attribute.StringToWordVector -R 1,3 -W 1000 -prune-rate -1.0 -T -I -N 1 -L -S -stemmer weka.core.stemmers.NullStemmer -M 1 -stopwords C:\\\\Users\\\\toshibapc\\\\Documents\\\\NetBeansProjects\\\\TugasAI2\\\\stopwords\\\\stopwords.txt -tokenizer \\\"weka.core.tokenizers.WordTokenizer -delimiters \\\\\\\" \\\\\\\\r \\\\\\\\t.,;:\\\\\\\\\\\\\\'\\\\\\\\\\\\\\\"()?!0123456789-+=_-\\\\\\\"\\\"\" -F \"weka.filters.unsupervised.attribute.Reorder -R last-first\"");

        multiFilter.setOptions(options);
        multiFilter.setInputFormat(train);

        FilteredClassifier filteredClassifier = new FilteredClassifier();
        Classifier         cls;
        SMO                smo      = new SMO();
        String[]           options1 =
            weka.core.Utils.splitOptions(
                "-C 1.0 -L 0.001 -P 1.0E-12 -N 0 -V -1 -W 4 -K \"weka.classifiers.functions.supportVector.PolyKernel -E 1.0 -C 250007\"");

        filteredClassifier.setFilter(multiFilter);
        smo.setOptions(options1);
        cls = smo;
        filteredClassifier.setClassifier(cls);
        filteredClassifier.buildClassifier(train);
        model = filteredClassifier;
        write_to_model(model, "Model");

        Evaluation eval = new Evaluation(train);

        if (pilSkema == 0) {
            eval.evaluateModel(filteredClassifier, train);
        } else {
            eval.crossValidateModel(filteredClassifier, train, 10, new Random(1));
        }

        String[] Result = new String[2];

        Result[0] = eval.toSummaryString("\n--Result--\n", false);
        Result[1] = eval.toMatrixString("\n--Matrix--\n");
        System.out.println(eval.toSummaryString("\n--Result--\n", false));
        System.out.println(eval.toMatrixString("\n--Matrix--\n"));

//      return cls;
    }

    // Read model
    public static Classifier read_to_model(String Model) throws Exception {
        Classifier read = (Classifier) SerializationHelper.read(Model);

        return read;
    }

    // Write model
    public static void write_to_model(Classifier write, String model) throws Exception {
        SerializationHelper.write(model, write);
    }

    public static void ClassifyFromUser(Classifier cls) throws Exception {

        // loading data
        Instances unlabeled = DataSource.read("inputuser.arff");

        unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

        // copy
        Instances labeled = new Instances(unlabeled);

        // label instance
        for (int i = 0; i < unlabeled.numInstances(); i++) {
            double clsLabel = cls.classifyInstance(unlabeled.instance(i));

            labeled.instance(i).setClassValue(clsLabel);
        }

        DataSink.write("news_classified.arff", labeled);
    }

    public static void ClassifyFromCSV(Classifier cls) throws Exception {

        // loading data
        Instances unlabeled = DataSource.read("inputcsv.arff");

        unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

        // copy
        Instances labeled = new Instances(unlabeled);

        // label instance
        for (int i = 0; i < unlabeled.numInstances(); i++) {
            double clsLabel = cls.classifyInstance(unlabeled.instance(i));

            labeled.instance(i).setClassValue(clsLabel);
        }

        DataSink.write("news_classified.arff", labeled);
    }

    public static void CSVToArff(String path) throws IOException {
        BufferedReader bufferedReader =
            new BufferedReader(
                new FileReader("C:\\Users\\toshibapc\\Documents\\NetBeansProjects\\EksplorasiWeka\\hoya hoya.csv"));
        BufferedWriter writer  = null;
        File           f       = new File(path);
        File           logFile = new File("inputcsv.arff");

        // This will output the full path where the file will be written to...
        writer = new BufferedWriter(new FileWriter(logFile));

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("@relation newsfromcsv\n");
        stringBuilder.append("@attribute judul string\n");
        stringBuilder.append("@attribute full_text string\n");
        stringBuilder.append(
            "@attribute label {'Pendidikan', 'Politik', 'Hukum dan Kriminal', 'Sosial Budaya', 'Olahraga', 'Teknologi dan Sains', 'Hiburan', 'Bisnis dan Ekonomi', 'Kesehatan', 'Bencana dan Kecelakaan'}\n");
        stringBuilder.append("@data\n");

        CSVParser cSVParser = new CSVParser(bufferedReader, CSVFormat.EXCEL);

        for (CSVRecord cSVRecord : cSVParser) {
            stringBuilder.append("'").append(cSVRecord.get(0)).append("'" + ","
                                 + "'").append(cSVRecord.get(1)).append("'" + ",?");
        }

        writer.write(stringBuilder.toString());
    }

    public static void WriteStringToArff(String[] input) {
        BufferedWriter writer = null;

        try {

            // create a temporary file
            File logFile = new File("inputuser.arff");

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(logFile));

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("@relation newsunlabelled\n");
            stringBuilder.append("@attribute judul string\n");
            stringBuilder.append("@attribute full_text string\n");
            stringBuilder.append(
                "@attribute label {'Pendidikan', 'Politik', 'Hukum dan Kriminal', 'Sosial Budaya', 'Olahraga', 'Teknologi dan Sains', 'Hiburan', 'Bisnis dan Ekonomi', 'Kesehatan', 'Bencana dan Kecelakaan'}\n");
            stringBuilder.append("@data\n");
            stringBuilder.append("'").append(input[0]).append("'" + "," + "'").append(input[0]).append("'" + ",?");
            writer.write(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {}
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
