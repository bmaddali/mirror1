def call(config) {
    try {
          setEnv() //This is for initial setup.
				  println "============================ BUILD MAVEN START ===================================="
          sh "mvn clean install"

				  //Check for Quality Gate conditions
				  //qualityGate(config)

			  println "============================ BUILD MAVEN COMPLETED ===================================="

    } catch (err) {
            error("Error encountered while maven build ${err}")
        }
}
