#!/usr/bin/env bash

# The list of known dependency service names.
export SERVICES=(
  "spring-testing"
)

# Re-defined Envs that declared from the Jenkinsfile.
export ORG="kennyhoang2208"
export APP_NAME="spring-testing-consumer"
export GIT_PROVIDER_URL="https://github.com"

export PROJ_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export PROJ_PARENT="$( cd ${PROJ_HOME}/.. && pwd )"

for service in "${SERVICES[@]}"; do
  # Clone the service source code
  echo "Deploying service: ---${service}---"
  echo "-----------------------------------"
  cd ${PROJ_PARENT}
  git clone ${GIT_PROVIDER_URL}/${ORG}/${service}.git ${service}

  # Build and deploy in to the preview environment
  cd ${PROJ_PARENT}/${service}
  echo $(jx-release-version) > VERSION
  gradle clean build
  export VERSION=`cat VERSION` && skaffold build -f skaffold.yaml
  jx step post build --image ${DOCKER_REGISTRY}/${ORG}/${service}:$(cat VERSION)

  # Config for service preview naming.
  export PREVIEW_VERSION=$VERSION
  export PREVIEW_NAMESPACE = "${service}-${BRANCH_NAME}"
  export HELM_RELEASE = $PREVIEW_NAMESPACE

  # Make preview
  service_home=${PROJ_PARENT}/${service}/
  cd "${service_home}/charts/preview"
  make preview
  jx preview --release ${service} --app ${service} --dir ../..

  echo "-----------------------------------"
done

cd ${PROJ_HOME}
