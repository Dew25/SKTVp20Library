/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mycomponents;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class ButtonComponent extends JPanel{
    private JLabel title;
    private JButton button;
    public ButtonComponent(String text, int widthPanel, int heightPanel, int left, int widthEdit) {
        initComponents(text,widthPanel,heightPanel,left, widthEdit);
    }
    
    private void initComponents(String text,int widthPanel, int heightPanel,int left, int widthEdit) {
        setPreferredSize(new Dimension(widthPanel,heightPanel));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        title = new JLabel("");
        title.setPreferredSize(new Dimension(left,27));
        title.setMinimumSize(title.getPreferredSize());
        title.setMaximumSize(title.getPreferredSize());
        title.setHorizontalAlignment(JLabel.RIGHT);
//        title.setFont(new Font("Tahoma",1,16));
        this.add(title);
        button = new JButton(text);
        button.setPreferredSize(new Dimension(widthEdit,27));
        button.setMinimumSize(button.getPreferredSize());
        button.setMaximumSize(button.getPreferredSize());
        this.add(button);
    }
}
