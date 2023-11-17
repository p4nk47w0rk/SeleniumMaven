pipeline {
	agent any
	stages {
		stage("verify toolings") {
			steps {
				sh '''
					docker version
					docker info
					docker compose version
					curl --version

				'''

			}
		}
	}
}
