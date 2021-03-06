package io.powersurfers.controller;

import io.powersurfers.model.document.Document;
import io.powersurfers.model.response.DocumentResponse;
import io.powersurfers.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<DocumentResponse> getDocumentById(@PathVariable String id) {
        DocumentResponse document = documentService.convertToResponse(documentService.getDocumentById(id));
        if (document == null) return new ResponseEntity<>(new DocumentResponse(), HttpStatus.OK);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @GetMapping("/byUserEmail")
    @ResponseStatus(HttpStatus.OK)
    private List<DocumentResponse> getDocumentByOwnerEmail(@RequestParam String email) {
        return documentService.getDocumentsByOwnerEmail(email).stream().map(i -> documentService.convertToResponse(i)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    private Document addDocument(@RequestBody Document document) {
        return documentService.addDocument(document);
    }


}
