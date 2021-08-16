package edu.upenn.cit594.processor;

import edu.upenn.cit594.util.Property;

import java.util.LinkedList;
import java.util.Map;

public class AverageLivableAreaCalculator implements AverageCalculator{
    @Override
    public int calculateAverage(String zipcode, Map<String, LinkedList<Property>> properties) {
        double counter = 0;
        double sum = 0;
        LinkedList<Property> propertiesByZip = properties.get(zipcode);
        for (Property currentProperty : propertiesByZip) {
            try {
                double currentMarketValue = Double.parseDouble(currentProperty.getTotalLivableArea());
                sum += currentMarketValue;
                counter++;
            } catch(Exception e) {
                // logging
            }
        }
        if (counter == 0 || sum == 0) return 0; // return 0 if no valid entry
        int average = (int) (sum/counter);
        return average;
    }
}
