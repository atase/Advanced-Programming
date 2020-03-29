import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;


public class ComponentPanel extends JPanel {
    final MainFrame frame;
    final static int width = 380, height = 600;

    private BufferedImage image;
    private Graphics2D form;

    JPanel settings;
    JLabel sizeLabel;
    JSpinner sizeField;
    JLabel colorLabel;
    JComboBox colorCombo;
    private String colors[] = {"black", "random"};

    public ComponentPanel(MainFrame frame){
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage(){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        form = image.createGraphics();
        form.setColor(Color.WHITE);
        form.fillRect(0,0,width, height);
    }

    private void init(){

        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEtchedBorder());

        RegularPolygon triangle = new RegularPolygon(100,100,50,3);
        RegularPolygon square = new RegularPolygon(250,100,50,4);
        RegularPolygon pentagon = new RegularPolygon(100,250,50,5);
        RegularPolygon hexagon = new RegularPolygon(250,400,50,10);
        RegularPolygon circle = new RegularPolygon(100,400,50,100);
        RegularPolygon octagon = new RegularPolygon(250,250,50,8);

        drawShape(triangle, Color.BLACK);
        drawShape(square , Color.BLACK);
        drawShape(pentagon, Color.BLACK);
        drawShape(octagon, Color.BLACK);
        drawShape(circle, Color.BLACK);
        drawShape(hexagon, Color.BLACK);


        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
                int x = event.getX();
                int y = event.getY();
                if(50 < x && x < 150 && 50 < y && y < 150){

                    drawShape(triangle, Color.RED);
                    frame.configPanel.setSidesFigure(3);
                    frame.configPanel.setSizeFigure(50);
                    frame.configPanel.setColorFigure("black");

                }else if(200 < x && x < 300 && 50 < y && y < 150){

                    drawShape(square, Color.RED);
                    frame.configPanel.setSidesFigure(4);
                    frame.configPanel.setSizeFigure(50);
                    frame.configPanel.setColorFigure("black");

                }else if(50 < x && x < 150 && 200 < y && y < 300){

                    drawShape(pentagon, Color.RED);
                    frame.configPanel.setSidesFigure(5);
                    frame.configPanel.setSizeFigure(50);
                    frame.configPanel.setColorFigure("black");

                }else if(200 < x && x < 300 && 350 < y && y < 450){

                    drawShape(hexagon, Color.RED);
                    frame.configPanel.setSidesFigure(10);
                    frame.configPanel.setSizeFigure(50);
                    frame.configPanel.setColorFigure("black");

                }else if(50 < x && x < 150 && 350 < y && y < 450){

                    drawShape(circle, Color.RED);
                    frame.configPanel.setSidesFigure(200);
                    frame.configPanel.setSizeFigure(50);
                    frame.configPanel.setColorFigure("black");

                }else if(200 < x && x < 300 && 200 < y && y < 300){

                    drawShape(octagon, Color.RED);
                    frame.configPanel.setSidesFigure(8);
                    frame.configPanel.setSizeFigure(50);
                    frame.configPanel.setColorFigure("black");

                }
                revalidate();
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                drawShape(triangle, Color.BLACK);
                drawShape(square, Color.BLACK);
                drawShape(pentagon, Color.BLACK);
                drawShape(hexagon, Color.BLACK);
                drawShape(circle, Color.BLACK);
                drawShape(octagon, Color.BLACK);
                revalidate();
                repaint();
            }
        });
    }

    private void drawShape(RegularPolygon figure, Color color){
        form.setColor(color);
        form.fill(figure);
    }

    protected void paintComponent(Graphics graphics){
        graphics.drawImage(image, 0, 0, this);
    }

}
