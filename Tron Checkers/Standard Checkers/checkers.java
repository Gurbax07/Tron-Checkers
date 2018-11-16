import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;


public class checkers extends Applet implements ActionListener
{
    int row = 8;
    int col = 8;
    int pic [] [] = {{1, 3, 1, 3, 1, 3, 1, 3}, {3, 1, 3, 1, 3, 1, 3, 1}, {1, 3, 1, 3, 1, 3, 1, 3}, {2, 1, 2, 1, 2, 1, 2, 1}, {1, 2, 1, 2, 1, 2, 1, 2}, {4, 1, 4, 1, 4, 1, 4, 1}, {1, 4, 1, 4, 1, 4, 1, 4}, {4, 1, 4, 1, 4, 1, 4, 1}};
    JButton a[] = new JButton [row * col];
    char f = 'l';
    char w = 'z';

    public void init ()
    {

	Panel g = new Panel (new GridLayout (8, 8));
	for (int i = 0 ; i < a.length ; i++)
	{
	    a [i] = new JButton (createImageIcon ("b1.png"));
	    a [i].addActionListener (this);
	    a [i].setActionCommand ("" + i);
	    a [i].setPreferredSize (new Dimension (60, 60));
	    g.add (a [i]);
	}
	add (g);
	redraw ();

    }


    public void redraw ()
    {
	int move = 0;
	for (int i = 0 ; i < 8 ; i++)
	{
	    for (int j = 0 ; j < 8 ; j++)
	    {
		a [move].setIcon (createImageIcon ("b" + pic [i] [j] + ".png"));
		move++;
	    }
	}
    }


    public void actionPerformed (ActionEvent e)
    {
	if (w == 'z')
	{
	    int n = Integer.parseInt (e.getActionCommand ());
	    a [n].setIcon (createImageIcon ("b2.png"));
	    w = 'x';
	}
	else
	{

	    int n = Integer.parseInt (e.getActionCommand ());
	    int z = n / row;
	    int d = n % row;
	    showStatus ("(" + z + ", " + d + ")");

	    if (f == 'l')
	    {
		showStatus ("Red turn");
		a [n].setIcon (createImageIcon ("b3.png"));
		f = 'w';
	    }
	    else
	    {
		showStatus ("White turn");
		a [n].setIcon (createImageIcon ("b4.png"));
		f = 'l';
	    }
	    w = 'z';

	}

    }


    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = checkers.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}


	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}


