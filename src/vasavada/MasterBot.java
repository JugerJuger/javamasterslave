package vasavada;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import utilize.ConsoleManager;

public class MasterBot extends Thread {

	private Socket s;
	private int num;
	private static ConsoleManager cm = ConsoleManager.getInstance();

	public static void main(String args[]) {

		try {
			int i = 0;
			ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("localhost"));
			cm.writeWithLt("server is started");
			while (true) {
				new MasterBot(i, server.accept());
				i++;
			}
		} catch (Exception e) {
			System.out.println("init error: " + e);
		}
	}

	public MasterBot(int num, Socket s) {
		this.num = num;
		this.s = s;
		setDaemon(true);
		setPriority(NORM_PRIORITY);
		start();
	}

	public void run() {
		try {
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			byte buf[] = new byte[64 * 1024];
			int r = is.read(buf);
			String data = new String(buf, 0, r);
			data = "" + num + ": " + "\n" + data;
			os.write(data.getBytes());
			s.close();
		} catch (Exception e) {
			System.out.println("init error: " + e);
		} 
	}
}
