import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {
    public ChatWindow() {
        setTitle("Чат");
        setBounds(150, 150, 400, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea chatHistory = new JTextArea();
        chatHistory.setEnabled(false);

        JScrollPane scrollHistory = new JScrollPane(chatHistory);
        add(scrollHistory, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());

        JTextField message = new JTextField();
        message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (message.getText().isBlank()) return;
                StringBuilder txt = new StringBuilder();
                txt.append(chatHistory.getText());
                if (!chatHistory.getText().isBlank()) txt.append("\n");
                txt.append(message.getText());
                chatHistory.setText(txt.toString());
                message.setText("");
            }
        });

        JButton send = new JButton("Send");
        send.addActionListener(new ButtonListener(message, chatHistory));
        JComboBox<String> changeRoom = new JComboBox<>(new String[]{"Room 1", "Room 2", "Room 3"});

        messagePanel.add(changeRoom, BorderLayout.WEST);
        messagePanel.add(message, BorderLayout.CENTER);
        messagePanel.add(send, BorderLayout.EAST);
        add(messagePanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
