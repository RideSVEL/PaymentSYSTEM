package ua.nure.vasilchenko.SummaryTask4.db.entity;

/**
 * Card entity.
 *
 * @author S.Vasilchenko
 */
public class Card extends Entity {

    private long userId;
    private String name;
    private long number;
    private int money;
    private int activityId;
    private int requestId;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", money=" + money +
                ", activityId=" + activityId +
                ", requestId=" + requestId +
                '}';
    }
}
