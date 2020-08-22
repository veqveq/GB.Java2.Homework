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
    private Set<String> onlineList;
    private Set<String> allClients;
    private String name;
    private DataInputStream in;
    private DataOutputStream out;
    private UsersList userList;


    public ChatWindow(Set<String> allClients, Set<String> onlineList, String name, DataInputStream in, DataOutputStream out) {
        this.onlineList = onlineList;
        this.allClients = allClients;
        this.name = name;
        this.in = in;
        this.out = out;
        /*
         *Настройка окна чата
         */
        setTitle(String.format("Чат. Пользователь: %s", name));
        setBounds(150, 150, 800, 800);
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
        userList = new UsersList(this);                                                            //Создание объекта класса UserList
        removeUserList();

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
        chatTitle.setHorizontalAlignment(SwingConstants.CENTER);

        messagePanel.add(message, BorderLayout.CENTER);
        messagePanel.add(send, BorderLayout.EAST);

        chatWindow.add(chatTitle, BorderLayout.NORTH);
        chatWindow.add(scrollHistory, BorderLayout.CENTER);
        chatWindow.add(messagePanel, BorderLayout.SOUTH);

        add(chatWindow, BorderLayout.CENTER);
        getContentPane().add(userList, BorderLayout.WEST);

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
                if (checkMessage(message)) {
                    txt.append(chatHistory.getText()).append("\n").append(message);
                    chatHistory.setText(txt.toString());
                    txt.setLength(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void sendMessage() {
        try {
            out.writeUTF(String.format("/%s /addr[%s]: %s", chatTitle.getText(), name, message.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean checkMessage(String message) {
        StringBuilder txt = new StringBuilder(message);
        if (message.startsWith("/userReg")) {
            txt.delete(0, 9);
            message = txt.toString();
            addNewUser(message);
            userList.revalidate();
            return false;
        } else if (message.startsWith("/userOnline")) {
            txt.delete(0, 12);
            onlineList.add(txt.toString());
            removeUserList();
            return false;
        } else if (message.startsWith("/userOffline")) {
            txt.delete(0, 13);
            onlineList.remove(txt.toString());
            removeUserList();
            return false;
        }
        return true;
    }

    protected void addNewUser(String name) {
        userList.setUser(name);
    }

    private boolean checkUserOffline(String name) {
        for (String user : onlineList) {
            if (user.equals(name)) return false;
        }
        return true;
    }

    private void removeUserList() {
        userList.clean();
        userList.setTitle("Онлайн: ");

        for (String r : onlineList) {
            if (!name.equals(r)) {
                userList.setUser(r);
            }
        }

        userList.setTitle("Оффлайн: ");

        for (String r : allClients) {
            if (checkUserOffline(r) && !name.equals(r)) {
                userList.setUser(r);
            }
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