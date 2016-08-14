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
//    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
//        img.push();
//        // should happen after smoke test
//        img.push 'latest'
//    }
    stage 'deploy'
    img.run('-p 8080', '-e dev')

    stage 'report'
    step(['$class' : 'InfluxDbPublisher', 'selectedTarget' : 'influx'])

}
