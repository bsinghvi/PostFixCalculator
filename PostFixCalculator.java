import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PostFixCalculator extends JFrame implements ActionListener
{
	static JTextField number;
	JButton[] button = new JButton[17];
	static JTextField stringy;
	int counter=0;
	static String message = "Post-fix expression: ";
	static String line = " ";
	static int[] stax = new int[100]; 
	static int place =0;
	
	public static void main(String[] args) 
	{
		PostFixCalculator obj = new PostFixCalculator();
		obj.setVisible(true);
	}
	public PostFixCalculator()
	{
		super("Post-fix Calculator");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel northCanvas = new JPanel();
			northCanvas.setLayout(new GridLayout(2,2));
			number = new JTextField("0");
			northCanvas.add(number);
			stringy = new JTextField(message);
			northCanvas.add(stringy);
		add(northCanvas, BorderLayout.NORTH);
		JPanel westCanvas = new JPanel();
			westCanvas.setLayout(new GridLayout(5,3));
			JButton clear = new JButton("C");
			westCanvas.add(clear);
			clear.addActionListener(this);
			for(int i=1; i<=9;i++)
			{
				button[i]=new JButton(i+"");
				westCanvas.add(button[i]);
				button[i].addActionListener(this);
			}
			button[10] = new JButton("%");
			westCanvas.add(button[10]);
			button[10].addActionListener(this);
			button[11] = new JButton("Enter");
			westCanvas.add(button[11]);
			button[11].addActionListener(this);
		add(westCanvas, BorderLayout.WEST);
		JPanel eastCanvas = new JPanel();
			eastCanvas.setLayout(new GridLayout(5,1));
			button[12] = new JButton("+");
			button[13] = new JButton("-");
			button[14] = new JButton("*");
			button[15] = new JButton("/");
			button[16] = new JButton("=");
			for(int i=12;i<=16;i++)
			{
				eastCanvas.add(button[i]);
				button[i].addActionListener(this);
			}
		add(eastCanvas, BorderLayout.EAST);
	}
	public void actionPerformed(ActionEvent e) 
	{
		String select = e.getActionCommand();
		if(select.equals("Enter"))
		{
			if(counter!=3)
			{
				if(counter==0)
				{
					line = number.getText();
				}
				else
				{
					line = line + number.getText();
				}
				stringy.setText(message + line);
				counter=1;
			}	
		}
		if(select.equals("%"))
		{
			number.setText("%");
		}
		if(select.equals("0"))
		{
			number.setText("0");
		}
		if(select.equals("1"))
		{
			number.setText("1");
		}
		if(select.equals("2"))
		{
			number.setText("2");
		}
		if(select.equals("3"))
		{
			number.setText("3");
		}
		if(select.equals("4"))
		{
			number.setText("4");
		}
		if(select.equals("5"))
		{
			number.setText("5");
		}
		if(select.equals("6"))
		{
			number.setText("6");
		}
		if(select.equals("7"))
		{
			number.setText("7");
		}
		if(select.equals("8"))
		{
			number.setText("8");
		}
		if(select.equals("9"))
		{
			number.setText("9");
		}
		if(select.equals("+"))
		{
			number.setText("+");
		}
		if(select.equals("*"))
		{
			number.setText("*");
		}
		if(select.equals("-"))
		{
			number.setText("-");
		}
		if(select.equals("/"))
		{
			number.setText("/");
		}
		if(select.equals("="))
		{
			line = line.replaceAll("\\s+",""); // Remove all white space and assign to same
			line = line.trim();
			line = line.replaceAll("x", "*");
			stringy.setText(message + line);
			stax = new int[100]; 
			counter = 3;
			try
			{
				solve();
			}
			catch(Exception x)
			{
				number.setText("Expression not solvable!!!!");
			}
			//for(int i=0; i<17;i++){ button[i].setEnabled(false);}
			button[11].setEnabled(false);
			button[16].setEnabled(false);
			stringy.setEnabled(false);
		}	
		if(select.equals("C"))
		{
			number.setText("0");
			line = "";
			stringy.setText(message);
			counter=0;
			//for(int i=0; i<17;i++){button[i].setEnabled(true)}
			button[11].setEnabled(true);
			button[16].setEnabled(true);
			stringy.setEnabled(true);
		}
	}
	public static void solve()
	{
		String[] array = new String[line.length()];
		for(int i=0; i<line.length();i++)
		{
			array[i] = line.charAt(i)+"";
			if(array[i].equals("*"))
			{
				System.out.println(stax[1] + " * " + stax[0]);
				stax[0]=stax[0]*stax[1];
				pop();
			}
			else if(array[i].equals("-"))
			{
				System.out.println(stax[1] + " - " + stax[0]);
				stax[0]=stax[1]-stax[0];
				pop();
			}
			else if(array[i].equals("+"))	
			{
				System.out.println(stax[1] + " + " + stax[0]);
				stax[0]=stax[1]+stax[0];
				pop();
			}
			else if(array[i].equals("/"))
			{
				System.out.println(stax[1] + " / " + stax[0]);
				stax[0]=stax[1]/stax[0];
				pop();
			}
			else if(array[i].equals("%"))
			{
				System.out.println(stax[1] + " % " + stax[0]);
				stax[0]=stax[1]%stax[0];
				pop();
			}
			else
			{
				push(Integer.parseInt(array[i]+""));
			}
			System.out.println(stax[0]);
			for(int j=1; j<place;j++)
			{
				System.out.print("|" + stax[j]);
			}
			System.out.println();
		}
		number.setText(":) Final result: " + stax[0]+ " :)");
	}
	public static void push(int number)
	{
		for(int i=place; i>0; i--)
		{
			stax[i]=stax[i-1];
		}
		stax[0]=number;
		place++;
	}
	public static void pop()
	{
		for(int i=1; i<place; i++)
		{
			stax[i]=stax[i+1];
		}
		place--;
	}
}
