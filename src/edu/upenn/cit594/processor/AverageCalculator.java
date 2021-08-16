package edu.upenn.cit594.processor;

import edu.upenn.cit594.util.Property;

import java.util.LinkedList;
import java.util.Map;

public interface AverageCalculator {
    public int calculateAverage(String zipcode, Map<String, LinkedList<Property>> properties);
}
