package com.cmas.project.convertor.service.impl;

import com.cmas.project.convertor.service.ConvertorService;
import com.cmas.project.convertor.service.model.ConstantWords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author <a href="mailto:milad.mofidi@gmail.com">Milad Mofidi </a>
 */

@Service
public class ConvertorServiceImpl implements ConvertorService {

	private static final Logger logger = LoggerFactory.getLogger( ConvertorServiceImpl.class );

	/**
	 * Getting data from the user in console(it can be loaded automatically from a collection)
	 *
	 * @return List<Integer>
	 */
	@Override
	public List<Integer> getData() {
		Scanner scan = new Scanner( System.in );
		List<Integer> data = new ArrayList<>();

		//getting data from the user in console (it can be loaded automatically from a collection)
		System.out.print( "Enter the array size, then press enter: " + "\t" );
		int arraySize = scan.nextInt();
		try {
			if ( arraySize <= 0 ) {
				System.err.println( "Error: you should enter the size greater than zero" );
				throw new IllegalArgumentException( "Array size must be greater than zero" );
			}

			for ( int i = 1; i <= arraySize; i++ ) {
				System.out.println( "Please enter element " + i + " of your array then press enter: " );
				int input = scan.nextInt();
				data.add( input );
			}

		}
		catch ( Exception e ) {
			logger.error( "an error occurred: {} : {}", e.getMessage(), e );
		}
		return data;
	}

	/**
	 * Converting the inputted integers to array of string by replacing integers with corresponding word.
	 *
	 * @param data
	 * @return String[]
	 */
	@Override
	public String[] convertData( List<Integer> data ) {

		return data.stream()
			.map( i -> Integer.toString( i )
				.contains( "3" ) ? ConstantWords.GOOD :
				i % 15 == 0 ? ConstantWords.CMAS_SYSTEMS :
					i % 5 == 0 ? ConstantWords.SYSTEMS :
						i % 3 == 0 ? ConstantWords.CMAS :
							i + "" )
			.toArray( String[]::new );
	}

	/**
	 * Counting each word frequency
	 *
	 * @param input
	 * @return Map<String, Integer>
	 */
	// >Solution1: With the use of Collections api:
	@Override
	public Map<String, Integer> countDataSolution1( String[] input ) {
		Map<String, Integer> countingResult = new HashMap<>();
		//if default java assertion is active

		if ( Objects.isNull(input) ) {
			System.err.println( "Error: the inputted array is null" );
			throw new IllegalArgumentException( "Inputted array must be not null" );
		}

		int goodCounter = Collections.frequency( Arrays.asList( input ), ConstantWords.GOOD );
		int cmasSystemsCounter = Collections.frequency( Arrays.asList( input ), ConstantWords.CMAS_SYSTEMS );
		int cmasCounter = Collections.frequency( Arrays.asList( input ), ConstantWords.CMAS );
		int systemsCounter = Collections.frequency( Arrays.asList( input ), ConstantWords.SYSTEMS );
		int words = goodCounter + cmasSystemsCounter + cmasCounter + systemsCounter;
		int integersCounter = input.length - words;
		countingResult.put( ConstantWords.GOOD, goodCounter );
		countingResult.put( ConstantWords.CMAS_SYSTEMS, cmasSystemsCounter );
		countingResult.put( ConstantWords.CMAS, cmasCounter );
		countingResult.put( ConstantWords.SYSTEMS, systemsCounter );
		countingResult.put( ConstantWords.INTEGER, integersCounter );

		return countingResult;
	}

	// >Solution2: With the use of Stream api:
	@Override
	public Map<String, Integer> countDataSolution2( String[] input ) {

		Map<String, Integer> countingResultSul2 = new HashMap<>();
		long systemsCounterSolu2 = Arrays.stream( input ).filter( x -> x.equalsIgnoreCase( ConstantWords.SYSTEMS ) ).count();
		long goodCounterSolu2 = Arrays.stream( input ).filter( x -> x.equalsIgnoreCase( ConstantWords.GOOD ) ).count();
		long cmasCounterSolu2 = Arrays.stream( input ).filter( x -> x.equalsIgnoreCase( ConstantWords.CMAS ) ).count();
		long cmasSystemsCounterSolu2 = Arrays.stream( input ).filter( x -> x.equalsIgnoreCase( ConstantWords.CMAS_SYSTEMS ) ).count();
		countingResultSul2.put( ConstantWords.GOOD, (int) goodCounterSolu2 );
		countingResultSul2.put( ConstantWords.CMAS_SYSTEMS, (int) cmasSystemsCounterSolu2 );
		countingResultSul2.put( ConstantWords.CMAS, (int) cmasCounterSolu2 );
		countingResultSul2.put( ConstantWords.SYSTEMS, (int) systemsCounterSolu2 );
		int wordsSolu2 = (int) ( goodCounterSolu2 + cmasSystemsCounterSolu2 + cmasCounterSolu2 + systemsCounterSolu2 );
		int integersCounterSul2 = input.length - wordsSolu2;
		countingResultSul2.put( ConstantWords.INTEGER, integersCounterSul2 );
		return countingResultSul2;
	}

	// >Solution3: With the use of Collectors.groupingBy() and iterate result for check element is integer or for other strings:
	@Override
	public Map<String, Integer> countDataSolution3( String[] input ) {

		Map<String, Integer> countingResultSul3 = new HashMap<>();
		Map<String, Long> counts = Arrays.stream( input )
			.collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ) );

		countingResultSul3.put( "CMAS", counts.get( "CMAS" ).intValue() );
		countingResultSul3.put( "CMASsystems", counts.get( "CMASsystems" ).intValue() );
		countingResultSul3.put( "good", counts.get( "good" ).intValue() );
		countingResultSul3.put( "systems", counts.get( "systems" ).intValue() );

		int integers = counts.size() - countingResultSul3.size();
		countingResultSul3.put( "integer", integers );

		return countingResultSul3;

	}
}
