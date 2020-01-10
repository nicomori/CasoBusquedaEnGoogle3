package zenjob.testautomation.utils

import spock.lang.Specification

class TokenParserSpec extends Specification {
    void 'should convert parse A_YEAR_AGO token'() {
        given:
        TokenParser tokenParser = new TokenParser()

        expect:
        tokenParser.parseToken('@{A_YEAR_AGO}') == '18/02/2018'
    }
}
