package test01.domain;

public class Member {
  protected int    no;
  protected String name;
  protected String email;
  protected String password;
  protected String tel;
  protected String part;
  
  public Member() {
  }
  
  public Member(int no, String name, String email, String password, String tel, String part) {
    this.no = no;
    this.name = name;
    this.email = email;
    this.password = password;
    this.tel = tel;
    this.part = part;
  }
  
  @Override
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", tel=" + tel
        + ", part=" + part + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getPart() {
    return part;
  }
  public void setPart(String part) {
    this.part = part;
  }

}
