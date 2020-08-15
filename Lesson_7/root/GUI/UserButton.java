package root.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class UserButton extends JButton {

    private JLabel userName = new JLabel();

    public UserButton(String name, ChatWindow chatWindow) {
        setLayout(new BorderLayout());
        userName.setText(name);
        userName.setHorizontalAlignment(SwingConstants.LEFT);
        UserAvatar avatar = new UserAvatar(name, new Color(160, 50, rand()), this);
        JPanel a = new JPanel();
        a.add(avatar, BorderLayout.WEST);
        a.add(userName, BorderLayout.CENTER);
        add(a, BorderLayout.WEST);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(255, 255, 255, 0));
        setBorderPainted(false);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (isEnabled()) {
                chatWindow.setChatTitle(name);
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                if (isEnabled()) {
                userName.setFont(new Font("Calibri", Font.BOLD, 16));
                userName.setForeground(new Color(0x4A39AA));
//                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                if (isEnabled()) {
                userName.setFont(new Font("Calibri", Font.BOLD, 14));
                userName.setForeground(new Color(0, 0, 0));
//                }
            }
        });
    }

    private int rand() {
        Random rnd = new Random();
        return rnd.nextInt(255);
    }

}
