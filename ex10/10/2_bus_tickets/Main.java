public class Main {

	private final static float BASE_PRICE_PER_DAY = 2.0f;

	public static void main(String[] args) {
		System.out.println("Avialable tickets:");
		for (BusTicket ticket : BusTicket.values()) {
			System.out.println(
					String.format("Name: %10s \t Validity: %3d days "
									+ "\t Price: %3.2f NIS \t Description: %s",
							ticket, ticket.duration(), ticket.price(), ticket.description()));
		}
	}

	public enum BusTicket {
		DAILY,
		WEEKLY,
		MONTHLY,
		QUARTERLY,
		YEARLY;

		public int duration() {
			switch (this) {
				case DAILY:
					return 1;
				case WEEKLY:
					return 7;
				case MONTHLY:
					return 30;
				case QUARTERLY:
					return 90;
				case YEARLY:
					return 365;
				default:
					return Integer.MAX_VALUE;
			}
		}

		public float price() {
			float baseCost = this.duration() * BASE_PRICE_PER_DAY;
			switch (this) {
				case WEEKLY:
					return baseCost * 0.95f;
				case MONTHLY:
					return baseCost * 0.9f;
				case QUARTERLY:
					return baseCost * 0.8f;
				case YEARLY:
					return baseCost * 0.65f;
				default:
					return baseCost;
			}
		}

		public String description() {
			String desc = "Ticket valid for ";
			switch (this) {
				case DAILY:
					return desc + "24 hours";
				case WEEKLY:
					return desc + "7 days";
				case MONTHLY:
					return desc + "30 days";
				case QUARTERLY:
					return desc + "3 month";
				case YEARLY:
					return desc + "1 year";
				default:
					return desc + "forever";
			}
		}
	}
}