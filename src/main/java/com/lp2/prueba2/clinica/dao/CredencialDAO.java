/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lp2.prueba2.clinica.dao;

import com.lp2.prueba2.clinica.modelo.Credencial;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marcelo Esperguel
 */
public interface CredencialDAO extends CrudRepository<Credencial, Integer> {
    
    
    
    public Credencial findByRutAndPassword(String rut, String pass);

        
}
