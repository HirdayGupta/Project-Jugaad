package drums;
import arduino.*;
import java.util.Scanner;
public class ExecuteDrumSequence {
	
	static Arduino bass;
	static Arduino snare;
	static Arduino highHat;
	static Arduino cymbal;

	public static void main(String[] args) {
		Scanner ob = new Scanner(System.in);
		
		//Setting the USB ports that the Arduinos controlling each of the drum parts are connected to.
		String bassPort = "";  //store the usb/bluetooth/wifi ports of the Arduinos connected to each drum part
		String snarePort = "";
		String highHatPort = "";
		String cymbalPort = "";
		//Creating their Arduino objects, mounting each on the correct port, and setting BAUD_RATE to 9600.
		bass = new Arduino(bassPort, 9600);
		snare = new Arduino(snarePort, 9600);
		highHat = new Arduino(highHatPort, 9600);
		cymbal = new Arduino(cymbalPort, 9600);
		//opening connections
		bass.openConnection();
		snare.openConnection();
		highHat.openConnection();
		cymbal.openConnection();
		
		
		
		System.out.println("Enter the drum sequence to be played in drum tab format.");
		System.out.println("There should be 4 rows of 16 space-seperated integers, with 1 indicating that the drum should be played, and 0 indicating that it shouldn't.");
		System.out.println("Each row indicates a different drum part.");
		System.out.println();
		System.out.print("Enter the 16 space-seperated integers for the cymbal:"); 
		String cymbalString = ob.nextLine();
		
		System.out.print("Enter the 16 space-seperated integers for the high-hat:"); 
		String highHatString = ob.nextLine();
		
		System.out.print("Enter the 16 space-seperated integers for the snare:"); 
		String snareString = ob.nextLine();
		
		System.out.print("Enter the 16 space-seperated integers for the bass:"); 
		String bassString = ob.nextLine();
		
		System.out.println("\nEnter the tempo that you want this sequence played at. (Max tempo:180)");
		int tempo = ob.nextInt();
		
		cymbalString = "Tempo:"+tempo+"Sequence:"+cymbalString;
		highHatString = "Tempo:"+tempo+"Sequence:"+highHatString;
		snareString = "Tempo:"+tempo+"Sequence:"+snareString;
		bassString = "Tempo:"+tempo+"Sequence:"+bassString;
		
		System.out.println("Enter anything to execute");
		ob.next();
		ob.close();
		
		cymbal.serialWrite(cymbalString);
		highHat.serialWrite(highHatString);
		snare.serialWrite(snareString);
		bass.serialWrite(bassString);
		
		//closing all connections.
		cymbal.closeConnection();
		highHat.closeConnection();
		snare.closeConnection();
		bass.closeConnection();
		
	}
	
	

}
