package ua.nure.vasilchenko.SummaryTask4.db.enumeration;


import ua.nure.vasilchenko.SummaryTask4.db.entity.User;

public enum Activity {
    ACTIVE, BLOCKED;

    public static Activity getActivity(User user) {
        int activityId = user.getActivityId();
        return Activity.values()[activityId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
