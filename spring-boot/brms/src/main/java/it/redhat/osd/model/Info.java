package it.redhat.osd.model;

public class Info {

    private long counter = -1;
    private final String content = "OSD Micro Service to expose BRMS Decision Server ver. 1.0-SNAPSHOT";

    public Info setCounter(long count){
        this.counter = count;
        return this;
    }

    public long getCounter() {
        return counter;
    }

    public String getContent() {
        return content;
    }

}
