package src.il.ac.tau.cs.software1.ip;

import java.nio.ByteBuffer;

public class IPAddressInt implements IPAddress {

	private int address;

	IPAddressInt(int address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < 3; i++) {
			res += this.getOctet(i);
			res += ".";
		}
		res += this.getOctet(3);
		return res;
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
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(this.address);
		return (int) (buffer.get(index) & 0xFF);
	}

	@Override
	public boolean isPrivateNetwork() {
		int firstOctet = this.getOctet(0);
		if (firstOctet == 10)
			return true;
		int secondOctet = this.getOctet(1);
		if (firstOctet == 172 && secondOctet >= 16 && secondOctet <= 31)
			return true;
		if (firstOctet == 192 && secondOctet == 168)
			return true;
		if (firstOctet == 169 && secondOctet == 254)
			return true;
		return false;
	}

}
