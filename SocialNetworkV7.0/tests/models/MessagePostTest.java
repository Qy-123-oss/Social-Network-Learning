package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessagePostTest {
    private MessagePost messagePostBelow, messagePostExact, messagePostAbove, messagePostZero;

    @BeforeEach
    void setUp() {
        // 作者9字符，消息39字符
        messagePostBelow = new MessagePost("Mairead M", "Programming fundamentals assignment due");
        // 作者10字符，消息40字符
        messagePostExact = new MessagePost("SiobhanDro", "Objects and Classes Lecture starting now");
        // 作者11字符，消息41字符
        messagePostAbove = new MessagePost("SiobhanRoch", "Computing and Maths Centre open from 9am.");
        // 空作者、空消息
        messagePostZero = new MessagePost("", "");
    }

    @AfterEach
    void tearDown() {
        messagePostBelow = messagePostExact = messagePostAbove = messagePostZero = null;
    }

    @Nested
    class Getters {
        @Test
        void getMessage() {
            assertEquals("Programming fundamentals assignment due", messagePostBelow.getMessage());
            assertEquals("Objects and Classes Lecture starting now", messagePostExact.getMessage());
            assertEquals("Computing and Maths Centre open from 9am", messagePostAbove.getMessage());
            assertEquals("", messagePostZero.getMessage());
        }

        @Test
        void getAuthor() {
            assertEquals("Mairead M", messagePostBelow.getAuthor());
            assertEquals("SiobhanDro", messagePostExact.getAuthor());
            assertEquals("SiobhanRoc", messagePostAbove.getAuthor());
            assertEquals("", messagePostZero.getAuthor());
        }

        @Test
        void getLikes() {
            assertEquals(0, messagePostBelow.getLikes());
            assertEquals(0, messagePostExact.getLikes());
            assertEquals(0, messagePostAbove.getLikes());
            assertEquals(0, messagePostZero.getLikes());
        }
    }

    @Nested
    class Setters {
        @Test
        void setMessage() {
            assertEquals("Programming fundamentals assignment due", messagePostBelow.getMessage());
            // 39字符，修改生效
            messagePostBelow.setMessage("Programming fundamentals results -- out");
            assertEquals("Programming fundamentals results -- out", messagePostBelow.getMessage());
            // 40字符，修改生效
            messagePostBelow.setMessage("Programming fundamentals results are out");
            assertEquals("Programming fundamentals results are out", messagePostBelow.getMessage());
            // 41字符，超出限制，不更新
            messagePostBelow.setMessage("Programming fundamentals module now over!");
            assertEquals("Programming fundamentals results are out", messagePostBelow.getMessage());
        }

        @Test
        void setAuthor() {
            messagePostBelow.setAuthor("NewUser1");
            assertEquals("NewUser1", messagePostBelow.getAuthor());
            messagePostExact.setAuthor("");
            assertEquals("", messagePostExact.getAuthor());
        }

        @Test
        void setLikes() {
            // 合法值
            messagePostBelow.setLikes(5);
            assertEquals(5, messagePostBelow.getLikes());
            // 负数不生效
            messagePostBelow.setLikes(-10);
            assertEquals(5, messagePostBelow.getLikes());
            // 上限10000
            messagePostBelow.setLikes(10000);
            assertEquals(10000, messagePostBelow.getLikes());
            messagePostBelow.setLikes(10001);
            assertEquals(10000, messagePostBelow.getLikes());
        }
    }

    @Nested
    class DisplayMethods {
        @Test
        void testDisplay() {
            // 0点赞
            String displayStr = messagePostExact.display();
            assertTrue(displayStr.contains(messagePostExact.getAuthor()));
            assertTrue(displayStr.contains(messagePostExact.getMessage()));
            assertTrue(displayStr.contains("0 likes"));

            // 1点赞
            messagePostExact.setLikes(1);
            assertTrue(messagePostExact.display().contains("1 people like this"));
        }

        @Test
        void testDisplayCondensed() {
            messagePostExact.setLikes(3);
            String cond = messagePostExact.displayCondensed();
            assertTrue(cond.contains("SiobhanDro"));
            assertTrue(cond.contains("3 likes"));
            assertTrue(cond.contains("Message"));

            messagePostZero.setLikes(0);
            String condZero = messagePostZero.displayCondensed();
            assertTrue(condZero.contains("(0 likes)"));
        }
    }

    @Nested
    class LikesOnPosts {
        @Test
        void testingLikingOfPosts() {
            assertEquals(0, messagePostExact.getLikes());
            messagePostExact.likeAPost();
            assertEquals(1, messagePostExact.getLikes());
            messagePostExact.likeAPost();
            assertEquals(2, messagePostExact.getLikes());
        }

        @Test
        void testingUnLikingOfPosts() {
            // 初始0，取消点赞不变
            assertEquals(0, messagePostExact.getLikes());
            messagePostExact.unlikeAPost();
            assertEquals(0, messagePostExact.getLikes());

            // 设置2赞后取消
            messagePostExact.setLikes(2);
            assertEquals(2, messagePostExact.getLikes());
            messagePostExact.unlikeAPost();
            assertEquals(1, messagePostExact.getLikes());
            messagePostExact.unlikeAPost();
            assertEquals(0, messagePostExact.getLikes());
            messagePostExact.unlikeAPost();
            assertEquals(0, messagePostExact.getLikes());
        }
    }
}
