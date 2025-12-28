package com.ozalp.auth_service.util;

public final class Messages {

    public final static class AuthMessages {
        public static final String NOT_FOUND = "User not found";
        public static final String INVALID_AUTH_CREDENTIALS = "Invalid user credentials";
    }

    public static final class RefreshToken {
        public static final String NOT_FOUND = "Refresh token not found";
        public static final String INVALID_TOKEN = "Invalid token";
    }

    public static final class AccessToken {
        public static final String INVALID_TOKEN = "Invalid token";
    }
}
