package com.estc.mediatech.service;

import com.estc.mediatech.models.FactureEntity;
import com.estc.mediatech.models.LigneFactureEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

@Service
public class PdfService {

    public ByteArrayInputStream generateFacturePdf(FactureEntity facture) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // 1. Add Title
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph para = new Paragraph("Facture: " + facture.getReference(), font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            // 2. Add Client Info
            document.add(new Paragraph("Client: " + facture.getClient().getNom() + " " + facture.getClient().getPrenom()));
            document.add(new Paragraph("Date: " + facture.getDate().toString()));
            document.add(Chunk.NEWLINE);

            // 3. Create Table for Products
            PdfPTable table = new PdfPTable(3); // 3 columns
            
            // Add Header
            Stream.of("Produit", "Quantité", "Prix Unitaire").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle));
                table.addCell(header);
            });

            // Add Rows (The Products)
            for (LigneFactureEntity ligne : facture.getLigneFactures()) {
                table.addCell(ligne.getProduit().getLibelle());
                table.addCell(ligne.getQuantity().toString());
                table.addCell(ligne.getProduit().getPrix().toString());
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}