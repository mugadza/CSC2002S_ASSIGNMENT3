
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CloudCalculator{

	public static void main(String args []){
		System.out.println("----------- Creating a layer -----------");
		Layer cloudLayer = readData(args[0]);
		System.out.println("----------- Layer was created successfully -----------");
	
	}

	static Layer readData(String fileName){ 
		Layer layer;
		try{ 
			Scanner sc = new Scanner(new File(fileName));
			int dimt = sc.nextInt();
			int dimx = sc.nextInt(); 
			int dimy = sc.nextInt();

			layer = new Layer(dimt);

			for (int t = 0; t < dimt; t++){
				if(sc.hasNext()==true){
					
				}
				else{
					// something went terribly wrong
				}
			}
			

			sc.close(); 
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){ 
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}

		return layer;
	}

}