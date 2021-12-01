import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MasterfilesEdit {

	private FileWriter write;
	private final String LOG = "masterfiles.txt";
	
	public MasterfilesEdit(String user, String file, String date, String path, String[] servers) throws IOException {
		write = new FileWriter(new File(LOG));
		String line = user + "::" + file + "::"  + date + "::" + path + "::";
		for (int i =0; i < servers.length; i ++) {
			line = line + servers[i] + ",";
		}
		write.write(line);
	}
}
