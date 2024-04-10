FROM ubuntu:latest
LABEL authors="santino"

ENTRYPOINT ["top", "-b"]