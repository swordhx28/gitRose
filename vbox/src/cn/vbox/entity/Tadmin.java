package cn.vbox.entity;

import cn.vbox.annotation.Column;
import cn.vbox.annotation.Table;

@Table(name = "tadmin")
public class Tadmin {
	@Column(name = "id", primaryId = true, type = "int", isNull = "not null")
	private int id;
	@Column(name = "username", type = "varchar(10)")
	private String username;
	@Column(name = "password", type = "varchar(10)")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
