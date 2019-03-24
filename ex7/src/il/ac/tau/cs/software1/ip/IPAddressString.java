package src.il.ac.tau.cs.software1.ip;

public class IPAddressString implements IPAddress {

	private String address;

	IPAddressString(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.address;
	}

	@Override
	public boolean equals(IPAddress other) {
		for (int i = 0; i < 4; i++) {
			if (this.getOctet(i) != other.getOctet(i))
				return false;
		}
		return true;
	}

	@Override
	public int getOctet(int index) {
		return Integer.parseInt(this.address.split("\\.")[index]);
	}

	private boolean inRange(String val, String min, String max) {
		if (val.length() < min.length() || val.length() > max.length())
			return false;
		if (val.length() < max.length() && val.length() > min.length())
			return true;
		if (val.length() != max.length()) {
			for (int i = 0; i < val.length(); i++) {
				if (val.charAt(i) > min.charAt(i))
					return true;
				if (val.charAt(i) < min.charAt(i))
					return false;
			}
			return true;
		}
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i) < max.charAt(i))
				return true;
			if (val.charAt(i) > max.charAt(i))
				return false;
		}
		return true;
	}

	@Override
	public boolean isPrivateNetwork() {
		String[] octets = this.address.split("\\.");
		if (octets[0].equals("10"))
			return true;
		if (octets[0].equals("172") && octets[1].length() == 2 && inRange(octets[1], "16", "31"))
			return true;
		if (octets[0].equals("192") && octets[1].equals("168"))
			return true;
		if (octets[0].equals("169") && octets[1].equals("254"))
			return true;
		return false;
	}

}
