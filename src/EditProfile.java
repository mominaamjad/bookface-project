import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;

public class EditProfile extends JFrame {
    Color accent=new Color(24,119,242);
    Color accent2=new Color(81,180,5);
    Font f=new Font("Segoe UI",BOLD,50);

    User account;
    JPanel header,info,sub1,sub2,sub3,sub4;
    JTextField[] field=new JTextField[3];
    JLabel[] label=new JLabel[7];
    JLabel title;
    JComboBox<String> day,month,year,gender;
    JButton save;
    JButton back;

    EditProfile(User account) {
        this.account = account;
        MyActionListener a = new MyActionListener();

        setVisible(true);
        setTitle("Edit Profile");
        setSize(300,400);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        header = new JPanel();
        header.setBounds(30, 15, 400, 75);
        header.setBackground(Color.WHITE);
        title = new JLabel("bookface");
        title.setFont(f);
        title.setForeground(accent);
        header.add(title);

        info = new JPanel(new GridLayout(6, 2,5,10));
        info.setBounds(30, 120, 400, 300);
        info.setBackground(Color.WHITE);

        sub1 = new JPanel(new GridLayout(1, 2,5,5));
        sub1.setBackground(Color.WHITE);
        sub2 = new JPanel(new GridLayout(1, 2,5,5));
        sub2.setBackground(Color.WHITE);
        sub3 = new JPanel(new GridLayout(1, 2,5,5));
        sub3.setBackground(Color.WHITE);
        sub4 = new JPanel(new GridLayout(1, 2,5,5));
        sub4.setBackground(Color.WHITE);


        for (int i = 0; i < 3; i++) {
            field[i] = new JTextField();
        }

        label[0] = new JLabel("User Name");
        field[0].setText(account.getUserName());
        field[0].setEditable(false);
        label[1] = new JLabel("Email");
        field[1].setText(account.getEmail());
        label[2] = new JLabel("Password");
        field[2].setText(account.getPassword());
        label[3] = new JLabel("Day");
        label[4] = new JLabel("Month");
        label[5] = new JLabel("Year");
        label[6] = new JLabel("Gender");

        String[] days = new String[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = Integer.toString(i + 1);
        }
        day = new JComboBox<>(days);// combobox for DAYS
        day.setEditable(false);
        day.addActionListener(a);
        day.setFocusable(false);
        day.setBackground(Color.white);

        String[] months = new String[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = Integer.toString(i + 1);
        }
        month = new JComboBox<>(months);    // combo box for MONTHS
        month.addActionListener(a);
        month.setFocusable(false);
        month.setBackground(Color.white);

        String[] years = new String[20];
        for (int i = 0; i < years.length; i++) {
            years[i] = Integer.toString(i + 1987);
        }
        year = new JComboBox<>(years);    // combo box for YEARS
        year.addActionListener(a);
        year.setFocusable(false);
        year.setBackground(Color.white);

        String[] genders = {"Female","Male","Do Not Specify"};
        gender = new JComboBox<>(genders);
        gender.addActionListener(a);
        gender.setFocusable(false);
        gender.setBackground(Color.white);

        save = new JButton("Save");
        save.setForeground(Color.WHITE);
        save.setBackground(accent2);
        save.addActionListener(a);

        back = new JButton("Back");
        back.setForeground(Color.WHITE);
        back.setBackground(accent2);
        back.addActionListener(a);

        for (int i = 0; i < 3; i++) {
            info.add(label[i]);
            info.add(field[i]);
        }
        sub1.add(label[3]);
        sub1.add(label[4]);
        sub3.add(day);
        sub3.add(month);

        sub2.add(label[5]);
        sub2.add(label[6]);
        sub4.add(year);
        sub4.add(gender);

        info.add(sub1);
        info.add(sub2);
        info.add(sub3);
        info.add(sub4);

        info.add(save);
        info.add(back);

        add(header,BorderLayout.NORTH);
        add(info,BorderLayout.CENTER);
    }
    public class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(save)){
                Date d1=new Date((String) day.getSelectedItem(),(String) month.getSelectedItem(),(String) year.getSelectedItem());
                User u=new User(field[0].getText(),field[1].getText(),field[2].getText(),d1,(String) gender.getSelectedItem());
                FileOperations.editProfile(u);
                JOptionPane.showMessageDialog(new JFrame(),"Edits saved!");
                dispose();
            }
            if(e.getSource().equals(back))
                dispose();
        }
    }
}
