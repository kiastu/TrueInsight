import com.theeyetribe.client.GazeManager;
import com.theeyetribe.client.IGazeListener;
import com.theeyetribe.client.data.GazeData;
import src.org.tc33.jheatchart.HeatChart;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import java.io.Console;
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
    private GazeManager gm;

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
        arrayCoordinates = new double[E_WIDTH][E_HEIGHT];
        for(int i = 0; i < E_WIDTH; i++) {
            for(int j = 0; j < E_HEIGHT; j++){
                arrayCoordinates[i][j] = 0;
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

    private class GazeListener implements IGazeListener
    {
        @Override
        public void onGazeUpdate(GazeData gazeData)
        {
            int x1 = (int)gazeData.smoothedCoordinates.x;
            int y1 = (int)gazeData.smoothedCoordinates.y;

            /*Console line = System.console();
            System.out.println(gazeData.toString());
            try{
                String s = line.readLine();
                String[] parts = toString().split(", ");
                String part1 = parts[0];
                String part2 = parts[1];
                int x = Integer.parseInt(part1);
                int y = Integer.parseInt(part2);
            }catch(Exception e){
                System.out.print("error");
            }*/

            if(x1>=MAX_WIDTH || x1<0 || y1>=MAX_HEIGHT || y1<0){
                System.out.println("Error, you're looking out of bounds!"+"X:"+x1+ "Y:"+y1);
                return;
            }else{
                //within bounds
                x1/=RESCUT;
                y1/=RESCUT;
                System.out.println("X:"+x1+ "Y:"+y1);
                arrayCoordinates[x1][y1]+=1;
            }
        }
    }
    public void stopServer(){
        gm.deactivate();
    }
    
    public TETSimple(int x)
    {
        TETSimple TS = new TETSimple();
        try{
            Thread.sleep(10000);
        }catch(Exception e){}
        //stop running.
        TS.stopServer();

        HeatChart map = new HeatChart(TS.getArrayCoordinates());
        double [][]arrayCoordinates = TS.getArrayCoordinates();
        PrintWriter writer = new PrintWriter("logs.txt");
        for(int i = 0; i < E_WIDTH; i++) {
            String print = "";
            for(int j = 0; j < E_HEIGHT; j++){
                print += arrayCoordinates[i][j]+" ";
            }
            writer.println(print);
        }

        try {
            map.saveToFile(new java.io.File("java-heat-chart.png"));
            }catch(Exception e) {
        e.printStackTrace();        }
    }
    
    public static void main(String[] args) throws Exception
    {

        TETSimple TS = new TETSimple();
        try{
            Thread.sleep(10000);
        }catch(Exception e){}
        //stop running.
        TS.stopServer();

        HeatChart map = new HeatChart(TS.getArrayCoordinates());
        double [][]arrayCoordinates = TS.getArrayCoordinates();
        PrintWriter writer = new PrintWriter("logs.txt");
        for(int i = 0; i < E_WIDTH; i++) {
            String print = "";
            for(int j = 0; j < E_HEIGHT; j++){
                print += arrayCoordinates[i][j]+" ";
            }
            writer.println(print);
        }

        try {
            map.saveToFile(new java.io.File("java-heat-chart.png"));
            }catch(Exception e) {
        e.printStackTrace();        }

    }
}