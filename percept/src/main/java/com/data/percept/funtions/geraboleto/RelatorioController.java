package com.data.percept.funtions.geraboleto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.*;
import com.data.percept.services.ReportUtils;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.property.TextAlignment;


public class RelatorioController {


    public static ByteArrayInputStream gerarPdf() throws IOException {
        // Crie um PDF (exemplo usando iText)
        ReportUtils report = ReportUtils.getInstance();
        report.addParagraph(new Paragraph("Valores parcelados")
                .setFontSize(13)
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD)));

                report.closeDocument();
        // Retorne o PDF na resposta HTTP
        return report.getByteArrayInputStream();
    }
}
