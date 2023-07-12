package com.alquiler.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface FotoService {

    public Resource upload(String nombreFoto) throws MalformedURLException;
    public String copy(MultipartFile archivo)throws IOException;
    public boolean delete (String nombreFoto);
    public Path getPath(String nombreFoto);
}
