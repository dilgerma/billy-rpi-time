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
    def img = docker.build("dilgerm/billy-time:${env.BUILD_ID}", 'build/docker');
    stage 'push'
    img.push();
    stage 'deploy'
    img.run('-p 9090:8080')

    stage 'report'
    step(['$class' : 'InfluxDbPublisher', 'selectedTarget' : 'test'])

}
