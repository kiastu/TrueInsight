

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCPort;
import com.illposed.osc.OSCPortIn;
import com.illposed.osc.OSCMessage;

public class MuseServer {
	public MuseServer(){
		
	}
	public void run(){
		try{
			int port = 5001;
			OSCPortIn receiver = new OSCPortIn(port);
			OSCListener eeglistener = new OSCListener() {
				public void acceptMessage(java.util.Date time, OSCMessage message) {
					//we need to do something here to handle the inputs.
					System.out.println("Message received! Raw OSCMessage"+message.toString());
				}
			};
			receiver.addListener("/muse/eeg", eeglistener);//accept only messages with the eeg tag.
			receiver.startListening();
		}catch(Exception e){

		}
	}
}
