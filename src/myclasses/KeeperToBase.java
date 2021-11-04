/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class KeeperToBase implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SKTVp20LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveBooks(List<Book> books) {
        tx.begin();
            for (int i = 0; i < books.size(); i++) {
                em.persist(books.get(i));
            }
        tx.commit();
        
    }

    @Override
    public List<Book> loadBooks() {
        try {
            return (List<Book>) em.createQuery("SELECT b FROM Book b WHERE 1");
        } catch (Exception e) {
            System.out.println("Таблица BOOK пуста");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reader> loadReaders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveHistories(List<History> histories) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<History> loadHistories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
