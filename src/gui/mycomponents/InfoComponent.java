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
public class InfoComponent extends JPanel{
    private JLabel info;
    public InfoComponent(String text, int widthPanel, int heightPanel) {
        initComponents(text,widthPanel,heightPanel);
    }

    private void initComponents(String text,int widthPanel, int heightPanel) {
        setPreferredSize(new Dimension(widthPanel,heightPanel));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        info = new JLabel(text);
        info.setPreferredSize(new Dimension(widthPanel,heightPanel));
        info.setMinimumSize(info.getPreferredSize());
        info.setMaximumSize(info.getPreferredSize());
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setFont(new Font("Tahoma",0,12));
        this.add(info);
        
    }

    public JLabel getInfo() {
        return info;
    }
    
}
