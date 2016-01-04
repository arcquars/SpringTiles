/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author angel
 */
@Controller
public class MenuController {
    
    @RequestMapping(value="menumain", method= RequestMethod.GET)
    public String showRegistro(){
        System.out.println("Entro al controller menu principal!!!");
        return "menu";
    }
}
