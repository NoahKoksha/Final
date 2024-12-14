import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_C_GameObject extends GameObject implements KeyListener {

	public Type_C_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.RIGHT);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_C_Right.png"));
		imageList.add(new ImageIcon("images/Type_C_Left.png"));
	}

	public void move(Canvas c) {
		Icon icon = getCurrentImage();

		int iconWidth = icon.getIconWidth();
		int canvasWidth = (int) c.getSize().getWidth();

		switch (getDirection()) {
		case Direction.LEFT:
			setX(getX() - getVelocity());
			if (getX() < 0) {
				setX(0);
				setDirection(Direction.RIGHT);
			}
			break;
		case Direction.RIGHT:
			setX(getX() + getVelocity());
			if (getX() + iconWidth > canvasWidth) {
				setX((int) (canvasWidth - iconWidth));
				setDirection(Direction.LEFT);
			}
			break;
		default:
			break;
		}
	}

	public void setImage() {
		switch (getDirection()) {
		case Direction.LEFT:
			currentImage = 0;
			break;
		case Direction.RIGHT:
			currentImage = 1;
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (Canvas.highlighted == 3) {
			setVelocity(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			setVelocity(10);
		}
		if (Canvas.highlighted == 3) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.RIGHT);
				setVelocity(10);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.LEFT);
				setVelocity(10);
			}
		}
	}
}
