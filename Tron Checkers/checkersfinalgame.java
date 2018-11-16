/*
Name: Gurbax Barhe

Game: Checkers (both Single and Two Player)
Theme: Tron

Coding Sections that have not been completed or game bugs as of April 13th, 2017.
-Click driectly on the picture buttons because weird sizing is weird.
-Please remember to download fonts unless you want a whack looking game. Sorry not sorry.
-Cannot jump consecutively.
-Single player AI is very much work in progress.
    -The single player screen grid has no restrictions nor can the pieces move, therefore the game cannot be played on single player.
-You can jump over a piece even if there is a piece behind it on the other side. *Will be fixed one day after submission date with assistance from Ms.Gorski.


Link to Pictures Used:
1)http://pa1.narvii.com/5794/6e785885c912a51be38e716278cc58fea8b6c583_hq.gif
2)http://imgs.abduzeedo.com/files/tutorials/Tron_Logo_Tutorial/Step_22.jpg
3)http://vignette2.wikia.nocookie.net/disney/images/1/1d/Tron2.jpg/revision/latest?cb=20130816205701
4)http://orig05.deviantart.net/3e8b/f/2011/292/9/9/tron_grid_wallpaper_1680x1050_by_sarah_hextall_design-d4dajzv.jpg
5)http://i101.photobucket.com/albums/m45/MDFSpacePhantom/Renders/tRON/Tron-Legacy-2-2.png
6)http://payload.cargocollective.com/1/0/2995/1190820/061.TRONOT.18b_o.JPG
7)http://t04.deviantart.net/vQ7C3VUr8l90L5K8BFDzQROozkk=/300x200/filters:fixed_height(100,100):origin()/pre03/537a/th/pre/i/2010/145/1/3/lightcycle_tron_legacy_by_frlegolas.png
8)http://i470.photobucket.com/albums/rr62/DarkTitanUltima/Renders/Tron-Legacy-3-2.png

Music Source:
9)https://www.youtube.com/watch?v=9UbINHfPOZo
*/

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class checkersfinalgame extends Applet implements ActionListener
{
    Panel p_screen; //To hold all of the screens
    Panel Homescreen, loading, SinglePlayer, TwoPlayer, Rules, Credits; //List of all the screens that will  be utilized for the final game.
    CardLayout cdLayout = new CardLayout ();
    //Dimensions for game board (8x8)
    int row = 8;
    int col = 8;
    //The array that contains both the pices and grid squares.
    int pic[] [] = {{1, 3, 1, 3, 1, 3, 1, 3}, {3, 1, 3, 1, 3, 1, 3, 1}, {1, 3, 1, 3, 1, 3, 1, 3}, {2, 1, 2, 1, 2, 1, 2, 1}, {1, 2, 1, 2, 1, 2, 1, 2}, {4, 1, 4, 1, 4, 1, 4, 1}, {1, 4, 1, 4, 1, 4, 1, 4}, {4, 1, 4, 1, 4, 1, 4, 1}};
    JButton a[] = new JButton [row * col];
    AudioClip soundFile; //Declares the soundfile.
    int last = -1; //For positioning.
    char turn = 'o'; //Turns. Initially set to orange because orange always goes first.
    //Location of the Piece: O refers to orange, and B refers to blue.
    char piece[] [] = {
	    {'b', ' ', 'b', ' ', 'b', ' ', 'b', ' '},
	    {' ', 'b', ' ', 'b', ' ', 'b', ' ', 'b'},
	    {'b', ' ', 'b', ' ', 'b', ' ', 'b', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	    {'o', ' ', 'o', ' ', 'o', ' ', 'o', ' '},
	    {' ', 'o', ' ', 'o', ' ', 'o', ' ', 'o'},
	    {'o', ' ', 'o', ' ', 'o', ' ', 'o', ' '}};

    //The all encompassing god - INIT!1!1
    public void init ()
    {
	p_screen = new Panel ();
	p_screen.setLayout (cdLayout);
	resize (650, 650); //All gamescreen dimensions - 650 pixels by 650 pixels.
	setBackground (new Color (182, 213, 216));
	soundFile = getAudioClip (getDocumentBase (), "trongamemusic.wav");
	//This attaches the sound file "trongamemusic"
	soundFile.loop ();
	//Puts the sound on infinite loop; continues to repeat.
	Homescreen (); //Screen One
	SinglePlayer (); //Screen Two
	TwoPlayer (); //Screen Three
	Rules (); //Screen Four
	Credits (); //Screen Five
	setLayout (new BorderLayout ()); //Sets it all up.
	add ("Center", p_screen); //Adds all the screens.

    }


    public void Homescreen ()
    { //Screen 1 - The Homescreen. Will include options such as playing the game itself: a two player and single player option, a display of checkers rules and regulations, game credits, etc.
	Panel Homescreen = new Panel (); //Declaring the Homescreen panel.

	//The JLabels below are parts of the Homescreen which has been split up into pictures. A portion of the pictures have been assigned as buttons. These pictures were made in Adobe Photoshop CS6, a seperate program, in order to make the overall game look more visually appealing.
	JLabel homel1 = new JLabel (createImageIcon ("loading1.png"));
	JLabel homel2 = new JLabel (createImageIcon ("loading2.png"));
	JLabel homel3 = new JLabel (createImageIcon ("loading3.png"));
	JButton singlescreen = new JButton (createImageIcon ("loading4.png"));
	singlescreen.setActionCommand ("-2");
	singlescreen.addActionListener (this);
	singlescreen.setBorder (null);
	JButton twoplayer = new JButton (createImageIcon ("loading5.png"));
	twoplayer.setActionCommand ("-3");
	twoplayer.addActionListener (this);
	twoplayer.setBorder (null);
	JButton rules = new JButton (createImageIcon ("loading6.png"));
	rules.setActionCommand ("-4");
	rules.addActionListener (this);
	rules.setBorder (null);
	JButton credits = new JButton (createImageIcon ("loading7.png"));
	credits.setActionCommand ("-5");
	credits.addActionListener (this);
	credits.setBorder (null);
	JLabel homel8 = new JLabel (createImageIcon ("loading8.png"));

	Panel home = new Panel (new GridLayout (8, 1));
	home.add (homel1);
	home.add (homel2);
	home.add (homel3);
	home.add (singlescreen);
	home.add (twoplayer);
	home.add (rules);
	home.add (credits);
	home.add (homel8);
	Homescreen.add (home);
	p_screen.add ("1", Homescreen); //Adding the screen. Called as 1.
    }


    public void SinglePlayer ()
    { //Screen 2 - Single Player Option. Users will battle it out with a simpele AI. *Not yet completed - does not work.
	SinglePlayer = new Panel ();
	SinglePlayer.setBackground (new Color (64, 64, 64));

	//The JLabels below are parts of the SinglePlayer screen which has been split up into pictures. A portion of the pictures have been assigned as buttons. These pictures were made in Adobe Photoshop CS6, a seperate program, in order to make the overall game look more visually appealing.
	JLabel title1 = new JLabel ("Single Player");
	title1.setFont (new Font ("TR2N", Font.PLAIN, 80));
	title1.setForeground (Color.cyan);
	SinglePlayer.add (title1);

	JLabel movefirst1 = new JLabel ("CLICK ON A DISC, THEN CLICK ON AN EMTPY DIAGONAL SQUARE TO MOVE. ORANGE TO GO FIRST.");
	movefirst1.setFont (new Font ("Calibri", Font.PLAIN, 15));
	movefirst1.setForeground (Color.white);
	SinglePlayer.add (movefirst1);

	JButton back1 = new JButton (" Main Menu ");
	back1.setFont (new Font ("BigNoodleTitling", Font.PLAIN, 35));
	back1.setForeground (Color.cyan);
	back1.setBackground (Color.black);
	back1.setBorder (BorderFactory.createLineBorder (Color.white));
	back1.addActionListener (this);
	back1.setActionCommand ("-1");

	JButton rulescreen1 = new JButton (" Game Rules ");
	rulescreen1.setFont (new Font ("BigNoodleTitling", Font.PLAIN, 35));
	rulescreen1.setForeground (Color.cyan);
	rulescreen1.setBackground (Color.black);
	rulescreen1.setBorder (BorderFactory.createLineBorder (Color.white));
	rulescreen1.addActionListener (this);
	rulescreen1.setActionCommand ("-4");

	JButton credits1 = new JButton (" Game Credits ");
	credits1.setFont (new Font ("BigNoodleTitling", Font.PLAIN, 35));
	credits1.setForeground (Color.cyan);
	credits1.setBackground (Color.black);
	credits1.setBorder (BorderFactory.createLineBorder (Color.white));
	credits1.addActionListener (this);
	credits1.setActionCommand ("-5");

	//The checkers grid for single player.
	Panel gameboard1 = new Panel (new GridLayout (8, 8)); //Dimensions are 8x8.
	int count = 0;
	for (int i = 0 ; i < row ; i++) //Gotta make them loops tho.
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [count] = new JButton (createImageIcon ("sb1.png"));
		a [count].addActionListener (this);
		a [count].setActionCommand ("" + count);
		a [count].setPreferredSize (new Dimension (60, 60)); //Grid piece dimensions are 60 pixels x 60 pixels.
		a [count].setBorder (BorderFactory.createLineBorder (Color.cyan)); //Sets the border of each piece to cyan. Again, increasing the visual aspect.
		gameboard1.add (a [count]);
		count++;
	    }
	}

	//Grid-Piece Restrictions. These work flawlessly!
	for (int j = 0 ; j < 8 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 9 ; j < 17 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 16 ; j < 24 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 25 ; j < 33 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 32 ; j < 40 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 41 ; j < 49 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 48 ; j < 56 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 57 ; j < 64 ; j += 2)
	    a [j].setEnabled (false);

	//Below are added all the components coded above.
	SinglePlayer.add (gameboard1);

	Panel singleplayerscreenbuttons = new Panel (new GridLayout (1, 1));
	singleplayerscreenbuttons.add (back1);
	singleplayerscreenbuttons.add (rulescreen1);
	singleplayerscreenbuttons.add (credits1);
	SinglePlayer.add (singleplayerscreenbuttons);
	singleredraw ();
	p_screen.add ("2", SinglePlayer); //Adding the screen. Called as 2.
    }


    public void TwoPlayer ()
    { //Screen 3 - Two Player Option. Users will battle it out with another live oponent. *Double jump not fully completed.
	TwoPlayer = new Panel ();
	TwoPlayer.setBackground (new Color (64, 64, 64));

	//For more user-immersive experience.
	String orgname = JOptionPane.showInputDialog ("Initializing Orange Player ... Enter Name:");
	String bluename = JOptionPane.showInputDialog ("Initializing Blue Player ... Enter Name:");

	//The JLabels below are parts of the TwoPlayer screen which has been split up into pictures. A portion of the pictures have been assigned as buttons. These pictures were made in Adobe Photoshop CS6, a seperate program, in order to make the overall game look more visually appealing.
	JLabel title = new JLabel ("Two Player");
	title.setFont (new Font ("TR2N", Font.PLAIN, 87));
	title.setForeground (Color.cyan);
	TwoPlayer.add (title);

	JLabel movefirst = new JLabel ("CLICK ON A DISC, THEN CLICK ON AN EMTPY DIAGONAL SQUARE TO MOVE. ORANGE TO GO FIRST.");
	movefirst.setFont (new Font ("Calibri", Font.PLAIN, 15));
	movefirst.setForeground (Color.white);

	JButton back = new JButton (" Main Menu ");
	back.setFont (new Font ("BigNoodleTitling", Font.PLAIN, 35));
	back.setForeground (Color.cyan);
	back.setBackground (Color.black);
	back.setBorder (BorderFactory.createLineBorder (Color.white));
	back.addActionListener (this);
	back.setActionCommand ("-1");

	JButton reset = new JButton (" Reset ");
	reset.setFont (new Font ("BigNoodleTitling", Font.PLAIN, 35));
	reset.setForeground (Color.cyan);
	reset.setBackground (Color.black);
	reset.setBorder (BorderFactory.createLineBorder (Color.white));
	reset.addActionListener (this);
	reset.setActionCommand ("-6");

	JButton rulescreen = new JButton (" Game Rules ");
	rulescreen.setFont (new Font ("BigNoodleTitling", Font.PLAIN, 35));
	rulescreen.setForeground (Color.cyan);
	rulescreen.setBackground (Color.black);
	rulescreen.setBorder (BorderFactory.createLineBorder (Color.white));
	rulescreen.addActionListener (this);
	rulescreen.setActionCommand ("-4");

	JButton credits = new JButton (" Game Credits ");
	credits.setFont (new Font ("BigNoodleTitling", Font.PLAIN, 35));
	credits.setForeground (Color.cyan);
	credits.setBackground (Color.black);
	credits.setBorder (BorderFactory.createLineBorder (Color.white));
	credits.addActionListener (this);
	credits.setActionCommand ("-5");

	//The checkers grid for two player.
	Panel gameboard = new Panel (new GridLayout (8, 8)); //Dimensions are 8x8.
	int count = 0;
	for (int i = 0 ; i < row ; i++) //Gotta make them loops tho.
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [count] = new JButton (createImageIcon ("b1.png"));
		a [count].addActionListener (this);
		a [count].setActionCommand ("" + count);
		a [count].setPreferredSize (new Dimension (60, 60)); //Grid piece dimensions are 60 pixels x 60 pixels.
		a [count].setBorder (BorderFactory.createLineBorder (Color.cyan)); //Sets the border of each piece to cyan. Again, increasing the visual aspect.
		gameboard.add (a [count]);
		count++;
	    }
	}
	//Grid-Piece Restrictions. These work flawlessly!
	for (int j = 0 ; j < 8 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 9 ; j < 17 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 16 ; j < 24 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 25 ; j < 33 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 32 ; j < 40 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 41 ; j < 49 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 48 ; j < 56 ; j += 2)
	    a [j].setEnabled (false);
	for (int j = 57 ; j < 64 ; j += 2)
	    a [j].setEnabled (false);

	//Below are added all the components coded above.
	TwoPlayer.add (movefirst);
	TwoPlayer.add (gameboard);

	Panel twoplayerscreenbuttons = new Panel (new GridLayout (1, 1));
	twoplayerscreenbuttons.add (back);
	twoplayerscreenbuttons.add (reset);
	twoplayerscreenbuttons.add (rulescreen);
	twoplayerscreenbuttons.add (credits);
	TwoPlayer.add (twoplayerscreenbuttons);
	redraw ();
	p_screen.add ("3", TwoPlayer); //Adding the screen. Called as 3.
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


    public void singleredraw ()
    {
	int move = 0;
	for (int i = 0 ; i < 8 ; i++)
	{
	    for (int j = 0 ; j < 8 ; j++)
	    {
		a [move].setIcon (createImageIcon ("sb" + pic [i] [j] + ".png"));
		move++;
	    }
	}
    }


    public void Rules ()
    { //Screen 4 - Rules and regulations of the game will be displayed on this screen.
	Rules = new Panel (new GridLayout (5, 1));
	JLabel rule1 = new JLabel (createImageIcon ("rules1.png"));
	JLabel rule2 = new JLabel (createImageIcon ("rules2.png"));
	JLabel rule3 = new JLabel (createImageIcon ("rules3.png"));
	JLabel rule4 = new JLabel (createImageIcon ("rules4.png"));
	JButton mainmenu = new JButton (createImageIcon ("rules5.png"));
	mainmenu.setActionCommand ("-1");
	mainmenu.addActionListener (this);
	mainmenu.setBorder (null);
	Rules.add (rule1);
	Rules.add (rule2);
	Rules.add (rule3);
	Rules.add (rule4);
	Rules.add (mainmenu);
	p_screen.add ("4", Rules);
    }


    public void Credits ()
    { //Screen 5 - Game credits will be displayed on this screen.
	Credits = new Panel (new GridLayout (5, 1));
	JLabel credits1 = new JLabel (createImageIcon ("credits1.png"));
	JLabel credits2 = new JLabel (createImageIcon ("credits2.png"));
	JLabel credits3 = new JLabel (createImageIcon ("credits3.png"));
	JLabel credits4 = new JLabel (createImageIcon ("credits4.png"));
	JButton mainmenutwo = new JButton (createImageIcon ("credits5.png"));
	mainmenutwo.setActionCommand ("-1");
	mainmenutwo.addActionListener (this);
	mainmenutwo.setBorder (null);
	Credits.add (credits1);
	Credits.add (credits2);
	Credits.add (credits3);
	Credits.add (credits4);
	Credits.add (mainmenutwo);
	p_screen.add ("5", Credits);
    }


    public boolean canJump (int x1, int y1, int x2, int y2)
    {
	//King Code - consists of King's movements and crowning.
	if (piece [x2] [y2] == 'k' || piece [x2] [y2] == 'q')
	{
	    if (x2 - 2 == x1 && y2 - 2 == y1 && piece [x1] [y1] == ' ' && piece [x2 - 1] [y2 - 1] != ' ') //King code for upleft
	    {
		piece [x2 - 1] [y2 - 1] = ' ';
		pic [x2 - 1] [y2 - 1] = 2;
		return true;
	    }
	    else if (x2 + 2 == x1 && y2 + 2 == y1 && piece [x1] [y1] == ' ' && piece [x2 + 1] [y2 + 1] != ' ') //King code for downright
	    {
		piece [x2 + 1] [y2 + 1] = ' ';
		pic [x2 + 1] [y2 + 1] = 2;
		return true;
	    }
	    else if (x2 - 2 == x1 && y2 + 2 == y1 && piece [x1] [y1] == ' ' && piece [x2 - 1] [y2 + 1] != ' ') //King code for upright
	    {
		piece [x2 - 1] [y2 + 1] = ' ';
		pic [x2 - 1] [y2 + 1] = 2;
		return true;
	    }

	    else if (x2 + 2 == x1 && y2 - 2 == y1 && piece [x1] [y1] == ' ' && piece [x2 + 1] [y2 - 1] != ' ') //King code for downleft
	    {
		piece [x2 + 1] [y2 - 1] = ' ';
		pic [x2 + 1] [y2 - 1] = 2;
		return true;
	    }

	    else
		return false;
	}
	if (pic [x2] [y2] == 4) //Orange taking.
	{
	    if (x2 == x1 + 2 && ((y2 == y1 + 2 && piece [x1 + 1] [y1 + 1] == 'b') || (y2 == y1 - 2 && piece [x1 + 1] [y1 - 1] == 'b'))) //FIX
	    {
		if (y2 == y1 + 2)
		{
		    piece [x1 + 1] [y1 + 1] = ' ';
		    pic [x1 + 1] [y1 + 1] = 2;
		}
		else
		{
		    piece [x1 + 1] [y1 - 1] = ' ';
		    pic [x1 + 1] [y1 - 1] = 2;
		}

		return true;
	    }
	    else
		return false;
	}
	if (pic [x2] [y2] == 3) //Blue taking.
	{
	    if (x2 == x1 - 2 && ((y2 == y1 + 2 && piece [x1 - 1] [y1 + 1] == 'o') || (y2 == y1 - 2 && piece [x1 - 1] [y1 - 1] == 'o'))) //FIX
	    {
		if (y2 == y1 + 2)
		{
		    piece [x1 - 1] [y1 + 1] = ' ';
		    pic [x1 - 1] [y1 + 1] = 2;
		}
		else
		{
		    piece [x1 - 1] [y1 - 1] = ' ';
		    pic [x1 - 1] [y1 - 1] = 2;
		}

		return true;
	    }
	}
	return false;
    }


    public boolean canGo (int x1, int y1, int x2, int y2)
    {
	if (pic [x1] [y1] != 2)
	    return false;
	//King Code - consists of King's movements and crowning.
	if (piece [x2] [y2] == 'k' || piece [x2] [y2] == 'q')
	{
	    if ((x2 == x1 - 1 || x2 == x1 + 1) && (y2 == y1 + 1 || y2 == y1 - 1))
		return true;

	    else
		return false;
	}
	if (pic [x2] [y2] == 4) //Orange taking.
	{
	    if (x2 == x1 + 1 && (y2 == y1 + 1 || y2 == y1 - 1))
		return true;


	    else
		return false;
	}
	if (pic [x2] [y2] == 3) //Blue taking.
	{
	    if (x2 == x1 - 1 && (y2 == y1 + 1 || y2 == y1 - 1))
		return true;


	    else
		return false;
	}

	return false;
    }


    public void reset ()
    {
	char piecedefault[] [] = {
		{'b', ' ', 'b', ' ', 'b', ' ', 'b', ' '},
		{' ', 'b', ' ', 'b', ' ', 'b', ' ', 'b'},
		{'b', ' ', 'b', ' ', 'b', ' ', 'b', ' '},
		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
		{'o', ' ', 'o', ' ', 'o', ' ', 'o', ' '},
		{' ', 'o', ' ', 'o', ' ', 'o', ' ', 'o'},
		{'o', ' ', 'o', ' ', 'o', ' ', 'o', ' '}};
	int picdefault[] [] = {{1, 3, 1, 3, 1, 3, 1, 3}, {3, 1, 3, 1, 3, 1, 3, 1}, {1, 3, 1, 3, 1, 3, 1, 3}, {2, 1, 2, 1, 2, 1, 2, 1}, {1, 2, 1, 2, 1, 2, 1, 2}, {4, 1, 4, 1, 4, 1, 4, 1}, {1, 4, 1, 4, 1, 4, 1, 4}, {4, 1, 4, 1, 4, 1, 4, 1}};
	for (int i = 0 ; i < 8 ; i++)
	{
	    for (int j = 0 ; j < 8 ; j++)
	    {
		pic [i] [j] = picdefault [i] [j];
		piece [i] [j] = piecedefault [i] [j];
	    }
	}
	redraw ();
    }


    //Winning condition.
    public void haswon ()
    {
	boolean owon = true;
	boolean bwon = true;
	for (int i = 0 ; i < 8 ; i++)
	{
	    for (int j = 0 ; j < 8 ; j++)
	    {
		if (piece [i] [j] == 'b' || piece [i] [j] == 'q')
		    owon = false;
		else if (piece [i] [j] == 'o' || piece [i] [j] == 'k')
		    bwon = false;
	    }
	}


	if (bwon) //If blue player win.
	    JOptionPane.showMessageDialog (null, "Blue Wins!", "Congratulations Blue",
		    JOptionPane.WARNING_MESSAGE);

	else if (owon) //If orange player win.
	    JOptionPane.showMessageDialog (null, "Orange Wins!", "Congratulations Orange",
		    JOptionPane.WARNING_MESSAGE);
    }


    //The holy grail that makes my game work, godbless.
    public void actionPerformed (ActionEvent e)
    { //Code that moves between the screens
	int n = Integer.parseInt (e.getActionCommand ());
	if (n == -1)
	    cdLayout.show (p_screen, "1");
	else if (n == -2)
	    cdLayout.show (p_screen, "2");
	else if (n == -3)
	    cdLayout.show (p_screen, "3");
	else if (n == -4)
	    cdLayout.show (p_screen, "4");
	else if (n == -5)
	    cdLayout.show (p_screen, "5");
	else if (n == -6)
	    reset ();
	else //Grid Handling down below.
	{
	    int x1 = n / row;
	    int y1 = n % row;
	    if (last == -1)
	    {
		last = n;

		//Some of the comments below are for highlighting the clicked grid square, in order to make it easier for users to play with - Highlight Code.
		//Highlight Border Code - Highlight N on button array.
	    }
	    else
	    {
		int x2 = last / row;
		int y2 = last % row;
		//showStatus was utilzed because it is a cleaner method of providing instructions to the user.
		if (turn == 'o')
		{

		    showStatus ("Blue's Turn"); //Gives the user the intstruction that it is now the Blue side's turn.
		}
		else if (turn == 'b')
		{
		    showStatus ("Orange's Turn"); //Gives the user the intstruction that it is now the Orange side's turn.
		}

		//Highlight Border Code - Then dehighlt the last spot.
		//Move checking.
		if ((piece [x2] [y2] == 'b' && turn == 'o') || (piece [x2] [y2] == 'o' && turn == 'b'))

		    { //If wrong person moves, then this message displays, instructing the user on what to do.
			JOptionPane.showMessageDialog (null, "Wrong Player's Turn! Opposition's Move.", "Not Your Turn",
				JOptionPane.WARNING_MESSAGE);
		    }
		else
		{
		    if (canJump (x1, y1, x2, y2) || canGo (x1, y1, x2, y2))
		    {

			if (pic [x2] [y2] == 5) //King
			{
			    pic [x1] [y1] = 5;
			    piece [x1] [y1] = 'q';
			}
			else if (pic [x2] [y2] == 6) //King again
			{
			    pic [x1] [y1] = 6;
			    piece [x1] [y1] = 'k';
			}
			else if (pic [x2] [y2] == 4 && x1 == 0)
			{
			    pic [x1] [y1] = 6;
			    piece [x1] [y1] = 'k';
			}
			else if (pic [x2] [y2] == 4)
			{
			    pic [x1] [y1] = 4;
			    piece [x1] [y1] = 'o';
			}
			else if (piece [x2] [y2] == 'b' && x1 == 7) //Blue king, called Q for queen.
			{
			    piece [x1] [y1] = 'q';
			    pic [x1] [y1] = 5;
			}
			else
			{
			    pic [x1] [y1] = 3;
			    piece [x1] [y1] = 'b';
			}
			piece [x2] [y2] = ' ';
			pic [x2] [y2] = 2;

			if (turn == 'o')
			{ //Now B
			}
			else
			{ //Now O
			}
			turn = (turn == 'o') ? 'b':
			'o';

		    }
		}
		last = -1;
	    }
	}
	redraw ();
	haswon ();
    }


    //Standard required code for images. Finds image on computer. Otherwise reports if the file is missing.
    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = checkersfinalgame.class.getResource (path);
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
