package com.data.percept.interfaces;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.data.percept.models.InfoResultsGOV;

public interface BuscaAPIGovServices {
    
    @GetMapping("")
    InfoResultsGOV consultaInfo(@PathVariable String codeige, @PathVariable String mesAno) throws IOException, InterruptedException;

}