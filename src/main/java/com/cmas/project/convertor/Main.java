package com.cmas.project.convertor;

import com.cmas.project.convertor.service.impl.ConvertorServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Main
{

    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);

        ConvertorServiceImpl convertorServiceImpl = new ConvertorServiceImpl();
        //Getting data from the user in console(it can be loaded automatically from a collection)
        List<Integer> data = convertorServiceImpl.getData();

        //Converting and printing pure data with corresponding string
        String[] convertedData = convertorServiceImpl.convertData(data);
        System.out.println(Arrays.toString(convertedData));

        //Counting and printing each string occurrence in the data
        Map<String, Integer> countData = convertorServiceImpl.countData(convertedData);
        countData.forEach((K, V) -> {
            System.out.println(K + " : " + V);
        });

    }


}
