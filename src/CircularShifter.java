import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CircularShifter {

	public void shiftLine(String line) {
		String shiftedLine = line;

		List<String> bagOfWords = tokenize(line);
		int numberOfShifts = bagOfWords.size() - 1;

		shiftedLine(shiftedLine);
		for (int i = 0; i < numberOfShifts; i++) {
			shiftedLine = shiftWords(bagOfWords);
			shiftedLine(shiftedLine);
			bagOfWords = tokenize(shiftedLine);
		}
	}

	public String shiftWords(List<String> words) {
		StringBuilder shiftedLine = new StringBuilder();

		int lastWordIndex = words.size() - 1;
		int index = lastWordIndex;

		do {
			shiftedLine.append(words.get(index) + " ");
			if (index == lastWordIndex) {
				index = -1;
			}
			index++;
		} while (index < lastWordIndex);

		shiftedLine.deleteCharAt(shiftedLine.length() - 1);

		return shiftedLine.toString();
	}

	public List<String> tokenize(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line);

		List<String> tokens = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

}
