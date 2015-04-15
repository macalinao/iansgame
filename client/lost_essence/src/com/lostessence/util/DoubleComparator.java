/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.util;

import java.util.Comparator;
import java.util.Map;

/**
 * A useful comparer between doubles.
 * 
 * @author simplyianm
 */
public class DoubleComparator implements Comparator {
    private Map map;
    
    public DoubleComparator(Map map) {
        this.map = map;
    }
    
    @Override
    public int compare(Object a, Object b) {
        if ((Double) map.get(a) < (Double) map.get(b)) {
            return 1;
        } else if((Double) map.get(a) == (Double) map.get(b)) {
            return 0;
        } else {
            return -1;
        }
    }
}
