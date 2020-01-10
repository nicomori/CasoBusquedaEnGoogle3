package framework.src.Modules

import geb.Module
import geb.navigator.Navigator
import framework.src.Util.utils.OpsUIField
//import zenjob.testautomation.utils.OpsUIField

class FormFieldsModule extends Module {

    Navigator form

    /*
    *  Structural description of `fields`:
    *
    *  [
    *       {fieldName}: [ type: {someTypeDescriptionOrAClass} , mandatory: {true/false} , range  {start .. end}],
    *  ]
    *
    */

    Map fields

    Map<String, OpsUIField> fillFieldsRandom(Map args) {
        if (!fields) {
            return [:]
        }

        args = args ?: [:]

        fields.collectEntries {
            if (args && args.exclude && args.exclude.contains(it.key)) {
                return [(it.key): null]
            }
            // fieldName it.key
            // value is description with mandatory flag and type
            if (args.onlyMandatory) {
                if (it.value['mandatory']) {
                    OpsUIField rand = generateRandomByField(it.value)
                    form."${it.key}" = rand
                    return [(it.key): rand]
                }
                return [(it.key): null]
            }
            OpsUIField rand = generateRandomByField(it.value)
            form."${it.key}" = rand
            return [(it.key): rand]
        } as Map<String, OpsUIField>
    }

    Map<String, OpsUIField> fillMandatoryFieldsRandom(Map args) {
        args = args ?: [:]

        args.onlyMandatory = true
        fillFieldsRandom(args)
    }

    def fillField(String field) {
        fillField(field, null)
    }

    def fillField(String field, Object value) {
        if (!fields || !fields.containsKey(field)) {
            // throw field not found error
            return
        }
        if (value != null) {
            form."${field}" = value
        } else {
            Map description = (Map) fields.get(field)
            form."${field}" = generateRandomByField(description)
        }
        form."${field}"
    }

    String getField(String field) {
        if (!fields || !fields.containsKey(field)) {
            // throw field not found error
            return
        }
        form."${field}"
    }

    OpsUIField generateRandomByField(def fieldDescription) {
        Object type = fieldDescription['type']
        if (type in Generator) {
            return type.generate()
        }
    }
}
