pipeline {
	agent any
	stages {
		stage("verify toolings") {
			steps {
				sh '''
					docker info
					docker version
					docker compose version
					curl --version
					jq --version
				'''

			}
		}
	}
}
