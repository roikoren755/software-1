package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends AbsSpaceship {
	private static int MAINTENANENCE_COST = 3000;
	private static int CARGO_COST = 5;
	private static int PASSENGER_COST = 3;
	private int cargoCapacity;
	private int passengerCapacity;

	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers,
	                     int cargoCapacity, int passengerCapacity) {
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}

	public int getCargoCapacity() {
		return this.cargoCapacity;
	}

	public int getPassengerCapacity() {
		return this.passengerCapacity;
	}

	public int getAnnualMaintenanceCost() {
		int annualMaintenanceCost = TransportShip.MAINTENANENCE_COST;
		annualMaintenanceCost += TransportShip.CARGO_COST * this.getCargoCapacity();
		annualMaintenanceCost += TransportShip.PASSENGER_COST * this.getPassengerCapacity();
		return annualMaintenanceCost;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\n\tCargoCapacity=").append(this.getCargoCapacity());
		sb.append("\n\tPassengerCapacity=").append(this.getPassengerCapacity());
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}