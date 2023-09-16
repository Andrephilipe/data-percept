package com.data.percept.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class RelatorioController {

    public static ResponseEntity<byte[]> gerarPdf() throws IOException {
        // Crie um PDF (exemplo usando iText)
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(pdfStream));
        Document document = new Document(pdfDocument);
        document.add(new Paragraph("Olá, mundo!"));
        document.close();

        // Converta o PDF em um array de bytes
        byte[] pdfBytes = pdfStream.toByteArray();

        // Configure o cabeçalho da resposta HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(pdfBytes.length);
        headers.setContentDispositionFormData("attachment", "documento.pdf"); // Nome do arquivo para download

        // Retorne o PDF na resposta HTTP
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
