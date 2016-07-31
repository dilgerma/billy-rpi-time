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
    sh './gradlew creatDockerfile'

    sh 'cd build/docker && docker build'

    stage 'docker-push'
    //tasks
    sh 'gradlew dockerPush'

    stage 'deploy'
}