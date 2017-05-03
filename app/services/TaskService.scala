package services

import services.ResponseFactory._
import org.activiti.engine.ProcessEngines._
import org.activiti.engine.runtime.ProcessInstance
import org.activiti.engine.task.Task
import org.activiti.rest.service.api.history.HistoricTaskInstanceResponse
import org.activiti.rest.service.api.runtime.task.TaskResponse
import play.api.libs.json.Json

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import org.activiti.engine.repository.Deployment

/**
  * Created by vpandiyan001 on 5/1/2017.
  */
object TaskService {

  def find(id: String): TaskResponse = {
    val task = getDefaultProcessEngine().getTaskService().createTaskQuery().taskId(id).singleResult()
    createTaskResponse(task)
  }

  def findAll(): List[TaskResponse] = {
    val taskList: java.util.List[Task]  = getDefaultProcessEngine().getTaskService().createTaskQuery().list()
    createTaskResponseList(taskList).asScala.toList
    //getTasks(Map("" -> ""), null)
  }

  def findAll(clientId: String, email: String, size: Int, start: Int): CustomDataResponse = {
    val taskQuery = getDefaultProcessEngine().getTaskService().createTaskQuery()
      .processInstanceBusinessKey(clientId)
      .taskAssignee(email)
      .includeProcessVariables()
      .orderByTaskCreateTime().desc()

    val taskList: java.util.List[Task] = taskQuery.listPage(start, size)
    val jsValue = Json.toJson(createTaskResponseList(taskList).asScala
      .toList.map(x => play.libs.Json.toJson(x)))
    CustomDataResponse(jsValue, taskQuery.count)
  }

  def createTask(processId: String): HistoricTaskInstanceResponse = {
    val vars: Map[String, Object] = Map("clientId" -> "12345")
    val pIntance: ProcessInstance = getDefaultProcessEngine().getRuntimeService.startProcessInstanceByKey(processId, vars);
    val pId = pIntance.getProcessInstanceId
    val hTask = getDefaultProcessEngine.getHistoryService.createHistoricTaskInstanceQuery().processInstanceId(pId).taskDeleteReason(null).singleResult()
    createHistoricTaskInstanceResponse(hTask)
  }

  def deployProcess(): Deployment = {
    val deployment = getDefaultProcessEngine().getRepositoryService.createDeployment()
    deployment.addClasspathResource("MyProcess.bpmn").enableDuplicateFiltering()
    deployment.deploy()
  }

}

case class CustomDataResponse(data: play.api.libs.json.JsValue, total: Long)

object CustomDataResponse {
  //implicit val taskResponseFormat = Json.writes[TaskResponse]
  implicit val customDataResponseFormat = Json.writes[CustomDataResponse]
}
