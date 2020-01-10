package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.DataGenerator
import zenjob.testautomation.utils.OpsUIField

class EnumDataGeneration implements Generator {

    Class enumClass

    @Override
    @SuppressWarnings('UnnecessaryCollectCall')
    OpsUIField generate() {
        DataGenerator.getRandomElementFromList(enumClass.values().collect {
            return new OpsUIField(it.value, it.showValue)
        }) as OpsUIField
    }

    static final EnumDataGeneration BOOL_ENUM_DATAGENERATOR = new EnumDataGeneration(enumClass: BooleanEnum)

    enum BooleanEnum {
        TRUE    ('True'),
        FALSE   ('False'),

        private final String value
        private final String showValue

        BooleanEnum(String value) {
            this.value = value
            this.showValue = value
        }

        String toString() {
            value
        }

        String getValue() {
            value
        }
    }
}
