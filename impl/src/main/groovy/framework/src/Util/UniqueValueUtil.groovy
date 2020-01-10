package framework.src.Util

import java.security.MessageDigest

class UniqueValueUtil {

    static String getUniqueValue(String value) {
       return value + MessageDigest.getInstance("MD5").digest(new Date().toString().bytes).encodeHex().toString()
    }
}
