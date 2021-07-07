import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*; 
/* <applet code="test" width=1 height=1 >
<param name = "0.jpg" value = "0.png">
<param name = "1.jpg" value = "1.png">
<param name = "2.jpg" value = "2.png">
<param name = "3.jpg" value = "3.png">
<param name = "4.jpg" value = "4.png">
<param name = "5.jpg" value = "5.png">
<param name = "6.jpg" value = "6.png">
<param name = "7.jpg" value = "7.png">
<param name = "8.jpg" value = "8.png">
<param name = "9.jpg" value = "9.png">
</applet>*/

public class captcha2 extends Applet implements ActionListener
{
    //Declare the variables 
    String msg="";
    Button submit,change;
    TextField inputField;
    //Image img1,img2,img3,img4,img5;
	int n=5,condition;
	int arr_num[]=new int[5];
	int choice[]={1,2,3};
	String shapes[]= new String[n];
	String find_val;
	Vector<Integer> result = new Vector<Integer>();
	String input,ans="",statement,option1="Enter the numbers from left to right inside a circle",option2="Enter the numbers from left to right inside a triangle",option3="Enter the numbers from left to right inside a square";
	String options[]={option1,option2,option3};
	Random rand = new Random();
    HashMap<Integer, String> image = new HashMap<Integer, String>();
	
    
    //generator function for variation in captcha
    public void generator()
    {
    	//create random set of numbers 
    	for(int i=0;i<n;i++)
            arr_num[i]=rand.nextInt(10);
        
        //create hashmap for images database    
        for(int i=0;i<10;i++)
            image.put(i,Integer.toString(i)+".jpg");
       
        
        //generate random shapes for corresponding image
        for(int i=0;i<n;i++)
        {
            int pos=choice[rand.nextInt(3)];
            if(pos==1)
                shapes[i]="c";
            else if (pos==2)
                shapes[i]="t";
            else
                shapes[i]="s";
        }
        
        //condition for final answer
        condition=rand.nextInt(n);
        find_val=shapes[condition];
        if(find_val.equals("c"))
            statement=options[0];
        else if(find_val.equals("t"))
            statement=options[1];
        else
            statement=options[2];
        
        //find the final answer according to the condition    
        for(int i=0;i<n;i++)
            if(shapes[i]==find_val)
                result.add(arr_num[i]);
        //System.out.println(result);
        
        //convert the vector answer into string to match with input
        if(result.size()!=0)
            for(int i=0;i<result.size();i++)
                ans+=Integer.toString(result.get(i));
        
        //store the images for the digits in our Sequence of numbers
        /*img1 = getImage(getCodeBase(),image.get(arr_num[0]));
        img2 = getImage(getCodeBase(),image.get(arr_num[1]));
        img3 = getImage(getCodeBase(),image.get(arr_num[2]));
        img4 = getImage(getCodeBase(),image.get(arr_num[3]));
        img5 = getImage(getCodeBase(),image.get(arr_num[4]));*/
    }
    
    public void init()
    {
        setLayout(null);    //setting the layout 
        inputField = new TextField("",5);
        submit = new Button("SUBMIT");
        add(inputField);     //input box in the layout
        add(submit);    //submit button in the layout
        submit.addActionListener(this); //storing the click of button as event in applet
        inputField.setBounds(120,150,100,30);
        submit.setBounds(120,200,100,40);
        generator();   //call for new sequence of numbers
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String str=inputField.getText();
        //verify the input with the correct answer
        if(str.equals(ans))
        {
            msg="VERIFIED";     //valid msg
            repaint(); //repeat the process
        }
        else
        {
            msg="TRY AGAIN";    //invalid msg 
            ans="";             //empties ans for the next captcha
            result.clear();     //clear the result vector for next captcha
            generator();        //call generator for new sequence of numbers 
            repaint();  //repeat process
        }
    }
        
    public void paint(Graphics g)
    {
        g.drawString(statement,40,125);      //Instruction for the captcha to the layout
        g.drawString(msg,140,300);      //Validation message to be displayed after the taking input
        //For every digit display corresponding image and corresponding circle of the respective colour
        /*g.drawImage(img1, 40,20,50,75,this);
        g.setColor((rgb.get(shapes[0])));
        g.drawOval(40, 20, 48, 75);
        
        g.drawImage(img2, 92,20,50,75,this);
        g.setColor((rgb.get(shapes[1])));
        g.drawOval(92, 20, 48, 75);
        
        g.drawImage(img3, 142,20,50,75,this);
        g.setColor((rgb.get(shapes[2])));
        g.drawOval(142, 20, 48, 75);
        
        g.drawImage(img4, 192,20,50,75,this);
        g.setColor((rgb.get(shapes[3])));
        g.drawOval(192, 20, 48, 75);
        
        g.drawImage(img5, 242,20,50,75,this);
        g.setColor((rgb.get(shapes[4])));
        g.drawOval(242, 20, 48, 75);*/
        for(int i=0;i<n;i++)
	{
		int displace= (i==0)?13:15;
		g.drawImage(getImage(getCodeBase(),image.get(arr_num[i])), 40+(50*i)+displace,30,50,70,this);
		
		if(shapes[i].equals("s"))
			g.drawRect(40+(50*i)+displace, 30, 48, 65);
		else if(shapes[i].equals("t"))
		{
			g.drawPolygon(new int[] {37+(50*i)+displace, 50 + 50*(i)+25, 55+(50*(i+1))}, new int[] {90, 15, 90}, 3);
		}
		else 
			g.drawOval(40+(50*i)+displace, 25, 48, 70);
	}
        resize(350, 320);
    }
}