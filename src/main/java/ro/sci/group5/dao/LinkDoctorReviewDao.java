package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.LinkDoctorReview;

public interface LinkDoctorReviewDao extends BaseDAO<LinkDoctorReview> {

	Collection<LinkDoctorReview> searchByName(String query);
}
