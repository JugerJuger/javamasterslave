package vasavada;


import java.net.Socket;

public class SlaveBot extends Thread {

	private static final String KEY = "-h";

	private static String[] validateArgument(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException();
		if (KEY.equals(args[0]))
			return args[1].split(" ");
		else
			throw new IllegalArgumentException();

	}

	public static void main(String args[]) {
		String arr[] = validateArgument(args);
		String host = arr[0];
		Integer port = Integer.parseInt(arr[1]);
		System.out.println(port);
		try {

			Socket s = new Socket(host, port);

			String str = "Hello" + "\n" + s.getInetAddress().getHostAddress() + ":" + s.getLocalPort();
			s.getOutputStream().write("Hello".getBytes());

			byte buf[] = new byte[64 * 1024];
			int r = s.getInputStream().read(buf);
			String data = new String(buf, 0, r);

			System.out.println(data);
		} catch (Exception e) {
			System.out.println("init error: " + e);
		}
	}
}
