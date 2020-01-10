package framework.src.Util.utils

import groovy.time.TimeCategory
import jodd.util.RandomString

import java.text.SimpleDateFormat
import java.util.regex.Pattern

class TokenParser {
    static String generateRandomString() {
        new RandomString().randomAlpha(10)
    }

    static int generateRandomInteger() {
        Math.abs(new Random().nextInt()) as Integer
    }

    static String convertToken(String token) {
        String extractedToken = token.substring(2, token.length() - 1)

        if (extractedToken == 'RANDOM_ALPHANUMERIC') {
            generateRandomString()
        }

        else if (extractedToken == 'RANDOM_INTEGER') {
            generateRandomInteger()
        }

        else if (extractedToken == 'A_YEAR_AGO') {
            use(TimeCategory) {
                return 1.year.ago.format('dd/MM/YYYY')
            }
        }

        else if (extractedToken == 'HALF_A_YEAR_AGO') {
            use(TimeCategory) {
                return 6.months.ago.format('dd/MM/YYYY')
            }
        }

        else if (extractedToken == 'HALF_A_YEAR_FROM_NOW') {
            use(TimeCategory) {
                return 1.year.from.now.format('dd/MM/YYYY')
            }
        }

        else if (extractedToken == 'NOW') {
            use(TimeCategory) {
                return new Date().format('dd/MM/YYYY')
            }
        }

        else if (extractedToken == 'TOMORROW') {
            use(TimeCategory) {
                return 1.day.from.now.format('YYYY-MM-dd')
            }
        }

        else if (extractedToken == 'AFTER_TOMORROW') {
            use(TimeCategory) {
                return 2.day.from.now.format('YYYY-MM-dd')
            }
        }

        else if (extractedToken == 'TODAY') {
            def date = new Date()
            def sdf =  new SimpleDateFormat("YYYY-MM-dd")
            return sdf.format(date)
        }

        else {
            return 'TOKEN_NOT_FOUND'
        }
    }

    static String parseToken(String s) {
        Pattern tokenPattern = ~/@\{\w+\}/
        String token = s.find(tokenPattern)
        if(token) {
            s.replaceAll(tokenPattern, convertToken(token))
        }
        else {
            s
        }
    }
}

