import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Actions {
	private ObjectInputStream sInput;
	
	
	
	
	public boolean start() {
		System.out.println("- Able to receive message.");
		new ListenFromServer().start();
		return original();		
	}
	
	private void initStreams(){
		original();
		try {
			sInput = new ObjectInputStream(socket.getInputStream());
		} catch (IOException eIO) {
			showMsg("Exception creating new Input Stream: " + eIO);
		}
	}
	
	
	public void disconnect() {
		if (sInput != null) {
			try {
				sInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		original();
	}
	
	class ListenFromServer extends Thread {
		public void run() {
			while (true) {
				try {
					String msg = (String) sInput.readObject();
					System.out.println(msg);
					System.out.print("> ");
				} catch (Exception e) {
					showMsg("Server has close the connection: " + e);
					break;
				}
			}
		}
	}
}
