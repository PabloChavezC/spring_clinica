/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.prueba2.clinica.dao;

import com.lp2.prueba2.clinica.modelo.Medico;
import com.lp2.prueba2.clinica.modelo.Receta;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcelo Esperguel
 */
public interface MedicoDAO extends CrudRepository<Medico, Integer> {
    
    
    
    public Medico findById(int id);
    
    
    
}
