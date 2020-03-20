package display;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    public Window(int width, int height) {

        super();
        this.setSize(new Dimension(width, height));
        this.setTitle("Relativity-Theorem Project");
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

