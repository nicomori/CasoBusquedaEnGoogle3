package framework.src.Util.utils.datageneration

class BooleanDataGeneration implements framework.src.Util.utils.datageneration.Generator {

    static final BooleanDataGeneration INSTANCE = new BooleanDataGeneration()

    @Override
     framework.src.Util.utils.OpsUIField generate() {
        new framework.src.Util.utils.OpsUIField(framework.src.Util.utils.DataGenerator.getRandomElementFromList([true, false]).toString())
    }
}
