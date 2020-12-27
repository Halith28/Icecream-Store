
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class icecreamlist {
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> icecreamNames = new ArrayList<String>();
	static List<String> MinandMaxValue = new ArrayList<String>();
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("C:\\Users\\Admin\\Desktop\\file.txt"));

		while (s.hasNextLine()) {
			list.add(s.nextLine());
		}
		s.close();

		ArrayList<String> totalSales = new ArrayList<String>();
		
		for (int i = 1, len = list.size(); i < len; i++) {
			String line = list.get(i);

			String[] splitted = line.split("[,]");
			String totalQuantity = splitted[3];
			String IcecreamList = splitted[1];
			icecreamNames.add(IcecreamList);
			totalSales.add(totalQuantity);
		}
		List<Integer> totalSalesResult = totalSales.stream().map(Integer::parseInt).collect(Collectors.toList());
		int sumSales = totalSalesResult.stream().mapToInt(Integer::intValue).sum();
		Set<String> set = new HashSet<>(icecreamNames);
		icecreamNames.clear();
		icecreamNames.addAll(set);
		System.out.println("Total sales of the store -- " + sumSales);
		String st = "2019-01-01";
		String en = "2019-03-31";
		LocalDate start = LocalDate.parse(st);
		LocalDate end = LocalDate.parse(en);
		List<LocalDate> totalDates = new ArrayList<>();
		ArrayList<String> list11 = new ArrayList<String>();
		
		while (!start.isAfter(end)) {
			totalDates.add(start);
			ArrayList<String> hamd = new ArrayList<String>();
			String identifier = start.toString();
			for (int i = 0, len = list.size(); i < len; i++) {
				String line = list.get(i);
				String[] splitted = line.split("[,]");
				if (splitted[0].equals(identifier)) {
					String quantity = splitted[3];
					String name = splitted[1];
					String totalPrice = splitted[4];
					hamd.add(quantity + "," + name+","+totalPrice);
				}
			}
			list11.addAll(hamd);
			getJanTotals(list11, start);
			getFebTotals(list11, start);
			getMarchTotals(list11, start);
			start = start.plusDays(1);
		}
	}
	

	public static void getJanTotals(ArrayList<String> list11, LocalDate start) {
		String mnth1 = "2019-01-31";
		LocalDate monthend1 = LocalDate.parse(mnth1);
		ArrayList<String> janTotalSales = new ArrayList<String>();
		ArrayList<String> janTotalSales11 = new ArrayList<String>();
		if (start.equals(monthend1)) {
			janTotalSales.addAll(list11);
			getJanMostSoldItem(list11, start);
			list11.clear();
		}
		
		
		for (int i = 0, len = janTotalSales.size(); i < len; i++) {
			String line = janTotalSales.get(i);

			String[] splitted = line.split("[,]");
			String totalQuantity = splitted[0];
			janTotalSales11.add(totalQuantity);
		}
		List<Integer> totalSalesResult = janTotalSales11.stream().map(Integer::parseInt).collect(Collectors.toList());
		int sumSales = totalSalesResult.stream().mapToInt(Integer::intValue).sum();
		if (start.equals(monthend1))
		System.out.println("Total sales of the store in January " + sumSales);

	}

	public static void getFebTotals(ArrayList<String> list11, LocalDate start) {
		String mnth2 = "2019-02-28";
		LocalDate monthend2 = LocalDate.parse(mnth2);
		ArrayList<String> febTotalSales = new ArrayList<String>();
		ArrayList<String> febTotalSales11 = new ArrayList<String>();
		if (start.equals(monthend2)) {
			febTotalSales.addAll(list11);
			getFebMostSoldItem(list11, start);
			list11.clear();
		}
		
		
		for (int i = 0, len = febTotalSales.size(); i < len; i++) {
			String line = febTotalSales.get(i);

			String[] splitted = line.split("[,]");
			String totalQuantity = splitted[0];
			febTotalSales11.add(totalQuantity);
		}
		List<Integer> totalSalesResult = febTotalSales11.stream().map(Integer::parseInt).collect(Collectors.toList());
		int sumSales = totalSalesResult.stream().mapToInt(Integer::intValue).sum();
		if (start.equals(monthend2))
		System.out.println("Total sales of the store in Febraury " + sumSales);
	}

	public static void getMarchTotals(ArrayList<String> list11, LocalDate start) {
		String mnth2 = "2019-03-31";
		LocalDate monthend2 = LocalDate.parse(mnth2);
		ArrayList<String> marchTotalSales = new ArrayList<String>();
		ArrayList<String> marchTotalSales11 = new ArrayList<String>();
		if (start.equals(monthend2)) {
			marchTotalSales.addAll(list11);
			getMarMostSoldItem(list11, start);
			list11.clear();
		}
		
		for (int i = 0, len = marchTotalSales.size(); i < len; i++) {
			String line = marchTotalSales.get(i);

			String[] splitted = line.split("[,]");
			String totalQuantity = splitted[0];
			marchTotalSales11.add(totalQuantity);
		}
		List<Integer> totalSalesResult = marchTotalSales11.stream().map(Integer::parseInt).collect(Collectors.toList());
		int sumSales = totalSalesResult.stream().mapToInt(Integer::intValue).sum();
		if (start.equals(monthend2))
		System.out.println("Total sales of the store in March " + sumSales);
	}

	
	public static void getJanMostSoldItem(ArrayList<String> list11, LocalDate start) {
		String mnth1 = "2019-01-31";;
		LocalDate monthend1 = LocalDate.parse(mnth1);
		if (start.equals(monthend1)) {
			List<String> firstmonth = new ArrayList<String>();
			List<String> TotalRevenuePerIcecreamPerMonth = new ArrayList<String>();
			ArrayList<Integer> soldCount = new ArrayList<Integer>();
			ArrayList<String> mostSoldItems = new ArrayList<String>();
			ArrayList<Integer> soldPrice = new ArrayList<Integer>();
			ArrayList<String> mostSoldPrice = new ArrayList<String>();
			for (int j = 0, length = icecreamNames.size(); j < length; j++) {
				for (int i = 0, len = list11.size(); i < len; i++) {
					String line = list11.get(i);

					String[] splitted = line.split("[,]");
					if (splitted[1].equals(icecreamNames.get(j))) {
						String quantity = splitted[0];
						String IcecreamName = splitted[1];
						String TotalRevenuePerIcePerMonth = splitted[2];
						firstmonth.add(quantity);
						MinandMaxValue.add(IcecreamName+","+quantity);
						TotalRevenuePerIcecreamPerMonth.add(TotalRevenuePerIcePerMonth);
					}
				}
				List<Integer> listInteger = firstmonth.stream().map(Integer::parseInt).collect(Collectors.toList());
				int total = listInteger.stream().mapToInt(Integer::intValue).sum();
				firstmonth.clear();
				
				List<Integer> MonthRevenuePerIce = TotalRevenuePerIcecreamPerMonth.stream().map(Integer::parseInt).collect(Collectors.toList());
				int totalRevenue = MonthRevenuePerIce.stream().mapToInt(Integer::intValue).sum();
				TotalRevenuePerIcecreamPerMonth.clear();
				mostSoldItems.add(icecreamNames.get(j)+"--"+total);
				soldCount.add(total);
				mostSoldPrice.add(icecreamNames.get(j)+"--"+totalRevenue);
				soldPrice.add(totalRevenue);
//				System.out.println(icecreamNames.get(j) + "-->>" + totalRevenue);
				
			}
			int h = Collections.max(soldCount);
			int t = Collections.max(soldPrice);
			String soldIceCount=String.valueOf(h);
			String s=String.valueOf(t);
			
			List <String> listClone = new ArrayList<String>(); 
	           for (String string : mostSoldItems) {
	               if(string.contains(soldIceCount)){
	                   listClone.add(string);
	               }
	           }
	        System.out.println("\nMost Quantity sold item in january -- "+listClone);
	        String find11 = listClone.toString();
	        String line = listClone.get(0);
	        String[] splitted = line.split("[--]");
	        List <String> solve = new ArrayList<String>(); 
	        for (int i = 0, len = list11.size(); i < len; i++) {
				String line2 = list11.get(i);

				String[] splitted2 = line2.split("[,]");
				if (splitted2[1].contains(splitted[0])) {
					String minValue = splitted2[0];
					solve.add(minValue);
				}
			}
	        List <String> mostRevenue = new ArrayList<String>(); 
	           for (String string11 : mostSoldPrice) {
	               if(string11.contains(s)){
	            	   mostRevenue.add(string11);
	               }
	           }
	        System.out.println("Most sold Icecream's Revenue in january -- "+mostRevenue);
	        List<Integer> listInteger = solve.stream().map(Integer::parseInt).collect(Collectors.toList());
			int average = listInteger.stream().mapToInt(Integer::intValue).sum();
			int averageValue = average/solve.size();
	        System.out.println("Most sold icecream's Maximum count in january month - "+Collections.max(solve));
	        System.out.println("Most sold icecream's Minimum count in january month - "+Collections.min(solve));
	        System.out.println("Most sold icecream's Average count in january month - "+averageValue);

	       
		}
		
	}
	
	private static void getFebMostSoldItem(ArrayList<String> list11, LocalDate start) {
		String mnth1 = "2019-02-28";;
		LocalDate monthend1 = LocalDate.parse(mnth1);
		if (start.equals(monthend1)) {
			List<String> firstmonth = new ArrayList<String>();
			List<String> TotalRevenuePerIcecreamPerMonth = new ArrayList<String>();
			ArrayList<Integer> soldCount = new ArrayList<Integer>();
			ArrayList<String> mostSoldItems = new ArrayList<String>();
			ArrayList<Integer> soldPrice = new ArrayList<Integer>();
			ArrayList<String> mostSoldPrice = new ArrayList<String>();
			for (int j = 0, length = icecreamNames.size(); j < length; j++) {
				for (int i = 0, len = list11.size(); i < len; i++) {
					String line = list11.get(i);

					String[] splitted = line.split("[,]");
					if (splitted[1].equals(icecreamNames.get(j))) {
						String quantity = splitted[0];
						String TotalRevenuePerIcePerMonth = splitted[2];
						firstmonth.add(quantity);
						TotalRevenuePerIcecreamPerMonth.add(TotalRevenuePerIcePerMonth);
					}
				}
//	    	System.out.println("-->"+TotalRevenuePerIcecreamPerMonth);
				List<Integer> listInteger = firstmonth.stream().map(Integer::parseInt).collect(Collectors.toList());
				int total = listInteger.stream().mapToInt(Integer::intValue).sum();
				firstmonth.clear();
				
				List<Integer> MonthRevenuePerIce = TotalRevenuePerIcecreamPerMonth.stream().map(Integer::parseInt).collect(Collectors.toList());
				int totalRevenue = MonthRevenuePerIce.stream().mapToInt(Integer::intValue).sum();
				TotalRevenuePerIcecreamPerMonth.clear();
				mostSoldItems.add(icecreamNames.get(j)+"--"+total);
				soldCount.add(total);
				mostSoldPrice.add(icecreamNames.get(j)+"--"+totalRevenue);
				soldPrice.add(totalRevenue);
//				System.out.println(icecreamNames.get(j) + "-->>" + totalRevenue);
				
			}
			int h = Collections.max(soldCount);
			int t = Collections.max(soldPrice);
			String soldIceCount=String.valueOf(h);
			String s=String.valueOf(t);
			List <String> listClone = new ArrayList<String>(); 
	           for (String string : mostSoldItems) {
	               if(string.contains(soldIceCount)){
	                   listClone.add(string);
	               }
	           }
	        System.out.println("\nMost Quantity sold item in Febraury -- "+listClone);
	        List <String> mostRevenue = new ArrayList<String>(); 
	           for (String string11 : mostSoldPrice) {
	               if(string11.contains(s)){
	            	   mostRevenue.add(string11);
	               }
	           }
	        System.out.println("Most sold Icecream's Revenue in Febraury -- "+mostRevenue);
	        String find11 = listClone.toString();
	        String line = listClone.get(0);
	        String[] splitted = line.split("[--]");
	        List <String> solve = new ArrayList<String>(); 
	        for (int i = 0, len = list11.size(); i < len; i++) {
				String line2 = list11.get(i);

				String[] splitted2 = line2.split("[,]");
				if (splitted2[1].contains(splitted[0])) {
					String minValue = splitted2[0];
					solve.add(minValue);
				}
			}
	        List<Integer> listInteger = solve.stream().map(Integer::parseInt).collect(Collectors.toList());
			int average = listInteger.stream().mapToInt(Integer::intValue).sum();
			int averageValue = average/solve.size();
	        System.out.println("Most sold icecream's Maximum count in Febraury month - "+Collections.max(solve));
	        System.out.println("Most sold icecream's Minimum count in Febraury month - "+Collections.min(solve));
	        System.out.println("Most sold icecream's Average count in Febraury month - "+averageValue);

		}
		
	}

	private static void getMarMostSoldItem(ArrayList<String> list11, LocalDate start) {
		String mnth1 = "2019-03-31";;
		LocalDate monthend1 = LocalDate.parse(mnth1);
		if (start.equals(monthend1)) {
			List<String> firstmonth = new ArrayList<String>();
			List<String> TotalRevenuePerIcecreamPerMonth = new ArrayList<String>();
			ArrayList<Integer> soldCount = new ArrayList<Integer>();
			ArrayList<String> mostSoldItems = new ArrayList<String>();
			ArrayList<Integer> soldPrice = new ArrayList<Integer>();
			ArrayList<String> mostSoldPrice = new ArrayList<String>();
			for (int j = 0, length = icecreamNames.size(); j < length; j++) {
				for (int i = 0, len = list11.size(); i < len; i++) {
					String line = list11.get(i);

					String[] splitted = line.split("[,]");
					if (splitted[1].equals(icecreamNames.get(j))) {
						String quantity = splitted[0];
						String TotalRevenuePerIcePerMonth = splitted[2];
						firstmonth.add(quantity);
						TotalRevenuePerIcecreamPerMonth.add(TotalRevenuePerIcePerMonth);
					}
				}
//	    	System.out.println("-->"+TotalRevenuePerIcecreamPerMonth);
				List<Integer> listInteger = firstmonth.stream().map(Integer::parseInt).collect(Collectors.toList());
				int total = listInteger.stream().mapToInt(Integer::intValue).sum();
				firstmonth.clear();
				
				List<Integer> MonthRevenuePerIce = TotalRevenuePerIcecreamPerMonth.stream().map(Integer::parseInt).collect(Collectors.toList());
				int totalRevenue = MonthRevenuePerIce.stream().mapToInt(Integer::intValue).sum();
				TotalRevenuePerIcecreamPerMonth.clear();
				mostSoldItems.add(icecreamNames.get(j)+"--"+total);
				soldCount.add(total);
				mostSoldPrice.add(icecreamNames.get(j)+"--"+totalRevenue);
				soldPrice.add(totalRevenue);
//				System.out.println(icecreamNames.get(j) + "-->>" + totalRevenue);
				
			}
			int h = Collections.max(soldCount);
			int t = Collections.max(soldPrice);
			String soldIceCount=String.valueOf(h);
			String s=String.valueOf(t);
			List <String> listClone = new ArrayList<String>(); 
	           for (String string : mostSoldItems) {
	               if(string.contains(soldIceCount)){
	                   listClone.add(string);
	               }
	           }
	        System.out.println("\nMost Quantity sold item in March -- "+listClone);
	        List <String> mostRevenue = new ArrayList<String>(); 
	           for (String string11 : mostSoldPrice) {
	               if(string11.contains(s)){
	            	   mostRevenue.add(string11);
	               }
	           }
	        System.out.println("Most sold Icecream's Revenue in March -- "+mostRevenue);
	        String find11 = listClone.toString();
	        String line = listClone.get(0);
	        String[] splitted = line.split("[--]");
	        List <String> solve = new ArrayList<String>(); 
	        for (int i = 0, len = list11.size(); i < len; i++) {
				String line2 = list11.get(i);

				String[] splitted2 = line2.split("[,]");
				if (splitted2[1].contains(splitted[0])) {
					String minValue = splitted2[0];
					solve.add(minValue);
				}
			}
	        List<Integer> listInteger = solve.stream().map(Integer::parseInt).collect(Collectors.toList());
			int average = listInteger.stream().mapToInt(Integer::intValue).sum();
			int averageValue = average/solve.size();
	        System.out.println("Most sold icecream's Maximum count in March month - "+Collections.max(solve));
	        System.out.println("Most sold icecream's Minimum count in March month - "+Collections.min(solve));
	        System.out.println("Most sold icecream's Average count in March month - "+averageValue);

		}
		
		
	}
}

