package dao;


import java.util.ArrayList;
import java.util.List;

public class Resources {
	
	private static List<SocketEntity> list = new ArrayList<>();

	public static List<SocketEntity> getList() {
		return list;
	}

	public static void setList(List<SocketEntity> list) {
		Resources.list = list;
	}
	
	public static void addList(SocketEntity se) {
		Resources.list.add(se);
	}

}
