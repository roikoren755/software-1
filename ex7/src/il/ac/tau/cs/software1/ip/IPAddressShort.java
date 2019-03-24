package src.il.ac.tau.cs.software1.ip;

public class IPAddressShort implements IPAddress {

	private short[] address;

	IPAddressShort(short[] address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < 3; i++) {
			res += this.address[i];
			res += ".";
		}
		res += this.address[3];
		return res;
	}

	@Override
	public boolean equals(IPAddress other) {
		for (int i = 0; i < 4; i++) {
			if (this.address[i] != other.getOctet(i))
				return false;
		}
		return true;
	}

	@Override
	public int getOctet(int index) {
		return this.address[index];
	}

	@Override
	public boolean isPrivateNetwork() {
		if (this.address[0] == 10)
			return true;
		if (this.address[0] == 172 && this.address[1] >= 16 && this.address[1] <= 31)
			return true;
		if (this.address[0] == 192 && this.address[1] == 168)
			return true;
		if (this.address[0] == 169 && this.address[1] == 254)
			return true;
		return false;
	}

}
