package controllers

import services.TaskService._
import play.api.cache.CacheApi
import play.api.libs.json.Json
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def deploy() = Action {
    val deployment = deployProcess()
    Ok(Json.toJson(play.libs.Json.toJson(deployment)))
  }

  def create() = Action {
    val task = createTask("activitiReview")
    Ok(Json.toJson(play.libs.Json.toJson(task)))
  }

  def getTask(taskId: String) = Action { implicit request =>
    val task = find(taskId)
    Ok(Json.toJson(play.libs.Json.toJson(task)))
  }

  def getAllTasks() = Action {
    val list = findAll()
    Ok(Json.toJson(list.map(x => play.libs.Json.toJson(x))))
  }

}