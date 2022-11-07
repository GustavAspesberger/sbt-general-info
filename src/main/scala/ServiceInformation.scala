import play.api.libs.json.{Json, OFormat}

case class ServiceInformation(
  name: String,
  version: String,
  subProjects: List[String]
)

object ServiceInformation {

  implicit val json: OFormat[ServiceInformation] = Json.format[ServiceInformation]

}