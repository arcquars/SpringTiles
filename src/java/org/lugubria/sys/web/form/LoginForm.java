/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lugubria.sys.web.form;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author angel
 */
public class LoginForm {
    
    @NotEmpty
    @Size(min=1, max=50)
    private String username;
    @NotEmpty
    @Size(min=1, max=50)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
