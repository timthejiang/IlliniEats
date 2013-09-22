package com.cs196.illinieats;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DiningHallManager {
	private List<String> allowedNames = Arrays.asList("ISR", "LAR", "PAR", "FAR",
			"Ikenberry", "Busey-Evans");
	private HashMap<String, DiningHall> halls;
	
	private static DiningHallManager obj;
	
	private DiningHallManager()
	{
		halls = new HashMap<String, DiningHall>();
		for(String name : allowedNames)
			halls.put(name, null);
	}
	
	public static DiningHallManager getInstance()
	{
		if(obj == null)
			obj = new DiningHallManager();
		return obj;
	}
	
	public DiningHall getHallByName(String name)
	{
		if(allowedNames.contains(name) && obj != null)
		{
			if(halls.get(name) == null)
				halls.put(name, new DiningHall(name));
			return halls.get(name);
		}
		else
			return null;
	}
}
