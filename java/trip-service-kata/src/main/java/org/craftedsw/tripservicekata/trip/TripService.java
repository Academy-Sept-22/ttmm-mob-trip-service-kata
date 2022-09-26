package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	private final UserSessionProvider userSessionProvider;
	private final TripsDAOProvider tripsDAOProvider;

	public TripService(TripsDAOProvider tripsDAOProvider, UserSessionProvider userSessionProvider) {
		this.userSessionProvider = userSessionProvider;
		this.tripsDAOProvider = tripsDAOProvider;
	}


	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = userSessionProvider.getUserSessionInstance().getLoggedUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = tripsDAOProvider.findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}


}
