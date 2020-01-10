package framework.src.Util.utils.datageneration


import java.security.SecureRandom

class NumberDataGeneration implements framework.src.Util.utils.datageneration.Generator {

    int length = new SecureRandom().nextInt(10) + 3

    @Override
    framework.src.Util.utils.OpsUIField generate() {
        String number = new SecureRandom().nextInt(length)
        new framework.src.Util.utils.OpsUIField(number, number.toFloat().toString())
    }
}
