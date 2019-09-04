
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CloudCalculator{
	static ArrayList [][][] advection; // in-plane regular grid of wind vectors, that evolve over time
	static float [][][] convection; // vertical air movement strength, that evolves over time
	static int [][][] classification; // cloud type per grid point, evolving over time
	static int dimx, dimy, dimt; // data dimensions

	public static void main(String args []){
		readData(args[0]);
		System.out.println(xAverage());
	}


	public static float xAverage(){
		float sumOfX = 0;
		float sumOfEntries = dimt * dimx * dimy;


		for(int t = 0; t < dimt; t++){
			for(int x = 0; x < dimx; x++){
				for(int y = 0; y < dimy; y++){
					float temp = (float)advection[t][x][y].get(0);
					sumOfX+= temp;

					//System.out.println(advection[t][x][y].get(0));
				}
			}
		}

		return sumOfX/sumOfEntries;


	}

	static void readData(String fileName){ 
		try{ 
			Scanner sc = new Scanner(new File(fileName));
			while(sc.hasNext()==true){
				//input grid dimensions and simulation duration in timesteps
				dimt = sc.nextInt();
				dimx = sc.nextInt(); 
				dimy = sc.nextInt();
				
				//initialize and load advection (wind direction and strength) and convection
				advection = new ArrayList[dimt][dimx][dimy];
				convection = new float[dimt][dimx][dimy];
				classification = new int[dimt][dimx][dimy];

				for(int t = 0; t < dimt; t++)
					for(int x = 0; x < dimx; x++)
						for(int y = 0; y < dimy; y++){
							if(sc.hasNext()==true){
								advection[t][x][y] = new ArrayList();
								// System.out.println(advection[t][x][y]);
								advection[t][x][y].add(sc.next());

								advection[t][x][y].add(sc.next());
								System.out.println(advection[t][x][y].get(0));

								advection[t][x][y].add(sc.next());
								System.out.println(advection[t][x][y].get(1));

								//classification[t][x][y] = 0;
								//System.out.println(classification[t][x][y]);

								convection[t][x][y] = Float.parseFloat(sc.next());
								System.out.println(convection[t][x][y]);
							}


						}
			}
			
			//classification = new int[dimt][dimx][dimy];
			//sc.close(); 
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){ 
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}

}