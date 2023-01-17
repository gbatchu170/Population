import java.util.List;
/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	
 *	@since	
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName;
	private String stateName;
	private String cityType;
	private int population;
	
	// constructor
	public City(String s2, String s1, String s3, int i1)
	{
		cityName = s1;
		stateName = s2;
		cityType = s3;
		population = i1;
	}
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City otherCity)
	{
		if(population != otherCity.population)
			return population - otherCity.population;
		if(!stateName.equals(otherCity.stateName))
			return stateName.compareTo(otherCity.stateName);
		return cityName.compareTo(otherCity.cityName);
	}
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City otherCity)
	{
		return cityName.equals(otherCity.cityName) 
			&& stateName.equals(otherCity.stateName)
			&& cityType.equals(otherCity.cityType) 
			&& population == otherCity.population;
	}
	
	
	/**	Accessor methods */
	public String getCityName()	 {return cityName;}
	public String getStateName()	 {return stateName;}
	public String getCityType()	 {return cityType;}
	public int getPopulation()	 {return population;}
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, cityType,
						population);
	}
}
