/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.web.translator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lugubria.sys.domain.ProductDetailDto;
import org.lugubria.sys.web.modelDatabase.ProductJson;
import org.lugubria.sys.web.modelDatabase.ProductJsonList;

/**
 *
 * @author angel
 */
public class ProductJsonTranslator {
    
    public static ProductJsonList productoToProductjson(List<ProductDetailDto> listFrom){
        ArrayList<ProductJson> listTo = new ArrayList<ProductJson>();
        
        Iterator<ProductDetailDto> i = listFrom.iterator();
        ProductDetailDto fromPro;
        ProductJson toPro;
        
        while(i.hasNext()){
            fromPro = i.next();
            toPro = new ProductJson();
            toPro.setCategoryName(fromPro.getCategoryName());
            toPro.setCodOrigin(fromPro.getCodOrigin());
            toPro.setDescription(fromPro.getDescription());
            toPro.setFactoryName(fromPro.getFactoryName());
            toPro.setName(fromPro.getName()+"<strong style='visibility: hidden;'>"+fromPro.getProductId()+"</strong>");
            listTo.add(toPro);
        }
        
        ProductJsonList pjl = new ProductJsonList();
        pjl.setAaData(listTo);
        return pjl;
    }
    
    public static ArrayList<ProductJson> productoToProductjsonupload(List<ProductDetailDto> listFrom){
        ArrayList<ProductJson> listTo = new ArrayList<ProductJson>();
        
        Iterator<ProductDetailDto> i = listFrom.iterator();
        ProductDetailDto fromPro;
        ProductJson toPro;
        
        while(i.hasNext()){
            fromPro = i.next();
            toPro = new ProductJson();
            toPro.setCategoryName(fromPro.getCategoryName());
            toPro.setCodOrigin(fromPro.getCodOrigin());
            toPro.setDescription(fromPro.getDescription());
            toPro.setFactoryName(fromPro.getFactoryName());
            toPro.setName(fromPro.getName()+"<strong style='visibility: hidden;'>"+fromPro.getProductId()+"</strong>");
            listTo.add(toPro);
        }
        
        return listTo;
    }
}
