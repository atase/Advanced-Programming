import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel{
    final MainFrame frame;
    final JFileChooser chooser = new JFileChooser();
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton undoBtn = new JButton("Undo");
    JButton exitBtn = new JButton("Exit");
    private BufferedImage image;
    private BufferedImage newImage;
    private File filename;

    public ControlPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    public void init(){
        setLayout(new GridLayout(1,4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(undoBtn);
        add(exitBtn);

        this.saveBtn.addActionListener(this::save);
        this.loadBtn.addActionListener(this::load);
        this.resetBtn.addActionListener(this::reset);
        this.undoBtn.addActionListener(this::undo);
        this.exitBtn.addActionListener(this::exit);
    }

    private void save(ActionEvent event){
        this.newImage = null;
        int returnVal = chooser.showSaveDialog(getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try{
                ImageIO.write(this.frame.canvas.image, "PNG", file);
            }catch(IOException exception){
                System.err.println(exception);
            }
        }
        /*try{
            ImageIO.write(this.frame.canvas.image, "PNG", new File("test.png"));
        }catch(IOException exception){
            System.err.println(exception);
        }*/
    }

    private void load(ActionEvent event){
        this.frame.canvas.resetShapesListSize();
        this.image = new BufferedImage(this.frame.canvas.getWidth(), this.frame.canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.newImage = new BufferedImage(this.frame.canvas.getWidth(), this.frame.canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        int returnVal = chooser.showOpenDialog(getParent());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try{
                this.image = ImageIO.read(file);
                this.newImage = ImageIO.read(file);
                this.filename = file;
            }catch(IOException exception){
                System.err.println(exception);
            }
            this.frame.canvas.setImage(this.image);
            this.frame.canvas.repaint();
        }

        /*try {
            image = ImageIO.read(new File("test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public BufferedImage getImage(){
        if(this.newImage != null){
            try{
                this.image = ImageIO.read(filename);
                this.newImage = ImageIO.read(filename);
            }catch(IOException exception){
                System.err.println(exception);
            }
        }
        return this.newImage;
    }

    private void reset(ActionEvent event){
        this.newImage = null;
        this.frame.canvas.resetShapesListSize();
        this.frame.canvas.reset();
        this.frame.canvas.revalidate();
        this.frame.canvas.repaint();
    }

    private void undo(ActionEvent event){
        if(this.frame.canvas.getShapesListSize() != 0) {
            this.frame.canvas.undoShape();
            this.frame.canvas.revalidate();
            this.frame.canvas.repaint();
        } else {
          JOptionPane.showMessageDialog(this.frame, "There is no shapes to delete ! ");
        }
    }

    private void exit(ActionEvent event){
        System.exit(0);
    }

}
