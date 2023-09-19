package com.data.percept.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.data.percept.PerceptApplication;
import com.data.percept.dto.OrdersDTO;
import com.data.percept.funtions.geraboleto.GenerateHeaderPdf;
import com.data.percept.funtions.geraboleto.RelatorioController;
import com.data.percept.interfaces.BuscaAPIGovServices;
import com.data.percept.models.InfoResultsGOV;
import com.data.percept.models.OrderPaymentsBoleto;
import com.data.percept.repository.PaymentsBoletoRepository;
import com.data.percept.services.ReportService;


@RestController
public class GovRestController {
	
public static Logger logger = LoggerFactory.getLogger(PerceptApplication.class);
	
	@Autowired
	private BuscaAPIGovServices buscaService;

	@Autowired
    PaymentsBoletoRepository paymentsBoletoRepository;

	ReportService schoolService;

	@GetMapping("/gov/{codeibge}/{mesAno}")
	public ResponseEntity<InfoResultsGOV> getInfo(@PathVariable String codeibge, @PathVariable String mesAno)
			throws IOException, InterruptedException {
		logger.info("GovRestController: Inicio");
		try {

			logger.info("GovRestController: Entrando no consultaInfo");
			InfoResultsGOV resultsInfo = buscaService.consultaInfo(codeibge, mesAno);

			logger.info("GovRestController: retornou do consultaInfo");
			return resultsInfo != null ? ResponseEntity.ok().body(resultsInfo) : ResponseEntity.notFound().build();

		} catch (Exception e) {
			logger.info("GovRestController: Erro" + e);

			return ResponseEntity.internalServerError().build();
		}

	}

	@GetMapping("/pdf")
	public ResponseEntity<byte[]> getInfoPdf()
			throws IOException, InterruptedException {
		logger.info("GovRestController: Inicio");
		try {

			logger.info("GovRestController: Entrando no consultaInfo");
			RelatorioController.gerarPdf();

			logger.info("GovRestController: retornou do consultaInfo");
			return RelatorioController.gerarPdf();

		} catch (Exception e) {
			logger.info("GovRestController: Erro" + e);

			return ResponseEntity.internalServerError().build();
		}

	}

	@PostMapping("/boletoGera")
    public ResponseEntity<byte[]> createOrderBoleto(@Valid @RequestBody OrdersDTO orderPaymentBoleto) {

        logger.info("createOrderBoleto : start");

        try {

            OrderPaymentsBoleto orderBoletoCreated = new OrderPaymentsBoleto();
            orderBoletoCreated.setCpf(orderPaymentBoleto.getCpf());
            orderBoletoCreated.setDataCriacao(orderBoletoCreated.getDataCriacao());
            orderBoletoCreated.setDataValidade(orderBoletoCreated.getDataValidade());
            orderBoletoCreated.setDataVencimento(orderBoletoCreated.getDataVencimento());
            orderBoletoCreated.setNomeTitular(orderPaymentBoleto.getNomeTitular());
            orderBoletoCreated.setValor(orderPaymentBoleto.getValor());
            orderBoletoCreated.setStatusBoleto("criado");
            orderBoletoCreated.setMunicipio(orderPaymentBoleto.getMunicipio());

            paymentsBoletoRepository.save(orderBoletoCreated);
			return GenerateHeaderPdf.generatePdf(orderBoletoCreated);

        } catch (Exception e) {
            logger.info("createOrderBoleto : erro", e);
            //return ResponseEntity.internalServerError().body("createOrderBoleto not created");
        }
        logger.info("createOrderBoleto : end");
        // return GenerateHeaderPdf.generatePdf(orderBoletoCreated);
		return null;
    }

	@GetMapping("/report")
    public ResponseEntity<InputStreamResource> report() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=students.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(this.schoolService.report()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
