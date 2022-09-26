package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	private final UserSessionProvider userSessionProvider;
	private final TripsDAOProvider tripsDAOProvider;

	public TripService(TripsDAOProvider tripsDAOProvider, UserSessionProvider userSessionProvider) {
		this.userSessionProvider = userSessionProvider;
		this.tripsDAOProvider = tripsDAOProvider;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if (user.isFriend(checkUserLoggedIn())) {
			return tripsDAOProvider.findTripsByUser(user);
		}
		return new ArrayList<>();
	}

	private User checkUserLoggedIn() {
		return userSessionProvider.getLoggedUser();
	}

}
