package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Method to retrieve all datas from the data sheet
	@DataProvider(name = "data")
	public String[][] getAllData() throws IOException{
		String path = System.getProperty("user.dir")+"//testData//DataSheet.xlsx";
		ExcelUtility xl = new ExcelUtility(path);
		
		int rowCount = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCount("Sheet1", 1);
		String apiData[][] = new String[rowCount][colCount];
		
		for(int i=1; i<=rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
	}
	
	//Method to retrieve only data of the userName coloumn
	@DataProvider(name = "userNames")
	public String[] getUserNames() throws IOException{
		String path = System.getProperty("user.dir")+"//testData//DataSheet.xlsx";
		ExcelUtility xl = new ExcelUtility(path);
		
		int rowCount = xl.getRowCount("Sheet1");
		String apiData[] = new String[rowCount];
		
		for(int i=1; i<=rowCount; i++) {
			apiData[i-1] = xl.getCellData("Sheet1", i, 1);
		}
		
		return apiData;
	}
}
