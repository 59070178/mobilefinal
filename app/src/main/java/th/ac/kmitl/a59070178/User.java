package th.ac.kmitl.a59070178;
import android.content.ContentValues;

public class User {

    String id;
    String fname;
    String lname;
    String age;
    String password;
    ContentValues row = new ContentValues();


    public User(String id, String fname, String lname, String age, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.password = password;
    }

    public ContentValues getContent() {
        return row;
    }

    public void setContent(String id, String fname, String lname, String age, String password) {

        this.row.put("id", id);
        this.row.put("fname", fname);
        this.row.put("lname", lname);
        this.row.put("age", age);
        this.row.put("password", password);

    }


    User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
