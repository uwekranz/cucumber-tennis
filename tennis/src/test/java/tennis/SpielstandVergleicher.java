package tennis;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class SpielstandVergleicher {
    public static Matcher<MatchSpielstand> gleicht(final MatchSpielstand erwarteterSpielstand) {
        return new TypeSafeMatcher<MatchSpielstand>() {

            @Override
            protected boolean matchesSafely(MatchSpielstand tatsaechlicherSpielstand) {
                return tatsaechlicherSpielstand.equals(erwarteterSpielstand);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(erwarteterSpielstand.toString());
            }
        };
    }
}
