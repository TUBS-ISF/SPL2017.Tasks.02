import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public aspect Receive {

	declare precedence: Text ,Receive, Send, EnterRoom;
	private ObjectInputStream sInput;
	private Socket socket;

	pointcut run(): execution(void Basic.start());

	pointcut a_start(): call(* Actions.start());

	pointcut a_initStream(): execution(void Actions.initStreams());

	pointcut a_dn(): execution(void Actions.disconnect());

	after():run(){
		System.out.println(" - Receive module loaded.");
	}

	before():a_start(){
		new ListenFromServer().start();
	}

	after():a_initStream(){
		try {
			sInput = new ObjectInputStream(socket.getInputStream());
		} catch (IOException eIO) {
			showMsg("Exception creating new Input Stream: " + eIO);
		}
	}
	after():a_dn(){
		if (sInput != null) {
			try {
				sInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class ListenFromServer extends Thread {
		public void run() {
			while (true) {
				try {
					String msg = (String) sInput.readObject();
					System.out.println(msg);
					System.out.print("> ");
				} catch (Exception e) {
//					showMsg("Server has close the connection: " + e);
					break;
				}
			}
		}
	}

	private void showMsg(String msg) {
		System.out.println(msg);
	}
}
