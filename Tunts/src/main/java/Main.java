
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

	public static void main(String... args) {
		AverageGrades grades = new AverageGrades();
		
		try {
			grades.updateAlumniStatus();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			
			e.printStackTrace();
		}
	}

}
