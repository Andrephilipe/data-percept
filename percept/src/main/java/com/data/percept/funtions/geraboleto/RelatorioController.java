package com.data.percept.funtions.geraboleto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.*;

public class RelatorioController {


    public static ResponseEntity<byte[]> gerarPdf() {
        // Crie um PDF (exemplo usando iText)
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(pdfStream));
        Document document = new Document(pdfDocument);
        document.add(new Paragraph("Olá, mundo!"));
        document.setBorder(new SolidBorder(1));
        document.setBorderBottom(new SolidBorder(1));
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
