package com.rohit.fb.hkcup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BalancedSmileys {

  public static final String LEFT_PARAN = "(";
	public static final String RIGHT_PARAN = ")";
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			List<Boolean> outputStorageList = new ArrayList<Boolean>();
			String intStr = br.readLine();
			int numTestCases = Integer.parseInt(intStr);
			for (int i = 0; i < numTestCases; i++) {
				outputStorageList.add(processLine(br.readLine()));
			}
			dumpResult(outputStorageList);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private static void dumpResult(List<Boolean> outputStorageList) {
		int num = 1;
		Iterator<Boolean> iterator = outputStorageList.iterator();
		while (iterator.hasNext()) {
			String output = (iterator.next()) ? "YES" : "NO";
			System.out.println("Case # " + num + ": " + output);
			++num;
		}

	}

	private static Boolean processLine(String inputLine) {
		Boolean returnVar = Boolean.FALSE;
		int length = inputLine.length();
		if (length == 0) {
			/*
			 * In case the input is an empty String we can safely Assume that it
			 * is a case of a balanced Smiley and set the returnVar TRUE
			 */

			returnVar = Boolean.TRUE;
		} else if (inputLine.contains(LEFT_PARAN)
				|| inputLine.contains(RIGHT_PARAN)) {
			returnVar = Boolean.TRUE;
			/*
			 * We need to look further into the Iteration only if the line
			 * contains a parentheses either left or right
			 */
			int lIndex = -1, rIndex = -1, lCount = 0, rCount = 0;
			for (int i = 0; i < length; i++) {

				char vChar = inputLine.charAt(i);
				if (vChar == ':') {
					++i;
				} else if (vChar == '(' || vChar == ')') {
					if (vChar == '(') {
						lIndex = i;
						lCount++;
					}
					if (vChar == ')') {
						rCount++;
						if (lIndex != -1) {
							rIndex = -1;
							lIndex = -1;
						}

					}
				}

			}
			if (lIndex == -1 && rIndex == -1 && lCount == rCount)
				returnVar = Boolean.TRUE;
			else
				returnVar = Boolean.FALSE;
		} else {
			/*
			 * If the string does not contains any parentheses then we can
			 * safely set the return variable as TRUE
			 */
			returnVar = Boolean.TRUE;
		}
		return returnVar;
	}

}
