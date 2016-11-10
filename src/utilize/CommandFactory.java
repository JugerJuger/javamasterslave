package utilize;

import process.ConnectProcess;
import process.DisconnectProcess;
import process.ListProcess;
import process.Processable;

public enum CommandFactory {

	LIST, CONNECT, DISCONNECT;
	
	
	
	public static Processable process(CommandFactory algoritm) {
		switch (algoritm) {
		case LIST:
			return new ListProcess();
		case CONNECT:
			return new ConnectProcess();
		case DISCONNECT:
			return new DisconnectProcess();
		}
		throw new IllegalArgumentException();
	}
}
