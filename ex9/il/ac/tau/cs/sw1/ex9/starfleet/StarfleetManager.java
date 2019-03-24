package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in ascending order by commission year
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear(Collection<Spaceship> fleet) {
		ArrayList<Spaceship> fleetArray = new ArrayList<Spaceship>();
		fleetArray.addAll(fleet);
		fleetArray.sort(new SpaceshipComparator());
		List<String> sortedDescriptions = new ArrayList<String>();
		for (Spaceship ship : fleetArray)
			sortedDescriptions.add(ship.toString());
		return sortedDescriptions;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> instancesPerClass = new HashMap<String, Integer>();
		for (Spaceship ship : fleet) {
			String name = ship.getClass().getSimpleName();
			instancesPerClass.put(name, instancesPerClass.getOrDefault(name, 0) + 1);
		}
		return instancesPerClass;
	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost(Collection<Spaceship> fleet) {
		int totalMaintenanceCost = 0;
		for (Spaceship ship : fleet)
			totalMaintenanceCost += ship.getAnnualMaintenanceCost();
		return totalMaintenanceCost;
	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> fleetWeaponNames = new HashSet<String>();
		for (Spaceship ship : fleet) {
			try {
				AbsBattleship absShip = (AbsBattleship) ship;
				for (Weapon weapon : absShip.getWeapon())
					fleetWeaponNames.add(weapon.getName());
			} catch (ClassCastException e) {
			}
		}
		return fleetWeaponNames;
	}

	/**
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int totalCrewMembers = 0;
		for (Spaceship ship : fleet)
			totalCrewMembers += ship.getCrewMembers().size();
		return totalCrewMembers;
	}

	/**
	 * Returns the average age of all officers serving on board of the given fleet's ships.
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		float sumOfAges = 0.0f;
		int numOfOfficers = 0;
		for (Spaceship ship : fleet) {
			for (CrewMember crewMember : ship.getCrewMembers()) {
				if (crewMember instanceof Officer) {
					numOfOfficers++;
					sumOfAges += crewMember.getAge();
				}
			}
		}
		return sumOfAges / numOfOfficers;
	}

	/**
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> highestRankingPerShip = new HashMap<Officer, Spaceship>();
		for (Spaceship ship : fleet) {
			Officer highestRanking = null;
			for (CrewMember crewMember : ship.getCrewMembers()) {
				if (crewMember instanceof Officer) {
					Officer officer = (Officer) crewMember;
					if (highestRanking == null)
						highestRanking = officer;
					else {
						if (highestRanking.getRank().compareTo(officer.getRank()) == -1)
							highestRanking = officer;
					}
				}
			}
			if (highestRanking == null)
				continue;
			highestRankingPerShip.put(highestRanking, ship);
		}
		return highestRankingPerShip;
	}

	/**
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted descendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		HashMap<OfficerRank, Integer> officerRanks = new HashMap<OfficerRank, Integer>();
		for (Spaceship ship : fleet) {
			for (CrewMember crewMember : ship.getCrewMembers()) {
				if (crewMember instanceof Officer) {
					OfficerRank rank = ((Officer) crewMember).getRank();
					officerRanks.put(rank, officerRanks.getOrDefault(rank, 0) + 1);
				}
			}
		}
		List<Map.Entry<OfficerRank, Integer>> ranksList = new ArrayList<Map.Entry<OfficerRank, Integer>>();
		ranksList.addAll(officerRanks.entrySet());
		ranksList.sort(new RankComparator());
		return ranksList;
	}

}