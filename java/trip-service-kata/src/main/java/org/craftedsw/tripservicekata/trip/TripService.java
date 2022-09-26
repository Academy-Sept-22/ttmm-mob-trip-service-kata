package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    private final UserSessionProvider userSessionProvider;
    private final TripsDAOProvider tripsDAOProvider;

    public TripService(TripsDAOProvider tripsDAOProvider, UserSessionProvider userSessionProvider) {
        this.userSessionProvider = userSessionProvider;
        this.tripsDAOProvider = tripsDAOProvider;
    }


    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        if (isFriend(user, checkUserLoggedIn())) {
            tripList = tripsDAOProvider.findTripsByUser(user);
        }
        return tripList;
    }

    private User checkUserLoggedIn() {
        Optional<User> userOptional = Optional.ofNullable(userSessionProvider.getUserSessionInstance().getLoggedUser());
        if (userOptional.isEmpty()) {
            throw new UserNotLoggedInException();
        }
        return userOptional.get();
    }

    private boolean isFriend(User user, User loggedUser) {
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                return true;
            }
        }
        return false;
    }


}
