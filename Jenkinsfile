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

    stage 'docker-push'
    dockerBuildAndPublish {
        repositoryName('example/project-a')
//        tag('${BUILD_TIMESTAMP}-${GIT_REVISION,length=7}')
        registryCredentials('docker-hub')
        forcePull(false)
        createFingerprints(false)
        skipDecorate()
    }

    stage 'deploy'
}
