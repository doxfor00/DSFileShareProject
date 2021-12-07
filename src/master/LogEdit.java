/*
 * Class to edit log file
 * The symbol "_" is local variable represents "or" in this class
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogEdit {
	
	private FileWriter write;
	private final String LOG = "log.txt";
	
	//node connect/fail, user attempt/fail, file failure
	public LogEdit(String type, String date, String time, String node_user, String IP_file) throws IOException {
		write = new FileWriter(new File(LOG));
		String line = "[" + type + "]" + "\t" +
				      date + "  " + 
				      time + "," +
				      node_user + "," +
				      IP_file;
		write.write(line);
	}
	//file insert 
	public LogEdit(String type, String date, String time, String node, String user, String file, int size, String path) throws IOException {
		write = new FileWriter(new File(LOG));
		String line = "[" + type + "]" + "\t" +
					  date + "  " +
					  time + "," +
					  node + "," +
					  user + "," +
					  file + "," +
					  Integer.toString(size) + "," +
					  path;
		write.write(line);
	}
	//file delete, file access
	public LogEdit(String type, String date, String time, String node, String user, String file) throws IOException {
		write = new FileWriter(new File(LOG));
		String line = "[" + type + "]" + "\t" + 
					  date + "  " +
					  time + "," +
					  node + "," +
					  user + "," +
					  file;
		write.write(line);
	}
}
