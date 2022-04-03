package com.bowling.model;

public class Frame {
    private Integer firstRoll;
    private Integer secondRoll;
    private Integer thirdRoll;

    public Integer getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(Integer firstRoll) {
        this.firstRoll = firstRoll;
    }

    public Integer getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(Integer secondRoll) {
        this.secondRoll = secondRoll;
    }

    public Integer getThirdRoll() {
        return thirdRoll;
    }

    public void setThirdRoll(Integer thirdRoll) {
        this.thirdRoll = thirdRoll;
    }

    public Boolean hasStrike() {
        return firstRoll == 10;
    }

    public Boolean hasSpare() {
        return (firstRoll + secondRoll == 10) && (firstRoll < 10);
    }

    public Boolean validFrame() {
        return (firstRoll >= 0 && secondRoll >= 0) && (firstRoll <= 10 && secondRoll <= 10);
    }

    public Integer calculateScore() {
        return firstRoll + secondRoll;
    }

    public Integer calculateTotalScore() {
        return firstRoll + secondRoll + thirdRoll;
    }

}
