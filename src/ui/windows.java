package ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class windows extends JFrame {
    JFrame jFrame;
    Container container;
    JPanel visPanel;
    JPanel ioPanel;
    JPanel statPanel;
    JLabel label;
    public windows() {
        jFrame = new JFrame("SkipList");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1080,720);
        container = jFrame.getContentPane();
        setVisPanel();
        setIoPanel();
        setStatPanel();
        jFrame.setVisible(true);
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int mode  = -1;
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    mode = 0;
                updateLabel(mode,e.getKeyChar());
            }
        });

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
        ioPanel.setBackground(Color.yellow);
        ioPanel.setLayout(new FlowLayout());
        label = new JLabel();
        label.setBackground(Color.green);
        ioPanel.add(label);
        container.add(ioPanel);
    }

    void setStatPanel() {
        statPanel = new JPanel();
        statPanel.setBounds(0,648,1080,72);
        statPanel.setBackground(Color.DARK_GRAY);
        container.add(statPanel);
    }

    void updateLabel(int i, char ch) {
        switch (i) {
            case 0:
                if(label.getText().length() != 0)
                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                break;
            default:
                if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <='Z') || (ch >= '0' && ch <= '9') || (ch == '(') || (ch == ')') || (ch == ',') || (ch == ' '))
                    label.setText(label.getText()+""+ch);
        }
    }
}
