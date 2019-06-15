package alignstuff;

public class Align 
{
	StringBuilder b1;
	StringBuilder b2;
	
	public Align()
	{
		b1 = new StringBuilder();
		b2 = new StringBuilder();
	}
	
	public int globalAlign(String s1, String s2)
	{
		String target1 = s1;
		String target2 = s2;
		//int bigTargetLength = Math.max(target1.length(), target2.length());
		//int lengthDifference = Math.max(bigTargetLength - target1.length(), bigTargetLength - target2.length());
		CellInfo[][] multi = new CellInfo[target2.length()+2][target1.length()+2];
		for(int i = 0; i<target1.length(); i++) //sets a string into the first row horizontally
		{
			multi[0][i+2] = new CellInfo(target1.charAt(i));
			multi[1][i+2] = new CellInfo(((-2*i)-2), Direction.LEFT);
		}
		
		multi[1][1] = new CellInfo(0,Direction.UP);
		for(int r = 0; r<target2.length(); r++) //set a string into the first column vertically
		{
			multi[r+2][0] = new CellInfo(target2.charAt(r));
			multi[r+2][1] = new CellInfo(((-2*r)-2), Direction.UP);
		}
		
		for(int i=2; i<=target1.length()+1; i++) //out loop filling array
		{
			for(int r=2; r<=target2.length()+1; r++) //inner loop filling array
			{

				int match = -1;
				if(multi[r][0].getChar() == multi[0][i].getChar())
					match = 1;
				int numDiag = multi[r-1][i-1].getValue() + match;
				int numUp = multi[r-1][i].getValue() - 2;
				int numLeft = multi[r][i-1].getValue() - 2;
				int winner = Math.max(Math.max(numDiag, numUp), numLeft);
				Direction d = Direction.UP;
				if(winner == numUp)
				{
					d = Direction.UP;
					if(winner == numDiag)
						d = Direction.UPD;
				}
				if(winner == numLeft)
				{
					d = Direction.LEFT;
					if(winner == numDiag)
						d = Direction.LEFTD;
				}
				if(winner == numDiag)
				{
					d = Direction.DIAG;
					if(winner == numUp && winner == numLeft)
						d = Direction.LDU;
				}
				multi[r][i] = new CellInfo(winner, d);
				System.out.print(winner+" "); //print out array
			}
			System.out.println();
		}

		int score = this.GlobalRecurse(multi, (1+target2.length()), (1+target1.length()), multi[1+target2.length()][1+target1.length()].getValue());
		return score;
		
	}
	
	public int GlobalRecurse(CellInfo[][] a, int r, int i, int value)
	{
		if(r == 1 && i == 1)
			return (Math.min(a[r][i].getValue(), value));
		int num = a[r][i].getValue();
		int worst = Math.min(num, value);
		Direction way = a[r][i].getDirection();
		if(way == Direction.LEFT || way == Direction.LEFTD || way == Direction.LDU)
		{
			b2.insert(0,a[0][i].getChar());
			b1.insert(0,'-');
			return GlobalRecurse(a, r ,i-1, worst);
		}
		if(way == Direction.LEFTD || way == Direction.DIAG || way == Direction.UPD || way == Direction.LDU)
		{
			b2.insert(0,a[0][i].getChar());
			b1.insert(0,a[r][0].getChar());
			return GlobalRecurse(a, r-1 ,i-1, worst);
		}
		if(way == Direction.UP || way == Direction.UPD || way == Direction.LDU)
		{
			b1.insert(0,a[r][0].getChar());
			b2.insert(0,'-');
			return GlobalRecurse(a, r-1 ,i, worst);
		}
			
		return worst;
			
		
	}
	
	public void printAlignment()
	{
		System.out.println(b1);
		System.out.println(b2);
	}
	
	

}
