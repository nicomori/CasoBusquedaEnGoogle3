package framework.src.Util.utils.datageneration

class MobileNumberDataGeneration implements framework.src.Util.utils.datageneration.Generator {

    int length = 11

    @Override
    framework.src.Util.utils.OpsUIField generate() {
        new framework.src.Util.utils.OpsUIField('+49176' + DataGenerator.getRandomString(('0'..'9').join(), length - 3))
    }
}
