package TurtleGraphics;

import tools.Utilities;
// testing
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;

//test again

public class AppPanel extends JPanel implements ActionListener {

    private Turtle model;
    private TurtleView view;
    private JFrame frame;
    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGHT = 500;

    //AppPanel constructor
    public AppPanel() {

        model = new Turtle();				    //Create new Turtle
        JPanel controlPanel = new JPanel();		//Create new JPanel
        view = new TurtleView(model);

        setLayout((new GridLayout(1, 2)));		//Set grid size to 1x2
        add(controlPanel);						            //Add first component (Control panel)
        controlPanel.setLayout(new GridLayout(4, 3));
        add(view);								            //Add second component (View panel)
        view.setBackground(Color.WHITE);


        JButton northBtn = new JButton("North");
        northBtn.addActionListener(this);
        JButton eastBtn = new JButton("East");
        eastBtn.addActionListener(this);
        JButton westBtn = new JButton("West");
        westBtn.addActionListener(this);
        JButton southBtn = new JButton("South");
        southBtn.addActionListener(this);
        JButton penBtn = new JButton("Pen");
        penBtn.addActionListener(this);
        JButton colorBtn = new JButton("Color");
        colorBtn.addActionListener(this);
        JButton clearBtn = new JButton("Clear");
        clearBtn.addActionListener(this);

        JPanel p = new JPanel();
        p = new JPanel();
        p.add(northBtn);
        controlPanel.add(p);
        p = new JPanel();
        p.add(eastBtn);
        controlPanel.add(p);
        p = new JPanel();
        p.add(westBtn);
        controlPanel.add(p);
        p = new JPanel();
        p.add(southBtn);
        controlPanel.add(p);
        p = new JPanel();
        p.add(penBtn);
        controlPanel.add(p);
        p = new JPanel();
        p.add(colorBtn);
        controlPanel.add(p);
        p = new JPanel();
        p.add(clearBtn);
        controlPanel.add(p);


        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Turtle Graphics");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setJMenuBar(createMenuBar());

    }//End of AppPanel constructor

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[] {"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[] {"North", "East", "West", "South", "Pen", "Color"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[] {"Help", "About"}, this);
        result.add(helpMenu);


        return result;
    }


    public void display() { frame.setVisible(true); }	//Makes the frame visible and starts looking for actions

    public void actionPerformed(ActionEvent ae) {	//Since we implemented Action Listener, we need to look for actions
        String cmmd = ae.getActionCommand();

        if (cmmd == "Save") {
            try {
                //String fName = Utilities.ask("File Name?");
                String fName = Utilities.getFileName(null, false);
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                os.writeObject(model);
                os.close();

            } catch (Exception err) {
                Utilities.error(err);
            }
        } else if (cmmd == "Open") {
            try {
                String fName = Utilities.getFileName(null, true);
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                model = (Turtle)is.readObject();
                is.close();
            } catch (Exception err) {
                Utilities.error(err.getMessage());
            }
        } else if (cmmd == "New") {
            model = new Turtle();

        } else if (cmmd == "Quit") {
            //Utilities.saveChanges(model);
            System.exit(1);
        } else if (cmmd == "About") {
            Utilities.inform("Samantha Elaine Guanzon, TurtleGraphics. March 5, 2020. ");
        } else if (cmmd == "Help") {
            Utilities.inform("North, East, West, and South buttons prompts you the number of steps the turtle should take, " +
                    "then moves the turtle that number of steps in the specified direction.\n" +
                    "The Pen button can be turned on and off. When the pen is down, the turtle's path is drawn. \n" +
                    "The Clear button erases the drawing.");
        } else if (cmmd == "Change") {
            //model.change();
        } else if (cmmd == "Pen") {
            if (model != null) {
                model.penChange();
            }
            else {
                System.out.println("no model?");
            }
        } else if (cmmd=="Color") {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", model.getColor());
        } else  {
            Utilities.error("Unrecognized command: " + cmmd);
        }
    }//End of actionPerformed method


    public static void main(String[] args) {
        AppPanel app = new AppPanel();
        app.display();
    }//End of main method

}//End of AppPanel class
