import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel {



    private final List<DrawableObject> drawableObjectList = new ArrayList<>();

    public GamePanel() {

        setBackground(new Color(56,34,26));

    }

    public void addDrawableObject(DrawableObject drawableObject) {
        drawableObjectList.add(drawableObject);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawableObjectList.forEach(o -> o.draw(g));

    }
}
