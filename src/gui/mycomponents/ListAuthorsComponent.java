/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mycomponents;

import entity.Author;
import gui.mycomponents.renderers.ListAuthorsCellRenderer;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author user
 */
public class ListAuthorsComponent extends JPanel{
    private JLabel title;
    private JList<Author> list;
    public ListAuthorsComponent(String text, int widthPanel, int heightPanel, int widthEdit) {
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
        title.setAlignmentY(TOP_ALIGNMENT);
//        title.setFont(new Font("Tahoma",1,16));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HEIGHT);
        list.setAlignmentY(TOP_ALIGNMENT);
        list.setCellRenderer(new ListAuthorsCellRenderer());
        JScrollPane scrollPane = new JScrollPane(list);
        if(widthEdit == 0){
            scrollPane.setPreferredSize(new Dimension(widthPanel-widthPanel/3 - 80,27));
        }else{
            scrollPane.setPreferredSize(new Dimension(widthEdit,heightPanel));
        }
        scrollPane.setMinimumSize(scrollPane.getPreferredSize());
        scrollPane.setMaximumSize(scrollPane.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(scrollPane);
    }

    public JList getList() {
        return list;
    }
    
}
