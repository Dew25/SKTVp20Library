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
import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class GuiApp{
    
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private Keeping keeping = new KeeperToFile();
    public GuiApp() {
        books = keeping.loadBooks();
        readers = keeping.loadReaders();
        histories = keeping.loadHistories();
    }
    
    public void run(){
        JFrame windows = new JFrame();
        
        
    }

    
}
