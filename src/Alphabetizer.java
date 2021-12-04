import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Alphabetizer {
	private TreeSet<String> tree;

	public Alphabetizer() {
		tree = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	}

	public List<String> getAlphabetizedLines() {
		List<String> lines = new ArrayList<String>();
		Iterator<String> itr = tree.iterator();
		while (itr.hasNext()) {
			lines.add(itr.next());
		}
		return lines;
	}

	public void addToAlphabetizedSet(String line) {
		tree.add(line);
	}
}
