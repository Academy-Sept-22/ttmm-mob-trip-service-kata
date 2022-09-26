package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class UserSessionProvider {
    public UserSessionProvider() {
    }

    User getLoggedUser() throws UserNotLoggedInException {
        return UserSession.getInstance().getLoggedUser();
    }
}