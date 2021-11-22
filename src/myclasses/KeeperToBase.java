/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Author;
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
                if(books.get(i).getId() == null){
                    em.persist(books.get(i));
                }
            }
        tx.commit();
        
    }
@Override
    public void saveAuthors(List<Author> authors) {
        tx.begin();
            for (int i = 0; i < authors.size(); i++) {
                if(authors.get(i).getId() == null){
                    em.persist(authors.get(i));
                }
            }
        tx.commit();
        
    }

    @Override
    public List<Book> loadBooks() {
        try {
            return (List<Book>) em.createQuery("SELECT b FROM Book b")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Таблица BOOK пуста");
        }
        return new ArrayList<>();
    }
    
    public List<Author> loadAuthors() {
        try {
            return (List<Author>) em.createQuery("SELECT author FROM Author author")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Таблица Author пуста");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveReaders(List<Reader> readers) {
        tx.begin();
            for (int i = 0; i < readers.size(); i++) {
                if(readers.get(i).getId() == null){
                    em.persist(readers.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Reader> loadReaders() {
        try {
            return (List<Reader>) em.createQuery("SELECT reader FROM Reader reader")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Таблица Reader пуста");
        }
        return new ArrayList<>();
    }

    @Override
    public void saveHistories(List<History> histories) {
         tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    em.persist(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
         try {
            return (List<History>) em.createQuery("SELECT history FROM History history")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Таблица History пуста");
        }
        return new ArrayList<>();
    }
    
}
