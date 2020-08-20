package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CalcutateDemo extends JFrame implements ActionListener{
    SoNguyen soNguyen = new SoNguyen();
    String result;
    private JButton btnOK;
    private JTextField tf1, tf2;
    /*DĂ¹ng Ä‘á»ƒ nháº­n táº§ng ContentPane cá»§a JFrame*/
    private Container container;

    private JPanel panel1, panel2;

    /*HĂ m khá»Ÿi táº¡o vá»›i má»™t tham sá»‘ lĂ  String s*/
    public CalcutateDemo(String s) {
        super(s);
        /*Láº¥y lá»›p ContentPane Ä‘á»ƒ Ä‘áº·t cĂ¡c Ä‘á»‘i tÆ°á»£ng hiá»ƒn thá»‹*/
        container = this.getContentPane();

        /*Táº¡o cĂ¡c thĂ nh pháº§n trĂªn giao diá»‡n*/
        JLabel field1 = new JLabel("Nhập biểu thức");
        tf1 = new JTextField();
        JLabel field2 = new JLabel("Kết quả ");
        tf2 = new JTextField();
        tf2.setEditable(false);


        /*Panel1 chá»©a 3 Textfield*/
        panel1 = new JPanel();
        /*thiáº¿t láº­p Layout gá»“m 2 hĂ ng 2 cá»™t*/
        panel1.setLayout(new GridLayout(2,2));
        /*Ä�áº·t cĂ¡c thĂ nh pháº§n vĂ o panel 1*/
        panel1.add(field1);
        panel1.add(tf1);
        panel1.add(field2);
        panel1.add(tf2);
        /*Táº¡o 4 nĂºt biá»ƒu diá»…n 4 phĂ©p toĂ¡n*/
        btnOK = new JButton("OK");
        /*Panel2 chá»©a 4 nĂºt*/
        panel2 = new JPanel();
        panel2.add(btnOK);

        /*Ä�áº·t 2 panel vĂ o ContentPane*/
        container.add(panel1);
        container.add(panel2,"South");

        btnOK.addActionListener(this);

        /*Thiáº¿t láº­p kĂ­ch thÆ°á»›c hiá»ƒn thá»‹*/
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }


    /*Báº¯t Ä‘áº§u tĂ­nh toĂ¡n khi ngÆ°á»�i dĂ¹ng áº¥n cĂ¡c nĂºt phĂ©p toĂ¡n*/
    public void actionPerformed(ActionEvent e)
    {
            //Xá»­ lĂ½ trÆ°á»�ng há»£p chÆ°a nháº­p Biá»ƒu thá»©c nhÆ°ng Ä‘Ă£ báº¥m tĂ­nh toĂ¡n
            if(tf1.getText().equals("") ) {
                JOptionPane.showMessageDialog(this, "Báº¡n chÆ°a nháº­p Ä‘á»§");
            }else{
                result=tf1.getText();
                soNguyen.setMathExpression(result);
                String[] elementMath = soNguyen.processString(soNguyen.getMathExpression());
                elementMath = soNguyen.postfix(elementMath);     //  dua cac phan tu ve dang hau to
                tf2.setText(soNguyen.valueMath(elementMath));
        }
    }

    public static void main(String[] args) {
        CalcutateDemo cal = new CalcutateDemo("SimpleCalculator");
        cal.setLocationRelativeTo(null);
        cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
