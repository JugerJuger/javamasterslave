package process;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import dao.Resources;
import dao.SocketEntity;

public class DisconnectProcess implements Processable {

	@Override
	public void process(String[] s) {
		if (s.length == 1)
			removeall();
		if (s.length >= 2 && s[1].equalsIgnoreCase("all"))
			removeAll(s);
		if (s.length >= 2 && s[1].substring(0, 4).equalsIgnoreCase("Slave"))
			removeSlaAll(s);
		if (s.length >= 2 && !s[1].equalsIgnoreCase("all") && !s[1].substring(0, 4).equalsIgnoreCase("Slave"))
			removewitAll(s);
	}

	private void removewitAll(String[] string) {
		Iterator<SocketEntity> i = Resources.getList().iterator();
		while (i.hasNext()) {
			SocketEntity s = i.next();
			if (s.getHost().equals(string[1]) || s.getHost().equals(string[2]) && s.getPort().equals(Integer.parseInt(string[3]))) {
				try {
					s.getSocket().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				i.remove();
			}
		}
	}

	private void removeSlaAll(String[] string) {
		
		Iterator<SocketEntity> i = Resources.getList().iterator();
		while (i.hasNext()) {
			SocketEntity s = i.next();
			if (s.getName().equals(string[1]) && s.getHost().equals(string[2])
					&& s.getPort().equals(Integer.parseInt(string[3]))) {
				try {
					s.getSocket().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				i.remove();
			}
		}
	}

	private void removeAll(String[] string) {
		
		Iterator<SocketEntity> i = Resources.getList().iterator();
		while (i.hasNext()) {
			SocketEntity s = i.next();
			if (s.getHost().equals(string[2]) && s.getPort().equals(Integer.parseInt(string[3]))) {
				try {
					s.getSocket().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				i.remove();
			}
		}
	}

	private void removeall() {
		for (SocketEntity se : Resources.getList()) {
			try {
				se.getSocket().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Resources.getList().clear();
	}

}
