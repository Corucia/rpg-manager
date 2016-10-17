package com.dedale.frmwrk.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unchecked")
public class XMLMapper {
    
    public <T> T importFrom(Class<T> businessInterface, InputStream inputStream) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(businessInterface);
        Unmarshaller um = context.createUnmarshaller();
        return (T) um.unmarshal(inputStream);
    }
    
    public <T> void exportInto(T businessObject, OutputStream outputStream) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(businessObject.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(businessObject, outputStream);
    }
    
    public <T> T importFrom(Class<T> businessInterface, File inputFile) throws JAXBException, FileNotFoundException {
        return importFrom(businessInterface, new FileInputStream(inputFile));
    }
    
    public <T> void exportInto(T businessObject, File outputFile) throws JAXBException, FileNotFoundException {
        exportInto(businessObject, new FileOutputStream(outputFile));
    }
    
}
