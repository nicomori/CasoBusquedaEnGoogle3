package zenjob.testautomation.sync.readers

import zenjob.testautomation.sync.schemas.ExternalPlatformSchema

interface ExternalPlatformReader {
    ExternalPlatformSchema read(Map<String, List<Long>> filters)
}