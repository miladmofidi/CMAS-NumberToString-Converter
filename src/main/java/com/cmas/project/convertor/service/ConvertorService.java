package com.cmas.project.convertor.service;

import java.util.List;
import java.util.Map;

public interface ConvertorService
{
    List<Integer> getData();
    String[] convertData(List<Integer> data);
    Map<String, Integer> countDataSolution1(String[] input);
    Map<String, Integer> countDataSolution2(String[] input);
    Map<String, Integer> countDataSolution3(String[] input);
}
