/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.prueba2.clinica.controlador;

import com.lp2.prueba2.clinica.dao.CredencialDAO;
import com.lp2.prueba2.clinica.modelo.Credencial;
import com.lp2.prueba2.clinica.modelo.Medico;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marcelo Esperguel
 */
@Controller
public class CredencialController {
    
    
    @Autowired
    CredencialDAO cDao;
    
    @GetMapping("/login")
    public String muestraLogin(
            HttpServletRequest request,
            Model model
    ){
        
        Credencial medicoLogueado = (Credencial) request.getSession().getAttribute("medicoLogueado");
        
        if(medicoLogueado==null){
            model.addAttribute("credencial", new Credencial());
            return "login";
        }else{
            return "index";
        }
    }
    
    @PostMapping("login")
    public String login(
            Model model,  
            @ModelAttribute Credencial c,
            HttpServletRequest request
    ){
        
        Credencial credencialBD = cDao.findByRutAndPassword(c.getRut(),c.getPassword());
        
        if(credencialBD!=null){
            request.getSession().setAttribute("medicoLogueado", credencialBD);
            return "index";
        }else{
            model.addAttribute("credencial", new Credencial());
            model.addAttribute("error", true);
            model.addAttribute("mensaje_error", "Usuario o Contraseña incorrectos. Intentalo de nuevo");
            return "login";
        } 
        
    }
    
    @PostMapping("logout")
    public String logout(HttpServletRequest request){
        
        request.getSession().invalidate();
        
        return "redirect:/";
    }
}
