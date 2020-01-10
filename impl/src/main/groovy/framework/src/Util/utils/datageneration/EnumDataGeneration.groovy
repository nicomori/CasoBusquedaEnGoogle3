package framework.src.Util.utils.datageneration

class EnumDataGeneration implements framework.src.Util.utils.datageneration.Generator {

    Class enumClass

    @Override
    @SuppressWarnings('UnnecessaryCollectCall')
    framework.src.Util.utils.OpsUIField generate() {
        framework.src.Util.utils.DataGenerator.getRandomElementFromList(enumClass.values().collect {
            return new framework.src.Util.utils.OpsUIField(it.value, it.showValue)
        }) as framework.src.Util.utils.OpsUIField
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
