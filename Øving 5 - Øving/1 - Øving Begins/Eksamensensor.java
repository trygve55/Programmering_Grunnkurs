

class Sensurering {
	final String eksamensFag;
	final String eksamensType;
	final double eksamensTid;     	//hour
	final int antallBersvarelser;

	public Sensurering(int type) {  //muntlig

	}


	public double sensurerMuntlig(double tid) {
		return tid + 3;
		}

	public double sensurerSkriftlig(int besvarelser, double tid) {
		return besvarelser * 0.15 * tid;
		}

	public double sensurerProsjekt() {
		return 8;
		}
}

class Eksamenssensor {

	public static void main(String[] args) {



	}

}