/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Author;
import entity.Book;
import interfaces.Keeping;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import myclasses.KeeperToBase;

/**
 *
 * @author user
 */
public class GuiApp extends JFrame{
    Keeping keeper = new KeeperToBase();
    Container contentPane;
    public GuiApp() {
        this.contentPane = super.getContentPane();
        super.setTitle("SKTVp20Library");
        super.setPreferredSize(new Dimension(640,480));
        
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private ListSelectionListener createListSelectionListener(JList list) {
        return e -> {
            if (!e.getValueIsAdjusting()) {
                System.out.println(list.getSelectedValue());
            }
        };
    }
    private ListCellRenderer<? super Book> createListRenderer(){
        return new DefaultListCellRenderer(){
            private final Color background = new Color(0, 100, 255, 15);
            private final Color defaultBackground = (Color) UIManager.get("List.background");

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    Book book = (Book) value;
                    label.setText(String.format("%d. %s. %s %d. В наличии: %d%n"
                            ,book.getId()
                            ,book.getBookName()
                            ,listStringAuthors(book.getAuthors())
                            ,book.getPublishedYear()
                            ,book.getCount()
                    ));
                    if (!isSelected) {
                        label.setBackground(index % 2 == 0 ? background : defaultBackground);
                    }
                }
                return component;
            }
            private String listStringAuthors(List<Author> authors){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < authors.size(); i++) {
                    sb.append(authors.get(i).getFirstname())
                      .append(" ")
                      .append(authors.get(i).getLastname())
                      .append(". ");
                }
                return sb.toString();
            }
        };
        
    }
    public void createFrame(){
        contentPane.setLayout(new BorderLayout()); 
        contentPane.add(new Label("Список книг:"),BorderLayout.NORTH);
        JPanel pListBooks = new JPanel();
        List<Book> listBooks = keeper.loadBooks();
        JList<Book> jListBooks = new JList<>(listBooks.toArray(new Book[listBooks.size()]));
        jListBooks.setCellRenderer(createListRenderer());
        jListBooks.addListSelectionListener(createListSelectionListener(jListBooks));
        JScrollPane pane = new JScrollPane(jListBooks);
        contentPane.add(pane, BorderLayout.LINE_START);
        
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
