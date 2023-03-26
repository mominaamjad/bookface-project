import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;

public class SignUpScreen extends JFrame {
    Color accent=new Color(24,119,242);
    Color accent2=new Color(81,180,5);
    Font f=new Font("Segoe UI",BOLD,52);
    Font f1=new Font("Arial",PLAIN,12);
    JPanel header,info,date,genderPanel;
    JTextField[] field=new JTextField[3];
    JLabel[] label=new JLabel[5];
    JLabel title;
    JButton signup;
    JButton back;
    JComboBox<String> day,month,year,gender;

    SignUpScreen(){
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1370,760);
        setLayout(null);
        setTitle("Bookface Sign up");
        MyActionListener a=new MyActionListener();

        title=new JLabel("bookface");
        title.setFont(f);
        title.setForeground(accent);
        header=new JPanel();
        header.setBounds(500,30,350,70);
        header.add(title);

        for(int i=0;i<3;i++){
            field[i]=new JTextField();
        }

        label[0]=new JLabel("Username");
        label[0].setBounds(20,20,300,20);
        field[0].setBounds(20,50,360,35);
        label[1]=new JLabel("Email");
        label[1].setBounds(20,90,300,20);
        field[1].setBounds(20,120,360,35);
        label[2]=new JLabel("Password");
        label[2].setBounds(20,160,300,20);
        field[2].setBounds(20,190,360,35);
        label[3]=new JLabel("Date of Birth");
        label[3].setBounds(20,230,300,20);
        label[4]=new JLabel("Gender");


        for(int i=0;i<5;i++)
            label[i].setFont(f1);

        info=new JPanel();
        info.setLayout(null);
        info.setBackground(Color.WHITE);
        info.setAlignmentY(100);
        info.setAlignmentX(100);
        info.setBounds(475,145,400,440);

        signup=new JButton("Sign up");
        signup.addActionListener(a);
        signup.setBackground(accent2);
        signup.setForeground(Color.WHITE);
        signup.setBounds(200,375,180,35);

        back=new JButton("Back");
        back.addActionListener(a);
        back.setBackground(Color.white);
        back.setForeground(accent);
        back.setBounds(20,375,180,35);

        String[] days=new String[31];
        for(int i=0;i<days.length;i++){
            days[i]=Integer.toString(i+1);
        }
        day = new JComboBox<String>(days);    // combo box for DAYS
        day.addActionListener(a);
        day.setBackground(Color.white);
        day.setFocusable(false);

        String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        month = new JComboBox<>(months);    // combo box for MONTHS
        month.addActionListener(a);
        month.setBackground(Color.WHITE);
        month.setFocusable(false);

        String[] years=new String[20];
        for(int i=0;i<years.length;i++){
            years[i]=Integer.toString(i+1987);
        }
        year = new JComboBox<>(years);    // combo box for YEARS
        year.addActionListener(a);
        year.setBackground(Color.WHITE);
        year.setFocusable(false);

        date=new JPanel(new GridLayout(1,3,15,15));
        date.setBounds(20,260,360,35);
        date.setBackground(Color.WHITE);
        date.add(day);
        date.add(month);
        date.add(year);

        String[] genders={"Female","Male","Do not specify"};
        gender = new JComboBox<>(genders);    // combo box for YEARS
        gender.addActionListener(a);
        gender.setBackground(Color.WHITE);
        gender.setFocusable(false);

        genderPanel=new JPanel(new GridLayout(1,2,15,15));
        genderPanel.setBackground(Color.white);
        genderPanel.setBounds(25,310,360,35);
        genderPanel.add(label[4]);
        genderPanel.add(gender);

        info.add(label[0]);
        info.add(field[0]);
        info.add(label[1]);
        info.add(field[1]);
        info.add(label[2]);
        info.add(field[2]);
        info.add(label[3]);
        info.add(date);
        info.add(genderPanel);
        info.add(signup);
        info.add(back);


        add(header);
        add(info);
    }
    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Sign up")) {
                if(!FileOperations.validUsername(field[0].getText())){
                    JOptionPane.showMessageDialog(new JFrame(),"Username unavailable");
                }
                else if(!field[1].getText().contains("@")){
                    JOptionPane.showMessageDialog(new JFrame(),"Invalid Email");
                }
                else{
                    Date d1=new Date((String) day.getSelectedItem(),(String) month.getSelectedItem(),(String) year.getSelectedItem());
                    User u=new User(field[0].getText(),field[1].getText(),field[2].getText(),d1,(String)gender.getSelectedItem());
                    FileOperations.userSignup(u);
                    JOptionPane.showMessageDialog(new JFrame(),"Sign up successful");
                    dispose();
                    LoginScreen ls=new LoginScreen();
                }

            }
            else if(e.getActionCommand().equals("Back")){
                dispose();
                LoginScreen ls=new LoginScreen();
            }
        }
    }
}
