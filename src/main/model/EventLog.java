package main.model;

import java.io.Serializable;
import java.util.*;

public class EventLog implements Observer, Iterable, Serializable {
    private List<EventHistory> eventHistoryList;

    public EventLog() {
        eventHistoryList = new ArrayList<>();
    }

    public void addEventHistory(EventHistory eventHistory) {
        eventHistoryList.add(eventHistory);
    }

    public List<EventHistory> getEventHistoryList() {
        return eventHistoryList;
    }

    public EventHistory getLatestEventHistory() {
        EventHistory eventHistory = eventHistoryList.get(eventHistoryList.size() - 1);
        eventHistoryList.remove(eventHistoryList.size() - 1);
        return eventHistory;
    }

    @Override
    public void update(Observable o, Object arg) {
        EventHistory eventHistory = (EventHistory) arg;
        addEventHistory(eventHistory);
    }

    @Override
    public Iterator iterator() {
        return eventHistoryList.iterator();
    }
}
