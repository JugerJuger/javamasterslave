package utilize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ConsoleManager {

	private InputStream inputStream = System.in;
	private static ConsoleManager instanse = null;
	private static Set<String> comands = new HashSet<>();

	
	
	private ConsoleManager() {
		instanse = this;
		initializeComands();
	}

	
	public static ConsoleManager getInstance() {
		return (instanse == null) ? new ConsoleManager() : instanse;
	}

	
	public String read() {
		writeWithLt();
		String result = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			result = reader.readLine();
			if (!comands.contains(result.toUpperCase())) {
				help();
				result = read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	public String read(String warning) {
		writeWithLt();
		String result = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			result = reader.readLine();
			if (!comands.contains(result.toUpperCase())) {
				System.out.println(warning);
				result = read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void writeWithLt() {
		System.out.print(ViewsElement.VIEW_LT);
	}

	public static void writeWithLt(String str) {
		writeWithLt();
		System.out.println(str);
	}

	public static void write(String str) {
		System.out.print("		");
		System.out.println(str);
	}

	public void closeSefully() {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initializeComands() {
		for (CommandFactory com : CommandFactory.values()) {
			comands.add(com.name());
		}
	}

	public static Set<String> getComands() {
		return comands;
	}

	public static void setComands(Set<String> comands) {
		ConsoleManager.comands = comands;
	}

	public static void addComand(String comand) {
		ConsoleManager.comands.add(comand.toUpperCase());
	}

	public void help() {
		writeWithLt("List of avalable comands:");
		for (CommandFactory com : CommandFactory.values()) {
			write(com.name());
		}
	}

}
