#!groovy

/**
 * Parse a CSV and return a list of maps with job parameters.
 * The following columns are supported:
 * <ol>
 *     <li>APPLICATION</li>
 *     <li>VERSION</li>
 * </ol>
 * @param csv CSV containing the application names &amp: versions to be deployed.
 * @return list of maps with job parameters
 */
@NonCPS
List<Map<String, String>> toApplicationJobParameterList(String csv) {
    final List<Map<String, String>> list = []
    csv?.eachLine { line ->
        // skip header line, e.g. "APPLICATION,VERSION"
        if (!line.trim().isEmpty() && !line.startsWith("APPLICATION")) {
            final List<String> tokens = line.trim().tokenize(",;")
            final String application = tokens[0].trim()
            final String version = tokens[1].trim()
            final String gitRef = "master"
            list.add(["APPLICATION": application, "VERSION": version, "GIT_REF": gitRef])
        }
    }
    return list
}

return this