package root.GUI;

import javax.swing.*;
import java.awt.*;

public class UserAvatar extends JPanel {

    private Color color;
    private JButton currentButton;
    private JLabel userName = new JLabel();
    private Font currentFont;
    private int w;

    public UserAvatar(String name, Color color, JButton j) {
        this.color = color;
        this.currentButton = j;
        setBounds(0, 0, w, w);
        setBackground(new Color(255, 255, 255, 0));
        String[] userNick = name.split("\\s");
        StringBuilder txt = new StringBuilder();
        int val;
        if (userNick.length > 1) {
            val = 2;
        } else {
            val = 1;
        }
        for (int i = 0; i < val; i++) {
            txt.append(userNick[i].charAt(0));
        }
        userName.setText(txt.toString());
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setVerticalAlignment(SwingConstants.CENTER);
//        userName.setMinimumSize(new Dimension(10,50));
        userName.setForeground(Color.WHITE);
    }

    protected void paintComponent(Graphics gr) {
        w = currentButton.getHeight();
        gr.setColor(color);
        gr.fillOval(0, 0, w/2 , w/2);
        currentFont = new Font("Calibri", Font.BOLD, w/3 );
        userName.setFont(currentFont);
        add(userName);
    }
}
