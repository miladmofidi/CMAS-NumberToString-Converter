package com.cmas.project.convertor.service.impl;

import com.cmas.project.convertor.service.ConvertorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @Author <a href="mailto:milad.mofidi@gmail.com">Milad Mofidi </a>
 *
 */

@Service
public class ConvertorServiceImpl implements ConvertorService
{
    private static final Logger logger = LoggerFactory.getLogger(ConvertorServiceImpl.class);


    /**
     * Getting data from the user in console(it can be loaded automatically from a collection)
     * @return List<Integer>
     */
    @Override
    public List<Integer> getData()
    {
        Scanner scan = new Scanner(System.in);
        List<Integer> data = new ArrayList<>();

        //getting data from the user in console (it can be loaded automatically from a collection)
        System.out.print("Enter the array size: " + "\t");
        int arraySize = scan.nextInt();
        try
        {
            //if default java assertion is active
            assert arraySize > 0;

            if (arraySize <= 0)
            {
                System.err.println("Error: you should enter the size greater than zero");
                throw new IllegalArgumentException("Array size must be greater than zero");
            }
            if (arraySize > 0)
            {
                for (int i = 1; i <= arraySize; i++)
                {
                    System.out.println("Please enter element " + i + " of your array: ");
                    int input = scan.nextInt();
                    data.add(input);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("an error occurred: {} : {}", e.getMessage(), e);
        }
        return data;
    }


    /**
     * Converting the inputted integers to array of string by replacing integers with corresponding word.
     * @param data
     * @return String[]
     */
    @Override
    public String[] convertData(List<Integer> data) {

        return data.stream()
                .map(i -> Integer.toString(i).contains("3") ? "good" :
                          i % 15 == 0 ? "CMASsystems" :
                                   i % 5 == 0 ? "systems" :
                                            i % 3 == 0 ? "CMAS" :
                                                    i + "")
                .toArray(String[]::new);
    }

    /**
     * Counting each word frequency
     * @param input
     * @return Map<String, Integer>
     */
    @Override
    public Map<String, Integer> countData(String[] input)
    {
        Map<String, Integer> countingResult = new HashMap<>();
        //if default java assertion is active
        assert input != null;

        if (input == null)
        {
            System.err.println("Error: the inputted array is null");
            throw new IllegalArgumentException("Inputted array must be not null");
        }

        //*There are 3 solutions for counting elements in this method:

        // >Solution1: With the use of Collections api:
        int goodCounter = Collections.frequency(Arrays.asList(input), "good");
        int cmasSystemsCounter = Collections.frequency(Arrays.asList(input), "CMASsystems");
        int cmasCounter = Collections.frequency(Arrays.asList(input), "CMAS");
        int systemsCounter = Collections.frequency(Arrays.asList(input), "systems");
        int words = goodCounter + cmasSystemsCounter + cmasCounter + systemsCounter;
        int integersCounter = input.length - words;
        countingResult.put("good", goodCounter);
        countingResult.put("CMASsystems", cmasSystemsCounter);
        countingResult.put("CMAS", cmasCounter);
        countingResult.put("systems", systemsCounter);
        countingResult.put("integer", integersCounter);


        // >Solution2: With the use of Stream api:
/*      long systemsCounter = Arrays.stream(input).filter(x -> x.equalsIgnoreCase("systems")).count();
        long goodCounter = Arrays.stream(input).filter(x -> x.equalsIgnoreCase("good")).count();
        long cmasCounter = Arrays.stream(input).filter(x -> x.equalsIgnoreCase("CMAS")).count();
        long cmasSystemsCounter = Arrays.stream(input).filter(x -> x.equalsIgnoreCase("CMASsystems")).count();
        countingResult.put("good", (int)goodCounter );
        countingResult.put("CMASsystems", (int)cmasSystemsCounter);
        countingResult.put("CMAS", (int)cmasCounter);
        countingResult.put("systems", (int)systemsCounter);
        int words = (int) (goodCounter + cmasSystemsCounter + cmasCounter + systemsCounter);
        int integersCounter = input.length - words;
        countingResult.put("integer", integersCounter);*/


        // >Solution3: With the use of Collectors.groupingBy() and iterate result for check element is integer or for other strings:
/*        Map<String, Long> counts = Arrays.stream(input)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        AtomicInteger numberCoounter = new AtomicInteger();
        counts.forEach((K, V) -> {
            try
            {
                Long.parseLong(K);
                numberCoounter.getAndIncrement();
            }
            catch (NumberFormatException e)
            {
                System.err.println("Invalid number format : " + K);
            }
        });
        countingResult.put("CMAS", counts.get("CMAS").intValue());
        countingResult.put("CMASsystems", counts.get("CMASsystems").intValue());
        countingResult.put("good", counts.get("good").intValue());
        countingResult.put("systems", counts.get("systems").intValue());
        countingResult.put("integer", numberCoounter.intValue());*/


        return countingResult;
    }
}
