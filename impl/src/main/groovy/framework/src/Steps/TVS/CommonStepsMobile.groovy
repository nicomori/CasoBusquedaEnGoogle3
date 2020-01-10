package framework.src.Steps.TVS

import framework.src.Util.AndroidSelector
import framework.src.Util.IosSelector
import geb.Browser
import geb.binding.BindingUpdater

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

Given(~/^I am accessing ios$/) { ->
    println('kkkkkkkkkkkk22222')
    IosSelector browserSelector = new IosSelector(binding)

    println('kkkkkkkkkkkk')

    browserSelector.startingDriverIOS()
}

Given(~/^I am accessing android$/) { ->

    println('kkkkkkkkkk 1111')

    AndroidSelector browserSelector = new AndroidSelector(binding)

    println('kkkkkkkkkk22222')
    browserSelector.startingDriverAndroid()

    println('kkkkkkkkkk333333')
}