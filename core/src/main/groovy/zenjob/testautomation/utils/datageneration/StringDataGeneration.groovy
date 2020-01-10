package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.DataGenerator
import zenjob.testautomation.utils.OpsUIField

import java.security.SecureRandom

class StringDataGeneration implements Generator {

    int length = new SecureRandom().nextInt(10) + 3
    String characterSet

    static final String ALPHA_NUMERIC_SET = (('a'..'z') + ('0'..'9')).join()

    @Override
    OpsUIField generate() {
        return new OpsUIField(DataGenerator.getRandomString(characterSet, length))
    }
}
