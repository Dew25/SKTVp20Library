/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mycomponents;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.security.jca.JCAUtil;

/**
 *
 * @author user
 */
public class CaptionComponent extends JPanel{
    private JLabel caption;
    public CaptionComponent(String text, int widthPanel, int heightPanel) {
        initComponents(text,widthPanel,heightPanel);
    }

    private void initComponents(String text,int widthPanel, int heightPanel) {
        setPreferredSize(new Dimension(widthPanel,heightPanel));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        caption = new JLabel(text);
        caption.setPreferredSize(new Dimension(widthPanel,heightPanel));
        caption.setMinimumSize(caption.getPreferredSize());
        caption.setMaximumSize(caption.getPreferredSize());
        caption.setHorizontalAlignment(JLabel.CENTER);
        caption.setFont(new Font("Tahoma",1,16));
        this.add(caption);
        
    }
    
}
