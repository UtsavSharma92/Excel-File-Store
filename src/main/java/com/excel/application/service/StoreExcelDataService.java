package com.excel.application.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.application.domain.Car;
import com.excel.application.repository.CarRepository;

@Service
public class StoreExcelDataService {

	private static final Logger log = LoggerFactory.getLogger(StoreExcelDataService.class);

	@Autowired
	private CarRepository carRepository;

	public String uploadExcelFileData(MultipartFile file) throws Exception {

		Workbook carExcel = new XSSFWorkbook(file.getInputStream());

		Sheet carSheet = carExcel.getSheet("Sheet1");

		

		for (int i = 1; i <= carSheet.getLastRowNum(); i++) {
			
			Car car = new Car();

			log.info("Reading and printing contents of row:" + i);

			Row carRow = carSheet.getRow(i);

			for (int column = 0; column < 3; column++) {

				if (column == 0) {
					car.setCarName(carRow.getCell(column).toString());
				}
				if (column == 1) {
					car.setCarColor(carRow.getCell(column).toString());
				}
				if (column == 2) {
					car.setCarBrand(carRow.getCell(column).toString());
				}

				System.out.println(carRow.getCell(column));

			}

			carRepository.save(car);

		}

		carExcel.close();
		return "ok";
	}

}
