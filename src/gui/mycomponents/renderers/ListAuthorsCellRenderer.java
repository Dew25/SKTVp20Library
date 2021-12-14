/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mycomponents.renderers;

import entity.Author;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class ListAuthorsCellRenderer extends DefaultListCellRenderer{
   
    private final Color background = new Color(0, 100, 255, 15);
    private final Color defaultBackground = (Color) UIManager.get("List.background");
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, 
            Object value, int index,
            boolean isSelected, 
            boolean cellHasFocus
    ){
        Component component = super.getListCellRendererComponent(
                list, 
                value, 
                index, 
                isSelected, 
                cellHasFocus
        );
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            Author author = (Author) value;
            label.setText(String.format("%d. %s %s"
                    ,author.getId()
                    ,author.getFirstname()
                    ,author.getLastname()
            ));
            if (!isSelected) {
                label.setBackground(index % 2 == 0 ? background : defaultBackground);
            }
        }
        return component;
    }

    
}
