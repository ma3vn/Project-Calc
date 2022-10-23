//Developed by Masoud Kazemi Aghdam==>970171336<==
package Project;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;//کتابخانه ها

class Calculator extends JFrame implements  ActionListener
{
    Container c ;
    JTextField result ;
    JPanel p = new JPanel();
    JButton b[] = new JButton[16];
    String s[] = {"0","1","2","3","4","5","6","7","8","9","+","-","/","*","=","C"};
    //------------------------------------------------
    String Screen="",monitor1="",monitor2="",OperationOnScreen="";
    boolean CommandEmpty=true,switcher=true;
    double R=Integer.MIN_VALUE,L=Integer.MIN_VALUE;
    //------------------------------------------------
    public Calculator ()
    {
        super ("Masoud Kazemi Aghdam | 970171336");

        c=getContentPane();
        result = new JTextField();
        result.setEditable(false);
        result.setBackground(Color.white);

        p.setLayout(new GridLayout(4,4));
        for (int i=0;i<16;i++)
        {
            b[i] = new JButton(s[i]);
            b[i].addActionListener(this);
            p.add(b[i]);
        }
        c.add(result,BorderLayout.NORTH);
        c.add(p);

        setSize(400,400);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }//پایان کانستراکتورها
    public static void main (String[] args)
    {
        new Calculator();
    }
    //------------------------------------------------
    public void actionPerformed(ActionEvent event)
    {
        for (int i=0; i<=9; i++)//اعداد
        {
            if(event.getSource()==b[i])
            {
                Screen+=i;
                result.setText("");
                result.setText(Screen);
            }
        }

        for (int i=10; i<=14; i++)
        {
            if(event.getSource()==b[i])
            {
                if(result.getText().lastIndexOf(OperationOnScreen)!=-1)//جلوگیری از (exception)
                    result.setText(result.getText().substring(0,result.getText().lastIndexOf(OperationOnScreen))+s[i]);
                else
                    result.setText(result.getText()+s[i]);
                OperationOnScreen=s[i];

                if(switcher)
                {monitor1=s[i];switcher=false;}
                else {monitor2=s[i];switcher=true;}

                if (monitor1!=monitor2 && monitor2!="")
                {
                    if(switcher) //execute older,send sign newer
                    {Calc(event,monitor1.charAt(0),monitor2); }
                    else {Calc(event,monitor2.charAt(0),monitor1); }
                }
                if(s[i]!="=") //ماشین حساب 0 را بر میگرداند
                    Calc(event,s[i].charAt(0),s[i]);
            }
        }

        if(event.getSource()==b[15]) //دستورات دکمه ی پاک کردن
        {
            Screen=""; monitor1=""; monitor2="";
            switcher=true; CommandEmpty=true;
            result.setText("");
        }
    }

    public void Calc(ActionEvent event,char OpType,String Operator)
    {		if (Operator=="=")
        Operator="";

        if(CommandEmpty && Screen=="")
        {
            return;
        }

        else if(CommandEmpty && Screen!="")
        {
            R=Integer.parseInt(Screen);
            result.setText(Screen+Operator);
            Screen="";
            CommandEmpty=false;
        }
        else if(!CommandEmpty && Screen!="")
        {
            L=Integer.parseInt(Screen);
            R=Operations(R,L,OpType);//محاسبه
            Screen="";
            result.setText("");
            result.setText(R+Operator);
        }
    }//پایان ماشین حساب

    public static double  Operations(double R, double L, char op)//عملیات های ماشین حساب
    {
        switch (op)
        {
            case '+':
                return R+L;
            case '-':
                return R-L;
            case '*':
                return R*L;
            case '/':
                return R/L;
        }
        return 0;
    }
}//پایان کلاس