/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simplyianm
 */
public class MessageDigest {
    private List<Message> digestContent = new ArrayList<Message>();
    
    public void add(Message message) {
        digestContent.add(message);
    }
    
    public List<Message> getDigestContent() {
        return digestContent;
    }
}