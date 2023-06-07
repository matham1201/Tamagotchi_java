package com.tamagochi;

import javafx.event.Event;
import javafx.event.EventType;

public class TamagotchiUpdateEvent extends Event {
    public static final EventType<TamagotchiUpdateEvent> TAMAGOTCHI_UPDATE =
            new EventType<>(Event.ANY, "TAMAGOTCHI_UPDATE");

    private Tamagotchi tamagotchi;

    public TamagotchiUpdateEvent(Tamagotchi tamagotchi) {
        super(TAMAGOTCHI_UPDATE);
        this.tamagotchi = tamagotchi;
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }
}

