/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.web.translator;

import java.util.Iterator;
import org.lugubria.sys.domain.CreditDetailDto;
import org.lugubria.sys.domain.ListCreditDetailDto;

/**
 *
 * @author angel
 */
public class CreditTranslator {
    
    public static void addAction(ListCreditDetailDto list){
        Iterator<CreditDetailDto> i = list.getaaData().iterator();
        String actions;
        while (i.hasNext()) {
            CreditDetailDto creditDetailDto = i.next();
            actions = "<div><div class=\"inlinelinks\" ><a class=\"inlinelinks\" href=\"#\" onclick=\"detailPayment("+creditDetailDto.getCreditId()+"); return false;\" ><span class=\"ui-icon ui-icon-note\" title=\"Detalle de Pagos\">Detalle de Pagos</span></a></div>";
            actions += "<div class=\"inlinelinks\" ><a class=\"inlinelinks\" href=\"#\" onclick=\"detailBuy("+creditDetailDto.getCreditId()+"); return false;\" ><span class=\"ui-icon ui-icon-suitcase\" title=\"Detalle de Compra al credito\">Detalle de Compra al Credito</span></a></div></div>";
            creditDetailDto.setAction(actions);
        }
    }
    
}
