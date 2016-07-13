node {
    stage 'build'

    checkout scm
    sh './gradlew build'

    stage 'integration-test'
    stage 'docker-build'
    stage 'docker-push'
    stage 'deploy'
}