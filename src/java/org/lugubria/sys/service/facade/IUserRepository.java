package org.lugubria.sys.service.facade;

import org.lugubria.sys.domain.User;

/**
 *
 * @author angel
 */
public interface IUserRepository {
    
    public boolean validateUser(String username, String password);
    
    public User getUser(String username, String password);
    
    public int getUserId(String username, String password);
    
}
