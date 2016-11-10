package process;

import java.io.IOException;

import dao.Resources;
import dao.SocketEntity;

public class DisconnectProcess implements Processable{

	@Override
	public void process() {
		for (SocketEntity se : Resources.getList()) {
			try {
				se.getSocket().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
