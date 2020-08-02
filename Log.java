
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

//INCOMPLETE

public class Log {
	public FileWriter fw;

	public  FileWriter getFW() {
		return fw;
	}

	public void setFW(FileWriter f) {
		this.fw = f;
	}

	public void start() {
		try {
			fw = new FileWriter("Airport_Log.txt");
		} catch (IOException e) {e.printStackTrace();}
	}

	public void writeToLog(String message) {
		    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
				fw.write("\n" + timestamp + "		" + message + "\n");            
		} catch (IOException e) {e.printStackTrace();}
	}

	public void closeLog() {
		try {fw.close();} catch (IOException e) {e.printStackTrace();}
		System.out.println("Logger closed.");
	}

}