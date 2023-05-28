# Trival Image Viewer

** This repo is discontinued. The setup with Scala and graphql seems too complex for a relatively simple app. Instead, a new architecture with nodejs, nextjs and trpc is chosen for hopefully better productivity... **

This is the repo of all backend and frontend projects for the Trival image libraries viewer.

The project aims to let you manage and browse your directory based image libraries on your local computer.
Later a sync server should enable you to view and edit your libraries remotely from mobile devices.

## TODOS

### MVP (local library viewing)

- use json files as config persistence
- compile imagemagick with heic, avif and jxl (jpeg xl) support (see https://eplt.medium.com/5-minutes-to-install-imagemagick-with-heic-support-on-ubuntu-18-04-digitalocean-fe2d09dcef1 for guidance)
- read filesize and dimensions by imagemagick

### Remove Library viewing

- implement a remote server that allows to register local libraries for viewing and browsing on any device connected to the remote server.
