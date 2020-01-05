package test;

import main.model.Board;
import main.model.EventHistory;
import main.model.EventLog;
import main.model.Position;
import main.model.pieces.EmptyPiece;
import main.model.pieces.Horse;
import main.model.pieces.Queen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private EventLog eventLog;
    private Board board;
    
    @BeforeEach
    public void setUp() {
        eventLog = new EventLog();
        board = new Board();
    }
    
    @Test
    public void getLatestEventHistoryTest() {
        EventHistory eventHistory = new EventHistory(new Queen(3, 4, 1, board),
                new Horse(5, 4, 0, board), new Position(3, 4));
        eventLog.addEventHistory(eventHistory);
        EventHistory eventHistory1 = new EventHistory(new Queen(5, 4, 1, board),
                new EmptyPiece(5, 1, board), new Position(5, 4));
        eventLog.addEventHistory(eventHistory1);
        assertEquals(eventLog.getEventHistoryList().size(), 2);
        EventHistory eh = eventLog.getLatestEventHistory();
        assertEquals(eventHistory1, eh);
        assertEquals(eventLog.getEventHistoryList().size(), 1);
    }
}
