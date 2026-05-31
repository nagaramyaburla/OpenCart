package com.qa.opencart.utils;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVUtil {

	private static final String CSV_PATH = "./src/test/resources/testdata/";

	public static Object[][] getCSVData(String csvName) {

		String csvFile = CSV_PATH + csvName + ".csv";

		Object[][] data = null;

		try {
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			List<String[]> records = reader.readAll();
			// Skip header row
			int rowCount = records.size() - 1;
			data = new Object[rowCount][];

			for (int i = 1; i < records.size(); i++) {
				data[i - 1] = records.get(i);
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
