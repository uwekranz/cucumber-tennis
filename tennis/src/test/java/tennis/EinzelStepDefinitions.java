package tennis;

import cucumber.api.java.de.Angenommen;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static tennis.AufschlagRolle.AUFSCHLAEGER;
import static tennis.AufschlagRolle.RUECKSCHLAEGER;

public class EinzelStepDefinitions {

    @Angenommen("^Federa beginnt das Match als Aufschläger gegen Djokovic$")
    public void Federa_beginnt_das_Match_als_Aufschläger_gegen_Djokovic() throws Throwable {
        Match match = Match.getInstance();
        Spieler federa = new Spieler("Federa", AUFSCHLAEGER,new SpielerSpielstand());
        Spieler djokovic = new Spieler("Djokovic", RUECKSCHLAEGER, new SpielerSpielstand());
        match.fuegeSpielerHinzu(federa, djokovic);
    }

}
