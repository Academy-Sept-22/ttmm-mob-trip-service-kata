package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    // happy path: valid user is logged in
    // mock logged user
    // mock tripDAO
    // returns triplist if logged user is a friend of the given user

    @Mock
    UserSession userSession;
    @Mock
    TripDAO tripDAO;

    @Test
    void returns_users_trip_list_if_logged_user_is_a_friend_of_the_given_user() {
        User loggedUser = new User();
        User givenUser = new User();
        try (MockedStatic<UserSession> userSessionStatic = Mockito.mockStatic(UserSession.class)) {
            userSessionStatic.when(UserSession::getInstance).thenReturn(userSession);
        }

        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        givenUser.addFriend(loggedUser);


    }

}
