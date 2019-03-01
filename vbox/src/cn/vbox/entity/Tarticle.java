package cn.vbox.entity;

import cn.vbox.annotation.Column;
import cn.vbox.annotation.Table;

@Table(name="Tarticle")
public class Tarticle {
	@Column(name = "id", primaryId = true, type = "int", isNull = "not null")
	private int id;
	@Column(name = "content", type = "text")
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
