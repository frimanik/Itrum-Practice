package org.example.ContOfElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountElements {

    public Map<Object,Integer> frequency(ArrayList<? extends Object>arr){
        HashMap<Object,Integer>result = new HashMap<>();
        for (Object o:arr){
            result.put(o,result.getOrDefault(o,0)+1);
        }
        return result;
    }
}
