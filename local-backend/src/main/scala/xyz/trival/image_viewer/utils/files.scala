package xyz.trival.image_viewer.utils.files

def isPathADirectory(path: String): Boolean =
  java.nio.file.Files.isDirectory(java.nio.file.Paths.get(path))
