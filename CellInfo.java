package alignstuff;

public class CellInfo 
{
	private Direction compass;
	private int value;
	private char ch;
	
	public CellInfo(int v, Direction d)
	{
		compass = d;
		value = v;
		ch = '0';
	}
	
	public CellInfo(char c)
	{
		compass = null;
		value = 0;
		ch = c;
	}
	
	public char getChar()
	{return ch;}
	
	public int getValue()
	{return value;}
	
	public Direction getDirection()
	{return compass;}
	

}
