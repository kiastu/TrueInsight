
public class MuseAdapter {
	public static void main(String[] args){
		//this is for testing purposes. Shows how to call the server.
		MuseServer server = new MuseServer();
		server.run();//starts the server, so it will communicate with the muse.
		//server.stop();//stops the server, call for destructors so you don't have runaway threads.
		server.getData();//fetches the data that the muse has streamed over. Since we're doing a heatmap application, I'm assuming we don't need consistent data.
		
	}
}
