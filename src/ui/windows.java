package ui;

import utils.parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class windows extends JFrame {
    JFrame jFrame;
    Container container;
    JPanel visPanel;
    JPanel ioPanel;
    JPanel statPanel;
    JTextArea label;
    static int lines = 1;
    static int len = 2;
    parser inputParser;
    public windows() {
        inputParser = new parser();
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
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    mode = 1;
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
        ioPanel.setBackground(Color.black);
        ioPanel.setLayout(new FlowLayout(SwingConstants.LEADING));
        label = new JTextArea();
        label.setEditable(false);
        label.setCursor(null);
        label.setFocusable(false);
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setText("> ");
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
                if(label.getText().length() != len)
                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                break;
            case 1:
                lines++;
                inputParser.parse(getInput());
                label.setText(label.getText()+"\n> ");
                len = label.getText().length();
                break;
            default:
                if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <='Z') || (ch >= '0' && ch <= '9') || (ch == '(') || (ch == ')') || (ch == ',') || (ch == ' '))
                    label.setText(label.getText()+""+ch);
        }
    }

    String getInput() {
        return label.getText().substring(len,label.getText().length()-0);
    }
}
