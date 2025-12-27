package com.estc.mediatech.web;

import com.estc.mediatech.dto.FactureRequest;
import com.estc.mediatech.models.FactureEntity;
import com.estc.mediatech.service.FactureService;
import com.estc.mediatech.service.PdfService; // Import the new service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/factures")
@CrossOrigin("*")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @Autowired
    private PdfService pdfService; // Inject the PDF Service

    @PostMapping
    public FactureEntity create(@RequestBody FactureRequest request) {
        return factureService.createFacture(request);
    }

    @GetMapping
    public List<FactureEntity> getAll() {
        return factureService.getAll();
    }

    // NEW: Endpoint to download PDF
    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> facturReport(@PathVariable Integer id) {
        FactureEntity facture = factureService.getAll().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Facture not found"));

        ByteArrayInputStream bis = pdfService.generateFacturePdf(facture);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=facture-" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping("/my-factures")
    public List<FactureEntity> getMyFactures(java.security.Principal principal) {
        return factureService.getFacturesByUsername(principal.getName());
    }

    @PostMapping("/my-factures")
    public FactureEntity createMyFacture(@RequestBody List<com.estc.mediatech.dto.LigneRequest> lignes,
            java.security.Principal principal) {
        return factureService.createFactureForUser(principal.getName(), lignes);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        factureService.delete(id);
    }
}