package com.stargi.backend.iam.domain.Responses;

public class LeaderInfo {
    private final boolean isLeader;
    private final Long teamId;

    public LeaderInfo(boolean isLeader, Long teamId) {
        this.isLeader = isLeader;
        this.teamId = teamId;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public Long getTeamId() {
        return teamId;
    }
}
