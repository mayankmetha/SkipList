package ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class windows extends JFrame {
    JFrame jFrame;
    Container container;
    JPanel visPanel;
    JPanel ioPanel;
    JPanel statPanel;
    JTextField io;
    public windows() {
        jFrame = new JFrame("SkipList");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1080,720);
        container = jFrame.getContentPane();
        setVisPanel();
        setIoPanel();
        setStatPanel();
        jFrame.setVisible(true);

    }

    void setVisPanel() {
        visPanel = new JPanel();
        visPanel.setBounds(0,0,1080,432);
        visPanel.setBackground(Color.WHITE);
        container.add(visPanel);
    }

    void setIoPanel() {
        ioPanel = new JPanel();
        ioPanel.setBounds(0,432,1080,216);
        ioPanel.setBackground(Color.BLACK);
        ioPanel.setLayout(new FlowLayout());
        container.add(ioPanel);
    }

    void setStatPanel() {
        statPanel = new JPanel();
        statPanel.setBounds(0,648,1080,72);
        statPanel.setBackground(Color.DARK_GRAY);
        container.add(statPanel);
    }
}
