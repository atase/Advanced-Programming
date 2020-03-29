import java.awt.*;

public class Shape extends Polygon{

    private Point point;
    private int sizeFigure;
    private int sidesFigure;
    private Color color;

    Shape(Point point, int sizeFigure, int sidesFigure, Color color){
        this.point = new Point();
        this.point = point;

        this.sizeFigure = sizeFigure;
        this.sidesFigure = sidesFigure;
        this.color = color;
    }

    public void setPoint(Point point){
        this.point = point;
    }
    public void setSize(int sizeFigure){
        this.sizeFigure = sizeFigure;
    }
    public void setSides(int sidesFigure){
        this.sidesFigure = sidesFigure;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Point getPoint(){
        return this.point;
    }
    public int getSize(){
        return this.sizeFigure;
    }
    public int getSides(){
        return this.sidesFigure;
    }

    public Color getColor(){
        return this.color;
    }
}
