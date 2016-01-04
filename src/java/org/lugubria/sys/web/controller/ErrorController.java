/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lugubria.sys.web.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.lugubria.sys.domain.Person;
import org.lugubria.sys.service.DetailBuyServices;
import org.lugubria.sys.service.UserServices;
import org.lugubria.sys.web.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.lugubria.sys.service.PersonServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author angel
 */
@Controller
@RequestMapping("error")
public class ErrorController {

    public ErrorController() {

    }

    @RequestMapping(method = RequestMethod.GET)
    public String showLogin(Map model) {
        return "error";

    }

    private static void fillStackTrace(Throwable t, PrintWriter w) {
        if (t == null) {
            return;
        }
        t.printStackTrace(w);
        if (t instanceof ServletException) {
            Throwable cause = ((ServletException) t).getRootCause();
            if (cause != null) {
                w.println("Root cause:");
                fillStackTrace(cause, w);
            }
        } else if (t instanceof SQLException) {
            Throwable cause = ((SQLException) t).getNextException();
            if (cause != null) {
                w.println("Next exception:");
                fillStackTrace(cause, w);
            }
        } else {
            Throwable cause = t.getCause();
            if (cause != null) {
                w.println("Cause:");
                fillStackTrace(cause, w);
            }
        }
    }
}
