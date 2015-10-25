package tennis;

import cucumber.api.java.de.Angenommen;

public class DoppelStepDefinitions {

    @Angenommen("^Team (\\d+) besteht aus (\\w+\\s\\w+) und (\\w+\\s\\w+)$")
    public void Team_besteht_aus_Bob_Bryan_und_Mark_Bryan(int arg1,String name1, String name2) throws Throwable {
        Match match = Match.getInstance();
        match.fuegeSpielerHinzu(new Spieler(name1, null, null),new Spieler(name2, null, null));
    }

}
