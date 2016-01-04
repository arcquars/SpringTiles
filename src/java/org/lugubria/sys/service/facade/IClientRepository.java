/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.service.facade;

import java.util.List;
import org.lugubria.sys.domain.dto.client.ClientDto;

/**
 *
 * @author angel
 */
public interface IClientRepository {
    
    public ClientDto getClientFromCi(int ci);
    
    public void updateClient(ClientDto client);
    
    public List<ClientDto> getClientByCriteria(int type, String criteria);
    
    public String getStringClientByCriteria(int type, String criteria);
}
