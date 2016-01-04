/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.translator;

import java.util.Iterator;
import java.util.List;
import org.lugubria.sys.domain.KeyValue;

/**
 *
 * @author angel
 */
public class KeyValueTranslator {
    
    public static String translateKeyValueToHtml(List<KeyValue> listKv){
        Iterator<KeyValue> iKv = listKv.iterator();
        String htmlResult = "";
        while (iKv.hasNext()) {
            KeyValue keyValue = iKv.next();
            htmlResult = htmlResult+"<option value='"+keyValue.getKey()+"'>"+keyValue.getValue()+"</option>";
        }
        
        return htmlResult;
    }
    
}
