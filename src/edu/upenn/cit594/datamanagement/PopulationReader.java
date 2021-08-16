package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class PopulationReader {
    protected String filename;

    public PopulationReader(String filename){
        this.filename = filename;
    }

    public HashMap<String, Integer> getPopulation() {
        // <key, value> -> <zip, population>
        HashMap<String, Integer> populationMap = new HashMap<String, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] lineTemp = line.split("\\s+");
                String zipcode = (lineTemp[0].strip());
                Integer population = Integer.parseInt(lineTemp[1].strip());
                populationMap.put(zipcode, population);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: population file is not accessible");
            System.exit(0);
        }
        return populationMap;
    }

//    public static void main(String[] args){
//        PopulationReader test = new PopulationReader("/Users/zetianwu/Desktop/UPENN:MCIT/594/Group Project/CIT594-Project/population.txt");
//        HashMap<Integer, Integer> result = test.getPopulation();
//        for (Integer zip : result.keySet()) {
//            System.out.println(zip);
//        }
//    }
}
