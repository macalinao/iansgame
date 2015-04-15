/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence;

import com.lostessence.client.MainC;
import com.lostessence.server.MainS;

/**
 *
 * @author simplyianm
 */
public class Main {
    public static void main(String[] args) {
        if (args[0].equals("c")) {
            MainC.main(args);
        } else {
            MainS.main(args);
            
        }
    }
}
