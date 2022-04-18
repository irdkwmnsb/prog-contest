package main;



import net.egork.chelper.checkers.TokenChecker;
import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

import java.util.*;

public class CChecker extends TokenChecker implements Checker {
    public CChecker(String parameters) {
        super(parameters);
    }

    static class MyEntry {
        String key, val;

        public MyEntry(String key, String val) {
            this.key = key;
            this.val = val;
        }

        public String getKey() {
            return key;
        }

        public String getVal() {
            return val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyEntry)) return false;
            MyEntry myEntry = (MyEntry) o;
            return Objects.equals(getKey(), myEntry.getKey()) && Objects.equals(getVal(), myEntry.getVal());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getVal());
        }
    }


    public Verdict check(String input, String expectedOutput, String actualOutput) {
        Scanner in = new Scanner(input);
        Scanner wrongAnswer = new Scanner(actualOutput);
        StringBuilder out = new StringBuilder();
        List<MyEntry> map = new LinkedList<>();
        while (in.hasNextWord()) {
            String op = in.nextWord();
            switch (op) {
                case "put": {
                    String key = in.nextWord();
                    String val = in.nextWord();
                    map.removeIf(s -> s.getKey().equals(key));
                    map.add(new MyEntry(key, val));
                    break;
                }
                case "delete": {
                    String key = in.nextWord();
                    map.removeIf(s -> s.getKey().equals(key));
                    break;
                }
                case "get": {
                    String key = in.nextWord();
                    boolean found = false;
                    for (MyEntry myEntry : map) {
                        if (myEntry.key.equals(key)) {
                            out.append(myEntry.val).append("\n");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        out.append("none\n");
                    }
                    break;
                }
                case "prev": {
                    String key = in.nextWord();
                    boolean found = false;
                    for (int i = 0; i < map.size() - 1; i++) {
                        if (map.get(i + 1).key.equals(key)) {
                            out.append(map.get(i).val).append("\n");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        out.append("none\n");
                    }
                    break;
                }
                case "next": {
                    String key = in.nextWord();
                    boolean found = false;
                    for (int i = 1; i < map.size(); i++) {
                        if (map.get(i - 1).key.equals(key)) {
                            out.append(map.get(i).val).append("\n");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        out.append("none\n");
                    }
                    break;
                }
            }
        }
        String correct = out.toString();
        if (super.check(input, correct, actualOutput).type == Verdict.VerdictType.OK) {
            return Verdict.OK;
        } else {
            return new Verdict(Verdict.VerdictType.WA, "Expected:\n" + correct);
        }
    }
}
