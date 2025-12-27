package com.estc.mediatech.dto;

import lombok.Data;
import java.util.List;

@Data
public class FactureRequest {
    private Integer clientId;
    private List<LigneRequest> lignes; // List of products to add
}