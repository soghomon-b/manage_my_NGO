package model;

import java.util.ArrayList;
import java.util.List;

public class LogWriter implements Observer {
    EventLog eventLog;

    public LogWriter() {

    }

    @Override
    public void update(Event event) {
        EventLog.getInstance().logEvent(event);
    }

}
