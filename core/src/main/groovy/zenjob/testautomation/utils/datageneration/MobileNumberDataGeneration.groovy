package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.OpsUIField

class MobileNumberDataGeneration implements Generator {

    int length = 11

    @Override
    OpsUIField generate() {
        new OpsUIField('+49176' + DataGenerator.getRandomString(('0'..'9').join(), length - 3))
    }
}
