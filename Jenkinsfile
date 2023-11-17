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
		stage("Prune Docker data"){
			steps{
				sh'docker system prune -a --volumes -f'
			}
		}
		stage("Starting Container"){
			steps{
				sh'docker compose up -d --wait'
				sh'docker compose ps'

			}
		}
		//stage("Run tests against the container"){

		//}
	}
	post{
		always{
			sh'docker compose down --remove-orphans -v'
			sh'docker compose ps'
		}
	}
}
