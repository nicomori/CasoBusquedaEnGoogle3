package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.DataGenerator
import zenjob.testautomation.utils.OpsUIField

class BooleanDataGeneration implements Generator {

    static final BooleanDataGeneration INSTANCE = new BooleanDataGeneration()

    @Override
     OpsUIField generate() {
        new OpsUIField(DataGenerator.getRandomElementFromList([true, false]).toString())
    }
}
