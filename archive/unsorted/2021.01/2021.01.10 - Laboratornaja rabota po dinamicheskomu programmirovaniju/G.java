package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class G {
    PrintWriter out;

    static class Meeting {
        public int min, max, delta;

        public Meeting(int min, int max, int delta) {
            this.min = min;
            this.max = max;
            this.delta = delta;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int k = in.nextInt();
        Meeting[] meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            meetings[i] = new Meeting(in.nextInt(), in.nextInt(), in.nextInt());
        }
        boolean[][] dp = new boolean[1 << n][n];
        //dp[mask][i] - can we visit masked meetings and end up on i
        Arrays.fill(dp[0], true);
        int[] prevMask = new int[(1 << n)];
        int maxMask = 0;
        int maxMeetings = 0;
        for (int startMask = 0; startMask < (1 << n); startMask++) {
            int startHappiness = k;
            for (int i = 0; i < n; i++) {
                if ((startMask & (1 << i)) != 0) {
                    startHappiness += meetings[i].delta;
                }
            }
            for (int i = 0; i < n; i++) {
                if (dp[startMask][i]) {
                    for (int j = 0; j < n; j++) {
                        int newMask = startMask | (1 << j);
                        if (startMask != newMask) {
                            Meeting meeting = meetings[j];
//                        if (meeting.min <= startHappiness && startHappiness <= meeting.max) {
                            if (Utils.inRange(meeting.min, meeting.max, startHappiness)) { // We can visit that meeting
                                dp[newMask][j] = true;
                                prevMask[newMask] = startMask;
                                int meetingsCount = Integer.bitCount(newMask);
                                if (meetingsCount > maxMeetings) {
                                    maxMask = newMask;
                                    maxMeetings = meetingsCount;
                                }
                            }
                        }
                    }
                }
            }
        }
        IntList ans = new IntList();
        while (maxMask != 0) {
            int newMask = prevMask[maxMask];
            ans.push(Integer.numberOfTrailingZeros(newMask ^ maxMask) + 1);
            maxMask = newMask;
        }
        if (maxMeetings != ans.length()) {
            while (true) ;
        }
        out.println(maxMeetings);
        for (int i = ans.length() - 1; i >= 0; i--) {
            out.print(ans.get(i));
            out.print(" ");
        }
    }
}
