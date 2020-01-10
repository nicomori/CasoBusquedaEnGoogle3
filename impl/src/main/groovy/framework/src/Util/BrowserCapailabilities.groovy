package framework.src.Util

import org.openqa.selenium.remote.DesiredCapabilities

class BrowserCapailabilities {
    static DesiredCapabilities returnCapailabilities (){
        String driverParameter = ''

        try {
            driverParameter = System.getProperty('browser').toLowerCase()
        } catch (Exception e) {
            println('Appear a issue at the momento to try to get the parameter browser from the command line.')
        }

        if(driverParameter.contains('chro')){
            return DesiredCapabilities.chrome()
        }

        if(driverParameter.contains('fire')){
            return DesiredCapabilities.firefox()
        }

        if(driverParameter.contains('ie')){
            return DesiredCapabilities.internetExplorer()
        }

        if(driverParameter.contains('edge')){
            return DesiredCapabilities.edge()
        }

        return TestConstants2.DefaultBrowser.CHROME_BROUSER
    }
}
