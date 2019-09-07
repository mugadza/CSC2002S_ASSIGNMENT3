
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

		if(cloudLayer != null){
			average(cloudLayer);
		}

		for(int x = 0; x < cloudLayer.layerLength(); x++){
			cloudLayer.at(x).classifier();

		}
		System.out.print(cloudLayer.layerLength() );
		System.out.print(cloudLayer.at(0).getYLength() );
		System.out.println(cloudLayer.at(0).getXLength() );

		System.out.println(cloudLayer);
	
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
		float tempx = layer.getXAverage();
		System.out.print(String.format("%.6f",tempx));
		float tempy = layer.getYAverage();
		System.out.println(String.format(" %.6f",tempy));

	}

	

}