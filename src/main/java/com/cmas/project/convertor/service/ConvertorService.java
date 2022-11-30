package com.cmas.project.convertor.service;

import java.util.List;
import java.util.Map;

public interface ConvertorService
{
    List<Integer> getData();
    String[] convertData(List<Integer> data);
    Map<String, Integer> countData(String[] input);
}
