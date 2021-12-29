package io.inceptor.drl.drl.bean;

public class RuleStatus {
    private String ruleId;
    private String text;
    private String status;
    private long startTime;
    private long endTime;

    public static RuleStatus apply(String ruleId, String text, String status, long startTime, long endTime) {
        return new RuleStatus(ruleId, text, status, startTime, endTime);
    }

    public RuleStatus(String ruleId, String text, String status, long startTime, long endTime) {
        this.ruleId = ruleId;
        this.text = text;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public static String RUNNING = "RUNNING";
    public static String NOT_RUNNING = "NOT_RUNNING";

    @Override
    public String toString() {
        return "rule " + ruleId + " is " + status;
    }
}
