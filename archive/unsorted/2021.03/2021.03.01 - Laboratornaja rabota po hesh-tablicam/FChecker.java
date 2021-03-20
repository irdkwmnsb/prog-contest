package main;


import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FChecker implements Checker {
    public FChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        Scanner out = new Scanner(actualOutput);
        Scanner in = new Scanner(input);
        Set<String> words = new TreeSet<>();
        int k = in.nextInt();
        String word = out.nextWord();
        int hashCode = word.hashCode();
        words.add(word);
        for (int i = 0; i < k - 1; i++) {
            if (!out.hasNextWord())
                return new Verdict(Verdict.VerdictType.PE, "Expected word");
            word = out.nextWord();
            if(word.length() > 1000) {
                return new Verdict(Verdict.VerdictType.PE, "Word too long");
            }
            if (word.hashCode() != hashCode) {
                return new Verdict(Verdict.VerdictType.WA, word + " doesn't have first word's hashcode (" + hashCode + ")");
            }
            if (words.contains(word)) {
                return new Verdict(Verdict.VerdictType.WA, word + " appears twice");
            }
        }
        return new Verdict(Verdict.VerdictType.OK, "Hash code was " + hashCode);
    }
}
