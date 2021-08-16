package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.Property;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class PropertiesReader {

    protected String filename;

    public PropertiesReader(String filename){
        this.filename = filename;
    }

    public static final int START = 0;
    public static final int FIELD = 1;
    public static final int OPEN_QUOTE = 2;
    public static final int CLOSE_QUOTE = 3;

    public Map<String, LinkedList<Property>> getAllProperties() {
        Map<String, LinkedList<Property>> properties = new HashMap<String, LinkedList<Property>>();
        Map<String, Integer> headerIdx = new HashMap<String, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // read header and record index
            String header = br.readLine();
            headerIdx = processHeader(header);

            // read data entry
            String entry;
            while ((entry = br.readLine()) != null) {
                ArrayList<String> lineArray = processLine(entry);
                String raw_zip_code = lineArray.get(headerIdx.get("zip_code")).trim();
                String zip_code;
                if(raw_zip_code.length() < 5 || !raw_zip_code.matches("[0-9]+") ){
                    continue;
                } else {
                    zip_code = raw_zip_code.substring(0, 5);
                }
                // may throw java.lang.NumberFormatException:
//                Integer market_value = Integer.parseInt(lineArray.get(headerIdx.get("market_value")));
                String market_value = lineArray.get(headerIdx.get("market_value")).trim();
                String total_livable_area = lineArray.get(headerIdx.get("total_livable_area")).trim();
                String building_code = lineArray.get(headerIdx.get("building_code")).trim();
                Property currentProperty = new Property(zip_code, market_value, building_code, total_livable_area);

                // put into return map
                if (!properties.containsKey(zip_code)){
                    LinkedList<Property> sameZipProperties = new LinkedList<Property>();
                    sameZipProperties.add(currentProperty);
                    properties.put(zip_code, sameZipProperties);
                } else {
                    LinkedList<Property> sameZipProperties = properties.get(zip_code);
                    sameZipProperties.add(currentProperty);
                    properties.put(zip_code, sameZipProperties);
                }
            }


            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: properties data file is not accessible");
            System.exit(0);
        }

        return properties;
    }

    public static ArrayList<String> processLine(String line) {
        int state = START;
        ArrayList<String> lineArray = new ArrayList<String>();
        StringBuilder currentField = new StringBuilder();;
        char[] currentLine = line.toCharArray();
        for (int i = 0; i < currentLine.length; i++) {
            char c = currentLine[i];
            switch(state) {
                case START:
                    switch (c) {
                        case ',':
                            lineArray.add("");
                            continue;
                        case '\"':
                            currentField = new StringBuilder();
                            state = OPEN_QUOTE;
                            continue;
                        default:
                            currentField = new StringBuilder();
                            currentField.append(c);
                            state = FIELD;
                            continue;
                    }
                case FIELD:
                    switch (c) {
                        case ',':
                            lineArray.add(currentField.toString().trim());
                            state = START;
                            continue;
                        default:
                            currentField.append(c);
                            if (i == currentLine.length - 1){
                                lineArray.add(currentField.toString().trim());
                            }
                            continue;
                    }
                case OPEN_QUOTE:
                    switch (c) {
                        case '\"':
                            state = CLOSE_QUOTE;
                            continue;
                        default:
                            currentField.append(c);
                    }
                case CLOSE_QUOTE:
                    switch (c) {
                        case ',':
                            lineArray.add(currentField.toString().trim());
                            state = START;
                            continue;
                        case '\"':
                            state = OPEN_QUOTE;
                            currentField.append('\"');
                            continue;
                    }
            }
        }
        return lineArray;
    }

    public static Map<String, Integer> processHeader(String header) {
        Map<String, Integer> headerIdx = new HashMap<String, Integer>();
        String[] headerArray = header.split(",");
        for (int i = 0; i < headerArray.length; i++) {
            String s = headerArray[i];
            if (s.equals("zip_code") || s.equals("market_value") || s.equals("building_code") || s.equals("total_livable_area")){
                headerIdx.put(s, i);
            }
        }
        return headerIdx;
    }

//    public static void main(String[] args){
//        PropertiesReader test = new PropertiesReader("/Users/zetianwu/Desktop/UPENN:MCIT/594/Group Project/CIT594-Project/properties.csv");
//        Map<String, LinkedList<Property>>result = test.getAllProperties();
//        for (String zip : result.keySet()) {
//            System.out.println(zip);
//            if(zip.equals("19148")) {
//                System.out.println("get size");
//                System.out.println(result.get(zip).size());
//            }
//        }
//
//    }

}
