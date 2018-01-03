import java.util.Scanner;
import java.util.Random;

public class Runner {
	
	private static boolean mapOn = false;
	private static boolean visitedRoom = false;
	private static boolean gameOn = true;
	
	private static Room[][] rooms;
	
	
	public static void main(String[] args)
	{		
	
		Board r1 = new Board();
		
		Room[][] building = r1.getMap();
		
		if(mapOn == true)
		{
			r1.printBoard();
		}
		s
		//Fill the building with normal rooms
		for (int x = 0; x<building.length; x++)
		{
			for (int y = 0; y < building[x].length; y++)
			{
				building[x][y] = new Room(x,y);
			}
		}
		
		//Depending on this, the winning room is either on the bottom or right. Determines location of the room.
		if((int)Math.random() * 2 <= 1)
		{
			int x = building.length * (int)Math.random();		
			int y = building[0].length - 1;						//Right
			building[x][y] = new ExitRoom(x, y);
		}
		else
		{
			int x = building.length - 1;						//Bottom
			int y = (int)Math.random();
			building[x][y] = new ExitRoom(x, y);
		}
		
		//Puts the map room close to the player's starting location
		int x = 2;
		int y = 2;
		building[x][y] = new MapRoom(x, y);
	
		//Setup player 1 and the input scanner
		Person player1 = new Person("FirstName", "FamilyName", 0,0);
		building[0][0].enterRoom(player1);
		Scanner in = new Scanner(System.in);
		while(gameOn)
		{
			System.out.println("Where would you like to move? (Choose N, S, E, W) ");
			String move = in.nextLine();
			if(validMove(move, player1, building))
			{
				if(mapOn == false)
				{
					System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
					System.out.println("Score: " + player1.getscore());
				}
				else if(mapOn == true)
				{
					System.out.println("Your coordinates: row = " + player1.getxLoc() + " col = " + player1.getyLoc());
					r1.printBoard();
					System.out.println("Score: " + player1.getscore());
				}
			}
			
			else {
				System.out.println("Please choose a valid move.");
			}			
			
		}
		in.close();
	}
	
	public static boolean validMove(String move, Person p, Room[][] map)
	{
		move = move.toLowerCase().trim();
		switch (move) {
			case "n":
				if (p.getxLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			case "e":
				if (p.getyLoc()< map[p.getyLoc()].length -1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "s":
				if (p.getxLoc() < map.length - 1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "w":
				if (p.getyLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			default:
				break;
					
		}
		return true;
	}
	
	public static void gameOff()
	{
		gameOn = false;
	}
	
	public static void mapOn()
	{
		mapOn = true;
	}

	public static void visitedRoom()
	{
		visitedRoom = true;
	}
}