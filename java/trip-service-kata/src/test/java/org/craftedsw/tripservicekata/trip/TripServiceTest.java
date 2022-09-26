package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    // happy path: valid user is logged in
    // mock logged user
    // mock tripDAO
    // returns triplist if logged user is a friend of the given user
    @Mock private UserSessionProvider userSessionProvider;
    @Mock private UserSession userSession;
    @Mock
    private TripsDAOProvider tripsDAOProvider;

    private User loggedUser;

    @BeforeEach public void setup() {
        loggedUser = new User();
    }

    @Test
    void returns_users_trip_list_if_logged_user_is_a_friend_of_the_given_user() {
        User givenUser = new User();
        givenUser.addFriend(loggedUser);

        List<Trip> expectedTrips = new ArrayList<>();
        Trip trip = new Trip();
        expectedTrips.add(trip);

        when(userSessionProvider.getUserSessionInstance()).thenReturn(userSession);
        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        when(tripsDAOProvider.findTripsByUser(givenUser)).thenReturn(expectedTrips);

        TripService tripService = new TripService(tripsDAOProvider, userSessionProvider);
        List<Trip> resultTrips = tripService.getTripsByUser(givenUser);

        assertEquals(expectedTrips, resultTrips);

    }

}
