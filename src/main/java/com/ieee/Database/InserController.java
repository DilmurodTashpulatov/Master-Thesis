/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieee.Database;

import com.ieee.Models.SearchString;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dilmurod Tashpulatov
 */

public class InserController implements java.io.Serializable{
    
    public static void insertAll(SearchString ss){
        try{
            
            if(ss == null) return;
            
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JavaApplication1PU" );
            
            EntityManager entitymanager = emfactory.createEntityManager( );
            
            entitymanager.getTransaction( ).begin( );
            
            
            entitymanager.persist(ss);
            
            entitymanager.getTransaction( ).commit( );

            
            entitymanager.close( );
            
            emfactory.close( );
        }catch(Exception e){
            
            e.printStackTrace();
        
        }
    }
}
