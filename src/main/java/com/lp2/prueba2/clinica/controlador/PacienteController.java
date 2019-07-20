/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.prueba2.clinica.controlador;

import com.lp2.prueba2.clinica.dao.MedicoDAO;
import com.lp2.prueba2.clinica.dao.PacienteDAO;
import com.lp2.prueba2.clinica.modelo.Credencial;
import com.lp2.prueba2.clinica.modelo.Medico;
import com.lp2.prueba2.clinica.modelo.Paciente;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marcelo Esperguel
 */
@Controller
public class PacienteController {

    @Autowired
    PacienteDAO pDao;
    @Autowired
    MedicoDAO mDao;

    @GetMapping("pacientes/ver")
    public String muestraPacientes(
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("medicoLogueado");

        if (c == null) {

            return "redirect:/login";
        } else {
            int idMedico = c.getIdMedico().getId();
            Medico m = mDao.findById(idMedico);

            List<Paciente> pacientes = m.getPacienteList();
            model.addAttribute("pacientes", pacientes);

            return "verPacientes";
        }

    }

    @GetMapping("pacientes/nuevo")
    public String mostrarForm(
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("medicoLogueado");

        if (c == null) {

            return "redirect:/login";
        } else {

            model.addAttribute("paciente", new Paciente());

            return "nuevoPaciente";
        }
    }

    @PostMapping("pacientes/nuevo")
    public String nuevoPaciente(
            @ModelAttribute Paciente p,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("medicoLogueado");

        if (c == null) {

            return "redirect:/login";
        } else {

            int idMedico = c.getIdMedico().getId();
            Medico medico = mDao.findById(idMedico);

            medico.getPacienteList().add(p);

            pDao.save(p);
            mDao.save(medico);

            return "redirect:/pacientes/ver";
        }

    }

    //MODIFICACION PARA EDITAR Y ELIMINAR NO CONTEMPLADO EN LA PROPUESTA
    @PostMapping("pacientes/editar")
    public void editarPacientes(@ModelAttribute Paciente paciente, HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {
        Paciente pacienteBD = pDao.findById(id.intValue());
        this.pDao.save(paciente);
        response.sendRedirect("");
    }

    @PostMapping ("pacientes/eliminar")
    public String eliminarPacientes(
    
        @PathVariable("idPaciente") int idPaciente
    ){
        
        Paciente p = pDao.findById(idPaciente);
        
        int idPaciente = p.getId();
        
        pDao.delete(p);
        
        return "redirect:/pacientes/ver";
    }
}
