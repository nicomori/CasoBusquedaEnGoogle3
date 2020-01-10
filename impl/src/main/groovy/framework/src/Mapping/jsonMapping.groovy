package framework.src.Mapping

import groovy.json.JsonSlurper

class jsonMapping {

    static Map<String, String> jsonMappingDifferentFields =
            ['Firstname': 'Name', 'Lastname':'Lastname', 'Gender':'Gender', 'Birthday':'Birthday' ,'Mobile number': 'Mobile Number',
             'Email':'E-Mail','Profession':'Profession', 'Personal title': 'Personal Title','Street':'Street', 'Street number':'Street Number',
             'Supplementary':'Supplementary', 'Postal Code':'Postal Code', 'City':'City','Country':'Country',
             'Employment Status':'Employment status', 'City of Birth':'City of birth','Country of birth':'Country of birth',
             'Birth name - previous last name':'Birth name - previous last name','Nationality':'Nationality',
             'Maritial status':'Marital Status', '#Children':'Children', 'Tax class':'Tax class',
             'Tax identification number':'Tax ident number','Denomination':'Denomination', 'Social insurance number':'Social insurance number',
             'Health insurance name':'Health insurance name', 'IBAN':'IBAN','BIC':'BIC', 'University':'University',
             'Study Subject':'Study Subject', 'Multiple Employment?':'Multiple Employment?','Car':'Car','Drivers Licenses':'Drivers Licenses',
             'Clothing Size':'Clothing Size','Sonstige Anmerkungen':'Internal zenjob comment', 'Weitere Erfahrungen':'Internal zenjob comment',
             'Primary / other jobs':'Primary / other jobs', 'Tags':'Tags']

    static Map<String, String> jsonMappingGender = ['Männlich': 'MALE', 'Weiblich':'FEMALE']


    static filename = '/impl/src/main/resources/employee.json'

    static File getJsonFile(){
        def defaultPathBase = new File( "." ).getCanonicalPath()
        File jsonFile = new File(defaultPathBase,filename)
        return jsonFile
    }

    static Map readJsonFromFile(){
        def jsonFile = getJsonFile()
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parse(jsonFile)
        return object

    }

    static String getOPSField(String jsonKey, def jsonValue) {
        def opsField
        if ( (jsonValue == true || false) && (jsonKey != "Car")) {
            opsField = "Job Categories"
        } else {
            opsField = jsonMappingDifferentFields.find { it.key == "${jsonKey}" }?.value
        }
        return opsField
    }

    static String getExpectedOPSGender(String jsonValue) {

        def expectedOPSGender = jsonMappingGender.find{ it.key == "${jsonValue}"}?.value
        return expectedOPSGender
    }

    static String getExpectedOPSBirthday(String jsonValue) {

        Date date = Date.parse( 'dd/MM/yyyy', jsonValue )
        String opsBirthday = date.format( 'yyyy-MM-dd' )
        return opsBirthday
    }

    static def getExpectedOPChildren(def jsonValue) {

        def opsChildren
        if (jsonValue == "Nein") {
            opsChildren = 0.0
        } else {
            opsChildren = jsonValue + ".0"
        }
        return opsChildren
    }

    static def getExpectedOpsCar(boolean jsonValue) {

        def opsCar
        if (jsonValue == true) {
            opsCar = "Yes"
        } else {
            opsCar = "No"
        }
        return opsCar
    }

    static String getExpectedOPSValue(String jsonKey,def jsonValue, String email) {
        def expectedOPSValue
        if (jsonValue == true && jsonKey != 'Car' ) {
            expectedOPSValue = jsonKey
        }
        else if (jsonKey in ['Gender','Personal title']) {
            expectedOPSValue = getExpectedOPSGender(jsonValue)

        }
        else if (jsonKey in ['Birthday']) {
            expectedOPSValue = getExpectedOPSBirthday(jsonValue)
        }
        else if (jsonKey in ['Email']) {
            expectedOPSValue = email
        }
        else if (jsonKey in ['#Children']) {
            expectedOPSValue = getExpectedOPChildren(jsonValue)
        }
        else if (jsonKey in ['Car']) {
            expectedOPSValue = getExpectedOpsCar(jsonValue)
        }
        else {
            expectedOPSValue = jsonValue
        }
        return expectedOPSValue
    }
}