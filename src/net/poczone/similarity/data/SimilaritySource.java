package net.poczone.similarity.data;

import java.util.List;

public interface SimilaritySource<User, Target> {
	Iterable<Target> getTargets();

	List<User> getSupporters(Target target);
}
