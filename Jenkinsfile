pipeline {
	agent any{
		stages["verify toolings"] {
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
