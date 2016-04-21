package test01.domain;

import java.sql.Date;

public class Project {
  private int no;
  private String title;
  private Date sd;
  private Date ed;
  private String dec;
  private int teamNo;
  private int teachNo;
  private String pw;
  
  @Override
  public String toString() {
    return "Project [no=" + no + ", title=" + title + ", sd=" + sd + ", ed=" + ed + ", dec=" + dec + ", teamNo="
        + teamNo + ", teachNo=" + teachNo + "]";
  }
  
  public String getPw() {
    return pw;
  }
  public void setPw(String pw) {
    this.pw = pw;
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
  public Date getSd() {
    return sd;
  }
  public void setSd(Date sd) {
    this.sd = sd;
  }
  public Date getEd() {
    return ed;
  }
  public void setEd(Date ed) {
    this.ed = ed;
  }
  public String getDec() {
    return dec;
  }
  public void setDec(String dec) {
    this.dec = dec;
  }
  public int getTeamNo() {
    return teamNo;
  }
  public void setTeamNo(int teamNo) {
    this.teamNo = teamNo;
  }
  public int getTeachNo() {
    return teachNo;
  }
  public void setTeachNo(int teachNo) {
    this.teachNo = teachNo;
  }
  
  
}//
