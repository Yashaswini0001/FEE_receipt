package studentpack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class student{
    public student(){
        JFrame j = new JFrame("VIEW FEES DETAILS");

        j.setSize(580, 800);
        j.setLayout(new BorderLayout(0,50));
        String url="jdbc:mysql://localhost:3306";
        String uname="root";
        String paswd="5361";
        String databaseName="college";
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel l1 = new JLabel("Enter the registration number to search:");
        JTextField t1 = new JTextField(20);
        JButton b1 = new JButton("Search");
        panel.setBounds(0,0,580,30);

        l1.setPreferredSize(new Dimension(230,30));
        t1.setPreferredSize(new Dimension(200,30));
        panel.add(l1);
        panel.add(t1);
        panel.add(b1);

        BufferedImage logoImage = null;
        try {
            logoImage = ImageIO.read(new File("C:\\Users\\Tejaswini\\Desktop\\yashu\\images\\rit4.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        JLabel l2,l3,l4,l5,l6,l7,l8,l9,l10;
        l2=new JLabel("NAME:",JLabel.CENTER);
        l3=new JLabel("REGISTRATION No.:",JLabel.CENTER);
        l4 = new JLabel("DEPARTMENT:",JLabel.CENTER);
        l5 = new JLabel("REMAINING FEES:",JLabel.CENTER);
        l6 = new JLabel("SEMESTER:",JLabel.CENTER);
        l7 = new JLabel("D.O.B:",JLabel.CENTER);
        l8 = new JLabel("PHONE NUMBER:",JLabel.CENTER);
        l9 = new JLabel();
        l10 = new JLabel();

        JTextField t2,t3,t4,t5,t6,t7,t8;
        t2= new JTextField();
        t3= new JTextField();
        t4= new JTextField();
        t5= new JTextField();
        t6= new JTextField();
        t7= new JTextField();
        t8= new JTextField();

        JPanel panel1=new JPanel(new GridLayout(10,2,10,10));
        panel1.setBounds(0,200,580,700);
        panel1.add(l2);panel1.add(t2);panel1.add(l3);panel1.add(t3);panel1.add(l4);panel1.add(t4);panel1.add(l5);
        panel1.add(t5);panel1.add(l6);panel1.add(t6);panel1.add(l7);panel1.add(t7);panel1.add(l8);panel1.add(t8);
        panel1.add(l9);panel1.add(l10);

        JButton b2= new JButton("PRINT");
        JButton b3 = new JButton("BACK");
//        JPanel panel2=new JPanel(new GridLayout(1,2,10,10));
//        panel2.add(b2);panel2.add(b3);
        panel1.add(b3);
        panel1.add(b2);

        j.add(panel,BorderLayout.NORTH);
        j.add(panel1,BorderLayout.CENTER);
//        j.add(panel2,BorderLayout.SOUTH);
        BufferedImage finalLogoImage = logoImage;
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob();

                // Create a Printable object to define what to print
                Printable printable = new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if (pageIndex > 0) {
                            return Printable.NO_SUCH_PAGE;
                        }

                        Graphics2D g2d = (Graphics2D) graphics;
                        Graphics2D g2d1 = (Graphics2D) graphics;
                        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                        g2d1.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                        if (finalLogoImage != null) {
                            g2d.drawImage(finalLogoImage, 150, 50, null); // Adjust the position as needed
                        }


                        // Define the content to print with separate lines
                        g2d1.setFont(new Font("Monospaced", Font.BOLD, 20));
                        g2d.setFont(new Font("Monospaced", Font.PLAIN, 12));
                        int y = 50; // Starting Y position for text
                        y += (finalLogoImage != null) ? finalLogoImage.getHeight() + 20 : 0;
                        g2d1.drawString("Ramaiah Institue of technology",260,80);
                        g2d.drawString("M.S.R Nagar,Mathikere,Bengaluru,560054",240,100);
                        y+=40;
                        g2d.drawString("************************************************************",100,y);
                        y+=20;
                        g2d.drawString("Student Details", 275, y);
                        y += 20; // Move to the next line
                        g2d.drawString("************************************************************",100,y);
                        y+=20;
                        g2d.drawString("Name: " + t2.getText(), 150, y);
                        y += 20;

                        g2d.drawString("Registration No: " + t3.getText(), 150, y);
                        y += 20;

                        g2d.drawString("Department: " + t4.getText(), 150, y);
                        y += 20;


                        g2d.drawString("Semester: " + t6.getText(), 150, y);
                        y += 20;

                        g2d.drawString("D.O.B: " + t7.getText(), 150, y);
                        y += 20;

                        g2d.drawString("Phone Number: " + t8.getText(), 150, y);
                        y += 20;
                        g2d.drawString("************************************************************",100,y);
                        y+=20;
                        g2d.drawString("Fees Paid: " + t5.getText(), 360, y);
                        y += 20;
                        g2d.drawString("************************************************************",100,y);


                        return Printable.PAGE_EXISTS;
                    }
                };

                job.setPrintable(printable);

                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });



        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url,uname,paswd);
                    try (Statement st = con.createStatement()) {
                        String t11=t1.getText();
                        String q1="USE "+databaseName+";";
                        String q2="select * from student1 where Register_No="+t11+";";
                        st.execute(q1);
                        ResultSet rs=st.executeQuery(q2);
                        int columncount=rs.getMetaData().getColumnCount();
                        Object[] row=new Object[columncount];
                        if(rs.next()){
                            for(int i=1;i<=columncount;i++){
                                row[i-1]=rs.getObject(i);
                            }
                        }
                        t2.setText((String) row[0]);
                        t3.setText((String) row[1]);
                        t4.setText((String) row[2]);
                        t5.setText((String) row[3]);
                        t6.setText((String) row[4]);
                        t7.setText((String) row[5]);
                        t8.setText((String) row[6]);
                        t2.setEditable(false);
                        t3.setEditable(false);
                        t4.setEditable(false);
                        t5.setEditable(false);
                        t6.setEditable(false);
                        t7.setEditable(false);
                        t8.setEditable(false);

                        // String query3 = "CREATE TABLE IF NOT EXISTS student1 (Name VARCHAR(50), Register_No INT PRIMARY KEY, Department VARCHAR(50) NOT NULL, Remaining_amount INT NOT NULL, Semester VARCHAR(50) NOT NULL, City VARCHAR(50) NOT NULL, Phone_No VARCHAR(15) NOT NULL);";
                        //String query3 = "CREATE TABLE IF NOT EXISTS student1 (Name VARCHAR(50), Register_No INT PRIMARY KEY, Department VARCHAR(50) , Remaining_amount INT NOT NULL, Semester VARCHAR(50) NOT NULL, City VARCHAR(50) NOT NULL, Phone_No VARCHAR(15) NOT NULL);";
                        //model.addRow(new Object[]{t11,t12,t13,t14,t15,t16,t17});
                        //departmentComboBox.setSelectedItem("Select Department");

                        st.executeUpdate(q1);

                        System.out.print(columncount + " columns");
                    }
                    con.close();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        j.setVisible(true);
        j.setLocationRelativeTo(null);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.dispose();
            }
        });


    }

    public static void main(String args[]){
        student s= new student();
    }
}