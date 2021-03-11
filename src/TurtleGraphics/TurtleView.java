package TurtleGraphics;

import tools.Subscriber;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import java.awt.Graphics;
import java.awt.Color;


public class TurtleView extends JPanel implements Subscriber {
    private Turtle model;
    private int diameter;
    private int row, col;

    //TurtleView Constructor
    public TurtleView(Turtle model) {
        this.model = model;
        model.subscribe(this);
        diameter = 15;
        row = 200;
        col = 200;
    }

    //Method to color our component
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        gc.setColor(model.getColor());
        gc.fillOval(row, col, diameter, diameter);
        gc.setColor(oldColor);

    }

    @Override
    public void update() {
        repaint();
    }


}
