/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author user
 */
@Entity
public class History implements Serializable{
    @Id
    @GeneratedValue
    @OneToOne
    private Reader reader;
    @OneToOne
    private Book book;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date givenDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date returnedDate;

    public History() {
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "History{" 
                + "reader=" + reader 
                + ", book=" + book 
                + ", givenDate=" + givenDate 
                + ", returnedDate=" + returnedDate 
                + '}';
    }
    
}
