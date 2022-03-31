package com.excel.application.util;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.excel.application.domain.Person;
import com.excel.application.repository.PersonRepository;

@Component
public class LoadExcel {

	private static final Logger log = LoggerFactory.getLogger(LoadExcel.class);

	@Value("${person.excel}")
	private File excelResource;

	@Autowired
	PersonRepository personRepository;

	@PostConstruct
	public void loadExcelPostConstruct() throws Exception {

		log.info("Excel file loading started");

		Workbook personExcel = new XSSFWorkbook(new FileInputStream(excelResource.getAbsoluteFile()));
		Sheet personExcelSheet = personExcel.getSheet("Sheet1");
		for (int i = 1; i <= personExcelSheet.getLastRowNum(); i++) {
			Person person = new Person();
			Row row = personExcelSheet.getRow(i);

			log.info("Setting values of row " + i);
			for (int column = 0; column < 4; column++) {
				if (column == 0) {
					person.setName(row.getCell(column).toString());
				}
				if (column == 1) {
					int age = (int) row.getCell(column).getNumericCellValue();

					person.setAge(Integer.parseInt(String.valueOf(age)));
				}
				if (column == 2) {
					person.setHobbies((row.getCell(column).toString()));
				}
				if (column == 3) {
					if (row.getCell(column).getStringCellValue().equals("yes")) {
						person.setAadhar(true);
					} else {
						person.setAadhar(false);
					}

				}

			}
			log.info("Printing person entity values for row " + i + ":" + person.toString());
			log.info("Saving record in Person table");
			personRepository.save(person);
			log.info("Record saved successfully in Person table");

		}
		log.info("Excel file loading ended for Chubb LSA Mapping");
		personExcel.close();

		log.info("Inside method: loadExcelPostConstruct()");

	}

	/*
	 * @Bean public void loadExcelWithoutPostConstruct() {
	 * 
	 * System.out.println("Printing value of:" + log);
	 * 
	 * log.info("load excel method called without post construct");
	 * 
	 * // return new LoadExcel();
	 * 
	 * }
	 */

}
