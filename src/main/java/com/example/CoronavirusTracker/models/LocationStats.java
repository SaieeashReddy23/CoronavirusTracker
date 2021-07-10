package com.example.CoronavirusTracker.models;

public class LocationStats {
    private String state;
    private String country;
    private String latestStats;
    private int delta;

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public LocationStats() {
    }

    public LocationStats(String state, String country, String latestStats) {
        this.state = state;
        this.country = country;
        this.latestStats = latestStats;
    }

    @Override
    public String toString() {
        return "locStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestStats='" + latestStats + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatestStats() {
        return latestStats;
    }

    public void setLatestStats(String latestStats) {
        this.latestStats = latestStats;
    }
}
