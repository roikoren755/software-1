package il.ac.tau.cs.sw1.hw6;

public class Polynomial {

	private double[] coefficients;

	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial() {
		this.coefficients = new double[]{0.0};
	}

	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) {
		this.coefficients = coefficients;
	}

	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial) {
		int thisDegree = this.getDegree();
		int otherDegree = polynomial.getDegree();
		boolean self = thisDegree >= otherDegree;
		double[] newCoefficients = self ? new double[thisDegree + 1] : new double[otherDegree + 1];
		for (int i = 0; i < (self ? otherDegree : thisDegree); i++)
			newCoefficients[i] = this.getCoefficient(i) + polynomial.getCoefficient(i);
		for (int i = 0; i < Math.abs(thisDegree - otherDegree); i++)
			newCoefficients[self ? i + otherDegree : i + thisDegree] = (self ? this.getCoefficient(i + otherDegree) : polynomial.getCoefficient(i + thisDegree));
		return new Polynomial(newCoefficients);

	}

	/*
	 * Multiplies a to this polynomial and returns
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a) {
		double[] newCoefficients = new double[this.getDegree() + 1];
		for (int i = 0; i < newCoefficients.length; i++)
			newCoefficients[i] = a * this.getCoefficient(i);
		return new Polynomial(newCoefficients);

	}

	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree() {
		return this.coefficients.length - 1;
	}

	/*
	 * Returns the coefficient of the variable x
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n) {
		if (n >= this.coefficients.length)
			return 0.0;
		return this.coefficients[n];
	}

	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.

	 */
	public Polynomial getFirstDerivation() {
		double[] newCoefficients = new double[this.getDegree()];
		for (int i = 0; i < newCoefficients.length; i++)
			newCoefficients[i] = (i + 1) * this.getCoefficient(i + 1);
		return new Polynomial(newCoefficients);
	}

	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(int x) {
		int deg = this.getDegree();
		double res = this.getCoefficient(deg);
		for (int i = this.getDegree() - 1; i >= 0; i--) {
			res *= x;
			res += this.getCoefficient(i);
		}
		return res;
	}
}
