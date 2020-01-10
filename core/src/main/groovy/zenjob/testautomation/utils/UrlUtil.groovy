package zenjob.testautomation.utils

class UrlUtil {

    /***
     * @param bseUrl, urlString
     * @return a list of segments after the base url
     * @example
     * if "https://staging-main.zenjob.com/ops/employee/show/2223" is the urlString
     * and "https://staging-main.zenjob.com" is the baseUrl
     * then the resulting segment list will be ['ops', 'employee', 'show', '2223']
     */

    static getUrlSegments(String baseUrl, String urlString) {
        // cut off the baseUrl part
        String dynamicUrlPart = urlString.substring(baseUrl.length())
        dynamicUrlPart.split('/')
    }

    static  Map getUrlParamters(URL url) {
        if (!url.query) {
            return [:]
        }
        url.query.split('&').inject([:]) { map, kv ->
            def (String key, String value) = kv.split('=').toList()
            if (value != null) {
                map[key] = URLDecoder.decode(value, 'utf-8')
            }
            return map
        } as Map
    }
}
