package root.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UsersList extends JPanel {

    private JPanel usersListPane;
    private ChatWindow chatWindow;
    private JComboBox<String> changeRoom;

    public UsersList(ChatWindow chatWindow) {
        this.chatWindow = chatWindow;
        this.usersListPane = new JPanel();
        setLayout(new BorderLayout());
        JLabel userListTitle = new JLabel(String.format("%-10s", "Пользователи:"));
        userListTitle.setFont(new Font("", Font.BOLD, 25));
        userListTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(userListTitle, BorderLayout.NORTH);
        usersListPane.setLayout(new GridLayout(10, 2));
        add(usersListPane, BorderLayout.CENTER);
        changeRoom = new JComboBox<>();                                        //Создание комбо бокса для выбора комнаты
        changeRoom.addItem("Общий чат");
        add(changeRoom, BorderLayout.SOUTH);
        changeRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatWindow.setChatTitle(changeRoom.getSelectedItem().toString());
            }
        });
    }

    public void setUser(String name) {
        changeRoom.addItem(name);
        UserButton bt = new UserButton(name, chatWindow);
        bt.setEnabled(false);
        usersListPane.add(bt, BorderLayout.CENTER);
    }
}
