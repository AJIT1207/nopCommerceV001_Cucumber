package VowelCount;


import java.util.*;


public class VowelCount {
	private String productName;
	private int price;
	public VowelCount(String productName, int price) {
		
		this.productName = productName;
		this.price=price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public List<String> getProductList()
	{
		List<String> productList = new ArrayList();
		productList.add("Macbook pro");
		productList.add("Macbook pro 12");
		productList.add("Macbook pro 13");
		return productList;
		
	}
	
	//public static void main(String[] args) {

		/*
		 * if (args.length >= 4) { System.out.println("Extra arguments passed."); } else
		 * { Map<String, Integer> vowelCount = new HashMap<>(); Map<String, Integer>
		 * consonantCount = new HashMap<>(); List<String> argumentList =
		 * Arrays.asList(args); argumentList.forEach(argument ->
		 * vowelCount.put(argument, getVowelCount(argument)));
		 * argumentList.forEach(argument -> consonantCount.put(argument,
		 * getConsonants(argument))); System.out.println(vowelCount);
		 * System.out.println(consonantCount); } }
		 * 
		 * 
		 * 
		 * private static Integer getVowelCount(String input) { String[] inputChars =
		 * input.split(""); return (int) Arrays.stream(inputChars).filter(inputChar ->
		 * inputChar.matches("[aeiou]")).count(); }
		 * 
		 * private static Integer getConsonants(String input) { String[] inputChars =
		 * input.split(""); return (int) Arrays.stream(inputChars).filter(inputChar ->
		 * inputChar.matches("^[b-z&&[^eiou]]+$")).count();
		 */	
	
  //}
}