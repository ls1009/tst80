package test01.domain;

import java.sql.Date;

public class Board {
	private int no;						//BNOS
	private String title;				//BNM
	private String content;				//CONT
	private String password;
	private Date createDate;			//CR_DT
	private Date editDate;				//ED_DT
	private int views;					//VIEWS
	private String writer;				//WRRT
	private int bno;					//BNO
	
	@Override
	public String toString() {
		return no + "," + title +"," + content + "," 
			   + createDate + "," + editDate + "," + views + "," + writer; 
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
}