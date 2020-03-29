import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JSpinner sidesField;
    JLabel sizeLabel;
    JSpinner sizeField;
    JLabel colorLabel;
    JComboBox colorCombo;
    private String colors[] = {"black", "random"};

    private int sizeFigure=20;
    private int sidesFigure=5;
    private String colorFigure="random";

    /*JLabel figureLabel;
    JComboBox figureCombo;*/

    public ConfigPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        sidesLabel = new JLabel("Number of sides: ");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        colorLabel = new JLabel("Color: ");
        colorCombo = new JComboBox(colors);

        sizeLabel = new JLabel("Size of figure: ");
        sizeField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 5));

        sizeField.setValue(Integer.valueOf(this.sizeFigure));
        sidesField.setValue(Integer.valueOf(this.sidesFigure));
        colorCombo.setSelectedIndex(1);

        this.sidesField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                JSpinner spinner = (JSpinner) event.getSource();
                sidesFigure = (int)spinner.getValue();
            }
        });

        this.sizeField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                JSpinner spinner = (JSpinner) event.getSource();
                sizeFigure = (int) spinner.getValue();
            }
        });

        this.colorCombo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                colorFigure = (String) colorCombo.getSelectedItem();
            }
        });

        add(sidesLabel);
        add(sidesField);
        add(colorLabel);
        add(colorCombo);
        add(sizeLabel);
        add(sizeField);
    }

    public void setSizeFigure(int sizeFigure) {
        this.sizeFigure = sizeFigure;
        sizeField.setValue(Integer.valueOf(sizeFigure));
    }

    public void setSidesFigure(int sidesFigure){
        this.sidesFigure = sidesFigure;
        sidesField.setValue(Integer.valueOf(sidesFigure));
    }

    public void setColorFigure(String colorFigure){
        this.colorFigure = colorFigure;
        colorCombo.setSelectedIndex(1);
    }

    public int getSizeFigure(){
        return this.sizeFigure;
    }

    public int getSidesFigure(){
        return this.sidesFigure;
    }

    public String getColorFigure(){
        return this.colorFigure;
    }

}
