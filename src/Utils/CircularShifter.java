package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CircularShifter {
	private List<String> circularShiftedLines;

	public List<String> shiftLines(List<String> lines){
		circularShiftedLines = new ArrayList<>();
		for (String line : lines){
			shiftLine(line);
		}
		return circularShiftedLines;
	}

	public void shiftLine(String line) {
		circularShiftedLines.add(line);

		List<String> bagOfWords = tokenize(line);
		int numberOfShifts = bagOfWords.size() - 1;

		for (int i = 0; i < numberOfShifts; i++) {
			String shiftedLine = shiftWords(bagOfWords);
			circularShiftedLines.add(shiftedLine);
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
