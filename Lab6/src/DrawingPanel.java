import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int width = 800, height = 600;

    BufferedImage image;
    Graphics2D graphics;

    private String button;
    private int xLastFigure = -1;
    private int yLastFigure = -1;
    private ArrayList shapesList;
    private boolean load;

    public DrawingPanel(MainFrame frame){
        this.frame = frame;
        createOffscreenImage();
        init();
    }


    private void createOffscreenImage(){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,width, height);
    }

    private void init(){
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEtchedBorder());

        this.shapesList = new  ArrayList<Shape>();

        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent event){

                drawShape(event.getX(), event.getY());
                repaint();
            }
        });
    }

    private void drawShape(int x, int y){
        Color color;
        String colorFigure = this.frame.configPanel.getColorFigure();
        if(colorFigure.equals("black")){
            color = Color.BLACK;
        }else{
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            color = new Color(r, g, b);
        }

        Shape shape = new Shape(new Point(x,y), this.frame.configPanel.getSizeFigure(), this.frame.configPanel.getSidesFigure(), color);
        shapesList.add(shape);

        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, this.frame.configPanel.getSizeFigure(), this.frame.configPanel.getSidesFigure()));
    }

    public void undoShape(){
        if(this.shapesList != null) {
            createOffscreenImage();
            if(this.frame.controlPanel.getImage() != null) {
                this.frame.canvas.setImage(this.frame.controlPanel.getImage());
            }
            this.frame.canvas.repaint();
            shapesList.remove(shapesList.size()-1);
            for(int index=0;index<shapesList.size();index++) {
                Shape shape = (Shape)shapesList.get(index);
                Point point = shape.getPoint();
                graphics.setColor(shape.getColor());
                graphics.fill(new RegularPolygon(point.x, point.y, shape.getSize(), shape.getSides()));
                this.frame.canvas.revalidate();
                this.frame.canvas.repaint();
            }

        }
    }

    public void reset(){
        createOffscreenImage();
    }

    protected void paintComponent(Graphics graphics){
        graphics.drawImage(image, 0, 0, this);
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getShapesListSize(){
        return this.shapesList.size();
    }

    public void setButtonPressed(String button){
        this.button = button;
    }

    public void resetShapesListSize(){
        this.shapesList = new ArrayList<Shape>();
    }

    public void setImage(BufferedImage image){
        this.image = image;
        graphics = image.createGraphics();
        this.paintComponent(graphics);
    }
}
