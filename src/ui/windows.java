package ui;

import utils.parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class windows extends JFrame {
    private Container container;
    private JTextArea label;
    private JLabel timeLabel;
    private static int len = 2;
    private parser inputParser;
    private int lines = 0;

    public windows() {
        inputParser = new parser();
        JFrame jFrame = new JFrame("SkipList");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1080,720);
        container = jFrame.getContentPane();
        container.setLayout(null);
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

    private void setVisPanel() {
        JPanel visPanel = new JPanel();
        visPanel.setBounds(0,0,1080,432);
        visPanel.setBackground(Color.WHITE);
        container.add(visPanel);
    }

    private void setIoPanel() {
        JPanel ioPanel = new JPanel();
        ioPanel.setBounds(0,432,1080,216);
        ioPanel.setBackground(Color.black);
        FlowLayout ioLayout = new FlowLayout(SwingConstants.LEADING);
        ioPanel.setLayout(ioLayout);
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

    private void setStatPanel() {
        JPanel statPanel = new JPanel();
        statPanel.setBounds(0,648,1080,72);
        statPanel.setBackground(Color.DARK_GRAY);
        FlowLayout statLayout = new FlowLayout();
        statPanel.setLayout(statLayout);
        timeLabel = new JLabel();
        setTime(0L);
        timeLabel.setForeground(Color.white);
        statPanel.add(timeLabel);
        container.add(statPanel);
    }

    private void updateLabel(int i, char ch) {
        switch (i) {
            case 0:
                if(label.getText().length() != len)
                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                break;
            case 1:
                Long startTime = System.nanoTime();
                setOutput(inputParser.parseInput(getInput()));
                Long stopTime = System.nanoTime();
                setTime(stopTime- startTime);
                lines++;
                fitRows();
                label.setText(label.getText()+"\n> ");
                updateLabelLength();
                break;
            default:
                if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <='Z') || (ch >= '0' && ch <= '9') || (ch == '(') || (ch == ')') || (ch == ',') || (ch == ' ') || (ch == '/') || (ch == '.'))
                    label.setText(label.getText()+""+ch);
        }
    }

    private String getInput() {
        return label.getText().substring(len);
    }

    private void fitRows() {
        if(lines >= 10) {
            label.setText(label.getText().substring(label.getText().indexOf('\n')+1));
            lines--;
            updateLabelLength();
        }
    }

    private void updateLabelLength() {
        len = label.getText().length();
    }

    private void setOutput(String str) {
        String line[] = str.split("\n");
        for(String x: line) {
            lines++;
            fitRows();
            label.setText(label.getText()+"\n"+x);
            updateLabelLength();
        }
    }

    private void setTime(Long time) {
        timeLabel.setText("<html><div style='text-align: center;'>"+ time/Math.pow(10,6) + " ms<br>"+ time + " ns</div></html>");
    }

}
