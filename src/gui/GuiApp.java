/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Author;
import entity.Book;
import facade.AuthorFacade;
import facade.BookFacade;
import gui.mycomponents.ButtonComponent;
import gui.mycomponents.CaptionComponent;
import gui.mycomponents.ComboBoxComponent;
import gui.mycomponents.EditComponent;
import gui.mycomponents.InfoComponent;
import gui.mycomponents.ListAuthorsComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
    private InfoComponent infoComponent;
    private EditComponent bookNameComponent;
    private ListAuthorsComponent authorsComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    private JList<Author> authors;
    public GuiApp() {
        super.setPreferredSize(new Dimension(600,450));
        super.setMaximumSize(getPreferredSize());
        super.setMinimumSize(getPreferredSize());
        initComponents();
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        captionComponent = new CaptionComponent("Добавление новой книги",this.getWidth(), 35);
//      captionComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        this.getContentPane().add(captionComponent);
        infoComponent = new InfoComponent("Информаци о резултате",this.getWidth(), 30);
//      infoComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.getContentPane().add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        bookNameComponent = new EditComponent("Название книги ", this.getWidth(), 30, 270);
//        editComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
        this.getContentPane().add(bookNameComponent);
        authorsComponent = new ListAuthorsComponent("Авторы книги ", this.getWidth(), 120, 270);
        //authorsComponent.setBorder(BorderFactory.createLineBorder(Color.yellow));
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
        buttonComponent.getButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book();
                if(bookNameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите название книги");
                    return;
                }
                book.setBookName(bookNameComponent.getEditor().getText());
                List<Author> authorsBook = authorsComponent.getList().getSelectedValuesList();
                if(authorsBook.isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите авторов книги");
                    return;
                }
                try {
                    book.setPublishedYear(Integer.parseInt(publishedYearComponent.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите год публикации книги цифрами");
                    return;
                }
                try {
                    book.setQuantity(Integer.parseInt(quantityComponent.getEditor().getText()));
                    book.setCount(book.getQuantity());
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите количество книг цифрами");
                    return;
                }
                BookFacade bookFacade = new BookFacade(Book.class);
                try {
                    bookFacade.create(book);
                    
                    infoComponent.getInfo().setForeground(Color.GREEN);
                    infoComponent.getInfo().setText("Книга успешно добавлена");
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Добасить книгу не удалось");
                }
            }
            
        });
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
