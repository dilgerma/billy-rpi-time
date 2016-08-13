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
    sh 'docker build -t dilgerm/billy-time:1.0.0 build/docker'
    stage 'deploy'
    stage 'report'
    step(['$class' : 'InfluxDbPublisher', 'selectedTarget' : 'test'])

}
