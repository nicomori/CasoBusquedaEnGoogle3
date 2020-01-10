package framework.src.Util.utils.datageneration

class DateDataGeneration implements framework.src.Util.utils.datageneration.Generator {

    def range = {
        Date start, end
        use(groovy.time.TimeCategory) {
            Date date = new Date()
            start = date - 10.years
            end = date + 10.years
        }
        start..end
    }

    String format = 'dd/MM/yyyy'
    boolean asString = true

    @Override
    framework.src.Util.utils.OpsUIField generate() {
        Date date = framework.src.Util.utils.DataGenerator.randomDate(range)
        asString ? new framework.src.Util.utils.OpsUIField(date.format(format)) : new framework.src.Util.utils.OpsUIField(date)
    }
}
