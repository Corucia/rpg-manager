package com.dedale.frmwrk.meta;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class MetaDataListTest {
    
    private MetaDataList metaDataList;
    
    @Before
    public void beforeName() throws Exception {
        metaDataList = new MetaDataList();
    }
    
    @Test
    public void findByName_returns_absent_when_list_is_empty() throws Exception {
        Optional<?> absent = metaDataList.findByName("any");
        assertFalse(absent.isPresent());
    }
    
}
