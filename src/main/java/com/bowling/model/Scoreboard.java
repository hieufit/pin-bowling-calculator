package com.bowling.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Scoreboard {
    private List<Frame> frames;

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public Boolean framesValid() {
        Boolean validFrames = true;
        Boolean validFinalFrame = true;

        for (int i = 0; i < frames.size(); i++) {
            validFrames = validFrames ? frames.get(i).validFrame() : false;

            if (frames.get(i).getThirdRoll() > 0 && i < frames.size() - 1) validFinalFrame = false;
            if (!validFrames && !validFinalFrame) break;
        }

        return (validFrames && validFinalFrame);
    }

    public Integer calculateScore() {
        Integer[] score = Collections.nCopies(frames.size(), 0).toArray(new Integer[0]);

        Frame f = frames.get(frames.size() - 1);
        if (f.hasStrike() && f.getSecondRoll() == 10) {
            score[frames.size() - 1] = f.calculateScore() + f.getThirdRoll();
        } else {
            score[frames.size() - 1] = f.calculateScore();
        }

        for (int i = frames.size() - 2; i >= 0; i--) {
            Frame curr = frames.get(i);
            Frame next = frames.get(i + 1);

            if (curr.hasStrike() && next.hasStrike()) {
                score[i] = 30;
            } else if (curr.hasSpare() && next.hasStrike()) {
                score[i] = 20;
            } else if (
                    (curr.hasSpare() && next.hasSpare()) ||
                            (curr.hasStrike() && next.hasSpare())
            ) {
                score[i] = 10 + next.getFirstRoll();
            } else if (curr.hasStrike() && !next.hasStrike() && !next.hasSpare()) {
                score[i] = curr.calculateScore() + next.calculateScore();
            } else if (curr.hasSpare() && !next.hasStrike() && !next.hasSpare()) {
                score[i] = curr.calculateScore() + next.getFirstRoll();
            } else {
                score[i] = curr.calculateScore();
            }
        }

        return Arrays.stream(score)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
