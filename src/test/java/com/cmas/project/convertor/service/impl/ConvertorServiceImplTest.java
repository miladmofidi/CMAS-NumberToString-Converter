package com.cmas.project.convertor.service.impl;


import com.cmas.project.convertor.service.ConvertorService;

import java.util.Arrays;
import java.util.List;

public class ConvertorServiceImplTest
{
    //unit test without any framework
    private static ConvertorService convertorService = new ConvertorServiceImpl();

    public static void main(String[] args)
    {
        testConvertData_Must_Success();
        System.out.println("Test passed");
    }

    static void testConvertData_Must_Success()
    {

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        String[] expectedData =
                {"1", "2", "good", "4", "systems", "CMAS", "7", "8", "CMAS", "systems", "11", "CMAS", "good", "14",
                 "CMASsystems", "16", "17", "CMAS", "19", "systems"};
        String[] actualData = convertorService.convertData(data);

        if (!Arrays.equals(expectedData,actualData))
            throw new IllegalArgumentException("Test failed: the expected result is not same with actual result");

        for (int i = 0; i < data.size(); i++)
        {
            if (!actualData[i].equals(expectedData[i])) {
                throw new IllegalArgumentException("Test failed: the expected result is not same with actual result");
            }
        }
    }
}
