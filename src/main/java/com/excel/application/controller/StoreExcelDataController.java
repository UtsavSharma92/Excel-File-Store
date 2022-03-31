package com.excel.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.excel.application.service.StoreExcelDataService;
import com.excel.application.util.LoadExcel;

@RestController
public class StoreExcelDataController {

	private static final Logger log = LoggerFactory.getLogger(StoreExcelDataController.class);

	@Autowired
	@Qualifier("loadExcel")
	private LoadExcel loadExcel123;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private StoreExcelDataService storeExcelService;

	@PostMapping(value = "/upload/excel/")
	public String storeExcelData(@RequestParam("file") MultipartFile file) throws Exception {

		log.info("Entered file upload controller");

		String response = storeExcelService.uploadExcelFileData(file);

		return response;

	}

}
