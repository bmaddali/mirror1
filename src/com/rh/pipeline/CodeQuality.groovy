package com.rh.pipeline

class CodeQuality implements Serializable {

    def steps

    CodeQuality(steps){
        this.steps = steps
    }

    def scanWithSonar(sonarSourcePath, productName, jacocoReportPath = null, appVersion) {
        if (!sonarSourcePath) && (!productName) && (!appVersion) throw new IllegalArgumentException("Error Missing Parameters, it can not be null or empty.")

        def sonar = steps.tool name: 'sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'

        steps.withSonarQube('RH-SonarQube') {
            steps.sh """
                ${sonar}/bin/sonar-runner -e \\
                -Dsonar.sources=${sonarSourcePath} \\
                -Dsonar.projectKey=org.rh:${productName} \\
                -Dsonar.projectName=${productName} \\
                -Dsonar.coverage.jacoco.xmlReportPaths=${jacocoReportPath} \\
                -Dsonar.projectVersion=${appVersion} \\
                -Dsonar.sourceEncoding=UTF-8 \\
    	          -Dsonar.java.binaries=.
            """
        }
    }
}
