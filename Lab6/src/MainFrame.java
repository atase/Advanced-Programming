import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    ComponentPanel componentsPanel;

    public MainFrame(){
        super("My Drawing Application");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        componentsPanel = new ComponentPanel(this);

        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
        add(configPanel, BorderLayout.NORTH);
        add(componentsPanel, BorderLayout.EAST);


        setSize(1200, 700);
        setVisible(true);
    }

}
