package controllers;

import models.EventPost;
import models.MessagePost;
import models.PhotoPost;
import models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NewsFeedTest {
    private MessagePost messagePostBelow, messagePostExact, messagePostAbove, messagePostZero;
    private EventPost eventPostBelow, eventPostExact, eventPostAbove, eventPostZero;
    private PhotoPost photoPostBelow, photoPostExact, photoPostAbove, photoPostZero;
    private NewsFeed newsFeed = new NewsFeed();
    private NewsFeed emptyNewsFeed = new NewsFeed();

    @BeforeEach
    void setUp() {
        // MessagePost
        messagePostBelow = new MessagePost("Mairead M", "Programming fundamentals assignment due");
        messagePostExact = new MessagePost("SiobhanDro", "Objects and Classes Lecture starting now");
        messagePostAbove = new MessagePost("SiobhanRoch", "Computing and Maths Centre open from 9am.");
        messagePostZero = new MessagePost("", "");

        // EventPost
        eventPostBelow = new EventPost("Mairead M", "Programming Hackathon : Room IT101", -1);
        eventPostExact = new EventPost("SiobhanDro", "Programming Fundamentals Quiz Night", 99999);
        eventPostAbove = new EventPost("SiobhanRoch", "Programming Fundamentals Study Group", 100000);
        eventPostZero = new EventPost("", "", 0);

        // PhotoPost
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

        // 填充信息流
        newsFeed.addPost(messagePostBelow);
        newsFeed.addPost(messagePostExact);
        newsFeed.addPost(eventPostBelow);
        newsFeed.addPost(photoPostExact);
        newsFeed.addPost(messagePostZero);
        newsFeed.addPost(photoPostAbove);
        newsFeed.addPost(eventPostAbove);
        newsFeed.addPost(photoPostBelow);
        newsFeed.addPost(photoPostZero);
    }

    @AfterEach
    void tearDown() {
        messagePostBelow = messagePostExact = messagePostAbove = messagePostZero = null;
        eventPostBelow = eventPostExact = eventPostAbove = eventPostZero = null;
        photoPostBelow = photoPostExact = photoPostAbove = photoPostZero = null;
        newsFeed = emptyNewsFeed = null;
    }

    @Nested
    class GettersSetters {
        @Test
        void gettingPostListReturnsList() {
            List<Post> testPosts = new ArrayList<>();
            testPosts.add(messagePostBelow);
            testPosts.add(messagePostExact);
            testPosts.add(eventPostBelow);
            testPosts.add(photoPostExact);
            testPosts.add(messagePostZero);
            testPosts.add(photoPostAbove);
            testPosts.add(eventPostAbove);
            testPosts.add(photoPostBelow);
            testPosts.add(photoPostZero);
            assertEquals(testPosts, newsFeed.getPosts());
            assertEquals(new ArrayList<>(), emptyNewsFeed.getPosts());
        }

        @Test
        void settingPostListReplacesList() {
            List<Post> testPosts = new ArrayList<>();
            testPosts.add(messagePostBelow);
            testPosts.add(messagePostExact);
            testPosts.add(eventPostBelow);

            assertEquals(9, newsFeed.numberOfPosts());
            newsFeed.setPosts((ArrayList<Post>) testPosts);
            assertEquals(testPosts, newsFeed.getPosts());
            assertEquals(3, newsFeed.numberOfPosts());

            emptyNewsFeed.setPosts(new ArrayList<>());
            assertEquals(0, emptyNewsFeed.numberOfPosts());
        }
    }

    @Nested
    class ArrayListCRUD {
        @Test
        void addingAPostAddsToArrayList() {
            assertEquals(9, newsFeed.numberOfPosts());
            assertTrue(newsFeed.addPost(messagePostAbove));
            assertEquals(messagePostAbove, newsFeed.findPost(newsFeed.numberOfPosts() - 1));

            assertTrue(newsFeed.addPost(photoPostBelow));
            assertEquals(photoPostBelow, newsFeed.findPost(newsFeed.numberOfPosts() - 1));

            assertTrue(newsFeed.addPost(eventPostExact));
            assertEquals(eventPostExact, newsFeed.findPost(newsFeed.numberOfPosts() - 1));

            assertEquals(0, emptyNewsFeed.numberOfPosts());
            assertTrue(emptyNewsFeed.addPost(eventPostExact));
            assertEquals(eventPostExact, emptyNewsFeed.findPost(0));
        }

        @Test
        void updatingAPostThatDoesNotExistReturnsFalse() {
            assertFalse(newsFeed.updateMessagePost(10, "Updating Message", "Work"));
            assertFalse(newsFeed.updateEventPost(9, "Updating Event", "No update", 1));
            assertFalse(newsFeed.updatePhotoPost(-1, "Updating Photo", "No update", "No file"));
            assertFalse(emptyNewsFeed.updateMessagePost(0, "Updating Note", "Work"));
        }

        @Test
        void updatingAMessagePostThatExistsReturnsTrueAndUpdates() {
            MessagePost foundPost = (MessagePost) newsFeed.findPost(1);
            assertEquals(messagePostExact, foundPost);

            assertTrue(newsFeed.updateMessagePost(1, "NewAuthor", "Updated Message"));
            MessagePost updatedPost = (MessagePost) newsFeed.findPost(1);
            assertEquals("NewAuthor", updatedPost.getAuthor());
            assertEquals("Updated Message", updatedPost.getMessage());
        }

        @Test
        void updatingAnEventPostThatExistsReturnsTrueAndUpdates() {
            EventPost found = (EventPost) newsFeed.findPost(2);
            assertEquals(eventPostBelow, found);
            assertTrue(newsFeed.updateEventPost(2, "NewEvent", "New Hack", 50));
            EventPost upd = (EventPost) newsFeed.findPost(2);
            assertEquals("NewEvent", upd.getAuthor());
            assertEquals("New Hack", upd.getEventName());
            assertEquals(50, upd.getEventCost());
        }

        @Test
        void updatingAPhotoPostThatExistsReturnsTrueAndUpdates() {
            PhotoPost found = (PhotoPost) newsFeed.findPost(3);
            assertEquals(photoPostExact, found);
            assertTrue(newsFeed.updatePhotoPost(3, "PicUser", "new cap", "test.jpg"));
            PhotoPost upd = (PhotoPost) newsFeed.findPost(3);
            assertEquals("PicUser", upd.getAuthor());
            assertEquals("new cap", upd.getCaption());
            assertEquals("test.jpg", upd.getFilename());
        }

        @Test
        void deletingAPostThatDoesNotExistReturnsNull() {
            assertNull(emptyNewsFeed.deletePost(0));
            assertNull(newsFeed.deletePost(-1));
            assertNull(newsFeed.deletePost(newsFeed.numberOfPosts()));
        }

        @Test
        void deletingAPostThatExistsDeletesAndReturnsDeletedObject() {
            assertEquals(9, newsFeed.numberOfPosts());
            assertEquals(messagePostBelow, newsFeed.deletePost(0));
            assertEquals(8, newsFeed.numberOfPosts());

            assertEquals(8, newsFeed.numberOfPosts());
            assertEquals(photoPostZero, newsFeed.deletePost(7));
            assertEquals(7, newsFeed.numberOfPosts());
        }
    }

    @Nested
    class CountingMethods {
        @Test
        void numberOfPostsCalculatedCorrectly() {
            assertEquals(9, newsFeed.numberOfPosts());
            assertEquals(0, emptyNewsFeed.numberOfPosts());
        }

        @Test
        void numberOfEventPostsCalculatedCorrectly() {
            assertEquals(2, newsFeed.numberOfEventPosts());
            assertEquals(0, emptyNewsFeed.numberOfEventPosts());
        }

        @Test
        void numberOfMessagePostsCalculatedCorrectly() {
            assertEquals(3, newsFeed.numberOfMessagePosts());
            assertEquals(0, emptyNewsFeed.numberOfMessagePosts());
        }

        @Test
        void numberOfPhotoPostsCalculatedCorrectly() {
            assertEquals(4, newsFeed.numberOfPhotoPosts());
            assertEquals(0, emptyNewsFeed.numberOfPhotoPosts());
        }
    }

    @Nested
    class LikingMethods {
        @Test
        void likingAnExistingPostIncreasesTheLikesByOne() {
            PhotoPost foundPost = (PhotoPost) newsFeed.findPost(3);
            assertEquals(0, foundPost.getLikes());
            newsFeed.likeAPost(3);
            assertEquals(1, foundPost.getLikes());
            // 无效索引无异常
            newsFeed.likeAPost(newsFeed.numberOfPosts());
        }

        @Test
        void unLikingAnExistingPostDecreasesTheLikesByOne() {
            PhotoPost foundPost = (PhotoPost) newsFeed.findPost(3);
            assertEquals(0, foundPost.getLikes());
            newsFeed.likeAPost(3);
            newsFeed.likeAPost(3);
            assertEquals(2, foundPost.getLikes());

            newsFeed.unLikeAPost(3);
            assertEquals(1, foundPost.getLikes());
            newsFeed.unLikeAPost(3);
            assertEquals(0, foundPost.getLikes());
            newsFeed.unLikeAPost(3);
            assertEquals(0, foundPost.getLikes());

            newsFeed.unLikeAPost(newsFeed.numberOfPosts());
        }
    }

    @Nested
    class ListingMethods {
        @Test
        void showReturnsPostsWhenArrayListHasPostsStored() {
            assertEquals(9, newsFeed.numberOfPosts());
            String posts = newsFeed.show();
            assertTrue(posts.contains("Programming fundamentals assignment due"));
            assertTrue(posts.contains("Objects and Classes Lecture starting now"));
            assertTrue(posts.contains("Programming Hackathon : Room IT101"));
            assertTrue(posts.contains("Programming Fundamentals Quiz Night"));
            assertTrue(posts.contains("Programming Fundamentals Study Group"));
            assertTrue(posts.contains("Prog Fund Quiz 2021-Applied Winning Team"));
        }

        @Test
        void showMessagesReturnsNoPostsStoredWhenArrayListIsEmpty() {
            assertTrue(emptyNewsFeed.showMessagePosts().toLowerCase().contains("no message posts"));
        }

        @Test
        void showMessagesReturnsPostsWhenArrayListHasPostStored() {
            String out = newsFeed.showMessagePosts();
            assertTrue(out.contains("Mairead M"));
            assertTrue(out.contains("SiobhanDro"));
        }

        @Test
        void showPhotosReturnsNoPostsStoredWhenArrayListIsEmpty() {
            assertTrue(emptyNewsFeed.showPhotoPosts().toLowerCase().contains("no photo posts"));
        }

        @Test
        void showPhotosReturnsPostsWhenArrayListHasPostStored() {
            String out = newsFeed.showPhotoPosts();
            assertTrue(out.contains("Hackathon IT101-BSc"));
        }

        @Test
        void showEventReturnsNoPostsStoredWhenArrayListIsEmpty() {
            assertTrue(emptyNewsFeed.showEventPosts().toLowerCase().contains("no event posts"));
        }

        @Test
        void showEventReturnsPostsWhenArrayListHasPostStored() {
            String out = newsFeed.showEventPosts();
            assertTrue(out.contains("Programming Hackathon"));
        }
    }

    @Nested
    class FindingMethods {
        @Test
        void findPostReturnsPostWhenIndexIsValid() {
            assertEquals(9, newsFeed.numberOfPosts());
            assertEquals(messagePostBelow, newsFeed.findPost(0));
            assertEquals(photoPostZero, newsFeed.findPost(8));
        }

        @Test
        void findPostReturnsNullWhenIndexIsInValid() {
            assertEquals(0, emptyNewsFeed.numberOfPosts());
            assertNull(emptyNewsFeed.findPost(0));
            assertEquals(9, newsFeed.numberOfPosts());
            assertNull(newsFeed.findPost(-1));
            assertNull(newsFeed.findPost(9));
        }
    }

    @Nested
    class HelperMethods {
        @Test
        void isValidIndexReturnsTrueForValidIndex() {
            assertTrue(newsFeed.isValidIndex(0));
            assertTrue(newsFeed.isValidIndex(newsFeed.numberOfPosts() - 1));
        }

        @Test
        void isValidIndexReturnsFalseForInValidIndex() {
            assertFalse(emptyNewsFeed.isValidIndex(0));
            assertFalse(emptyNewsFeed.isValidIndex(1));
            assertFalse(newsFeed.isValidIndex(-1));
            assertFalse(newsFeed.isValidIndex(newsFeed.numberOfPosts()));
        }


    }
}
