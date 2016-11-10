package dao;

import java.net.Socket;
import java.time.LocalDateTime;

public class SocketEntity {

	private Socket socket;
	
	private String host;
	private Integer port;

	private String name;
	
	private LocalDateTime ldt;

	public SocketEntity() {
	}

	public SocketEntity(Socket socket, LocalDateTime ldt, String name) {
		this.socket = socket;
		this.host = socket.getInetAddress().getHostAddress();
		this.port = socket.getPort();
		this.ldt = ldt;
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public LocalDateTime getLdt() {
		return ldt;
	}

	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}