

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCPort;
import com.illposed.osc.OSCPortIn;
import com.illposed.osc.OSCMessage;

public class MuseServer {
	ArrayList<OSCMessage> messageList;
	OSCPortIn receiver;
	public MuseServer(){
		messageList = new ArrayList<>();
		int port = 5001;
		try {
			receiver = new OSCPortIn(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		try{
			OSCListener eeglistener = new OSCListener() {
				public void acceptMessage(java.util.Date time, OSCMessage message) {
					//we need to do something here to handle the inputs.
					System.out.println("Message received! Raw OSCMessage"+message.toString());
					// just keep them in a list, process them in another class.
					messageList.add(message);
				}
			};
			receiver.addListener("/muse/eeg", eeglistener);//accept only messages with the eeg tag.
			receiver.startListening();
		}catch(Exception e){

		}
	}
	public void stop(){
		receiver.stopListening();
	}
	public ArrayList<OSCMessage> getData(){
		return messageList;
	}
}
