package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.UserSession;

public class UserSessionProvider {
    public UserSessionProvider() {
    }

    UserSession getUserSessionInstance() {
        return UserSession.getInstance();
    }
}