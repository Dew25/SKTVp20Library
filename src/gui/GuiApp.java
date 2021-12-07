/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Author;
import facade.AuthorFacade;
import gui.mycomponents.ButtonComponent;
import gui.mycomponents.CaptionComponent;
import gui.mycomponents.ComboBoxComponent;
import gui.mycomponents.EditComponent;
import gui.mycomponents.ListComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 *
 * @author user
 */
public class GuiApp extends JFrame{
    
    private CaptionComponent captionComponent;
    private EditComponent bookNameComponent;
    private ListComponent authorsComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    private JList<Author> authors;
    public GuiApp() {
        initComponents();
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        this.setPreferredSize(new Dimension(600,450));
        this.setMaximumSize(getPreferredSize());
        this.setMinimumSize(getPreferredSize());
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        captionComponent = new CaptionComponent("Добавление новой книги",this.getWidth(), 100);
//        captionComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.getContentPane().add(captionComponent);
        bookNameComponent = new EditComponent("Название книги ", this.getWidth(), 30, 270);
//        editComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.getContentPane().add(bookNameComponent);
        authorsComponent = new ListComponent("Авторы книги ", this.getWidth(), 120, 270);
        authorsComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        authors = authorsComponent.getList();
        authors.setModel(getAuthorsModel());
        this.getContentPane().add(authorsComponent);
        publishedYearComponent = new EditComponent("Год издания книги ", this.getWidth(), 30, 100);
//        editComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.getContentPane().add(publishedYearComponent);
        quantityComponent = new EditComponent("Количество экземпляров ", this.getWidth(), 30, 50);
//        editComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.getContentPane().add(quantityComponent);
        buttonComponent = new ButtonComponent("Добавить книгу", this.getWidth(), 30, 320, 150);
//        editComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.getContentPane().add(buttonComponent);
    }
    
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

    private ListModel<Author> getAuthorsModel() {
        DefaultListModel listModel = new DefaultListModel();
        AuthorFacade authorFacade = new AuthorFacade(Author.class);
        List<Author> authors = authorFacade.findAll();
        for (Author author : authors) {
            listModel.addElement(author);
        }
        return listModel;
    }

}
