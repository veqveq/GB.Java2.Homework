import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    private StringBuilder txt = new StringBuilder();
    private JTextField message;
    private JTextArea history;

    public ButtonListener(JTextField message, JTextArea history) {
        this.message = message;
        this.history = history;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        txt.append(history.getText());
        if (!history.getText().isBlank()) txt.append("\n");
        txt.append(message.getText());
        history.setText(txt.toString());
        message.setText("");
        txt.setLength(0);
    }
}
