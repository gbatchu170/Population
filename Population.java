import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	
 *	@since	
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	
	public void readFile()
	{
		cities = new ArrayList<City>();
		Scanner scanner = FileUtils.openToRead(DATA_FILE);
		scanner.useDelimiter("[\t\n]");
		while(scanner.hasNextLine())
		{
			String para1 = scanner.next();
			String para2 = scanner.next();
			String para3 = scanner.next();
			String para4 = scanner.next();
			para4 = para4.replaceAll(",", "");
			cities.add(new City(para1, para2, para3, Integer.parseInt(para4)));
			scanner.nextLine();
		}
		System.out.println(cities.size() + " cities in database\n");
	}
	
	public static void main (String[] args)
	{
		Population pop = new Population();
		pop.printIntroduction();
		pop.readFile();
		pop.runChoices();
		System.out.println("\nThank you for using Population!");
	}
	public int selectionEnterer()
	{
		int input = 0;
		input = Prompt.getInt("Enter Selection");
		while(!((input == 1) ||(input == 2) || (input == 3) || (input == 4)
			|| (input == 5) || (input == 6)  || (input == 9)))
		{
			System.out.println("ERROR: Invalid Input. Please enter a val" +
			"ue from 1-6, or a 9 to quit.");
			input = Prompt.getInt("Enter Selection");
		}
		System.out.println();
		return input;
	}
	public void runChoices()
	{
		SortMethods sm = new SortMethods();
		int choice = 0;
		do
		{
			printMenu();
			choice = selectionEnterer();
			long startMillisec = 0;
			if(choice == 1)
			{
				startMillisec= System.currentTimeMillis();
				List<City> tempList = sm.selectionSort(cities);
				System.out.println("Fifty least populous cities");
				System.out.print("     ");
				System.out.printf("%-22s %-22s %-12s %12s %n", "State", "City", "Type",
						"Population");
				for(int i=1; i<=50; i++)
				{
					System.out.printf("%-5s", i+":");
					System.out.println(tempList.get(i).toString());
				}
			}
			else if(choice == 2)
			{
				startMillisec= System.currentTimeMillis();
				List<City> tempList = sm.mergeSort(cities);
				System.out.println("Fifty most populous cities");
				System.out.print("     ");
				System.out.printf("%-22s %-22s %-12s %12s %n", "State", "City", "Type",
						"Population");
				for(int i=1; i<=50; i++)
				{
					System.out.printf("%-5s", (i)+":");
					System.out.println(tempList.get(tempList.size() - i).toString());
				}
			}
			else if(choice == 3)
			{
				startMillisec= System.currentTimeMillis();
				List<City> tempList = sm.insertionSort(cities);
				System.out.println("Fifty cities sorted by name");
				System.out.print("     ");
				System.out.printf("%-22s %-22s %-12s %12s %n", "State", "City", "Type",
						"Population");
				for(int i=1; i<=50; i++)
				{
					System.out.printf("%-5s", i+":");
					System.out.println(tempList.get(i-1).toString());
				}
			}
			else if(choice == 4)
			{
				startMillisec= System.currentTimeMillis();
				List<City> tempList = sm.mergeSort2(cities);
				System.out.println("Fifty cities sorted by name descending");
				System.out.print("     ");
				System.out.printf("%-22s %-22s %-12s %12s %n", "State", "City", "Type",
						"Population");
				for(int i=1; i<=50; i++)
				{
					System.out.printf("%-5s", i+":");
					System.out.println(tempList.get(tempList.size() - i).toString());
				}
			}
			else if(choice == 5)
			{
				List<City> tempList = new ArrayList<City>(10);
				String stateReq = "";
				boolean first = true;
				do
				{
					stateReq = Prompt.getString("Enter state name (ie. Alabama)");
					tempList = sm.bubbleSortQuery5(cities, stateReq );
					if(!first)
					{
						System.out.println("ERROR: " + stateReq + " is not valid");
					}
					first = false;
				}while(tempList.size() == 0);
				startMillisec= System.currentTimeMillis();
				System.out.println("\nFifty most populous cities in " + stateReq);
				System.out.print("     ");
				System.out.printf("%-22s %-22s %-12s %12s %n", "State", "City", "Type",
						"Population");
				for(int i=1; i<=Math.min(50, tempList.size()); i++)
				{
					System.out.printf("%-5s", i+":");
					System.out.println(tempList.get(tempList.size() - i).toString());
				}
			}
			else if(choice == 6)
			{
				List<City> tempList = new ArrayList<City>(10);
				String cityReq = "";
				boolean first = true;
				do
				{
					cityReq = Prompt.getString("Enter city name");
					tempList = sm.bubbleSortQuery6(cities, cityReq);
					if(!first)
					{
						System.out.println("ERROR: " + cityReq + " is not valid");
					}
					first = false;
				}while(tempList.size() == 0);
				startMillisec= System.currentTimeMillis();
				System.out.println("\n City " + cityReq + " by population");
				System.out.print("     ");
				System.out.printf("%-22s %-22s %-12s %12s %n", "State", "City", "Type",
						"Population");
				for(int i=1; i<=Math.min(50, tempList.size()); i++)
				{
					System.out.printf("%-5s", i+":");
					System.out.println(tempList.get(tempList.size() - i).toString());
				}
			}
			long endMillisec = System.currentTimeMillis();
			if(choice!=9)
				System.out.println("\nElapsed time " + (endMillisec-startMillisec) + " milliseconds.\n");
			
			
		}while(choice != 9);
	}
}
