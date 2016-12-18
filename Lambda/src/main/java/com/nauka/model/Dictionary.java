package com.nauka.model;

import com.nauka.service.FileService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by LENOVO on 2016-12-14.
 */
public class Dictionary {

    final static Logger LOG = Logger.getLogger(FileService.class);

    private Set<String> wordSet = new TreeSet<>();
    private Map<String, String> dictionaryMap = new HashMap<>();

    public Map<String, String> getDictionaryMap() {
        return dictionaryMap;
    }

    public void addWordSet(Set<String> wordSet) {
        this.wordSet.addAll(wordSet);
        setDictionaryMap();
    }

    private void setDictionaryMap() {

        LOG.debug("Setting dictionary Map");
        Coder coder = new Coder();
        //TODO : dodanie do mapy odwrotności
        wordSet.forEach(
                (x) -> dictionaryMap.put(coder.coding(x, CoderType.CONSONANT,SpaceType.NONE), x)
        );

        dictionaryMap.remove("");
        dictionaryMap.remove(null);
    }
}
