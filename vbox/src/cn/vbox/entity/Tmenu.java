package cn.vbox.entity;

import cn.vbox.annotation.Column;
import cn.vbox.annotation.Table;

@Table(name = "tmenu")
public class Tmenu {
	@Column(name = "id", primaryId = true, type = "int", isNull = "not null")
	private int id;
	@Column(name = "title", type = "varchar(18)")
	private String title;
	// çˆ¶èœå?
	@Column(name = "pid", type = "int")
	private int pid;
	// æ‰?¤„ä½ç½®é¡ºåº
	@Column(name = "posid", type = "int")
	private int posid;
	@Column(name = "fk_aid", type = "int")
	private int fk_aid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPosid() {
		return posid;
	}
	public void setPosid(int posid) {
		this.posid = posid;
	}
	public int getFk_aid() {
		return fk_aid;
	}
	public void setFk_aid(int fk_aid) {
		this.fk_aid = fk_aid;
	}
}