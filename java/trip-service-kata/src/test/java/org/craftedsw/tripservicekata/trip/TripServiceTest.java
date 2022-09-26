package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private UserSessionProvider userSessionProvider;
    @Mock
    private UserSession userSession;
    @Mock
    private TripsDAOProvider tripsDAOProvider;

    private User loggedUser;
    private TripService tripService;
    private User givenUser;

    @BeforeEach
    public void setup() {
        loggedUser = new User();
        givenUser = new User();
        tripService = new TripService(tripsDAOProvider, userSessionProvider);
    }

    @Test
    void returns_users_trip_list_if_logged_user_is_a_friend_of_the_given_user() {
        givenUser.addFriend(loggedUser);
        List<Trip> expectedTrips = buildTripList();

        prepareSessionBehavior(loggedUser);
        when(tripsDAOProvider.findTripsByUser(givenUser)).thenReturn(expectedTrips);

        List<Trip> resultTrips = tripService.getTripsByUser(givenUser);

        assertEquals(expectedTrips, resultTrips);
    }

    @Test
    public void throws_an_exception_when_user_is_not_logged_in() {
        when(userSessionProvider.getUserSessionInstance()).thenThrow(new UserNotLoggedInException());
        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(givenUser));
    }

    @Test
    void returns_empty_trip_list_if_logged_user_is_not_friend_of_the_given_user() {
        givenUser.addFriend(new User());

        prepareSessionBehavior(loggedUser);

        List<Trip> resultTrips = tripService.getTripsByUser(givenUser);

        verifyNoInteractions(tripsDAOProvider);
        assertEquals(new ArrayList<>(), resultTrips);
    }

    private void prepareSessionBehavior(User loggedUser) {
        when(userSessionProvider.getUserSessionInstance()).thenReturn(userSession);
        when(userSession.getLoggedUser()).thenReturn(loggedUser);
    }

    private static List<Trip> buildTripList() {
        List<Trip> expectedTrips = new ArrayList<>();
        Trip trip = new Trip();
        expectedTrips.add(trip);
        return expectedTrips;
    }

}
