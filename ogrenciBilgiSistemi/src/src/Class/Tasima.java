package src.Class;

import javax.swing.*;
import java.awt.event.*;

public class Tasima {
	private int xOffset;
	private int yOffset;

	// Bu kodda Component kısmına panelimizin ismini yazdıktan sonra o panel sürüklenebilir, taşınabilir olur.
	public Tasima(JFrame frame, JComponent component) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				xOffset = e.getX();
				yOffset = e.getY();
			}
		});

		component.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				frame.setLocation(x - xOffset, y - yOffset);
			}
		});
	}
}
