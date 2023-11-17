pipeline {
	agent any
	stages {
		stage("verify toolings") {
		steps {
			sh '''
				docker info
				docker compose version
				curl --version
				jq --version
				docker compose up
			'''

		}
	}
	}

}
