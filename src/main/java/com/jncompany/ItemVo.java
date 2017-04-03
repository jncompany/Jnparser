package com.jncompany;

public class ItemVo {
	
	private String subject;
	private String link;
	private String imgsrc;
	private String content;
	private String time;
	private int cnt;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "subject=" + subject +"\n" + ", link=" + link +"\n"+ ", imgsrc=" + imgsrc +"\n"+ ", content=" + content+"\n"
				+ ", time=" + time +"\n"+ ", cnt=" + cnt + "\n";
	}
	
	

}
