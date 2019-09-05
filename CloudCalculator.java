
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
		average(cloudLayer);
		System.out.println("----------- Layer was created successfully -----------");
		//System.out.println(cloudLayer);
	
	}

	static Layer readData(String fileName){ 
		Layer layer = null;
		try{ 
			Scanner sc = new Scanner(new File(fileName));

			// Read layer details i.e line 1
			int dimt = sc.nextInt();
			int dimx = sc.nextInt(); 
			int dimy = sc.nextInt();

			layer = new Layer(dimt);

			for (int t = 0; t < dimt; t++){
				if(sc.hasNext()==true){
					TimeStamp timeStamp = new TimeStamp(dimx, dimy);
					// Read a wind details
					for(int x = 0; x < dimx; x++){
						for(int y = 0; y < dimy; y++){
							float xAdvection = Float.parseFloat(sc.next());
							float yAdvection = Float.parseFloat(sc.next());
							float convection = Float.parseFloat(sc.next());

							WindDetails windDetails = new WindDetails(xAdvection, yAdvection, convection);

							// add this wind detail to the time stamp
							timeStamp.addWindDetail(x, y, windDetails);
						}
					}

					// add the constructed time stamp to the layer
					layer.addTimeStamp(t, timeStamp);
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

	static void average(Layer layer){

		float  sumOfX = 0;
		float  sumOfY = 0;
		float sumEntries = 0;

		//System.out.println(layer.timestamps[0].windDetails[0][0].xAdvection);

		for (int t = 0; t < layer.timestamps.length; t++){
			for(int x = 0; x < layer.timestamps[t].windDetails.length; x++){
				for(int y = 0; y < layer.timestamps[t].windDetails[0].length; y++){
					//System.out.println(layer.timestamps[t].windDetails[x][y].xAdvection);
					sumOfX += layer.timestamps[t].windDetails[x][y].xAdvection;
					sumEntries +=1;

				}
			}

		}


		for (int t = 0; t < layer.timestamps.length; t++){
			for(int x = 0; x < layer.timestamps[t].windDetails.length; x++){
				for(int y = 0; y < layer.timestamps[t].windDetails[0].length; y++){
					//System.out.println(layer.timestamps[t].windDetails[x][y].xAdvection);
					sumOfY+= layer.timestamps[t].windDetails[x][y].yAdvection;
					//sumEntries +=1;

				}
			}

		}

		float tempx = sumOfX/sumEntries;
		System.out.println(String.format("%.6f",tempx));
		float tempy = sumOfY/sumEntries;
		System.out.println(String.format("%.6f",tempy));

	}

}