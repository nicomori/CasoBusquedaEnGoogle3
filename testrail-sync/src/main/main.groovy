TestRailReader testRailReader = new TestRailReader()
Map<String, List<Long>> filters = [projectIds: [6]]
tr2fsTranslator = new TestRailToFileSystemTranslator(trs)
FileSystemSchema fss = tr2fsTranslator.translate()
FileSystemSchemaExecutor fssExecutor = new FileSystemSchemaExecutor(fss)

println 'Executing the TestRail synchronization script'

assert fssExecutor.execute()
