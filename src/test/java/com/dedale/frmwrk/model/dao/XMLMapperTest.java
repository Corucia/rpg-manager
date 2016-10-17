package com.dedale.frmwrk.model.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class XMLMapperTest {
    
    private XMLMapper mapper;
    
    @Before
    public void initializeXMLMapper() {
        mapper = new XMLMapper();
    }
    
    @Test
    public void import_should_return_expected_object() throws Exception {
        // Arrange
        File file = getResourceFile("initial/pojo.xml");
        
        // Act
        POJO pojo = mapper.importFrom(POJO.class, file);
        
        // Assert
        assertThat(pojo).isNotNull();
    }
    
    @Test
    public void export_should_return_expected_file() throws Exception {
        // Arrange
        POJO pojo = new POJO("anything", "else");
        File file = getResourceFile("expected/pojo.xml");
        file.createNewFile();
        
        // Act
        mapper.exportInto(pojo, file);
        
        // Assert
        File expectedFile = getResourceFile("expected/exportedPojo.xml");
        assertThat(file).hasSameContentAs(expectedFile);
    }
    
    private File getResourceFile(String filePath) {
        URL resource = XMLMapper.class.getResource("");
        File file = new File(resource.getPath(), filePath);
        return file;
    }
    
}
