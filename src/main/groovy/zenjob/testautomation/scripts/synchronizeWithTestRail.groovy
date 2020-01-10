package zenjob.testautomation.scripts

import zenjob.testautomation.sync.TestRailSynchronizer

TestRailSynchronizer synchronizer = new TestRailSynchronizer()
synchronizer.onlyProjectsWithIds()
            .synchronize()
