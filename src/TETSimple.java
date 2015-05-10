import com.theeyetribe.client.GazeManager;
import com.theeyetribe.client.IGazeListener;
import com.theeyetribe.client.data.GazeData;
import src.org.tc33.jheatchart.HeatChart;

import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import java.io.Console;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.rmi.server.ExportException;
import java.util.concurrent.TimeUnit;


public class TETSimple
{
    private static final int MAX_HEIGHT =1080 ;
    private static final int MAX_WIDTH = 1920;
    private static final int RESCUT = 5;
    private static final int E_HEIGHT = MAX_HEIGHT/RESCUT;
    private static final int E_WIDTH = MAX_WIDTH/RESCUT;

    private double[][] arrayCoordinates;
    private double[][] muse;
    private GazeManager gm;

    public static int geteWidth() {
        return E_WIDTH;
    }

    public static int geteHeight() {
        return E_HEIGHT;
    }

    public double[][] getArrayCoordinates() {
        return arrayCoordinates;
    }

    public void setArrayCoordinates(double[][] arrayCoordinates) {
        this.arrayCoordinates = arrayCoordinates;
    }

    public TETSimple()
    {
        gm = GazeManager.getInstance();
        boolean success = gm.activate(GazeManager.ApiVersion.VERSION_1_0, GazeManager.ClientMode.PUSH);

    }

    private class GazeListener implements IGazeListener
    {
        @Override
        public void onGazeUpdate(GazeData gazeData)
        {
            int x1 = (int)gazeData.smoothedCoordinates.x;
            int y1 = (int)gazeData.smoothedCoordinates.y;

            if(x1>=MAX_WIDTH || x1<0 || y1>=MAX_HEIGHT || y1<0){
                System.out.println("Error, you're looking out of bounds!"+"X:"+x1+ "Y:"+y1);
                return;
            }else{
                //within bounds
                x1/=RESCUT;
                y1/=RESCUT;
                System.out.println("X:"+x1+ "Y:"+y1);
                arrayCoordinates[y1][x1]+=1; //changed

            }
        }
    }
    public void startServer(){
        arrayCoordinates = new double[E_WIDTH][E_HEIGHT];
        for(int i = 0; i < E_WIDTH; i++) {
            for(int j = 0; j < E_HEIGHT; j++){
                arrayCoordinates[i][j] = 0;
            }
        }
        muse = new double[E_WIDTH][E_HEIGHT];
        for(int i = 0; i < E_WIDTH; i++) {
            for(int j = 0; j < E_HEIGHT; j++){
                muse[i][j] = 0;
            }
        }

        final GazeListener gazeListener = new GazeListener();
        gm.addGazeListener(gazeListener);

        //TODO: Do awesome gaze control wizardry

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                gm.removeGazeListener(gazeListener);
                gm.deactivate();
            }
        });
    }

    public void stopServer(){
        gm.deactivate();

        //mess with museMap;
        muse=arrayCoordinates;
        Random random = new Random();
        for(int i=0;i<arrayCoordinates.length;i++){
            for(int j=0;j<arrayCoordinates[i].length;j++){
                double num = random.nextDouble()*(1-0.5)+0.5;
                arrayCoordinates[i][j] *= num;
            }
        }
        HeatChart map = new HeatChart(getArrayCoordinates());
        HeatChart museMap = new HeatChart(muse);
        map.setBackgroundColour(new Color(0,0,0, 20));
        double [][]arrayCoordinates = getArrayCoordinates();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("logs.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < E_WIDTH; i++) {
            String print = "";
            for(int j = 0; j < E_HEIGHT; j++){
                print += arrayCoordinates[i][j]+" ";
            }
            writer.println(print);
        }

        try {
            map.saveToFile(new java.io.File("java-heat-chart.png"));
            museMap.saveToFile(new java.io.File("muse-heat-chart.png"));
        }catch(Exception e) {
            e.printStackTrace();        }
        // load source images
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("base.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage overlay = null;
        Image scaledOverlay =null;
        try {
            overlay = ImageIO.read(new File("java-heat-chart.png"));
            scaledOverlay =  overlay.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

// create the new image, canvas size is the max. of both image sizes
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.drawImage(scaledOverlay, 0, 0, null);


    }
}