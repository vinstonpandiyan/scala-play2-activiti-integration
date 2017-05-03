package services

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.BeanProperty
import org.activiti.engine.task.Task
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope
import org.activiti.rest.service.api.runtime.task.TaskResponse
import org.activiti.rest.service.api.{RestResponseFactory, RestUrlBuilder}
import play.api.libs.json.Json

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

/**
  * Created by vpandiyan001 on 5/1/2017.
  */
object ResponseFactory extends RestResponseFactory {
  override
  def createUrlBuilder: RestUrlBuilder = {
    RestUrlBuilder.usingBaseUrl("http://localhost:9000/")
  }

  override
  def createTaskResponse(task: Task, urlBuilder: RestUrlBuilder): TaskResponse = {
    val customTaskResponse: CustomTaskResponse = CustomTaskResponse(task)
    if (task.getProcessVariables != null) {
      val variableMap = task.getProcessVariables
      import scala.collection.JavaConversions._
      for (name <- variableMap.keySet) {
        customTaskResponse.addVariable(createRestVariable(name, variableMap.get(name), RestVariableScope.GLOBAL, task.getId, 1, false, urlBuilder))
      }
    }
    customTaskResponse
  }

}

case class CustomTaskResponse(task: Task) extends TaskResponse(task) {
  @JsonProperty("startTime") val startTime: String = {
    //task.getProcessVariables.map(x => x.)
    println(task.getProcessVariables.get("uploadDate"))
    task.getProcessVariables.get("uploadDate").toString
  }
}

/*object ResponseFactory {
  def apply() = {
    ResponseFactory
  }
}*/
