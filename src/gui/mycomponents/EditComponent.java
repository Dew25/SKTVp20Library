/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mycomponents;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class EditComponent extends JPanel{
    private JLabel title;
    private JTextField textField;
    public EditComponent(String text, int widthPanel, int heightPanel, int widthEdit) {
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
        textField = new JTextField();
        if(widthEdit == 0){
            textField.setPreferredSize(new Dimension(widthPanel-widthPanel/3 - 80,27));
        }else{
            textField.setPreferredSize(new Dimension(widthEdit,27));
        }
        textField.setMinimumSize(textField.getPreferredSize());
        textField.setMaximumSize(textField.getPreferredSize());
        this.add(textField);
    }
}
