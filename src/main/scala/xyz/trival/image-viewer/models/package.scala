package xyz.trival.image_viewer.models

enum Role:
  case SoftwareDeveloper, SiteReliabilityEngineer, DevOps

case class Employee(name: String, role: Role)

case class EmployeesArgs(role: Role)

case class EmployeeArgs(name: String)
