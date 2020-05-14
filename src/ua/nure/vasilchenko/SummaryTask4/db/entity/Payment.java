package ua.nure.vasilchenko.SummaryTask4.db.entity;

public class Payment extends Entity {

    long cardId;
    long cardDestinationId;
    String date;
    int money;
    int statusId;
    long cardNumber;
    long cardDestinationNumber;
    int balance;

    @Override
    public String toString() {
        return "Payment{" +
                "cardId=" + cardId +
                ", cardDestinationId=" + cardDestinationId +
                ", date='" + date + '\'' +
                ", money=" + money +
                ", statusId=" + statusId +
                ", cardNumber=" + cardNumber +
                ", cardDestinationNumber=" + cardDestinationNumber +
                ", balance=" + balance +
                '}';
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getCardDestinationNumber() {
        return cardDestinationNumber;
    }

    public void setCardDestinationNumber(long cardDestinationNumber) {
        this.cardDestinationNumber = cardDestinationNumber;
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public long getCardDestinationId() {
        return cardDestinationId;
    }

    public void setCardDestinationId(long cardDestinationId) {
        this.cardDestinationId = cardDestinationId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
