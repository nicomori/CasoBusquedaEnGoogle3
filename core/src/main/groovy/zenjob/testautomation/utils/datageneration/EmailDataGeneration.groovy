package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.DataGenerator
import zenjob.testautomation.utils.OpsUIField

import java.security.SecureRandom

class EmailDataGeneration implements Generator {

    int userLength = new SecureRandom().nextInt(10) + 3
    int domainLength = new SecureRandom().nextInt(10) + 5
    boolean uuidUser = true

    static final String INVALID_EMAIL = 'asdf'

    @Override
    OpsUIField generate() {
        String userString = uuidUser ? UUID.randomUUID().toString() : DataGenerator.getRandomStringAlphanumeric(userLength)
        new OpsUIField(userString + '@' + DataGenerator.getRandomStringAlphanumeric(domainLength) + '.' + DataGenerator.getRandomString(('a'..'z').join(), new SecureRandom().nextInt(2) + 2))
    }
}
