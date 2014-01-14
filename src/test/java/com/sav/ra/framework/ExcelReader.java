package com.sav.ra.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static String[][] readExcelData(String fileName, String sheetName) {
		try {

			//FileInputStream fis = new FileInputStream(fileName); //file can be located anywhere
			// ClassLoader loader = ClassLoader.getSystemClassLoader();
			// InputStream is = loader.getResourceAsStream(fileName);

			// InputStream
			InputStream fis=ClassLoader.getSystemClassLoader().getResourceAsStream(fileName); //file needs to be within the class path

			XSSFWorkbook workBook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workBook.getSheet(sheetName);
			int nofRows = sheet.getLastRowNum() + 1;
			int nofCols = sheet.getRow(0).getLastCellNum();

			String[][] sheetData = new String[nofRows - 1][nofCols];

			for (int i = 0; i < nofRows - 1; i++) {
				XSSFRow row = sheet.getRow(i + 1);
				String[] tempData=new String[nofCols];
				boolean isRowContainValue=false;
				for (int j = 0; j < nofCols; j++) {
					XSSFCell cell = row.getCell(j);
	
					String obj = cellToString(cell);
					if(cell==null){
						continue;
					}
					isRowContainValue=true;;
					//sheetData[i][j] = obj;
					tempData[j]=obj;
				}
				if(isRowContainValue){
					sheetData[i]=tempData;
				}

			}
			return sheetData;

		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
		return null;
	}
	
	public static String[][][] readExcelDataArr(String fileName, String sheetName) {
		try {

			//FileInputStream fis = new FileInputStream(fileName); //file can be located anywhere
			// ClassLoader loader = ClassLoader.getSystemClassLoader();
			// InputStream is = loader.getResourceAsStream(fileName);

			// InputStream
			InputStream fis=ClassLoader.getSystemClassLoader().getResourceAsStream(fileName); //file needs to be within the class path

			XSSFWorkbook workBook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workBook.getSheet(sheetName);
			int nofRows = sheet.getLastRowNum() + 1;
			int nofCols = sheet.getRow(0).getLastCellNum();

			String[][][] sheetData = new String[nofRows - 1][1][nofCols];

			for (int i = 0; i < nofRows - 1; i++) {
				XSSFRow row = sheet.getRow(i + 1);
				String[] tempData=new String[nofCols];
				boolean isRowContainValue=false;
				for (int j = 0; j < nofCols; j++) {
					XSSFCell cell = row.getCell(j);
	
					String obj = cellToString(cell);
					if(cell==null){
						continue;
					}
					isRowContainValue=true;;
					//sheetData[i][j] = obj;
					tempData[j]=obj;
				}
				if(isRowContainValue){
					sheetData[i][0]=tempData;
				}

			}
			return sheetData;

		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
		return null;
	}
	
	public static Object[][] readExcelDataObjects(String fileName, String sheetName) {
		try {

			//FileInputStream fis = new FileInputStream(fileName);
			// ClassLoader loader = ClassLoader.getSystemClassLoader();
			// InputStream is = loader.getResourceAsStream(fileName);

			// InputStream
			InputStream fis=ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);

			XSSFWorkbook workBook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workBook.getSheet(sheetName);
			int nofRows = sheet.getLastRowNum() + 1;
			int nofCols = sheet.getRow(0).getLastCellNum();

			Object[][] sheetData = new Object[nofRows - 1][nofCols];

			for (int i = 0; i < nofRows - 1; i++) {
				XSSFRow row = sheet.getRow(i + 1);
				Object[] tempData=new String[nofCols];
				boolean isRowContainValue=false;
				for (int j = 0; j < nofCols; j++) {
					XSSFCell cell = row.getCell(j);
	
					Object obj = cellToObject(cell);
					if(cell==null){
						continue;
					}
					isRowContainValue=true;;
					//sheetData[i][j] = obj;
					tempData[j]=obj;
				}
				if(isRowContainValue){
					sheetData[i]=tempData;
				}

			}
			return sheetData;

		} catch (IOException ioe) {
			ioe.printStackTrace();

		}
		return null;
	}

	
	private static String  cellToString(XSSFCell cell) {
		int type;
		String result;
		if(cell==null){
			return null;
		}
		type = cell.getCellType();

		switch (type) {
		case XSSFCell.CELL_TYPE_NUMERIC: // 0
			result = String.valueOf(cell.getNumericCellValue());
			break;
		case XSSFCell.CELL_TYPE_STRING: // 1
			result = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_FORMULA: // 2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case XSSFCell.CELL_TYPE_BLANK: // 3
			result = "";
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN: // 4
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_ERROR: // 5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: "
					+ type);
		}
		return result;
	}
	private static Object  cellToObject(XSSFCell cell) {
		int type;
		Object result;
		if(cell==null){
			return null;
		}
		type = cell.getCellType();

		switch (type) {
		case XSSFCell.CELL_TYPE_NUMERIC: // 0
			result = cell.getNumericCellValue();
			break;
		case XSSFCell.CELL_TYPE_STRING: // 1
			result = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_FORMULA: // 2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case XSSFCell.CELL_TYPE_BLANK: // 3
			result = "-";
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN: // 4
			result = cell.getBooleanCellValue();
			break;
		case XSSFCell.CELL_TYPE_ERROR: // 5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: "
					+ type);
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[][] dataObj = readExcelData(
				"TestCases.xlsx", "RegistrationTestCasesSheet");
		int noRows = dataObj.length;
		int noOfCols = dataObj[0].length;
		for (int i = 0; i < noRows; i++) {
			for (int j = 0; j < noOfCols; j++) {
				System.out.print(dataObj[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	public static String stripDecimal(String dString) {
		if (dString.indexOf(".0")!= -1) {
			return dString.substring(0, dString.indexOf(".0"));
		} else {
			return dString;
		}
	}

}
