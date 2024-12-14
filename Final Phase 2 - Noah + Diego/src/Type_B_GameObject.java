import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_B_GameObject extends GameObject implements KeyListener {

	private Random random;

	public Type_B_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.RIGHT);

		random = new Random();

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_B__Up.png"));
		imageList.add(new ImageIcon("images/Type_B_Down.png"));
		imageList.add(new ImageIcon("images/Type_B_Right.png"));
		imageList.add(new ImageIcon("images/Type_B_Left.png"));
	}

	public void move(Canvas c) {
		Icon icon = getCurrentImage();

		int iconHeight = icon.getIconHeight();
		int iconWidth = icon.getIconWidth();
		int canvasHeight = (int) c.getSize().getHeight();
		int canvasWidth = (int) c.getSize().getWidth();

		switch (getDirection()) {
		case Direction.UP:
			setY(getY() - getVelocity());
			if (getY() < 0) {
				setY(0);
				setDirection(random.nextBoolean() ? Direction.LEFT : Direction.RIGHT);
			}
			break;
		case Direction.DOWN:
			setY(getY() + getVelocity());
			if (getY() + iconHeight > canvasHeight) {
				setY((int) (canvasHeight - iconHeight));
				setDirection(random.nextBoolean() ? Direction.LEFT : Direction.RIGHT);
			}
			break;
		case Direction.LEFT:
			setX(getX() - getVelocity());
			if (getX() < 0) {
				setX(0);
				setDirection(random.nextBoolean() ? Direction.UP : Direction.DOWN);
			}
			break;
		case Direction.RIGHT:
			setX(getX() + getVelocity());
			if (getX() + iconWidth > canvasWidth) {
				setX((int) (canvasWidth - iconWidth));
				setDirection(random.nextBoolean() ? Direction.UP : Direction.DOWN);
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
		case Direction.LEFT:
			currentImage = 2;
			break;
		case Direction.RIGHT:
			currentImage = 3;
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (Canvas.highlighted == 2) {
			setVelocity(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (Canvas.highlighted == 2) {
			setVelocity(10);
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setDirection(Direction.UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setDirection(Direction.DOWN);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.LEFT);
			}
		}
	}
}