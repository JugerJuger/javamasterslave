package vasavada;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

import dao.Resources;
import dao.SocketEntity;
import utilize.CommandFactory;
import utilize.ConsoleManager;

public class MasterBot extends Thread {

	private static ConsoleManager cm = ConsoleManager.getInstance();
	
	private static final String KEY = "-p";

	private static String validateArgument(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException();
		if (KEY.equals(args[0]))
			return args[1];
		else
			throw new IllegalArgumentException();

	}
	
	public static void main(String args[]) {
		Integer port = Integer.parseInt(validateArgument(args));
		try {
			int i = 0;
			
			ServerSocket listener = new ServerSocket(port, 0, InetAddress.getByName("localhost"));
			StringBuilder out = new StringBuilder();
			cm.writeWithLt(out.append("MasterBot initialized at port ").append(port).toString());
			cm.writeWithLt("Listening for incoming SlaveBots...");
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							Socket s = listener.accept();
							SocketEntity se = new SocketEntity(s, LocalDateTime.now());
							Resources.addList(se);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			t.setDaemon(true);
			t.start();
			while (true) {
				CommandFactory.process(CommandFactory.valueOf(cm.read().toUpperCase())).process();
			}
		} catch (Exception e) {
			cm.writeWithLt("init error: " + e);
		}
	}
}
