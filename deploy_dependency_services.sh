#!/usr/bin/env bash
set -e

# The list of known dependency service names.
export DEPENDENCY_SERVICES=(
  "spring-testing"
)

# Re-defined Envs that declared from the Jenkinsfile.
export ORG="kennyhoang2208"
export APP_NAME="spring-testing-consumer"
export GIT_PROVIDER_URL="https://github.com"

export PROJ_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export PROJ_PARENT="$( cd ${PROJ_HOME}/.. && pwd )"

for service in "${DEPENDENCY_SERVICES[@]}"; do
  # Clone the service source code
  echo "Deploying service: ---${service}---"
  echo "-----------------------------------"
  cd ${PROJ_PARENT}
  # service_repo="${GIT_PROVIDER_URL}/${ORG}/${service}.git"

  echo "Make dir ...: ${service}"
  mkdir ${service}

  # Build and deploy in to the preview environment
  cd ${PROJ_PARENT}/${service}

  # Using the latest version
  echo 'latest' > VERSION

  # gradle clean build
  export VERSION=`cat VERSION`

  # Config for service preview naming.
  export PREVIEW_VERSION=$VERSION
  export PREVIEW_NAMESPACE="${service}-${BRANCH_NAME}"
  export HELM_RELEASE=$PREVIEW_NAMESPACE

  # Override app name
  export APP_NAME=$service

  # Make preview
  # Download the latest chart

  # Deploy preview
  jx preview --release "${service}-preview" --app ${service} --dir ../..

  echo "-----------------------------------"
done

cd ${PROJ_HOME}

exit 0
