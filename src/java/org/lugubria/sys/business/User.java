/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.business;

/**
 *
 * @author angel
 */
public interface User {
    
    public boolean userValid(String login, String password);
}
