/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.web.modelDatabase;

import java.util.List;
import org.lugubria.sys.domain.ReserveDto;

/**
 *
 * @author angel
 */
public class ReserveJsonList {

    List<ReserveJson> aaData;

    public List<ReserveJson> getAaData() {
        return aaData;
    }

    public void setAaData(List<ReserveJson> aaData) {
        this.aaData = aaData;
    }
}
