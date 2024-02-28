package com.mcelik.eventproject.enumEvent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BreakTime {
    LUNCH("Lunch", 60, "min"),
    NETWORKING_EVENT("Networking Event", 60, "min");

    public final String value;
    public final Integer duration;
    public final String durationUnit;
}
