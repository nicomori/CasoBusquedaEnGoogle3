package zenjob.testautomation.utils

import java.security.SecureRandom

class DataGenerator {

    static String getRandomString(String characterSet, int length) {
        (1..length).collect {
            characterSet[new SecureRandom().nextInt(characterSet.length())]
        }.join()
    }

    static String getRandomStringAlphanumeric(int length) {
        getRandomString((('a'..'z') + ('0'..'9')).join(), length)
    }

    static String getRandomEmailAddressWithLength(int userLength, int domainLength) {
        getRandomStringAlphanumeric(userLength) + '@' + getRandomStringAlphanumeric(domainLength) + '.' + getRandomString(('a'..'z').join(), new SecureRandom().nextInt(2) + 2)
    }

    static String getRandomEmailAddress() {
        getRandomEmailAddressWithLength(new SecureRandom().nextInt(10) + 3, new SecureRandom().nextInt(10) + 5)
    }

    static String getRandomZenjobEmailAddress() {
        int userLength = 7
        getRandomStringAlphanumeric(userLength) + '@zenjob.com'
    }

    static String getRandomMobileNumber(int length) {
        '+49176' + getRandomString(('0'..'9').join(), length - 3)
    }

    static String getRandomMobileNumber() {
        getRandomMobileNumber(11) // standard for germany
    }

    static String getRandomSelect(List<String> options) {
        options[new SecureRandom().nextInt(options.size())]
    }

    @SuppressWarnings('MethodReturnTypeRequired')
    @SuppressWarnings('NoDef')
    static getRandomElementFromList(List l) {
        l[new SecureRandom().nextInt(l.size())]
    }

    static String getRandomGender() {
        getRandomSelect([
                'MALE',
                'FEMALE',
        ])
    }

    @SuppressWarnings('GetterMethodCouldBeProperty')
    static String getRandomAcademicTitle() {
        'Prof.'
    }

    static String getRandomStreetNumberAddOn() {
        getRandomString(('A'..'Z').join(), 1)
    }

    static String getRandomSupplementary() {
        getRandomStringAlphanumeric(5) + ' ' + getRandomStringAlphanumeric(3)
    }

    static String getRandomDistrict() {
        List<String> districts = ['Neuköln', 'Charlottenburg', 'Mitte', 'Schöneberg', 'Moabit']
        getRandomElementFromList(districts)
    }

    static String getRandomState() {
        List<String> states = ['Berlin', 'Hamburg', 'Nordrhein-Westfalen', 'Bayern']
        getRandomElementFromList(states)
    }

    static String getRandomCity() {
        // TODO prefill with correct values
        List<String> cities = ['Berlin', 'Aachen', 'Bochum', 'Bonn', 'Darmstadt']
        getRandomElementFromList(cities)
    }

    static String getRandomCountry() {
        List<String> countries = ['Germany']
        getRandomElementFromList(countries)
    }

    static String getRandomPostalCode() {
        getRandomString(('0'..'9').join(), 5)
    }

    static String getRandomStreetNumber() {
        getRandomString(('0'..'9').join(), 2)
    }

    static String getRandomStreet() {
        List<String> streets = ['Rosenthaler Str.', 'Lenaustr.', 'Flensburger Str.']
        streets[new SecureRandom().nextInt(streets.size())]
    }

    static Set<String> getRandomMultiSelectList(int size) {
        // TODO prefill with correct values
        Set<String> categoryList = ['Helper', 'Sachbearbeiter']

        int addedCategoryCount = 0
        Set<String> randomCategories = []
        while (addedCategoryCount < size) {
            String randomCategory = categoryList[new SecureRandom().nextInt(categoryList.size())]
            if (!randomCategories.contains(randomCategory)) {
                randomCategories << randomCategory
                addedCategoryCount++
            }
            if (addedCategoryCount == categoryList.size() && size > categoryList.size()) {
                break
            }
        }
        randomCategories
    }

    static String getRandomTags(int size) {
        int a = 0
        List<String> tags = []
        while (a < size - 1) {
            String newTag = getRandomStringAlphanumeric(new SecureRandom().nextInt(10))
            while (tags.contains(newTag)) {
                newTag = getRandomStringAlphanumeric(new SecureRandom().nextInt(10))
            }
            tags << newTag
            a++
        }
        tags.join(',')
    }

    static Date randomDate(Range<Date> range) {
        range.from + new SecureRandom().nextInt(range.to - range.from + 1)
    }

    @SuppressWarnings('GetterMethodCouldBeProperty')
    static String getValidBirthday() {
        '12/1/1990'
    }

    @SuppressWarnings('GetterMethodCouldBeProperty')
    static String getInvalidEmailAddress() {
        'asdf'
    }

    static Range<Date> validStudentBirthdayRange() {
        Date start, end
        use(groovy.time.TimeCategory) {
            Date date = new Date()
            start = date - 35.years
            end = date - 18.years
        }
        start..end
    }

}
