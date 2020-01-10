package framework.src.Util.utils.datageneration


import java.security.SecureRandom

class StringDataGeneration implements framework.src.Util.utils.datageneration.Generator {

    int length = new SecureRandom().nextInt(10) + 3
    String characterSet

    static final String ALPHA_NUMERIC_SET = (('a'..'z') + ('0'..'9')).join()

    @Override
    framework.src.Util.utils.OpsUIField generate() {
        return new framework.src.Util.utils.OpsUIField(framework.src.Util.utils.DataGenerator.getRandomString(characterSet, length))
    }
}
