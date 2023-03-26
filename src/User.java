import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userName;
    private String email;
    private String password;
    private Date dob;
    private String gender;
    private ArrayList<Post> post;
    private ArrayList<String> friend;
    private ArrayList<String> request;

    public User(String userName, String email, String password, Date dob, String gender) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        post =new ArrayList<>();
        friend=new ArrayList<>();
        request=new ArrayList<>();
    }
    public User(User u){
        this.userName = u.userName;
        this.email = u.email;
        this.password = u.password;
        this.dob = u.dob;
        this.gender = u.gender;
        this.post.addAll(u.post);
        this.friend.addAll(u.friend);
    }
    public boolean equals(User u){
        return this.userName.equals(u.getUserName())&&this.email.equals(u.getEmail());
    }
    public String getUserName() {
        return userName;
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
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public ArrayList<Post> getPost() {
        return post;
    }
    public ArrayList<String> getFriends() {
        return friend;
    }
    public ArrayList<String> getRequest() {
        return request;
    }
    public void setRequest(ArrayList<String> request) {
        this.request = request;
    }
    void display(){
        System.out.println("name: "+userName);
        System.out.println(dob.toString());
        System.out.println(gender);
        for(Post p:post){
            p.display();
        }
    }
}
