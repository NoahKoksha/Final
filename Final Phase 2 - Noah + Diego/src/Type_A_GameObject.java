import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Type_A_GameObject extends GameObject implements KeyListener {

	public Type_A_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.UP);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_A_Up.png"));
		imageList.add(new ImageIcon("images/Type_A_Down.png"));
	}

	public void move(Canvas c) {
		Icon icon = getCurrentImage();
		int iconHeight = icon.getIconHeight();
		int canvasHeight = (int) c.getSize().getHeight();

		switch (getDirection()) {
		case Direction.UP:
			setY(getY() - getVelocity());
			if (getY() < 0) {
				setY(0);
				setDirection(Direction.DOWN);
			}
			break;
		case Direction.DOWN:
			setY(getY() + getVelocity());
			if (getY() + iconHeight > canvasHeight) {
				setY(canvasHeight - iconHeight);
				setDirection(Direction.UP);
			}
			break;
		default:
			break;
		}
	}

	public void setImage() {
		switch (getDirection()) {
		case Direction.UP:
			currentImage = 0;
			break;
		case Direction.DOWN:
			currentImage = 1;
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (Canvas.highlighted == 1) {
			setVelocity(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			setVelocity(10);
		}
		if (Canvas.highlighted == 1) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setDirection(Direction.UP);
				setVelocity(10);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setDirection(Direction.DOWN);
				setVelocity(10);
			}
		}
	}
}
