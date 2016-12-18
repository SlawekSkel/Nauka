package com.nauka.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileServiceTest {

    String fileName = "D:\\Programowanie\\Git\\Nauka\\Lambda\\src\\main\\resources\\slownik";
    FileService fileService = new FileService();

    @Before
    public void preparingFile() {
        fileService.createFile(fileName);
    }

    @Test
    public void ShouldReturnNotEmptyHashSet() {

        assertThat(fileService.getDictionarySet().isEmpty(), is(false));
    }


}
