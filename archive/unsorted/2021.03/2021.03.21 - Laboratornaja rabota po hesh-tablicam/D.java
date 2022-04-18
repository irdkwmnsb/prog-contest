package main;

import main.Scanner;
import main.hash.MyLinkedHashMap;

import java.io.PrintWriter;
import java.util.List;

public class D {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        MyLinkedHashMap<String, Treap<String>> map = new MyLinkedHashMap<>();
        while (in.hasNextWord()) {
            String op = in.nextWord();
            switch (op) {
                case "put": {
                    String key = in.nextWord();
                    String val = in.nextWord();
                    Treap<String> t = map.get(key);
                    if (t == null) {
                        t = new Treap<>();
                        map.put(key, t);
                    }
                    t.insert(val);
                    break;
                }
                case "delete": {
                    Treap<String> t = map.get(in.nextWord());
                    String val = in.nextWord();
                    if (t != null) {
                        t.delete(val);
                    }
                    break;
                }
                case "deleteall": {
                    map.delete(in.nextWord());
                    break;
                }
                case "get": {
                    Treap<String> t = map.get(in.nextWord());
                    if (t != null) {
                        List<String> res = t.toList();
                        out.print(res.size());
                        out.print(" ");
                        for (String a : res) {
                            out.print(a);
                            out.print(" ");
                        }
                        out.println();
                    } else {
                        out.println("0");
                    }
                }
            }
        }
    }
}
