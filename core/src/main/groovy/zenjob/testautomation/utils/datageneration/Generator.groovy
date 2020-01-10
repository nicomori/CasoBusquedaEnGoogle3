package zenjob.testautomation.utils.datageneration

import zenjob.testautomation.utils.OpsUIField

interface Generator {

    @SuppressWarnings('MethodReturnTypeRequired')
    @SuppressWarnings('NoDef')
    OpsUIField generate()
}
