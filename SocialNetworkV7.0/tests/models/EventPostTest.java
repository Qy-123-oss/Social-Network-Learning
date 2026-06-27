package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventPostTest {
    private EventPost eventPostBelow, eventPostExact, eventPostAbove, eventPostZero;

    @BeforeEach
    void setUp() {
        eventPostBelow = new EventPost("Mairead M", "Programming Hackathon : Room IT101", -1);
        eventPostExact = new EventPost("SiobhanDro", "Programming Fundamentals Quiz Night", 99999);
        eventPostAbove = new EventPost("SiobhanRoch", "Programming Fundamentals Study Group", 100000);
        eventPostZero = new EventPost("", "", 0);
    }

    @AfterEach
    void tearDown() {
        eventPostBelow = eventPostExact = eventPostAbove = eventPostZero = null;
    }

    @Nested
    class Getters {
        @Test
        void getEventName() {
            assertEquals("Programming Hackathon : Room IT101", eventPostBelow.getEventName());
            assertEquals("Programming Fundamentals Quiz Night", eventPostExact.getEventName());
            assertEquals("Programming Fundamentals Study Grou", eventPostAbove.getEventName());
            assertEquals("", eventPostZero.getEventName());
        }

        @Test
        void getEventCost() {
            assertEquals(0, eventPostBelow.getEventCost());
            assertEquals(99999, eventPostExact.getEventCost());
            assertEquals(0, eventPostAbove.getEventCost());
            assertEquals(0, eventPostZero.getEventCost());
        }

        @Test
        void getAuthor() {
            assertEquals("Mairead M", eventPostBelow.getAuthor());
            assertEquals("SiobhanDro", eventPostExact.getAuthor());
            assertEquals("SiobhanRoc", eventPostAbove.getAuthor());
            assertEquals("", eventPostZero.getAuthor());
        }


        @Nested
        class DisplayMethods {
            @Test
            void testDisplayCondensed() {
                String cond = eventPostExact.displayCondensed();
                assertTrue(cond.contains("Event"));
                assertTrue(cond.contains("€"));
            }
        }
    }
}
