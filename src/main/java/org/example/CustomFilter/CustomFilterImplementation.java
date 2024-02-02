package org.example.CustomFilter;

import java.util.ArrayList;
;

public class CustomFilterImplementation {

    public ArrayList<Object> filter(ArrayList<?> arraylist, Filter filter) {
        if (arraylist == null || filter == null) {
            throw new IllegalArgumentException("Input array and filter must not be null");
        }

        ArrayList<Object> resultArray = new ArrayList<>();
        Object temp;
        for (Object o : arraylist) {
            temp = filter.apply(o);
            if (temp==null) continue;
            resultArray.add(filter.apply(o));
        }

        return resultArray;
    }
}

