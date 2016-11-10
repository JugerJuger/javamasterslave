package process;

import dao.Resources;
import dao.SocketEntity;
import utilize.ConsoleManager;

public class ListProcess implements Processable{

	private ConsoleManager cm = ConsoleManager.getInstance();
	
	@Override
	public void process(String[] s) {
		StringBuilder sb;
		int i = 0;
		for (SocketEntity se : Resources.getList()) {
			i++;
			sb = new StringBuilder();
			sb.append(se.getName()).append(" ").append(se.getHost()).append(" ")
			.append(se.getPort()).append("   ").append(se.getLdt().toString());
			cm.writeWithLt(sb.toString());
		}
	}

}
