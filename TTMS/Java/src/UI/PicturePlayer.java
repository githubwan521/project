package UI;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * @author Hardneedl
 */
public class PicturePlayer extends JPanel {
    private File[] files;
    private Image img;
    private int index=-1;
    private Timer timer=new Timer(500,new TimerAction());
    private Point p;
    private JLabel jl;
    public PicturePlayer(Point p,JLabel jl){
    	this.jl=jl;
    	this.p=p;
    }
    private JComponent canvas = new JComponent(){
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(files!=null&&index>=0&&index< files.length-1){

                try {
                    img = ImageIO.read(files[index]);
                } catch(IOException e) {
                    e.printStackTrace();
                }

                if (img==null)return;
                Graphics gg = g.create();
                gg.drawImage(img,0,0,p.x,p.y,jl);
                gg.dispose();
            }
        }
    };


    private class OpenFileAction extends AbstractAction {
        private OpenFileAction() {super("Select Directory...");}
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser("E:\\java\\TTMS\\img");
                File f= chooser.getSelectedFile();
              
                	System.out.println(f);
                    files=f.listFiles();
                    index=files!=null&&files.length>0?0:-1;
                    if (files!=null&&files.length>0){
                        timer.start();
                    }
                    else {
                        timer.stop();
                    }
                
            }
        }

    private class TimerAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            index++;
            if (index>=files.length){
                index=0;
            }
            canvas.paintImmediately(canvas.getBounds());
        }
    }
} 