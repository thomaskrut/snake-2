import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel {



    private List<DrawableObject> drawableObjectList = new ArrayList<>();

    public GamePanel() {

        setBackground(Color.BLACK);

    }

    public void addDrawableObject(DrawableObject drawableObject) {
        drawableObjectList.add(drawableObject);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (DrawableObject d : drawableObjectList) {
            d.draw(g);
        }



    }
}
