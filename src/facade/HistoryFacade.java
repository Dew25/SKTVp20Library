/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Book;
import entity.History;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author user
 */
public class HistoryFacade extends AbstractFacade<History>{
    
    private EntityManager em;
   

    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public History find(Book book) {
        try {
            return (History) em.createQuery("SELECT h FROM History h WHERE h.book = :book AND h.returnedDate = null")
                    .setParameter("book", book)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<History> findGivenBooks() {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.returnedDate = null")
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
