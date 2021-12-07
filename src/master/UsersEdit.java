import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UsersEdit {
	
	private FileWriter write;
	private final String LOG = "accounts.txt";
	private String name;
	private String hash;
	
	public UsersEdit(String name, String hash) {
		this.name = name;
		this.hash = hash;
	}
	
	@SuppressWarnings("unused")
	private void addUser() throws IOException {
		write = new FileWriter(new File(LOG));
		String line = this.name + this.hash;
		write.write(line);
	}
	@SuppressWarnings("unused")
	private Boolean confirmUser(String n, String h) {
		if (this.name.equals(n) && this.hash.equals(h)) {
			return true;
		} else {
			return true;
		}
	}
}
