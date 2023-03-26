
import java.io.*;
import java.util.ArrayList;

public class FileOperations {
    public static ArrayList<User> readAllFromFile(){
        ArrayList<User> users = new ArrayList<>();
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("user.ser"));
            while(true){
                User u=(User) ois.readObject();
                users.add(u);
            }

        }
        catch(ClassNotFoundException cnf){}
        catch (EOFException eoff){}
        catch (IOException io){}
        finally {
            return users;
        }
    }
    public static void userSignup(User u){
        try{
            File f=new File("user.ser");
            ObjectOutputStream oos;
            if(f.exists())
                oos=new MyObjectOutputStream(new FileOutputStream(f,true));
            else
                oos=new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(u);
            oos.close();
        }
        catch (IOException e){
            System.out.println("Error in file writing");
        }
    }
    public static User userLogin(String userName,String password){
        ArrayList<User> users=readAllFromFile();
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean validUsername(String name){
        ArrayList<User> users=readAllFromFile();
        for(User u:users){
            if(name.equals(u.getUserName())){
                return false;
            }
        }
        return true;
    }
    public static User searchUser(String name){
        ArrayList<User> users=readAllFromFile();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(name)) {
                return users.get(i);
            }
        }
        return null;
    }
    public static boolean sendRequest(String sender,String receiver){
        boolean sent=false;
        ArrayList<User> users=readAllFromFile();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(receiver)) {
                if(!users.get(i).getFriends().contains(sender)) {
                    if(users.get(i).getRequest()!=null) {
                        if(users.get(i).getRequest().contains(sender)) {
                            break;
                        }
                        else {
                            users.get(i).getRequest().add(sender);
                            sent = true;
                        }
                    }
                    else{
                        ArrayList<String> req=new ArrayList<>();
                        req.add(sender);
                        users.get(i).setRequest(req);
                        sent = true;
                    }
                }
                break;
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        finally {return sent;}

    }
    public static void deleteRequest(String receiver,String sender){
        ArrayList<User> users=readAllFromFile();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(receiver)) {
                users.get(i).getRequest().remove(sender);
                break;
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    public static void acceptRequest(String receiver,String sender){
        ArrayList<User> users=readAllFromFile();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(receiver)) {
                users.get(i).getRequest().remove(sender);
                users.get(i).getFriends().add(sender);
            }
            if(users.get(i).getUserName().equals(sender)) {
                users.get(i).getRequest().remove(receiver);
                users.get(i).getFriends().add(receiver);
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    public static void editProfile(User u){
        ArrayList<User> users=readAllFromFile();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(u.getUserName())) {
                users.get(i).setEmail(u.getEmail());
                users.get(i).setPassword(u.getPassword());
                users.get(i).setGender(u.getGender());
                users.get(i).setDob(u.getDob());
                break;
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }

    public static void editPost(String name,Post post,String content){
        ArrayList<User> users=readAllFromFile();
        for(User u:users){
            if(u.getUserName().equals(name)){
                ArrayList<Post> posts=u.getPost();
                for(Post p:posts){
                    if(p.getContent().equals(post.getContent())){
                        p.setContent(content);
                    }
                }
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    public static void addPost(String name,Post p){
        ArrayList<User> users=readAllFromFile();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserName().equals(name)) {
                users.get(i).getPost().add(p);
                break;
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    public static Post findPost(String content){
        ArrayList<User> users=readAllFromFile();
        for(User u:users){
            ArrayList<Post> posts=u.getPost();
            for(Post p:posts){
                if(content.equals(p.getContent())){
                    return p;
                }
            }
        }
        return null;
    }
    public static void likePost(String user, Post p){
        ArrayList<User> users=readAllFromFile();
        for(User i:users){
            if(i.getUserName().equals(user)){
                ArrayList<Post> posts=i.getPost();
                for(Post t:posts) {
                    if(t.getContent().equals(p.getContent()))
                        t.setLike(t.getLike() + 1);
                }
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}

    }
    public static void commentOnPost(String name,Post p,String comment){
        ArrayList<User> users=readAllFromFile();
        for(User i:users){
            if(i.getUserName().equals(name)){
                ArrayList<Post> posts=i.getPost();
                for(Post t:posts){
                    if(t.getContent().equals(p.getContent())){
                        t.addComment(comment);
                    }
                }
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
    public static ArrayList<User> getFriends(User u){
        ArrayList<String> friend=u.getFriends();
        ArrayList<User> friendAccounts =new ArrayList<>();
        ArrayList<User> users=readAllFromFile();

        for(User i:users){
            for(String j:friend){
                if(i.getUserName().equals(j)){
                    friendAccounts.add(i);
                }
            }
        }
        return friendAccounts;
    }
    public static void removeFriend(String remover,String removee){
        ArrayList<User> users=readAllFromFile();
        for(User u:users){
            if(u.getUserName().equals(remover)){
                u.getFriends().remove(removee);
            }
            if(u.getUserName().equals(removee)){
                u.getFriends().remove(remover);
            }
        }
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.ser"));
            for(int i=0;i<users.size();i++){
                oos.writeObject(users.get(i));
            }
            oos.close();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
    }
}
