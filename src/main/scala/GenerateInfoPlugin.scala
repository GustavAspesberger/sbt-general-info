import sbt.*
import Keys.*
import play.api.libs.json.{JsValue, Json}
import sbt.Def.spaceDelimited

import java.io.File

object GenerateInfoPlugin extends AutoPlugin {

  object autoImport {
    val generateInfo = taskKey[Unit]("Generates Info in Json form to be consumed")

  }

  import autoImport._

  lazy val generateInfoSettings: Seq[Setting[_]] = Seq(
    generateInfo := {

      val ( serviceName: String, serviceVersion: String) = ((Compile / name).value, (Compile / version).value)
      val projects: List[String] = buildDependencies.value.classpath.keys.map(_.project).toList

      val generation = generateServiceInformation( serviceName, serviceVersion, projects )

      val x: JsValue = Json.toJson(generation)

      println("*************************** SERVICE INFORMATION ***************************")
      println(Json.prettyPrint(x))
      println("*************************** SERVICE INFORMATION ***************************")

      val file = new File("service-information.json")
      IO.write(file, x.toString())

    }
  )

  private def generateServiceInformation( name: String, version: String, projects: List[String] ): ServiceInformation = {
    ServiceInformation(
      name = name,
      version = version,
      subProjects = projects
    )
  }

  override lazy val projectSettings = generateInfoSettings

}
