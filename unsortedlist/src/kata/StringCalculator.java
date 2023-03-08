package kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

	static int Add(String number) throws IllegalArgumentException, DelimiterArgumentException, NegativeNotAllowedException {
		int res = 0;
		String delimiter = ",";
		// Add empty
		if(number == null)
			throw new IllegalArgumentException("number cannot be null");
		if(number.length() == 0)
			return 0;
		
		
		
		// Handle delimiters
		if(number.startsWith("//")) {
			number = number.substring(2); // Remove '//'
			
			// Handle String delimiters
			if (number.startsWith("[")){
				while(number.charAt(0) == '[') {
					number = number.substring(1); // Remove '['
					int index = number.indexOf("]"); // Found end of String delimiter
					if(index == -1) {
						throw new DelimiterArgumentException("Missing ] at end of delimiter");
					}
					
					number = number.replaceAll(delimiter, number.substring(0, index)); // Replace all previous delimiter by the new one
					delimiter = number.substring(0, index);
					number =  number.substring(index+1); // Remove 'delimiter]'
				}
				

				
			}
			
			// Handle Single Char delimiter
			else {
				delimiter = "\\"+String.valueOf(number.charAt(0));
				number = number.substring(1); // Remove 'delimiter'
			}
			
			// Handle '\n' before sum part
			if(number.charAt(0) != '\n') {
				throw new IllegalArgumentException("Missing \n at the end of delimiter ");
			} else {
				number = number.substring(1); // Remove '\n'
			}
		}

		// Remove \n characters
		number = number.replaceAll("\n", delimiter);
		
		// Check last char of the string.
		if(number.charAt(number.length()-1) == delimiter.charAt(delimiter.length()-1)) {
			throw new DelimiterArgumentException("Cannot finish with a delimiter.");
		}
		
		// Split and sum.
		ArrayList<Integer> negValues = new ArrayList<>();
		try {
			List<String> numbers = Arrays.asList(number.split(delimiter));
			for(String n : numbers) {
				int nInt = Integer.parseInt(n);
				if(nInt < 0) negValues.add(nInt);
				res += nInt > 1000 ? 0 : nInt;
			}
		// Errorswith parseInt	
		} catch (Exception e) {
			throw new IllegalArgumentException("Something went wrong with parseInt");

		} finally {
			// Negative values
			if(negValues.size() > 0)
				throw new NegativeNotAllowedException("Negatives values are not allowed: "+negValues.toString());
		}
		
		return res;
	}
		
}
