/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.prueba2.clinica.dao;

import com.lp2.prueba2.clinica.modelo.Paciente;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcelo Esperguel
 */
public interface PacienteDAO extends CrudRepository<Paciente, Integer> {
    
    
    //public List<Paciente> findByIdMedico_Id(int id); 
    
    public Paciente findById(int id);
    public Paciente getById(int id);
}
