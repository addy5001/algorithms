package ramesh.aadhavan.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivitySelection {
    final static class Activity implements Comparable<Activity> {
        final String id;
        final int timeInMinutes;

        public Activity(String id, int timeInMinutes) {
            this.id = id;
            this.timeInMinutes = timeInMinutes;
        }

        public String getId() {
            return id;
        }

        public int getTimeInMinutes() {
            return timeInMinutes;
        }

        @Override
        public int compareTo(Activity o) {
            return this.timeInMinutes - o.timeInMinutes;
        }
    }

    public static double findTurnAroundTime(List<Activity> activities) {
        double totalTime = activities.stream().mapToDouble(Activity::getTimeInMinutes).sum();
        double startTime = 0;
        double turnAroundTime = 0;
        for(Activity activity : activities) {
            double activityCompleteTime = startTime + activity.timeInMinutes;
            turnAroundTime += (activityCompleteTime - activity.timeInMinutes);
            startTime = activityCompleteTime;
        }

        return (turnAroundTime/activities.size());
    }

    public static double findOptimalTurnAroundTime(List<Activity> activities) {
        double totalTime = activities.stream().mapToDouble(Activity::getTimeInMinutes).sum();
        Collections.sort(activities);
        double startTime = 0;
        double turnAroundTime = 0;
        for(Activity activity : activities) {
            double activityCompleteTime = startTime + activity.timeInMinutes;
            turnAroundTime += (activityCompleteTime - activity.timeInMinutes);
            startTime = activityCompleteTime;
        }

        return (turnAroundTime/activities.size());
    }

    public static void main(String[] args) {
        List<Activity> activities = Arrays.asList(new Activity("1", 23),
                new Activity("2", 1),
                new Activity("3", 14),
                new Activity("4", 3));

        System.out.println(findTurnAroundTime(activities));
        System.out.println(findOptimalTurnAroundTime(activities));
    }
}
