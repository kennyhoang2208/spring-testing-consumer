pipeline {
  agent {
    label "jenkins-gradle"
  }
  environment {
    ORG = 'kennyhoang2208'
    APP_NAME = 'spring-testing-consumer'
    CHARTMUSEUM_CREDS = credentials('jenkins-x-chartmuseum')
  }
  stages {
    // Run unit test
    stage('Run unit tests') {
      when {
        branch 'PR-*'
      }
      steps {
        container('gradle') {
          // Run unit tests for the main service
          sh "gradle clean test"
        }
      }
    }

    stage('CI Build and push snapshot') {
      when {
        branch 'PR-*'
      }
      environment {
        PREVIEW_VERSION = "0.0.0-SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER"
        PREVIEW_NAMESPACE = "$APP_NAME-$BRANCH_NAME".toLowerCase()
        HELM_RELEASE = "$PREVIEW_NAMESPACE".toLowerCase()
      }
      steps {
        container('gradle') {
          // Build the main service, exclude the test since it has been already run.
          sh "gradle clean build --exclude-task test"

          sh "export VERSION=$PREVIEW_VERSION && skaffold build -f skaffold.yaml"
          sh "jx step post build --image $DOCKER_REGISTRY/$ORG/$APP_NAME:$PREVIEW_VERSION"
          dir('./charts/preview') {
            sh "make preview"
            sh "jx preview --app $APP_NAME --dir ../.."
          }
        }
      }
    }

    stage('Run Integration Unit Tests') {
      when {
        branch 'PR-*'
      }
      steps {
        container('gradle') {
          // Run integration tests
          // Sleep for 2 mins
          sh "echo 'Sleeping for 2 minutes' && sleep 2m"
          sh "gradle integrationTest"
        }
      }
    }

    stage('Run BDD tests') {
      when {
        branch 'PR-*'
      }
      steps {
        container('gradle') {
          // Run BDD tests
          sh "echo 'TODO'"
        }
      }
    }

    stage('Build Release') {
      when {
        branch 'master'
      }
      steps {
        container('gradle') {

          // ensure we're not on a detached head
          sh "git checkout master"
          sh "git config --global credential.helper store"
          sh "jx step git credentials"

          // so we can retrieve the version in later steps
          sh "echo \$(jx-release-version) > VERSION"
          sh "jx step tag --version \$(cat VERSION)"
          sh "gradle clean build"
          sh "export VERSION=`cat VERSION` && skaffold build -f skaffold.yaml"
          sh "jx step post build --image $DOCKER_REGISTRY/$ORG/$APP_NAME:\$(cat VERSION)"
        }
      }
    }
    stage('Promote to Environments') {
      when {
        branch 'master'
      }
      steps {
        container('gradle') {
          dir('./charts/spring-testing-consumer') {
            sh "jx step changelog --version v\$(cat ../../VERSION)"

            // release the helm chart
            sh "jx step helm release"

            // promote through all 'Auto' promotion Environments
            sh "jx promote -b --all-auto --timeout 1h --version \$(cat ../../VERSION)"
          }
        }
      }
    }
  }
  post {
        always {
          cleanWs()
        }
  }
}
