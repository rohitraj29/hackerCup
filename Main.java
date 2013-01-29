import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

  private static final Integer ONE = new Integer(1);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			List<Integer> outputStorageList = new ArrayList<Integer>();
			String intStr = br.readLine();
			int numTestCases = Integer.parseInt(intStr);
			for (int i = 0; i < numTestCases; i++) {
				outputStorageList.add(getMaximumBeauty(br.readLine()));
			}
			dumpResult(outputStorageList);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private static void dumpResult(List<Integer> outputStorageList) {
		Iterator<Integer> iterator = outputStorageList.iterator();
		int num =1;
		while (iterator.hasNext()) {
			System.out.println("Case # " + num + ": " + iterator.next());
			++num;
		}

	}

	private static Integer getMaximumBeauty(String readLine) {
		readLine = readLine.toLowerCase();
		readLine = readLine.replaceAll("[^\\p{L}\\p{Z}]", "");// Remove Special
																// Characters
		readLine = readLine.replaceAll("\\s+", "");// Remove spaces
		
		Map<String, Integer> occurenceMap = prepareOccurenceMap(readLine);
		
		SortedSet<StrWeight> sortedSet = createSortedSet(occurenceMap);
		
		return assignWeights(sortedSet);
		 
	}

	private static Integer assignWeights(SortedSet<StrWeight> sortedSet) {
		Iterator<StrWeight> iterator = sortedSet.iterator();
		Integer maxWeight = 26;
		Integer totalWeight =0;
		while(iterator.hasNext())
		{
			StrWeight element = iterator.next();
			element.setAssignedWeight(maxWeight);
			element.setTotalWeight(element.getAssignedWeight()*element.getOccurence());
			totalWeight+=element.getTotalWeight();
			--maxWeight;
		}
		
		return totalWeight;
		
	}

	private static SortedSet<StrWeight> createSortedSet(
			Map<String, Integer> occurenceMap) {
		Set<String> keySet = occurenceMap.keySet();
		SortedSet<StrWeight> sortedSet = new TreeSet<StrWeight>();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String letter = iterator.next();
			StrWeight strWeight = new StrWeight(letter,
					occurenceMap.get(letter));
			sortedSet.add(strWeight);
		}
		return sortedSet;
	}

	public static Map<String, Integer> prepareOccurenceMap(String inputString) {
		Map<String, Integer> container = new HashMap<String, Integer>();
		for (int i = 0; i < inputString.length(); i++) {
			String key = inputString.substring(i, i + 1);
			if (container.containsKey(key)) {
				Integer count = container.get(key);
				count++;
				container.put(key, count);
			} else {
				container.put(key, ONE);
			}
		}

		return container;
	}

}

class StrWeight implements Comparable<StrWeight> {
	private String letter;
	private Integer occurence;
	private Integer assignedWeight;
	private Integer totalWeight;

	public Integer getAssignedWeight() {
		return assignedWeight;
	}

	public void setAssignedWeight(Integer assignedWeight) {
		this.assignedWeight = assignedWeight;
	}

	public Integer getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	public StrWeight(String next, Integer integer) {
		this.letter = next;
		this.occurence = integer;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public Integer getOccurence() {
		return occurence;
	}

	public void setOccurence(Integer occurence) {
		this.occurence = occurence;
	}

	@Override
	public int compareTo(StrWeight o) {
		if (this.getOccurence() < o.getOccurence())
			return 1;
		else if (this.getOccurence() > o.getOccurence())
			return -1;
		else if (this.getLetter().equalsIgnoreCase(o.getLetter()))
			return 0;
		else
			return 1;
	}

	@Override
	public String toString() {

		return this.getLetter() + " : " + this.getOccurence().toString();
	}

}
