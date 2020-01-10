package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.DataGenerator
import zenjob.testautomation.utils.OpsUIField


class DateDataGeneration implements Generator {

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
    OpsUIField generate() {
        Date date = DataGenerator.randomDate(range)
        asString ? new OpsUIField(date.format(format)) : new OpsUIField(date)
    }
}
