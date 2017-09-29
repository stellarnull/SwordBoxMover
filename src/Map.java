import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Map extends BoxMover
{
	private int Map[][] = new int[16][16];//"Map" record the current map,the width and height is temporary 
	private int playerX;//x-coordinate of the location of Mover
	private int playerY;//y-coordinate of the location of Mover
	
	public void readMap()
	{
		//read the map from file according to the level
		String level_S = ".\\maps\\"+level+".map";//source file
		Scanner input;
		try 
		{
			input = new Scanner(new File(level_S));
			String row = "";
			for (int i = 0; i < 16; i++)
			{
				//get map information and use Map to record it
				row = input.next();
				for (int j = 0; j < 16; j++)
				{
					Map[i][j] = (int)row.charAt(j)-48;
				}
			}
			input.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void drawMap()
	{
		//draw the current map in cmd
		for(int i = 0; i < 16; i++)
		{
				for(int j = 0; j < 16; j++)
				{
					drawElement(Map[i][j]);
				}
				System.out.println();
		}
	}
	
	private static void drawElement(int element)
	{
		//change number to picture
		switch (element)
		{
			case 0: System.out.print("  ");break;
			case 1: System.out.print("��");break;
			case 2: System.out.print("  ");break;
			case 3: System.out.print("��");break;
			case 4: System.out.print("��");break;
			case 5: System.out.print("��");break;
			case 9: System.out.print("��");break;
			default : System.out.print("x");
		}
	}
	
	public int[][] getMap()
	{
		return Map;
	}
	
	public void setMap(int map[][])
	{
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				Map[i][j] = map[i][j];
			}
		}
	}
	
	public void clearMap(int i, int j)
	{
		Map = new int[i][j];
	}
	
	public int getMapElement(int i, int j){//getMapElement Method
		return Map[i][j];
	}
	
	public void setMapElement(int i, int j, int element){//setMapElement Method
		Map[i][j] = element;
	}
	
	public int getplayerX(){//get x-coordinate
		return playerX;
	}
	
	public int getplayerY(){//get y-coordinate
		return playerY;
	}
	
	public void findPos(){//find the position of Mover,needed before getting x and y coordinate
		int i = 0,j = 0;
	fP:	for(i = 0; i < 16; i ++){
			for(j = 0; j < 16; j++){
				if(Map[i][j] == 5)break fP;
			}
		}
		playerX = i;
		playerY = j;
	}
}