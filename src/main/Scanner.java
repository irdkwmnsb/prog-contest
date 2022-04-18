package main;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Scanner {
    @FunctionalInterface
    public interface TokenChecker {
        boolean test(char ch);
    }

    private final Reader in;
    private int lastCh = -1;
    private boolean closed = false;


    private Scanner(Reader in) {
        this.in = in;
    }

    public Scanner(InputStream in) {
        this(new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)));
    }

    public Scanner(String in) {
        this(new StringReader(in));
    }

    private int readChar() {
        int nc;
        try {
            nc = in.read();
            if (nc == -1) {
                in.close();
                closed = true;
            }
        } catch (IOException e) {
            nc = -1;
        }
        return nc;
    }

    public void close() {
        if (!closed) {
            try {
                in.close();
            } catch (IOException ignored) {

            } finally {
                closed = true;
            }
        }
    }

    public boolean hasNext(TokenChecker type) {
        if (lastCh != -1 && type.test((char) lastCh)) { // Уже на таком символе, перемещать указатель не нужно
            return true;
        }
        do {
            lastCh = readChar();
        } while (lastCh != -1 && !type.test((char) lastCh)); // Пока не начнётся токен читаем посимвольно
        return lastCh != -1;
    }

    public boolean hasNextBefore(TokenChecker type, TokenChecker before) {
        if (lastCh != -1 && type.test((char) lastCh)) { // Уже на таком символе, перемещать указатель не нужно
            return true;
        }
        if (lastCh != -1 && before.test((char) lastCh)) { // Уже на несоответствующем символе, перемещать указатель не нужно
            lastCh = -1;
            return false;
        }
        do {
            lastCh = readChar();
            if (lastCh == -1 || before.test((char) lastCh)) {
                return false;
            }
        } while (lastCh != -1 && !type.test((char) lastCh)); // Пока не начнётся токен читаем посимвольно
        return lastCh != -1;
    }

    public String next(TokenChecker type) {
        if (!hasNext(type)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append((char) lastCh);
        while (true) {
            lastCh = readChar();
            if (lastCh == -1) { // EOF
                break;
            }
            if (!type.test((char) lastCh)) {
                break;
            } else {
                sb.append((char) lastCh);
            }
        }
        return sb.toString();
    }

    public boolean atEOF() {
        if (lastCh == -1) {
            lastCh = readChar();
        }
        return lastCh == -1;
    }

    static boolean isInt(char c) {
        return Character.isDigit(c) || c == '-';
    }

    public int nextInt() {
        return Integer.parseInt(next(Scanner::isInt));
    }

    public long nextLong() {
        return Long.parseLong(next(Scanner::isInt));
    }

    public boolean hasNextInt() {
        return hasNext(Scanner::isInt);
    }

    public boolean hasNextChar() {
        return !atEOF();
    }

    public char nextChar() {
        if (lastCh == -1) {
            lastCh = readChar();
        }
        char res = (char) lastCh;
        lastCh = -1;
        return res;
    }

    public int[] readIntArray(int n) {
        int[] a = new int[n];
        readIntArray(a);
        return a;
    }

    public void readIntArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = nextInt();
        }
    }
    public long[] readLongArray(int n) {
        long[] a = new long[n];
        readLongArray(a);
        return a;
    }

    public void readLongArray(long[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = nextLong();
        }
    }

    static boolean isDouble(char c) {
        return c == 'e' || c == 'E' || c == '-' || c == '.' || Character.isDigit(c);
    }

    public double nextDouble() {
        return Double.parseDouble(next(Scanner::isDouble));
    }

    static boolean isWord(char c) {
        return (c != '\r' && c != '\n' && c != ' ' &&
                !Character.isWhitespace(c));
    }

    public String nextWord() {
        return next(Scanner::isWord);
    }

    public boolean hasNextWord() {
        return hasNext(Scanner::isWord);
    }

    public boolean isLineBreak(char c) {
        return c != '\n' && c != '\r';
    }

    public boolean hasNextLine() {
        return hasNext(this::isLineBreak);
    }

    public String nextLine() {
        return next(this::isLineBreak);
    }

    public void skipLine() {
        nextWord();
        hasNextWord();
    }
}
