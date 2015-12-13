import java.io.*;
import java.util.ArrayList;

public class Reader {

	public Reader() {
	}
	
	static public ArrayList<String> readFile(File newFile)
	{
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(newFile));
			try {
				String line = null;
				
				while ((line = input.readLine()) != null)
				{
					data.add(line);
				}
			}
			finally {
				input.close();
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
		return data;
	}
	
	static public ArrayList<String> analyzeData(ArrayList<String> data)
	{
		ArrayList<String> endPositions = new ArrayList<String>();
		String endCoordinates = data.get(0);
		data.remove(0);
		
		while(data.equals(null) == false)
		{
			String startPosition = data.get(0).substring(0, 2);
			String direction = Character.toString(data.get(0).charAt(2));
			String steps = data.get(1);
			
			endPositions.add(performTask(startPosition, direction, steps, endCoordinates));
			
			data.remove(1);
			data.remove(0);
			
			if (data.size() == 0)
			{
				break;
			}
		}
		
		writeFile(endPositions);
		return endPositions;
	}

	private static void writeFile(ArrayList<String> endPositions) {
		
		
		try {
			FileWriter writer = new FileWriter("C:/Users/pranavsharma34/Desktop/Output.txt");
			for (int i = 0; i < endPositions.size(); i++)
			{
				writer.write(endPositions.get(i));
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String performTask(String startPosition, String direction, String steps, String endCoordinates) 
	{
		int pointX = Integer.parseInt(startPosition.substring(0,1));
		int pointY = Integer.parseInt(startPosition.substring(1,2));
		
		String dir = direction;
		
		int endX = Integer.parseInt(endCoordinates.substring(0,1));
		int endY = Integer.parseInt(endCoordinates.substring(1,2));
		String point = null;
		String finalPosition = null;
		
		while((steps != null) && (steps.equals("")== false))
		{
			char c = steps.charAt(0);
			
			switch (c)
			{
				case 'L': 
					dir = left(dir);
					break;
				case 'R': 
					dir = right(dir);
					break;
				case 'M': 
					point = forward(pointX, pointY, endX, endY, dir);
					break;
				default :
					System.out.println("Neither L, nor R, nor M");
					break;
			}
			
			steps = steps.substring(1);
			
			if (point != null)
			{
				int comma = point.indexOf(",");
				pointX = Integer.parseInt(point.substring(0, comma));
				pointY = Integer.parseInt(point.substring(comma+1));
			}
		}
		
		finalPosition = pointX + " " + pointY + " " + dir;
		
		return finalPosition;
	}


	private static String forward(int pointX, int pointY, int endX,
			int endY, String dir) {

		int X = pointX;
		int Y = pointY;
		String direction = dir;
		String Point = null;
		
		if (direction.equals("N"))
		{
			Y = Y + 1;
		}
		
		else if (direction.equals("W"))
		{
			X = X - 1;
		}
		
		else if (direction.equals("S"))
		{
			Y = Y - 1;
		}
		
		else if (direction.equals("E"))
		{
			X = X + 1;
		}
		
		if (pointX > endX)
		{
			System.out.println("Rovers is out of bounds in the X coordinate");
		}
		
		if (pointY > endY)
		{
			System.out.println("Rovers is out of bounds in the Y coordinate");
		}
		
		Point = Integer.toString(X).concat(",").concat(Integer.toString(Y));
		
		return Point;
	}

	private static String right(String dir) {
		
		String direction = dir;
		
		if (direction.equals("N"))
		{
			direction = "E";
		}
		
		else if (direction.equals("E"))
		{
			direction = "S";
		}
		
		else if (direction.equals("S"))
		{
			direction = "W";
		}
		
		else if (direction.equals("W"))
		{
			direction = "N";
		}
		
		else
		{
			System.out.println("Direction specified is wrong -- Right");
		}
		
		return direction;
	}
	
	private static String left(String dir) {
		
		String direction = dir;
		
		if (direction.equals("N"))
		{
			direction = "W";
		}
		
		else if (direction.equals("W"))
		{
			direction = "S";
		}
		
		else if (direction.equals("S"))
		{
			direction = "E";
		}
		
		else if (direction.equals("E"))
		{
			direction = "N";
		}
		
		else
		{
			System.out.println("Direction specified is wrong -- Left");
		}
		
		return direction;
	}
	
	public static void main(String args[])
	{
		ArrayList<String> fileData = new ArrayList<String>();
		ArrayList<String> data = new ArrayList<String>();
		
		File f = new File("C:/Users/pranavsharma34/Desktop/Input.txt");
		fileData = readFile(f);
		data = analyzeData(fileData);
		
		System.out.println(data);
		
	}
}
