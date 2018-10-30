package com.example.chris.bj_final.data;

import android.provider.BaseColumns;

/**
 * Created by chris on 9/25/2017.
 */

public final class BlackJackContract {
    private BlackJackContract() {}

        public static final class BlackJackEntry implements BaseColumns {
            public final static String TABLE_NAME = "Blackjack";

            public final static String COLUMN_NAME = "Name";

            public final static String COLUMN_HS = "HighScore";

    }

}
