package root.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class UserButton extends JLabel {

    private JLabel userName = new JLabel();

    public UserButton(String name, ChatWindow chatWindow) {
        setBounds(0,0,100,100);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(new Color(255, 255, 255, 0));
        userName.setText(name);
        userName.setAlignmentY(LEFT);
        UserAvatar avatar = new UserAvatar(name, new Color(160, 50, rand()), this);

        gbc.insets.right=20;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        add(avatar, gbc);
        gbc.fill=GridBagConstraints.NONE;
        gbc.weightx=1;
        gbc.insets.right=0;
        gbc.anchor=GridBagConstraints.WEST;
        add(userName, gbc);

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
                setOpaque(true);
                userName.setFont(new Font("Calibri", Font.BOLD, 16));
                userName.setForeground(new Color(0x4A39AA));
                userName.setBackground(new Color(255, 255, 255, 255));
                setBackground(new Color(255, 255, 255, 255));
//                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                if (isEnabled()) {
                userName.setFont(new Font("Calibri", Font.BOLD, 14));
                userName.setForeground(new Color(0, 0, 0));
                userName.setBackground(new Color(255, 255, 255, 0));
                setBackground(new Color(255, 255, 255, 0));
                setOpaque(false);
//                }
            }
        });
    }

    private int rand() {
        Random rnd = new Random();
        return rnd.nextInt(255);
    }

}
