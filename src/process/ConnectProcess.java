package process;

import java.io.IOException;
import java.util.Iterator;

import dao.Resources;
import dao.SocketEntity;
import utilize.ConsoleManager;

public class ConnectProcess implements Processable {
	private static ConsoleManager cm = ConsoleManager.getInstance();

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

		StringBuilder sb;
		Iterator<SocketEntity> i = Resources.getList().iterator();
		while (i.hasNext()) {
			SocketEntity s = i.next();
			if (s.getHost().equals(string[1])
					|| s.getHost().equals(string[2]) && s.getPort().equals(Integer.parseInt(string[3]))) {
				sb = new StringBuilder();
				sb.append(s.getName()).append(" ").append(s.getHost()).append(" ").append(s.getPort()).append("   ")
						.append(s.getLdt().toString());
				cm.writeWithLt(sb.toString());
				i.remove();
			}
		}
	}

	private void removeSlaAll(String[] string) {
		StringBuilder sb;
		Iterator<SocketEntity> i = Resources.getList().iterator();
		while (i.hasNext()) {
			SocketEntity s = i.next();
			if (s.getName().equals(string[1]) && s.getHost().equals(string[2])
					&& s.getPort().equals(Integer.parseInt(string[3]))) {
				sb = new StringBuilder();
				sb.append(s.getName()).append(" ").append(s.getHost()).append(" ").append(s.getPort()).append("   ")
						.append(s.getLdt().toString());
				cm.writeWithLt(sb.toString());

			}
		}
	}

	private void removeAll(String[] string) {
		StringBuilder sb;
		Iterator<SocketEntity> i = Resources.getList().iterator();
		while (i.hasNext()) {
			SocketEntity s = i.next();
			if (s.getHost().equals(string[2]) && s.getPort().equals(Integer.parseInt(string[3]))) {
				sb = new StringBuilder();
				sb.append(s.getName()).append(" ").append(s.getHost()).append(" ").append(s.getPort()).append("   ")
						.append(s.getLdt().toString());
				cm.writeWithLt(sb.toString());
			}
		}
	}

	private void removeall() {
		StringBuilder sb;
		for (SocketEntity se : Resources.getList()) {
			sb = new StringBuilder();
			sb.append(se.getName()).append(" ").append(se.getHost()).append(" ").append(se.getPort()).append("   ")
					.append(se.getLdt().toString());
			cm.writeWithLt(sb.toString());
		}

	}

}
