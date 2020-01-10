package framework.src.Util.utils.datageneration


import java.security.SecureRandom

class EmailDataGeneration implements Generator {

    int userLength = new SecureRandom().nextInt(10) + 3
    int domainLength = new SecureRandom().nextInt(10) + 5
    boolean uuidUser = true

    static final String INVALID_EMAIL = 'asdf'

    @Override
    framework.src.Util.utils.OpsUIField generate() {
        String userString = uuidUser ? UUID.randomUUID().toString() : framework.src.Util.utils.DataGenerator.getRandomStringAlphanumeric(userLength)
        new framework.src.Util.utils.OpsUIField(userString + '@' + framework.src.Util.utils.DataGenerator.getRandomStringAlphanumeric(domainLength) + '.' + framework.src.Util.utils.DataGenerator.getRandomString(('a'..'z').join(), new SecureRandom().nextInt(2) + 2))
    }
}
