package tunts;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class AverageGrades {
	
	private static SpreadsheetManager sheet;
	private static List<List<Object>> values;
	
	
	public static void updateAlumniStatus() throws IOException, GeneralSecurityException{
		
		sheet = new SpreadsheetManager();		
		values = sheet.getAlumniData();
		
		
		if ( values == null || values.isEmpty() ) {
            System.out.println("MISSING DATA OR WRONG CONNECTION");
        }else {
        	Double avg, frq, naf; //Average, Frequency, Nota para Aprovação Final
 
        	for ( List row: values ) {
        		avg = 0.0;
        		avg += Double.valueOf( row.get(3).toString() );
        		avg	+= Double.valueOf( row.get(4).toString() );
        		avg += Double.valueOf( row.get(5).toString() );
        		avg /= 3;
        		
        		//Necessary in order to set empty fields
        		if( row.size() <= 5 ) {
        			row.add(6);
        			row.add(7);
        		}
        		
        		//Update the average grade and the student's status Approved, Reproved by grades or Attending to Final the Exams 
        		if( avg < 50 ) {
        			row.set( 6, new String( "Reprovado por Nota" ));
        			row.set( 7, new String( "0" ) );
        		}else if( avg < 70 ) {
        			row.set( 6, new String( "Exame Final" ) );
        			//the follow code computes the minimum grade to be approved in final exam
        			//since there's no previous data in the spreadsheet field
        			naf = 100.0 - Math.round( avg.doubleValue() );
        			row.set( 7, naf.toString() );
        		}else {
        			row.set( 6, new String( "Aprovado" ) );
        			row.set( 7, new String( "0" ) );
        		}
        		
        		//Update the student's status: if it is frequent or not
        		frq = Double.valueOf( row.get(2).toString() );
        		frq = frq / 60;
        		
        		if( frq > 0.25 ) {
        			row.set( 6, new String("Reprovado por Falta") );
        			row.set( 7, new String( "0" ) );
        		}
        	}
        	
        }
		
		sheet.setAlumniData(values);
	}

	
}
