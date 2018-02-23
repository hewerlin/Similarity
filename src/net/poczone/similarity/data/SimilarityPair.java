package net.poczone.similarity.data;

public class SimilarityPair<User extends Comparable<User>> {
	private User first;
	private User second;

	public SimilarityPair(User first, User second) {
		this.first = first;
		this.second = second;
	}

	public User getFirst() {
		return first;
	}

	public User getSecond() {
		return second;
	}

	public User getOther(User reference) {
		if (first.equals(reference)) {
			return second;
		} else if (second.equals(reference)) {
			return first;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public boolean has(User user) {
		return first.equals(user) || second.equals(user);
	}

	public static <User extends Comparable<User>> SimilarityPair<User> createOrdered(User a, User b) {
		if (a.compareTo(b) < 0) {
			return new SimilarityPair<User>(a, b);
		} else {
			return new SimilarityPair<User>(b, a);
		}
	}

	@Override
	public String toString() {
		return first + "+" + second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimilarityPair<?> other = (SimilarityPair<?>) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
}
