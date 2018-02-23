package net.poczone.similarity.data;

public class SimilarityRankedPair<User extends Comparable<User>> extends SimilarityPair<User>
		implements Comparable<SimilarityRankedPair<User>> {
	private int firstCount;
	private int secondCount;
	private int bothCount;
	private double similarity;

	public SimilarityRankedPair(User first, User second, int firstCount, int secondCount, int bothCount,
			double similarity) {
		super(first, second);
		this.firstCount = firstCount;
		this.secondCount = secondCount;
		this.bothCount = bothCount;
		this.similarity = similarity;
	}

	public int getFirstCount() {
		return firstCount;
	}

	public int getSecondCount() {
		return secondCount;
	}

	public int getBothCount() {
		return bothCount;
	}

	public double getSimilarity() {
		return similarity;
	}

	@Override
	public int compareTo(SimilarityRankedPair<User> other) {
		int cmp = Double.compare(other.similarity, similarity);
		if (cmp != 0) {
			return cmp;
		}

		cmp = getFirst().compareTo(other.getFirst());
		if (cmp != 0) {
			return cmp;
		}

		cmp = getSecond().compareTo(other.getSecond());
		return cmp;
	}

	@Override
	public String toString() {
		return super.toString() + " = (" + firstCount + "|" + secondCount + "|" + bothCount + ") = " + similarity;
	}
}
