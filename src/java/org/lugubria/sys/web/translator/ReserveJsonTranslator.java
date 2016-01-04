/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.web.translator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lugubria.sys.domain.ReserveDto;
import org.lugubria.sys.web.modelDatabase.ReserveJson;
import org.lugubria.sys.web.modelDatabase.ReserveJsonList;

/**
 *
 * @author angel
 */
public class ReserveJsonTranslator {

    public static ReserveJsonList reserveToReservejson(List<ReserveDto> listFrom){
        ArrayList<ReserveJson> listTo = new ArrayList<ReserveJson>();
        
        Iterator<ReserveDto> i = listFrom.iterator();
        ReserveDto fromPro;
        ReserveJson toPro;
        
        while(i.hasNext()){
            fromPro = i.next();
            toPro = new ReserveJson();
            toPro.setDateReserve(fromPro.getDateReserve());
            toPro.setDebit(fromPro.getDebit());
            toPro.setOnAccount(fromPro.getOnAccount());
            toPro.setPerson(fromPro.getPerson());
            toPro.setBranchName(fromPro.getBranchName());
            toPro.setTotal(fromPro.getTotal());
            toPro.setPagoId("<a href=\"#\" onclick=\"recordReserve("+fromPro.getReserveId()+"); return false;\" ><span class=\"ui-icon ui-icon-disk\" title=\"Cancelar Reserva\">Cancelar Reserva</span></a>");
            listTo.add(toPro);
        }
        
        ReserveJsonList rjList = new ReserveJsonList();
        rjList.setAaData(listTo);
        return rjList;
    }
}
