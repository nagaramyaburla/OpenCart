package com.qa.opencart.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.opencart.logger.Log;

public class ExcelUtil {
	
	public static String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";

	public static Object[][] getExcelData(String sheetName) {

		Log.info("Reading test data from sheet : " + sheetName);

		FileInputStream file = null;
		Workbook workbook = null;
		Object[][] data = null;

		try {

			file = new FileInputStream(TEST_DATA_SHEET_PATH);
			workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();

			data = new Object[rowCount][colCount];

			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (file != null)
					file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return data;

	}

}
