package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProver 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\pencartlogincredentials.xlsx";
		ExcelUtility xlutil =new ExcelUtility(path);
		
		int totalrow= xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet1", 1);
		
		String LoginData[][]= new String [totalrow][totalcols]; // create for two dimensional array
		 for(int i=1;i<=totalrow;i++)
		 {
			 for(int j=0;j<totalcols;j++)
			 {
				 LoginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			 }
		 }
		 
		 return LoginData; // returning two dimensional Array
	}
	

}
