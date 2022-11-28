# Trival Image Viewer - Server

## Development

Once you are inside the project directory, run the application:

You can reload the backend in watchmode from within the sbt console (using [sbt-revolver](https://github.com/spray/sbt-revolver))

```scala
~ localBackend/reStart
```

## TODOS

- use json files as config persistence
- compile imagemagick with heic, avif and jxl (jpeg xl) support (see https://eplt.medium.com/5-minutes-to-install-imagemagick-with-heic-support-on-ubuntu-18-04-digitalocean-fe2d09dcef1 for guidance)
- read filesize and dimensions by imagemagick
