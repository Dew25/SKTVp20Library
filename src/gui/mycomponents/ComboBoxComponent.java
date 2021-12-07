/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mycomponents;

import entity.Author;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class ComboBoxComponent extends JPanel{
    private JLabel title;
    private JComboBox<Author> comboBox;
    public ComboBoxComponent(String text, int widthPanel, int heightPanel, int widthEdit) {
        initComponents(text,widthPanel,heightPanel, widthEdit);
    }
    
    private void initComponents(String text,int widthPanel, int heightPanel,int widthEdit) {
        setPreferredSize(new Dimension(widthPanel,heightPanel));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        title = new JLabel(text);
        title.setPreferredSize(new Dimension(widthPanel/3,27));
        title.setMinimumSize(title.getPreferredSize());
        title.setMaximumSize(title.getPreferredSize());
        title.setHorizontalAlignment(JLabel.RIGHT);
//        title.setFont(new Font("Tahoma",1,16));
        this.add(title);
        comboBox = new JComboBox<>();
        if(widthEdit == 0){
            comboBox.setPreferredSize(new Dimension(widthPanel-widthPanel/3 - 80,27));
        }else{
            comboBox.setPreferredSize(new Dimension(widthEdit,27));
        }
        comboBox.setMinimumSize(comboBox.getPreferredSize());
        comboBox.setMaximumSize(comboBox.getPreferredSize());
        this.add(comboBox);
    }

    public JComboBox getComboBox() {
        return comboBox;
    }
    
}
