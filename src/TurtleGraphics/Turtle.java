package TurtleGraphics;

import tools.Publisher;

import javax.swing.*;
import java.awt.Color;


public class Turtle extends Publisher{
    private Color color = Color.white;

    public Color getColor() {
        return color;
    }

    public void penChange() {
        if (color.equals(Color.white)) color = Color.black;
        else if (color.equals(Color.black)) color = Color.white;
        notifySubscribers();
    }





}
