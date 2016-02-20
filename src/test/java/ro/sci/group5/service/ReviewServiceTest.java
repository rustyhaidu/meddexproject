package ro.sci.group5.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.sci.group5.ApplicationTests;
import ro.sci.group5.domain.Review;
import ro.sci.group5.service.ReviewService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
public class ReviewServiceTest {

	@Autowired
	private ReviewService service;

	@After
	public void tearDown() {
		for (Review review : service.listAll()) {
			service.delete(review.getId());
		}
	}

	@Test
	public void testSaveNewReview() {
		Review review = new Review();
		review.setFirstNameR("User1");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");

		Review savedReview = service.save(review);
		Assert.assertTrue(savedReview.getId() > 0);
		Assert.assertEquals("User1", savedReview.getFirstNameR());
		Assert.assertEquals("SurnameUser1", savedReview.getLastNameR());
		Assert.assertEquals("user@user.com", savedReview.getrEmail());
		Assert.assertEquals(4.0, savedReview.getGrade(), 0.0);
		Assert.assertEquals("Acest doctor este minunat", savedReview.getReviewContent());
	}

	@Test
	public void testSaveExistingReview() {
		Review review = new Review();
		review.setFirstNameR("User1");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");

		Review savedReview = service.save(review);
		Assert.assertTrue(savedReview.getId() > 0);
		Review savedReview2 = service.save(review);
		Assert.assertEquals(savedReview, savedReview2);
	}

	@Test
	public void testDeleteReview() {
		Review review = new Review();
		review.setFirstNameR("User1");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");
		Review savedReview = service.save(review);

		Assert.assertTrue(service.delete(savedReview.getId()));
		Assert.assertNull(service.findById(savedReview.getId()));

	}

	@Test
	public void testDoubleDeletionReview() {
		Review review = new Review();
		review.setFirstNameR("User1");
		review.setLastNameR("SurnameUser1");
		review.setrEmail("user@user.com");
		review.setGrade(4);
		review.setReviewContent("Acest doctor este minunat");
		Review savedReview = service.save(review);

		Assert.assertTrue(service.delete(savedReview.getId())); // delete always
																// returns false
																// in DB
		Assert.assertFalse(service.delete(savedReview.getId()));
		Assert.assertNull(service.findById(savedReview.getId()));
	}

	@Test
	public void testDeleteInexistingReview() {
		Assert.assertFalse(service.delete(-1));
	}

}
