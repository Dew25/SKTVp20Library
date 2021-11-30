/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Reader;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author user
 */
public class ReaderFacade  extends AbstractFacade<Reader>{
    private EntityManager em;

    public ReaderFacade(Class<Reader> entityClass) {
        super(entityClass);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
