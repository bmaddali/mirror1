def call(config) {
    try {
          setEnv() //This is for initial setup.
				  println "============================ BUILD MAVEN START ===================================="
          sh "mvn clean install"

				  //Check for Quality Gate conditions
				  //qualityGate(config)

              // publish artifact on when built from master branch.
              if ("${BRANCH_NAME}".contains("master")){
                    //build container and publish
              }
			  println "============================ BUILD MAVEN COMPLETED ===================================="

              // Common job rename script
/*              setEnv([getArtifactsVersion: "true" ]) //This is to get correct version after deploy and renaming the build correctly
              script {
                if (env.configVersion)
                    currentBuild.displayName = "${env.APP_VERSION}_${env.BUILD_NUMBER}"
                else
                    currentBuild.displayName = "${env.APP_VERSION}"
                }
              } */

    } catch (err) {
            error("Error encountered while maven build ${err}")
        }
}
