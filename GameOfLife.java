package warmup;

import java.util.Arrays;
//import java.util.ArrayList;
import java.util.Scanner;

public class GameOfLife {
	static char cells[][];
	static int numAlive = 0;

	public static void main(String args[]) throws InterruptedException {
		Scanner scan = new Scanner(System.in);
		cells = new char[11][24];
    	
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[row].length; column++) {
		        cells[row][column] = '•';
			}
		}   
		boolean prompt = true;
		String input;
		
		while(prompt) {
			System.out.println("Enter two coordinants (ex. '3 5' ). Type 'start' when you are ready.");
			input = scan.nextLine();
			
			if(input.equalsIgnoreCase("start")) {
				prompt = false;
			} else {
				String coordinants[] = input.split(" ");
				
				if(coordinants.length != 2) {
					System.out.println("Make sure to only enter two coordinants");
				} else {
					if(isNumber(coordinants[0]) && isNumber(coordinants[1])) {
						System.out.println("success");
						int x = Integer.valueOf(coordinants[0]);
						int y = Integer.valueOf(coordinants[1]);
						
						if(x >= 0 && x < cells.length && y >= 0 && y < cells.length) {
							cells[y][x] = 'o';
							numAlive += 1;
						} else {
							System.out.println("Values out of range!");
						}
					} else {
						System.out.println("Please enter two numbers.");
					}
				}
			}
		}
		
		for(int i=0; i < cells.length; i++){
			System.out.println();
			for(int j=0; j < cells[i].length; j++) {
				System.out.print(cells[i][j]);
				
			}
		}
		
		System.out.println("numAlive : " + numAlive);

		int life[][] = new int[cells.length][cells[0].length];
		
//		for(int i=0; i < cells.length; i++){
//			for(int j=0; j < cells[i].length; j++) {
//				life[i][j] = 0;
//			}
//		}
		
		
		boolean live = false;
		while(numAlive > 0) {
			for(int i=0; i < cells.length; i++){
				for(int j=0; j < cells[i].length; j++) {
					live = false;
					if(cells[i][j] == 'o') {
						live = true;
						
						if(checkLife(cells[i][j], live)) {
							//is alive and should remain alive
							life[i][j] = 0;
						} else {
							//is alive and should be dead
							life[i][j] = -1;
						}
						
					} else {
						if(checkLife(cells[i][j], live, j, i)) {
							// is dead and should be alive
							life[i][j] = 1;
						} else {
							// is dead and should remain dead
							life[i][j] = 0; 
						}
					}
					
				}
			}
			
			for(int i=0; i < life.length; i++){
				System.out.println();
				for(int j=0; j < life[i].length; j++) {
					System.out.print(life[i][j]);
					
				}
			}
			
			for(int i=0; i < cells.length; i++){
				for(int j=0; j < cells[i].length; j++) {					
					if(life[i][j] == -1) {
						numAlive -=1;
						cells[i][j] = '•';
					} else if(life[i][j] == 1) {
						System.out.println("+1 to numAlive");
						numAlive +=1;
						cells[i][j] = 'o';
					}
				}
			}
			
			System.out.println("numAlive : " + numAlive);
			
			
//			System.out.println("\n" + "\n");
			
			for(int i=0; i < cells.length; i++){
				System.out.println();
				for(int j=0; j < cells[i].length; j++) {
					System.out.print(cells[i][j]);
					
				}
			}
			
			Thread.sleep(2000);

		}
		

	}
//checkLife(cells[i][j]
//checkLife(cells[4][3], true)
	public static boolean checkLife(char point, boolean alive, int x, int y) {
		int adjacentCount = 0;
		for(int i=0; i < cells.length; i++) {
			for(int j=0; j < cells[i].length; j++) {
				if(i > 0) {
					if(cells[i-1][j] == point) {
				  //cells[5][3]
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}					
					}
				} 
				if(i < cells.length-1) {
					if(cells[i+1][j] == point) {
				  //cells[3][3]
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}
					}
				} 
				else if(j > 0) {
					if(cells[i][j-1] == point) {
				  //cells[4][4]
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}
					}
				} else if(j < cells[i].length-1) {
					if(cells[i][j+1] == point) {
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}					
					}
				} else if(i > 0 && j > 0) {
					if(cells[i-1][j-1] == point) {
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}					
					}
				} else if(i < cells.length-1 && j < cells[i].length-1) {
					if(cells[i+1][j+1] == point) {
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}						
					}
				} else if(i > 0 && j < cells[i].length-1) {
					if(cells[i-1][j+1] == point) {
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}	
					}
				} else if(j > 0 && i < cells.length-1) {
					if(cells[i+1][j-1] == point) {
						if(getIsAlive(cells[i][j])) {
							adjacentCount += 1;
							System.out.println("adj count: " + adjacentCount);

						}						
					}
				}
				

			}
		}
		System.out.println("final adj count: " + adjacentCount);

		if(alive) {
			System.out.println("if alive final adj count: " + adjacentCount);

			if(adjacentCount < 2) {
				System.out.println("if adj count  < 2, final adj count: " + adjacentCount);

				return false;
			} else if(adjacentCount == 2) {
				System.out.println("if adj count = 2, final adj count: " + adjacentCount);

				return true;
			} else if(adjacentCount == 3) {
				return true;
			} else if(adjacentCount > 3) {
				return false;
			}
		}
		
		if(!alive) {
			if(adjacentCount == 3) {
				System.out.println("if adj count == 3, final adj count: " + adjacentCount);

				return true;
			}
		}

		return false;
	}
	
	private static boolean isNumber(String str) {
		for(int i=0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean getIsAlive(char a) {
		if(a == 'o') {
//			System.out.println("getIsAlive true ----------------");
			return true;
		}
//		System.out.println("getIsAlive false ----------------");
		return false;
	}
}

//	Character.isAlphabetic('a');
//		Character.isDigit('3')




//ArrayList<String> inputs = newArrayList<String>();
//
//while(scan.hasNext()) {
//	String input = scan.next();
//	inputs.add(input);
//}
//
//for(int i=0; i < inputs.size(); i++) {
//	String input = scan.next();
//}
//
//boolean prompt = true;
//String input;
//while(prompt = true) {
//	System.out.println("enter a num");
//	input = scan.nextLine();
//}