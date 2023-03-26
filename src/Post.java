import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    private String content;
    private int like;
    private ArrayList<String> comment;

    public Post( String content) {
        this.content=content;
        comment =new ArrayList<>();
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public int getLike() {
        return like;
    }
    public ArrayList<String> getComment() {
        return comment;
    }
    public void setLike(int like) {
        this.like = like;
    }
    public void addComment(String c){
        comment.add(c);
    }
    public void display(){
        System.out.println(content);
    }
}
