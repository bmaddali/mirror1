@Grab('org.yaml:snakeyaml:1.17')

import org.yaml.snakeyaml.Yaml

def call(config) {

	try {
		// Discard the old builds keep max number of builds to 50
		if (currentBuild.rawBuild.project.logRotator == null || currentBuild.rawBuild.project.logRotator.numToKeepStr != "50" ) {
			discardOldBuilds()
			println "[INFO:] Discard old Builds is configured and Maximum number of builds to keep is set to ${currentBuild.rawBuild.project.logRotator.numToKeepStr}"
		}
		// check if buildType is defined in Jenkinsfile
		if("${env.buildType}" == "" || "${env.buildType}" == "None" || !env.buildType){
			println "[ERROR] Could not determine buildType from Jenkinsfile. Make sure you define buildType=\"npm\" or buildType=\"maven\""
			error("[ERROR] Could not determine buildType from Jenkinsfile. Make sure you define buildType=\"npm\" or buildType=\"maven\"")
		} else if ("${env.buildType}" != "gradle" && "${env.buildType}" != "maven" && "${env.buildType}" != "npm") {
			println "[ERROR] Acceptable value for buildType is only : buildType=\"npm\" or buildType=\"gradle\" or buildType=\"maven\". And Not: ${env.buildType}"
			error("[ERROR] Acceptable value for buildType is only : buildType=\"npm\" or buildType=\"gradle\" or buildType=\"maven\". And Not: ${env.buildType}")
		}

		env.mvnHome = tool 'maven-3.3.9'
		env.sonarScannerHome = tool name: 'RH-SonarQube', type: 'hudson.plugins.sonar.SonarRunnerInstallation';
		env.IS_PR_BUILD = "${BRANCH_NAME}".matches("PR-\\d+")
		env.ARTIFACTORY_URL ="artifacts.rhrepo.com"
		env.SONARQUBE_URL = "http://10.28.18.198:9000/"
}

def discardOldBuilds() {
	properties([
		buildDiscarder(logRotator(numToKeepStr: '50'))
	])
}
