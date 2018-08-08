package view;

import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

class CustomIconRenderer extends DefaultTreeCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 967937360839244309L;
    ImageIcon groupedIcon;
    ImageIcon unGroupedIcon;

    public CustomIconRenderer() throws MalformedURLException {
        URL url = new URL("http://i.stack.imgur.com/wCF8S.png");
        URL url1 = new URL("http://i.stack.imgur.com/T5uTa.png");
        groupedIcon = new ImageIcon(url1);
        unGroupedIcon = new ImageIcon(url);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        Object nodeObj = ((DefaultMutableTreeNode) value).getUserObject();
        String s = nodeObj.toString();
//        System.out.println("s: " + s);
        if(s.equals("Activity1001")) {
        	setIcon(groupedIcon);
        }
        

        return this;
    }
    public void setcommit(JTree tree) {
    	tree.setBackground(Color.black);
    }
}