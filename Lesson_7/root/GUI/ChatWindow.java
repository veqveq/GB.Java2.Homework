package root.GUI;

import root.AuthService;
import root.ClientHandler;
import root.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChatWindow extends JFrame {

    private JLabel chatTitle = new JLabel();
    JTextArea chatHistory = new JTextArea();
    JTextField message = new JTextField();                                                                          //Создание текстового поля
    private String name;
    private DataInputStream in;
    private DataOutputStream out;



    public ChatWindow(Set<String> logs, String name, DataInputStream in, DataOutputStream out ) {
        this.name = name;
        this.in = in;
        this.out = out;
        /*
         *Настройка окна чата
         */
        setTitle(String.format("Чат. Пользователь: %s",name));
        setBounds(150, 150, 400, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        /*
         *Создание основной рабочей области чата
         */
        JPanel chatWindow = new JPanel();                           //Создание основной рабочей панели чата
        JScrollPane scrollHistory = new JScrollPane(chatHistory);   //Добавление к полю вывода полосы прокрутки
        chatHistory.setEnabled(false);                              //Блокировка редактирования истории сообщений
        chatWindow.setLayout(new BorderLayout());                   //Назначение компоновщика основного окна


        /*
         * Создание области выбора собеседника
         */
        UsersList userList = new UsersList(this);                                                            //Создание объекта класса UserList
        for (String r : logs) {
            if (!name.equals(r)) {
                userList.setUser(r);
            }
        }

        /*
         * Создание строки для отправки сообщения
         */
        JPanel messagePanel = new JPanel();                                                                             //Создание панели со строкой отправки сообщения
        messagePanel.setLayout(new BorderLayout());                                                                     //Назначение компоновщика
        JButton send = new JButton("Send");                                                                         //Создание кнопки отправить

        message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (message.getText().isBlank()) return;
                StringBuilder txt = new StringBuilder();
                txt.append(chatHistory.getText());
                if (!chatHistory.getText().isBlank()) txt.append("\n");
                txt.append(String.format("[%s]: %s", name, message.getText()));
                chatHistory.setText(txt.toString());
                sendMessage();
                message.setText("");
            }
        });

        send.addActionListener(new ButtonListener(message, chatHistory, name, this));
        chatTitle.setFont(new Font("1", Font.BOLD, 25));
        chatTitle.setText("Общий чат");
        chatTitle.setHorizontalAlignment(SwingConstants.RIGHT);

        messagePanel.add(message, BorderLayout.CENTER);
        messagePanel.add(send, BorderLayout.EAST);

        chatWindow.add(chatTitle, BorderLayout.NORTH);
        chatWindow.add(scrollHistory, BorderLayout.CENTER);
        chatWindow.add(messagePanel, BorderLayout.SOUTH);

        add(chatWindow, BorderLayout.CENTER);
        add(userList, BorderLayout.WEST);

        setVisible(true);

        getMessage();

    }

    protected void setChatTitle(String title) {
        chatTitle.setText(title);
    }

    public void getMessage() {
                StringBuilder txt = new StringBuilder();
                while (true) {
                    try {
                        String message = in.readUTF();
                        txt.append(chatHistory.getText()).append("\n").append(message);
                        chatHistory.setText(txt.toString());
                        txt.setLength(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    protected void sendMessage() {
        try {
            out.writeUTF(String.format("/%s /addr[%s]: %s",chatTitle.getText(),name,message.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dispose() {
        try {
            out.writeUTF("/end");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
        super.dispose();
    }
}