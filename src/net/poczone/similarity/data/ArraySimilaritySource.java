package net.poczone.similarity.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArraySimilaritySource<User> implements SimilaritySource<User, Integer> {
	private User[][] groups;

	public ArraySimilaritySource(User[][] groups) {
		this.groups = groups;
	}

	@Override
	public Collection<Integer> getTargets() {
		return IntStream.range(0, groups.length).boxed().collect(Collectors.toList());
	}

	@Override
	public List<User> getSupporters(Integer target) {
		return Arrays.asList(groups[target]);
	}
}
