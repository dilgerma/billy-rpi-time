node {
    stage 'build'
    //tasks
    checkout scm
    sh './gradlew build'

    stage 'integration-test'
    //tasks
    sh './gradlew integrationTest'

    stage 'docker-build'
    //tasks
    sh './gradlew prepareDockerBuild'
    sh 'cd build/docker && docker build -t dilgerm/billy-time:1.0.${env.BUILD_NUMBER}'

    stage 'docker-push'
    //tasks

    stage 'deploy'
}