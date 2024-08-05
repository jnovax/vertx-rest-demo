FROM ubuntu:22.04

ARG USERNAME=vscode
ARG USER_GROUP_NAME=workspace
ARG USER_UID=1000
ARG USER_GID=1000

RUN groupadd --gid ${USER_GID} ${USER_GROUP_NAME} \
    && useradd --uid ${USER_UID} --shell /bin/bash --gid ${USER_GID} -m ${USERNAME}

ARG PKG="openjdk-17-jdk-headless openjdk-17-jre-headless"

SHELL ["/bin/bash", "-c"]

RUN apt-get update \
    && apt-get install -y ${PKG}


