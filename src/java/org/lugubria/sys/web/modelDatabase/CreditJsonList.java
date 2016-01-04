/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.web.modelDatabase;

import java.util.ArrayList;
import java.util.List;
import org.lugubria.sys.domain.CreditDto;

/**
 *
 * @author angel
 */
public class CreditJsonList {
    
    private List<CreditDto> aaData;

    public List<CreditDto> getAaData() {
        return aaData;
    }

    public void setAaData(List<CreditDto> aaData) {
        this.aaData = aaData;
    }
}
