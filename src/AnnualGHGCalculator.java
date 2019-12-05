
public class AnnualGHGCalculator {

	private double annualEquivalentGHG;

	public AnnualGHGCalculator(double equivalentGHG) {
		this.annualEquivalentGHG = equivalentGHG * 365;
	}

	public double getEquivalentCarKM() { // Source: US EPA
		return (this.annualEquivalentGHG / 0.25);
	}

	public double getEquivalentLightbulbDays() { // Source: US EPA
		return (this.annualEquivalentGHG / 0.093);
	}

	public double getEquivalentFlightKM() { // Source: css.umich.edu
		return (this.annualEquivalentGHG / 0.186);
	}
}
