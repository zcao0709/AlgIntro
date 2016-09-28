package com.alex;

import java.util.*;

/**
 * Created by Administrator on 2016/9/26.
 */
public class Test2 {

/*
 * import java.util.*;
 * import java.io.*;
 *
 * public List<String> getDirectFriendsForUser(String user)
 *
 * public List<String> getAttendedCoursesForUser(String user)
 *
 * Please complete the method below
 */

    public List<String> getDirectFriendsForUser(String user) {
        return new ArrayList<>();
    }

    public List<String> getAttendedCoursesForUser(String user) {
        return new ArrayList<>();
    }

    // time complexity O(nlogn)
    // spatial complexity O(n)
    public List<String> getRankedCourses(String user) {
        Set<String> ownCourses = new HashSet<>(getAttendedCoursesForUser(user));
        List<String> fList = getDirectFriendsForUser(user);
        Set<String> friends = new HashSet<>(fList);
        for (String f : fList) {
            friends.addAll(getDirectFriendsForUser(f));
        }
        Map<String, CoursePop> courses = new HashMap<>();
        for (String f : friends) {
            List<String> cs = getAttendedCoursesForUser(f);
            for (String c : cs) {
                if (ownCourses.contains(c))
                    continue;
                CoursePop cp = courses.get(c);
                if (cp == null) {
                    courses.put(c, new CoursePop(c, 1));
                } else {
                    cp.pop++;
                }
            }
        }
        List<CoursePop> cps = new ArrayList<>(courses.values());
        Collections.sort(cps, new Comparator<CoursePop>() {
            @Override
            public int compare(CoursePop cp1, CoursePop cp2) {
                return cp2.pop - cp1.pop;  // decreasing
            }
        });
        List<String> ret = new ArrayList<>(cps.size());
        for (CoursePop cp : cps) {
            ret.add(cp.name);
        }
        return ret;
    }

    private static class CoursePop {
        String name;
        int pop;

        CoursePop(String name, int pop) {
            this.name = name;
            this.pop = pop;
        }
    }
}
