package src.Class;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

// burda butonlara parametrede renk vermemizi sağlar.
public class ButonRenkEfekti {
    public ButonRenkEfekti(JButton button, Color hoverColor) {
        Color originalColor = button.getBackground();

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }
}
