package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

	@Test
	public void should_return_a_user_if_users_are_friends() {
		User userOne = new User();
		User friendOfUserOne = new User();
		userOne.addFriend(friendOfUserOne);

		assertTrue(userOne.isFriendsWith(friendOfUserOne));
	}

	@Test
	public void should_return_a_trip_to_a_user() {
		User userOne = new User();
		Trip trip = new Trip();

		assertEquals(0, userOne.trips().size());

		userOne.addTrip(trip);

		assertEquals(1, userOne.trips().size());
		assertEquals(trip, userOne.trips().get(0));
	}

}
