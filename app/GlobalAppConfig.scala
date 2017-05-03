import org.activiti.engine.{ProcessEngineConfiguration, ProcessEngines}
import play.api.{Application, GlobalSettings, Logger}

/**
  * GlobalSettings is instantiated by the framework when an application starts,
  * to let you perform specific tasks at start-up or shut-down.
  *
  * Created by vpandiyan001 on 5/1/2017.
  */
object GlobalAppConfig extends GlobalSettings {

  override
  def onStart(application: Application): Unit = {
    //println("\n\n\n\nonStartup")
    /*val configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
      .setJdbcDriver("org.postgresql.Driver")
      .setJdbcUrl("jdbc:postgresql://localhost/process-engine")
      .setJdbcUsername("")
      .setJdbcPassword("")
      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
      .setHistory(ProcessEngineConfiguration.HISTORY_AUDIT)
      .setJobExecutorActivate(true)*/
    val configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault()
    Logger.info("Starting process engine...")
    val engine = configuration.buildProcessEngine()

    /*Logger.info("Deploying process definition...")
    val deployment = engine.getRepositoryService.createDeployment()
    deployment.addClasspathResource("sample.bpmn").enableDuplicateFiltering()
    deployment.deploy()*/
  }

  override def onStop(application: Application): Unit = {
    println("\n\n\n\n\nonStop")
    ProcessEngines.getDefaultProcessEngine.close()
  }

}
