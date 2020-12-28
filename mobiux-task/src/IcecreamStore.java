import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class IcecreamStore {
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> filteredList = new ArrayList<String>();
	static ArrayList<String> janList = new ArrayList<String>();
	static ArrayList<String> febList = new ArrayList<String>();
	static ArrayList<String> marList = new ArrayList<String>();
	static ArrayList<String> icecreamNames = new ArrayList<String>();
	static ArrayList<Integer> totalSales = new ArrayList<Integer>();
	static ArrayList<Integer> monthlyTotalSalesCount = new ArrayList<Integer>();
	static List<Integer> totalSalesPerMonth = new ArrayList<Integer>();
	static List<Integer> TotalRevenuePerIcecreamPerMonth = new ArrayList<Integer>();
	static ArrayList<Integer> soldCount = new ArrayList<Integer>();
	static ArrayList<String> mostSoldItems = new ArrayList<String>();
	static ArrayList<Integer> soldPrice = new ArrayList<Integer>();
	static ArrayList<String> mostSoldPrice = new ArrayList<String>();
	static ArrayList<Integer> janSoldCount = new ArrayList<Integer>();
	static ArrayList<Integer> febSoldCount = new ArrayList<Integer>();
	static ArrayList<Integer> marSoldCount = new ArrayList<Integer>();
	static ArrayList<Integer> janSoldPrice = new ArrayList<Integer>();
	static ArrayList<Integer> febSoldPrice = new ArrayList<Integer>();
	static ArrayList<Integer> marSoldPrice = new ArrayList<Integer>();
	static List <String> soldListPerMonth = new ArrayList<String>();
	static String startDate = "2019-01-01";
	static String endDate = "2019-03-31";
	static String monthName = null;
	static String[] monthEndDates = {"2019-01-31", "2019-02-28", "2019-03-31"};
	public static void main(String[] args) throws FileNotFoundException {
		Scanner data = new Scanner(new File("C:\\Users\\Admin\\Desktop\\file.txt"));

		while (data.hasNextLine()) {
			list.add(data.nextLine());
		}
		data.close();

		for (int i = 1, len = list.size(); i < len; i++) {
			String line = list.get(i);
			String[] splitted = line.split("[,]");
			int totalQuantity = Integer.parseInt(splitted[3]);
			String IcecreamList = splitted[1];
			icecreamNames.add(IcecreamList);
			totalSales.add(totalQuantity);
		}
		int sumSales = totalSales.stream().mapToInt(Integer::intValue).sum();
		Set<String> set = new HashSet<>(icecreamNames);
		icecreamNames.clear();
		icecreamNames.addAll(set);
		System.out.println("Total sales of the store -- " + sumSales);
		
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		
		while (!start.isAfter(end)) {
			String identifier = start.toString();
			for (int i = 0, len = list.size(); i < len; i++) {
				String line = list.get(i);
				String[] splitted = line.split("[,]");
				if (splitted[0].equals(identifier)) {
					String quantity = splitted[3];
					String name = splitted[1];
					String totalPrice = splitted[4];
					filteredList.add(quantity + "," + name+","+totalPrice);
				}
			}
			getMonthTotals(filteredList, start);
			start = start.plusDays(1);
		}
		start=start.minusDays(90);
		while (!start.isAfter(end)) {
			if (start.equals(LocalDate.parse(monthEndDates[0]))) {
				getMostSoldItem(janList, start);}
				if (start.equals(LocalDate.parse(monthEndDates[1]))) {
					getMostSoldItem(febList, start);}
				if (start.equals(LocalDate.parse(monthEndDates[2]))) {
					getMostSoldItem(marList, start);}
			start = start.plusDays(1);
		}
		start=start.minusDays(90);
		while (!start.isAfter(end)) {
			if (start.equals(LocalDate.parse(monthEndDates[0]))) {
				getMostSoldItemRevenue(janList, start);}
				if (start.equals(LocalDate.parse(monthEndDates[1]))) {
					getMostSoldItemRevenue(febList, start);}
				if (start.equals(LocalDate.parse(monthEndDates[2]))) {
					getMostSoldItemRevenue(marList, start);}
			start = start.plusDays(1);
		}
		start=start.minusDays(90);
		while (!start.isAfter(end)) {
			if (start.equals(LocalDate.parse(monthEndDates[0]))) {
				getSoldItemCalculation(janList, start);}
				if (start.equals(LocalDate.parse(monthEndDates[1]))) {
					getSoldItemCalculation(febList, start);}
				if (start.equals(LocalDate.parse(monthEndDates[2]))) {
					getSoldItemCalculation(marList, start);}
			start = start.plusDays(1);
		}
	}
	

	public static void getMonthTotals(ArrayList<String> filteredList, LocalDate start) {
		
		for(int monthSales=0;monthSales<3;monthSales++) {
		if (start.equals(LocalDate.parse(monthEndDates[monthSales]))) {
			for (int i = 0, length = filteredList.size(); i < length; i++) {
				String line = filteredList.get(i);
				String[] splitted = line.split("[,]");
				monthlyTotalSalesCount.add(Integer.parseInt(splitted[0]));
			}
			int sumSales = monthlyTotalSalesCount.stream().mapToInt(Integer::intValue).sum();
			monthlyTotalSalesCount.clear();
			if (start.equals(LocalDate.parse(monthEndDates[0]))) {
				janList.addAll(filteredList);
			System.out.println("\nTotal sales of the store in January -- " + sumSales);}
			if (start.equals(LocalDate.parse(monthEndDates[1]))) {
				febList.addAll(filteredList);
				System.out.println("Total sales of the store in February -- " + sumSales);}
			if (start.equals(LocalDate.parse(monthEndDates[2]))) {
				marList.addAll(filteredList);
			System.out.println("Total sales of the store in March -- " + sumSales);}
			filteredList.clear();
		}
		}
	}
	
	public static void getMostSoldItem(ArrayList<String> filteredList, LocalDate start) {
		for(int month=0;month<monthEndDates.length;month++) {
		if (start.equals(LocalDate.parse(monthEndDates[month]))) {
			
			List <String> listClone = new ArrayList<String>();
			
			for (int j = 0, length = icecreamNames.size(); j < length; j++) {
				for (int item = 0, len = filteredList.size(); item < len; item++) {
					String data = filteredList.get(item);

					String[] splittedList = data.split("[,]");
					if (splittedList[1].equals(icecreamNames.get(j))) {
						totalSalesPerMonth.add(Integer.parseInt(splittedList[0]));
						TotalRevenuePerIcecreamPerMonth.add(Integer.parseInt(splittedList[2]));
					}
				}
				int total = totalSalesPerMonth.stream().mapToInt(Integer::intValue).sum();
				totalSalesPerMonth.clear();
				
				int totalRevenue = TotalRevenuePerIcecreamPerMonth.stream().mapToInt(Integer::intValue).sum();
				TotalRevenuePerIcecreamPerMonth.clear();
				mostSoldItems.add(icecreamNames.get(j)+"--"+total);
				soldCount.add(total);
				mostSoldPrice.add(icecreamNames.get(j)+"--"+totalRevenue);
				soldPrice.add(totalRevenue);
			}
			
			int numberofSales = Collections.max(soldCount);
			String soldIceCount=String.valueOf(numberofSales);
	           for (String string : mostSoldItems) {
	               if(string.contains(soldIceCount)){
	                   listClone.add(string);
	               }
	           }
	           
			if (start.equals(LocalDate.parse(monthEndDates[0]))) {
				janSoldCount.addAll(soldCount);
				janSoldPrice.addAll(soldPrice);
		        System.out.println("\nMost Quantity sold item in january -- "+listClone);
		        
		        }
		           if (start.equals(LocalDate.parse(monthEndDates[1]))) {
		        	   febSoldCount.addAll(soldCount);
		        	   febSoldPrice.addAll(soldPrice);
		   	        System.out.println("Most Quantity sold item in february -- "+listClone);}
		           if (start.equals(LocalDate.parse(monthEndDates[2]))) {
		        	   marSoldCount.addAll(soldCount);
		        	   marSoldPrice.addAll(soldPrice);
		   	        System.out.println("Most Quantity sold item in march -- "+listClone);
		   	        System.out.println();
		   	        }
		           soldCount.clear();
		           soldPrice.clear();
		}
		}
	}
	
	public static void getMostSoldItemRevenue(ArrayList<String> filteredList, LocalDate start) {
		int h = 0,t = 0;
		if(start.equals(LocalDate.parse(monthEndDates[0]))) {
			h = Collections.max(janSoldCount);
			t = Collections.max(janSoldPrice);
			monthName = "January";
		}
		if(start.equals(LocalDate.parse(monthEndDates[1]))) {
			h = Collections.max(febSoldCount);
			t = Collections.max(febSoldPrice);
			monthName = "February";
		}
		if(start.equals(LocalDate.parse(monthEndDates[2]))) {
			h = Collections.max(marSoldCount);
			t = Collections.max(marSoldPrice);
			monthName = "March";
		}
		String soldIceCount=String.valueOf(h);
		String maxSoldPrice=String.valueOf(t);
		
		List <String> listClone = new ArrayList<String>(); 
           for (String string : mostSoldItems) {
               if(string.contains(soldIceCount)){
                   listClone.add(string);
               }
           }
        
		
        String line = listClone.get(0);
        String[] splitted = line.split("[--]");
         
        for (int i = 0, len = filteredList.size(); i < len; i++) {
			String line2 = filteredList.get(i);

			String[] splitted2 = line2.split("[,]");
			if (splitted2[1].contains(splitted[0])) {
				String minValue = splitted2[0];
				soldListPerMonth.add(minValue);
			}
		}
        List <String> mostRevenue = new ArrayList<String>(); 
           for (String string11 : mostSoldPrice) {
               if(string11.contains(maxSoldPrice)){
            	   mostRevenue.add(string11);
               }
           }
        System.out.println("Most sold Icecream's Revenue in "+monthName+" -- "+mostRevenue);
	}
	
	public static void getSoldItemCalculation(ArrayList<String> filteredList, LocalDate start) {
		int h = 0;
		if(start.equals(LocalDate.parse(monthEndDates[0]))) {
			h = Collections.max(janSoldCount);
			monthName = "January";
		}
		if(start.equals(LocalDate.parse(monthEndDates[1]))) {
			h = Collections.max(febSoldCount);
			monthName = "Febraury";
		}
		if(start.equals(LocalDate.parse(monthEndDates[2]))) {
			h = Collections.max(marSoldCount);
			monthName = "March";
		}
		String soldIceCount=String.valueOf(h);
		
		List <String> listClone = new ArrayList<String>(); 
           for (String string : mostSoldItems) {
               if(string.contains(soldIceCount)){
                   listClone.add(string);
               }
           }
        String line = listClone.get(0);
        String[] splitted = line.split("[--]");
        for (int i = 0, len = filteredList.size(); i < len; i++) {
			String line2 = filteredList.get(i);

			String[] splitted2 = line2.split("[,]");
			if (splitted2[1].contains(splitted[0])) {
				String minValue = splitted2[0];
				soldListPerMonth.add(minValue);
			}
		}
        List<Integer> listInteger = soldListPerMonth.stream().map(Integer::parseInt).collect(Collectors.toList());
		int average = listInteger.stream().mapToInt(Integer::intValue).sum();
		int averageValue = average/soldListPerMonth.size();
        System.out.println("\nMost sold icecream's Maximum count in "+monthName+" month - "+Collections.max(soldListPerMonth));
        System.out.println("Most sold icecream's Minimum count in "+monthName+" month - "+Collections.min(soldListPerMonth));
        System.out.println("Most sold icecream's Average count in "+monthName+" month - "+averageValue);
        soldListPerMonth.clear();
	}
}

