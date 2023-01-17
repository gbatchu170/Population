import java.util.List;
import java.util.ArrayList;
public class SortMethods {
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) 
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	public List<City> bubbleSortQuery5(List<City> in, String stateReq)
	{
		List<City> arr = new ArrayList<City>();
		for(int i=0; i<in.size(); i++)
		{
			if(in.get(i).getStateName().equalsIgnoreCase(stateReq))
			{
				arr.add(in.get(i));
			}
		}
		if(arr.size() == 0)
			return arr;
		for(int i=0; i<arr.size() - 1; i++)
		{
			for(int j=0; j<arr.size() - 1 - i; j++) 
			{
				if(arr.get(j).getPopulation() > arr.get(j+1).getPopulation())
					swap(arr, j, j+1);
			}
		}
		return arr;
	}
	
	public List<City> bubbleSortQuery6(List<City> in, String stateReq)
	{
		List<City> arr = new ArrayList<City>();
		for(int i=0; i<in.size(); i++)
		{
			if(in.get(i).getCityName().equalsIgnoreCase(stateReq))
			{
				arr.add(in.get(i));
			}
		}
		if(arr.size() == 0)
			return arr;
		for(int i=0; i<arr.size() - 1; i++)
		{
			for(int j=0; j<arr.size() - 1 - i; j++)
			{
				if(arr.get(j).getPopulation() > arr.get(j+1).getPopulation())
					swap(arr, j, j+1);
			}
		}
		return arr;
	}
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public List selectionSort(List<City> arr) 
	{
		for(int i=0; i<arr.size(); i++)
		{
			int lowestIndex = i;
			for(int j=i+1; j<arr.size(); j++)
			{
				if(arr.get(j).getPopulation()<arr.get(lowestIndex).getPopulation())
					lowestIndex = j;
			}
			swap(arr, i, lowestIndex);
		}
		
		return arr;
	}
	
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public List<City> insertionSort(List<City> arr) 
	{
		for(int i=1; i<arr.size(); i++)
		{
			int j = i - 1; 
			City curCity = arr.get(i);
			while(j>=0 && comparerInsertion(arr, j, curCity))
			{
				arr.set(j+1,arr.get(j));
				j = j-1;
			}
			arr.set(j+1, curCity);
		}
		
		return arr;
	}
	
	public boolean comparerInsertion(List<City> arr, int j, City curCity)
	{
		if((arr.get(j).getCityName()).compareTo(curCity.getCityName()) == 0 )
			return arr.get(j).getPopulation() < curCity.getPopulation();
		else
			return (arr.get(j).getCityName()).compareTo(curCity.getCityName()) > 0;
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public List<City> mergeSort(List<City> arr) 
	{
		recursiveCall(arr, arr.size());
		return arr;
	}
	
	public List<City> mergeSort2(List<City> arr)
	{
		recursiveCall2(arr, arr.size());
		return arr;
	}
	
	
	public void recursiveCall(List<City> arr, int end)
	{
		if(end < 2)
			return;
		int middle = end/2;
		List<City> left = new ArrayList<City>();
		List<City> right = new ArrayList<City>();
		for(int i=0; i<middle; i++)
		{
			left.add(arr.get(i));
		}
		for(int i=middle; i<end; i++)
		{
			right.add(arr.get(i));
		}
		recursiveCall(left, middle);
		recursiveCall(right, end-middle);
		
		merge(arr, left, right, middle, end-middle);
	}
	
	public void recursiveCall2(List<City> arr, int end)
	{
		if(end < 2)
			return;
		int middle = end/2;
		List<City> left = new ArrayList<City>();
		List<City> right = new ArrayList<City>();
		for(int i=0; i<middle; i++)
		{
			left.add(arr.get(i));
		}
		for(int i=middle; i<end; i++)
		{
			right.add(arr.get(i));
		}
		recursiveCall2(left, middle);
		recursiveCall2(right, end-middle);
		
		merge2(arr, left, right, middle, end-middle);
	}
	
	public void merge(List<City> arr, List<City> sub1, List<City> sub2, int left, int right)
	{
		int sub1Counter = 0;
		int sub2Counter = 0;
		int arrCounter = 0;
		while(sub1Counter<left && sub2Counter<right)
		{
			if(sub1.get(sub1Counter).getPopulation() <= sub2.get(sub2Counter).getPopulation())
				arr.set(arrCounter++, sub1.get(sub1Counter++));
			else
				arr.set(arrCounter++, sub2.get(sub2Counter++));
		}
		while(sub1Counter<left)
		{
			arr.set(arrCounter++, sub1.get(sub1Counter++));
		}
		while(sub2Counter<right)
		{
			arr.set(arrCounter++, sub2.get(sub2Counter++));
		}
	}
	
	public void merge2(List<City> arr, List<City> sub1, List<City> sub2, int left, int right)
	{
		int sub1Counter = 0;
		int sub2Counter = 0;
		int arrCounter = 0;
		while(sub1Counter<left && sub2Counter<right)
		{
			if(sub1.get(sub1Counter).getCityName().compareTo(sub2.get(sub2Counter).getCityName()) < 0)
				arr.set(arrCounter++, sub1.get(sub1Counter++));
			else
				arr.set(arrCounter++, sub2.get(sub2Counter++));
		}
		while(sub1Counter<left)
		{
			arr.set(arrCounter++, sub1.get(sub1Counter++));
		}
		while(sub2Counter<right)
		{
			arr.set(arrCounter++, sub2.get(sub2Counter++));
		}
	}
	
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
	}
}
