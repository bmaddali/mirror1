package com.rh.pipeline

class CodeQuality implements Serializable {

    def steps

    CodeQuality(steps){
        this.steps = steps
    }

    def scanWithSonar(String productName, String appVersion, String jacocoReportPath) {
        //if (!sonarSourcePath) && (!productName) && (!appVersion) throw new IllegalArgumentException("Error Missing Parameters, it can not be null or empty.")

        def sonar = steps.tool name: 'Sonar'

        steps.withSonarQubeEnv('RH-SonarQube') {
            steps.sh "${Sonar}/bin/sonar-runner -e -Dsonar.sources=src/main/java/ -Dsonar.projectKey=org.rh:${productName} -Dsonar.projectName=${productName} -Dsonar.coverage.jacoco.xmlReportPaths=${jacocoReportPath} -Dsonar.projectVersion=${appVersion} -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=."
        }
    }
}
