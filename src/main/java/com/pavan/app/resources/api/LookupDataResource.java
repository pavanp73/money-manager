package com.pavan.app.resources.api;

import com.pavan.app.models.dto.LookupDataDto;
import com.pavan.app.services.core.LookupDataService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lookupData")
public class LookupDataResource {

    private final LookupDataService lookupDataService;

    @Autowired
    public LookupDataResource(LookupDataService lookupDataService) {
        this.lookupDataService = lookupDataService;
    }

    @GetMapping
    @Operation(summary = "List all account type, transaction types, payment modes")
    public LookupDataDto getLookupData(){
        return lookupDataService.getAllLookupData();
    }

}
