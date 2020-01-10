package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.OpsUIField

import java.security.SecureRandom

class NumberDataGeneration implements Generator {

    int length = new SecureRandom().nextInt(10) + 3

    @Override
    OpsUIField generate() {
        String number = new SecureRandom().nextInt(length)
        new OpsUIField(number, number.toFloat().toString())
    }
}
