package com.data.percept;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.data.percept.funtions.geraarquivoexel.ExcelService;
import com.itextpdf.text.DocumentException;

@SpringBootTest
class PerceptApplicationTests {

	@Test
	void conectaFire() {
	}
	
	ExcelService criarArquivoExcel = new ExcelService();

	@Test
	void criarArquivoExcelTest(){
		
	} 
}