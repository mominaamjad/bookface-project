import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static java.awt.Font.BOLD;

public class MainScreen extends JFrame implements ItemListener {
    Color accent=new Color(24,119,242);
    Color accent2=new Color(81,180,5);
    User account;
    final static String HOME = "Home";
    final static String PROFILE = "Profile";
    final static String FRIENDS = "Friends";
    final static String REQUESTS = "Requests";
    final static String LOGOUT = "Logout";
    final static String BOOKFACE = "bookface";
    JLabel title;
    JPanel titlePanel, parentPanel;
    JPanel cards,homeCard,profileCard,friendCard,requestCard;
    JPanel sidePanel;
    JLabel welcome;
    JComboBox<String> options;
    JButton logout;

    //components for HOME CARD
    JLabel createPost;
    JTextField content;
    JButton post;
    JPanel friendPosts;
    JLabel[] friendUsername;
    JLabel[] friendContent;
    JButton[] friendLike;
    JButton[] friendComment;

    //components for PROFILE CARD
    JButton editProfile;
    JLabel postHeader;
    JPanel ownPosts;        //panel to display all posts
    JLabel[] selfPost;      //the content of post
    JPanel[] postInfo;     //panel for likes and comments
    JButton[] selfLikes;
    JButton[] selfComments;
    JButton[] selfEdit;

    //components for FRIENDS CARD
    JPanel friendHeader;
    JLabel friendTitle;
    JPanel viewFriends;
    JLabel[] usernames;
    JButton[] remove;
    JButton[] view;

    //components for REQUEST CARD
    JTextField searchUser;
    JButton searchUserButton;
    JLabel searchLabel,requestLabel;
    JPanel viewRequests;
    JLabel[] requests;
    JButton[] accept;
    JButton[] delete;
    JScrollPane scroll;

    MainScreen(User u){
        Font f=new Font("Segoe UI",BOLD,50);
        Font f1=new Font("Segoe UI",Font.BOLD,15);
        Font f3=new Font("Segoe UI",Font.PLAIN,15);
        Font f2=new Font("Calibri",Font.PLAIN,15);

        MyActionListener a=new MyActionListener();
        account=u;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1370,760);
        setLayout(new BorderLayout());
        setTitle(BOOKFACE);

        title=new JLabel(BOOKFACE);
        title.setFont(f);
        title.setForeground(Color.WHITE);
        titlePanel=new JPanel();
        titlePanel.setBackground(accent);
        titlePanel.add(title);

        welcome=new JLabel("*:･ﾟ✧* Welcome, "+account.getUserName()+" !  *:･ﾟ✧*");
        welcome.setForeground(accent);
        welcome.setBounds(15,60,200,30);

        String[] comboBoxItems = { HOME,PROFILE,FRIENDS,REQUESTS };
        options = new JComboBox<>(comboBoxItems);
        options.setEditable(false);
        options.setBackground(Color.white);
        options.setForeground(accent);
        options.addItemListener(this);
        options.setBounds(15,120,170,30);

        logout=new JButton(LOGOUT);
        logout.addActionListener(a);
        logout.setBackground(accent2);
        logout.setFont(f1);
        logout.setForeground(Color.WHITE);
        logout.setBounds(30,540,100,40);

        sidePanel =new JPanel(null);
        sidePanel.setBounds(0,0,280,580);
        sidePanel.add(welcome);
        sidePanel.add(options);
        sidePanel.add(logout);

        createPost=new JLabel("Share your thoughts...");    //label for new post text field
        createPost.setForeground(accent);
        createPost.setFont(f1);
        createPost.setBounds(100,20,300,30);
        content=new JTextField();                                // text field for new post
        content.setBounds(100,60,510,40);
        post=new JButton("Post");                           //button to post
        post.setBounds(620,60,80,40);
        post.setBackground(accent2);
        post.setForeground(Color.white);
        post.addActionListener(a);

        //HOME CARD: to share new post and to show posts of friends (like and comment on them)
        friendContent=new JLabel[8];
        friendLike =new JButton[8];
        friendComment =new JButton[8];
        friendUsername=new JLabel[8];
        ArrayList<User> friends =FileOperations.getFriends(account);
        int k=0;
        if(friends.size()>0){
            for(User friend:friends){
                ArrayList<Post> p1=friend.getPost();
                if(p1.size()>0){
                    for(Post p:p1) {
                        friendUsername[k]=new JLabel(friend.getUserName());
                        friendUsername[k].setFont(f1);
                        friendContent[k] = new JLabel(p.getContent());
                        friendContent[k].setFont(f2);
                        friendLike[k] = new JButton("❤");
                        friendLike[k].setBackground(Color.white);
                        friendLike[k].setForeground(Color.red);
                        friendLike[k].addActionListener(a);
                        friendComment[k] = new JButton("✉");
                        friendComment[k].setForeground(Color.WHITE);
                        friendComment[k].setBackground(accent2);
                        friendComment[k].addActionListener(a);
                        k++;
                        if(k>=8)
                            break;
                    }
                }
            }
        }

        friendPosts=new JPanel(new GridLayout(k*2,2,0,5));
        friendPosts.setBounds(50,140,700,k*70);
        friendPosts.setBackground(Color.WHITE);
        for(int i=0;i<k;i++) {
            friendPosts.add(friendUsername[i]);
            friendPosts.add(friendContent[i]);
            friendPosts.add(friendLike[i]);
            friendPosts.add(friendComment[i]);
        }
       /* scroll=new JScrollPane(friendPosts);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(50,140,700,400);*/
        homeCard=new JPanel(null);
        homeCard.add(new JLabel("home"));
        homeCard.setBackground(Color.WHITE);
        homeCard.add(createPost);
        homeCard.add(content);
        homeCard.add(post);
        homeCard.add(friendPosts);

        //PROFILE CARD: option to edit your own details and to view your own posts & comments on them
        editProfile=new JButton("Edit Profile");
        editProfile.setForeground(Color.WHITE);
        editProfile.setBackground(accent2);
        editProfile.setFont(f1);
        editProfile.setBounds(50,30,700,40);
        editProfile.addActionListener(a);

        postHeader=new JLabel("                                                                                  POSTS");
        postHeader.setForeground(accent);
        postHeader.setBackground(accent);
        postHeader.setFont(f1);
        postHeader.setBounds(50,80,700,40);

        selfPost=new JLabel[6];
        selfLikes =new JButton[6];
        selfComments =new JButton[6];
        selfEdit=new JButton[6];
        postInfo=new JPanel[6];
        int noOfPosts=0;
        ArrayList<Post> self=account.getPost();
        if(self.size()>0){
            for(int i=0;i<self.size() && i<6;i++){
                postInfo[i]=new JPanel(new GridLayout(1,3));
                selfPost[i]=new JLabel(self.get(i).getContent());

                selfLikes[i]=new JButton(self.get(i).getLike()+" ❤");
                selfLikes[i].setBackground(accent);
                selfLikes[i].setForeground(Color.WHITE);
                selfLikes[i].setFocusable(false);

                selfComments[i]=new JButton("✉");
                selfComments[i].setBackground(accent);
                selfComments[i].setForeground(Color.WHITE);
                selfComments[i].addActionListener(a);

                selfEdit[i]=new JButton("Edit");
                selfEdit[i].setBackground(accent);
                selfEdit[i].setForeground(Color.WHITE);
                selfEdit[i].addActionListener(a);

                noOfPosts++;
            }
        }
        ownPosts=new JPanel(new GridLayout(noOfPosts,2,0,5));
        ownPosts.setBounds(50,130,700,noOfPosts*50);
        ownPosts.setBackground(Color.WHITE);
        for(int i=0;i<noOfPosts;i++){
            ownPosts.add(selfPost[i]);
            postInfo[i].add(selfLikes[i]);
            postInfo[i].add(selfComments[i]);
            postInfo[i].add(selfEdit[i]);
            ownPosts.add(postInfo[i]);
        }
        profileCard=new JPanel(null);
        profileCard.setBackground(Color.WHITE);
        profileCard.add(editProfile);
        profileCard.add(postHeader);
        profileCard.add(ownPosts);

        //FRIEND CARD: show friends of user. option to view profiles or remove them


        friendTitle=new JLabel(FRIENDS);
        friendTitle.setForeground(Color.WHITE);
        friendTitle.setFont(f1);

        friendHeader=new JPanel();
        friendHeader.setBackground(accent);
        friendHeader.add(friendTitle);
        friendHeader.setBounds(50,30,700,40);

        usernames=new JLabel[8];
        remove=new JButton[8];
        view=new JButton[8];
        int countFren=0;
        ArrayList<String> fren=account.getFriends();
        if(fren.size()>0){
            for(int i=0;i<fren.size()&&i<8;i++){
                usernames[i]=new JLabel(fren.get(i));

                view[i]=new JButton("View");
                view[i].setBackground(accent2);
                view[i].setForeground(Color.WHITE);
                view[i].addActionListener(a);

                remove[i]=new JButton("Remove");
                remove[i].setBackground(accent2);
                remove[i].setForeground(Color.WHITE);
                remove[i].addActionListener(a);

                countFren++;
            }
        }
        viewFriends=new JPanel(new GridLayout(countFren,3,0,5));
        viewFriends.setBounds(60,90,680,countFren*40);
        viewFriends.setBackground(Color.white);
        for(int i=0;i<countFren;i++){
            viewFriends.add(usernames[i]);
            viewFriends.add(view[i]);
            viewFriends.add(remove[i]);
        }
        friendCard=new JPanel(null);
        friendCard.setBackground(Color.WHITE);
        friendCard.add(friendHeader);
        friendCard.add(viewFriends);

        // REQUEST CARD: search for users to send request and to view received requests
        searchLabel=new JLabel("Search an account");
        searchLabel.setBounds(100,20,300,30);
        searchLabel.setForeground(accent);
        searchLabel.setFont(f1);

        searchUser=new JTextField();
        searchUser.setBounds(100,60,510,40);

        searchUserButton=new JButton("Search");
        searchUserButton.setBounds(620,60,80,40);
        searchUserButton.setBackground(accent2);
        searchUserButton.setForeground(Color.white);
        searchUserButton.addActionListener(a);

        requestLabel=new JLabel(REQUESTS);
        requestLabel.setForeground(accent);
        requestLabel.setBounds(100,120,300,20);
        requestLabel.setFont(f1);

        requests=new JLabel[5];
        accept=new JButton[5];
        delete=new JButton[5];
        int count=0;
        ArrayList<String> currentReq=u.getRequest();
        if(currentReq.size()>0) {
            for (int i = 0; i < currentReq.size() && i < 5; i++) {
                requests[i] = new JLabel(currentReq.get(i));
                accept[i] = new JButton("Accept");
                accept[i].setBackground(accent2);
                accept[i].setForeground(Color.white);
                accept[i].addActionListener(a);
                delete[i] = new JButton("Delete");
                delete[i].setBackground(Color.red);
                delete[i].setForeground(Color.white);
                delete[i].addActionListener(a);
                count++;
            }
        }
        viewRequests=new JPanel(new GridLayout(count,3, 0,5));
        viewRequests.setBounds(100,150,600,count*40);
        viewRequests.setBackground(Color.WHITE);
        for(int i=0;i<count;i++) {
            viewRequests.add(requests[i]);
            viewRequests.add(accept[i]);
            viewRequests.add(delete[i]);
        }
        requestCard=new JPanel(null);
        requestCard.setBackground(Color.white);
        requestCard.add(searchLabel);
        requestCard.add(searchUser);
        requestCard.add(searchUserButton);
        requestCard.add(requestLabel);
        requestCard.add(viewRequests);

        // hold all the cards
        cards=new JPanel(new CardLayout());
        cards.add(homeCard,HOME);
        cards.add(profileCard,PROFILE);
        cards.add(friendCard,FRIENDS);
        cards.add(requestCard,REQUESTS);
        cards.setBounds(290,30,800,580);

        parentPanel=new JPanel(null);
       // parentPanel.setBackground(Color.white);
        parentPanel.add(sidePanel);
        parentPanel.add(cards);

        add(titlePanel,BorderLayout.NORTH);
        add(parentPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // events on homeCard
            if (e.getActionCommand().equals("Post")) {
                String c = content.getText();
                Post p = new Post(c);
                FileOperations.addPost(account.getUserName(),p);
                JOptionPane.showMessageDialog(new JFrame(),"Posted!");
                content.setText("");
            }
            for(int i=0;i<friendLike.length;i++){
                if(e.getSource().equals(friendLike[i])){

                    friendLike[i].setEnabled(false);
                    Post p=FileOperations.findPost(friendContent[i].getText());
                    FileOperations.likePost(friendUsername[i].getText(),p);
                }
            }
            for(int i=0;i<friendComment.length;i++){
                if(e.getSource().equals(friendComment[i])){
                    Post p=FileOperations.findPost(friendContent[i].getText());
                    AddComment ac=new AddComment(friendUsername[i].getText(),p);
                }
            }


            // events on profile card
            if(e.getSource().equals(editProfile)){
                EditProfile ep=new EditProfile(account);
            }
            for(int i = 0; i< selfComments.length; i++){
                if(e.getSource().equals(selfComments[i])){
                    String content= selfPost[i].getText();
                    Post p=FileOperations.findPost(content);
                    if(p!=null) {
                        ViewComments vc = new ViewComments(p);
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(),"whoops");
                    }
                }
            }
            for(int i=0;i< selfEdit.length;i++){
                if(e.getSource().equals(selfEdit[i])){
                    String content= selfPost[i].getText();
                    Post p=FileOperations.findPost(content);
                    if(p!=null) {
                        EditPost ep = new EditPost(account.getUserName(), p);
                    }
                }
            }

            for(int i=0;i< remove.length;i++){
                if(e.getSource().equals(remove[i])){
                    FileOperations.removeFriend(usernames[i].getText(),account.getUserName());
                    JOptionPane.showMessageDialog(new JFrame(),"Friend Removed :(");
                    remove[i].setEnabled(false);
                    view[i].setEnabled(false);
                }
            }

            for(int i=0;i< view.length;i++){
                if(e.getSource().equals(view[i])){
                    User u=FileOperations.searchUser(usernames[i].getText());
                    if(u!=null) {
                        ViewFriend vf = new ViewFriend(account, u);
                    }
                }
            }

            // events on request Card
            if(e.getSource().equals(searchUserButton)){
                User u1=FileOperations.searchUser(searchUser.getText());
                if(u1!=null){
                    ViewUser vu=new ViewUser(account,u1);
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"User not found");
                }
            }
            for (int i = 0; i < accept.length; i++) {
                if (e.getSource() == accept[i]) {
                    FileOperations.acceptRequest(account.getUserName(),requests[i].getText());
                    accept[i].setEnabled(false);
                    delete[i].setEnabled(false);
                    JOptionPane.showMessageDialog(new JFrame(),"You are now friends <3");
                }
            }
            for (int i = 0; i < delete.length; i++) {
                if (e.getSource() == delete[i]) {
                    FileOperations.deleteRequest(account.getUserName(),requests[i].getText());
                    accept[i].setEnabled(false);
                    delete[i].setEnabled(false);
                    JOptionPane.showMessageDialog(new JFrame(),"Request Deleted");

                }
            }

            // event by logout button
            if (e.getActionCommand().equals("Logout")) {
                JOptionPane.showMessageDialog(new JFrame(), "You Have Been Logged Out");
                dispose();
                LoginScreen ls = new LoginScreen();
            }
        }
    }
}
