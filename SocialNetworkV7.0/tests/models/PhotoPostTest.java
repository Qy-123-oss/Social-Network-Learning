package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhotoPostTest {
    private PhotoPost photoPostBelow, photoPostExact, photoPostAbove, photoPostZero;

    @BeforeEach
    void setUp() {
        photoPostBelow = new PhotoPost("Mairead M",
                "Programming Hackathon : Room IT101 : group photo with all participants from BSc (Hons) Applied Yr 1",
                "Hackathon IT101-BSc (Hons) Applied Yr 1");
        photoPostExact = new PhotoPost("SiobhanDro",
                "Programming Fundamentals Quiz Night 2021- Podium photo of the winning team BSc (Hons) Applied Year 1",
                "Prog Fund Quiz 2021-Applied Winning Team");
        photoPostAbove = new PhotoPost("SiobhanRoch",
                "Programming Fundamentals Study Group - multiple groups hard at work on the morning of day 1, Sep 2021",
                "Programming Fundamentals Study Group 2021");
        photoPostZero = new PhotoPost("", "", "");
    }

    @AfterEach
    void tearDown() {
        photoPostBelow = photoPostExact = photoPostAbove = photoPostZero = null;
    }

    @Nested
    class Getters {
        @Test
        void getCaption() {
            assertTrue(photoPostExact.getCaption().contains("Quiz Night"));
        }
        @Test
        void getFilename() {
            assertEquals("Prog Fund Quiz 2021-Applied Winning Team", photoPostExact.getFilename());
        }
    }

    @Nested
    class DisplayMethods {
        @Test
        void testDisplayCondensed() {
            String s = photoPostExact.displayCondensed();
            assertTrue(s.contains("Photo"));
        }
    }
}