package tools;

import java.util.*;

public class Publisher {

    Set<Subscriber> subscribers = new HashSet<>();

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    public void notifySubscribers() {
        for(Subscriber s: subscribers) {
            s.update();
        }
    }

}