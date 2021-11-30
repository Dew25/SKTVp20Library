/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class Singleton {
    private static Singleton instance;
    private EntityManager entityManager;
    private Singleton(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SKTVp20LibraryPU");
        entityManager = emf.createEntityManager();
    }
    public static Singleton getInstance(){ // #3
        if(instance == null){		//если объект еще не создан
            instance = new Singleton();	//создать новый объект
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}
