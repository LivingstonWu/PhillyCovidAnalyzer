package edu.upenn.cit594.datamanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.upenn.cit594.util.*;

public interface CovidReader {
    public Map<Integer, ArrayList<CovidData>> getAllCovidData();
}
