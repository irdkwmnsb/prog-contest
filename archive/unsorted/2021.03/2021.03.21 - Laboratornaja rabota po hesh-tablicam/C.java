package main;



import main.hash.MyLinkedHashMap;

import java.io.PrintWriter;

public class C {
    PrintWriter out;

    public static String toStringOrNone(Object obj) {
        if (obj == null)
            return "none";
        return obj.toString();
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        MyLinkedHashMap<String, String> map = new MyLinkedHashMap<>();
        while (in.hasNextWord()) {
            String op = in.nextWord();
            switch (op) {
                case "put":
                    map.put(in.nextWord(), in.nextWord());
                    break;
                case "delete":
                    map.delete(in.nextWord());
                    break;
                case "get":
                    out.println(toStringOrNone(map.get(in.nextWord())));
                    break;
                case "prev":
                    out.println(toStringOrNone(map.prev(in.nextWord())));
                    break;
                case "next":
                    out.println(toStringOrNone(map.next(in.nextWord())));
                    break;
            }
        }
    }
}
