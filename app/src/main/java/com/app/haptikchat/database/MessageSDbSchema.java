package com.app.haptikchat.database;

/**
 * Created by swapna on 5/3/17.
 */
public class MessageSDbSchema {
    public static final class ListofTable {
        public static final String NAME = "MessageManager";

        public static final class Columns {
            protected static final String ID = "id";
            protected static final String USER_NAME = "user_name";
            protected static final String MESSAGES = "messages";
            protected static final String IMAGE = "image";
            protected static final String NAME = "name";
            protected static final String MYFAV = "fav";
            protected static final String DATE = "date";
            protected static final String ME = "me";
        }
    }
}
