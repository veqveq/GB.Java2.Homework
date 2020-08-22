package root.GUI;

import root.BasicAuthService;
import root.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
//        AuthWindow authWindow = new AuthWindow();
//        ChatWindow chat = new ChatWindow();
//        new RegWindow();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100,100,500,500);

        JPanel labels = new JPanel();
        labels.setLayout(new BoxLayout(labels,BoxLayout.Y_AXIS));
        JScrollPane scrollBar = new JScrollPane(labels);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
        JButton add = new JButton("+");
        JButton remove = new JButton("-");
        buttons.add(add);
        buttons.add(remove);
        JTextArea history = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(history);
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                labels.add(new JLabel("New Label"));
                frame.revalidate();
            }
        });
        frame.getContentPane().add(buttons, BorderLayout.NORTH);
        frame.getContentPane().add(scrollBar, BorderLayout.WEST);
        frame.getContentPane().add(scrollPane2,BorderLayout.CENTER);

        frame.setVisible(true);

    }
}
