package root.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class UsersList extends JPanel {

    private JPanel usersListPane;
    private JScrollPane scrollPane;
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

        usersListPane.setLayout(new GridLayout(15, 1));
//        usersListPane.setLayout(new BoxLayout(usersListPane,BoxLayout.Y_AXIS));
//        scrollPane = new JScrollPane(usersListPane);
//        add(scrollPane,BorderLayout.CENTER);
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
        UserButton newUserButton = new UserButton(name,chatWindow);
        usersListPane.add(newUserButton);
        chatWindow.revalidate();
    }

    public void setTitle(String title){
        JLabel titleList = new JLabel(title);
        titleList.setAlignmentX(JLabel.LEFT);
        titleList.setFont(new Font("1", Font.BOLD, 16));
        usersListPane.add(titleList);
    }

    public void clean(){
        usersListPane.removeAll();
    }
}
