package com.data.exception;

import java.io.FileNotFoundException;
 
public class GlobalExceptionHandler extends FileNotFoundException {
 
    private static final long serialVersionUID = -2346384470483785588L;
 
    public GlobalExceptionHandler() {
        super("Arquivo CSV não encontrado! Favor verificar o diretório user/files");
    }
 

}
