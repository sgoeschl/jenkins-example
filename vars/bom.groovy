#!groovy

@NonCPS
List<Map<String, String>> toJobData(String csv) {
    final List<Map<String, String>> list = []
    csv?.eachLine { line ->
        // skip header line, e.g. "APPLICATION,VERSION"
        if (!line.trim().isEmpty() && !line.startsWith("APPLICATION")) {
            final List<String> tokens = line.trim().tokenize(",;")
            list.add(["APPLICATION":tokens[0], "VERSION":tokens[1], "GIT_REF":"master"])
        }
    }
    return list
}

return this