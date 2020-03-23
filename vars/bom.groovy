#!groovy

List<Map<String, String>> toJobData(String csv) {
    final List<Map<String, String>> result = []
    csv?.eachLine { line, count ->
        // skip header line
        if (count > 0) {
            final List<String> tokens = line.trim().tokenize(",;")
            final Map<String, String> job = [:]
            job["APPLICATION"] = tokens[0]
            job["VERSION"] = tokens[1]
            job["GIT_REF"] = "master"
            result.add(job)
        }
    }
    return result
}

return this