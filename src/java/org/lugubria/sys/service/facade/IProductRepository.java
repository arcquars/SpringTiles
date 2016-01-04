/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import java.util.Map;
import org.lugubria.sys.domain.DeliveryBranch;
import org.lugubria.sys.domain.ProductDetailDto;
import org.lugubria.sys.domain.ProductDto;
import org.lugubria.sys.domain.ProductRegisterInventaryDto;
import org.lugubria.sys.domain.RecordProductDto;

/**
 *
 * @author angel
 */
public interface IProductRepository {
    
    public boolean createProduct(ProductDto product);
    
    public List<ProductDetailDto> getProductByCriteria(int type, String criteria);
    
    public Map<String, String> getAllCategory();
    
    public void deleteProduct(int proId);
    
    public void updateProduct(ProductDto product);
    
    public boolean productValidateName(String newName);
    
    public boolean createProductInventory(ProductRegisterInventaryDto prid);
    
    public List<RecordProductDto> getListRecordProduct(String ids);
    
    public DeliveryBranch getListDeliveryProduct(String ids);
    
    public ProductDto getProductDtoById(int id);
    
    public boolean validNameEdit(String newName, int prodId);
    
    public void updateProductDto(ProductDto productDto);
    
    public List<Double> getDatesPriceSaleByProductId(int productId);
}
