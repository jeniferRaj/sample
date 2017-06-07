


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;



public class CreateFILE {

    String fileNae = "D:/sample.txt";
    FileWriter fs;
    String strLine;
    HashMap<String, Double> product;
    TreeMap<Double, String> finalVal;

    public CreateFILE() {
        product = new HashMap<String, Double>();
        product.put("GOOG", 975.60);
        product.put("MS", 42.52);
        product.put("AMZN", 1006.73);
        product.put("SGI", 7.75);
    }

    public void createNewFile() throws IOException {
        try {
            File f = new File(fileNae);

            if (!f.exists()) {
                System.out.println("existtr");
                fs = new FileWriter(f);
            } else {
                f.delete();
                fs = new FileWriter(new File(fileNae));
                System.out.println(" not existtr");

            }
            fs.write("GOOG-50,MS-10\r\nSGI-100,GOOG-50,MS-10\r\nGOOG-100,AMZN-90,MS-80");
            fs.close();
            System.out.println("Wriet Successfully");

        } catch (IOException e) {
            System.out.println("File Error");
        } catch (Exception e) {
            System.out.println("normal");
        }
    }

    public void readFile(String fileName) throws IOException {

        try {
            finalVal = new TreeMap<Double, String>();
            FileReader fr = new FileReader(new File(fileName));
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            while ((strLine = br.readLine()) != null) {

                double tempVal = 0.0d;
                String[] splitVal = strLine.split(",");
                for (int j = 0; j < splitVal.length; j++) {
                    String[] splitAgVal = splitVal[j].split("-");
                    Double getProductVAl = product.get(splitAgVal[0]);
                    getProductVAl = getProductVAl * Integer.valueOf(splitAgVal[1]);
                    if (tempVal == 0) {
                        tempVal = getProductVAl;
                    } else {
                        tempVal = tempVal + getProductVAl;
                    }

                }
                i++;
                finalVal.put(tempVal, strLine);
            }
            NavigableMap nm = finalVal.descendingMap();
            for (Object strVal : nm.values()) {
                String text = strVal.toString();
                System.out.println( text);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
              System.out.println("fileName");
             new CreateFILE().readFile(args[0]);

    }
}
