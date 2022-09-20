# Trival Image Viewer - Server

## Development

Once you are inside the project directory, run the application:

You can reload the backend in watchmode from within the sbt console (using [sbt-revolver](https://github.com/spray/sbt-revolver))

```scala
~ backend/reStart
```

run the frontend test with

```scala
sbt frontend/run
```

## Autogenerating caliban client

So far the only way to trigger compilation is to change build.sbt, then compile the frontend without (re)starting the backend. If backend compiles first, client is not generated for some reason.

## TODOS

* [ ] Try DB Schema definition with [liquibase](https://www.liquibase.com/blog/3-ways-to-run-liquibase)
