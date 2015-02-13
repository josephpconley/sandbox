package cake

/**
 * User: jconley
 * Date: 7/14/2014
 */
trait Db {
  def name: String
}
class MySQLDb extends Db {
  val name = "MySQL"
}
class PostgresDb extends Db {
  val name = "Postgres"
}

trait WeatherWS {
  def currentTemp: Double
}
class ForecastIOWeatherWS extends WeatherWS {
  def currentTemp = 101.5
}
class WeatherDotComWeatherWS extends WeatherWS {
  def currentTemp = 99.7
}

trait DbConfig {
  val db: Db
}
trait WeatherWSConfig {
  val weather: WeatherWS
}
trait GlobalConfig extends DbConfig with WeatherWSConfig {
  def ping = println(s"Db is ${db.name} Weather is ${weather.currentTemp}")
}

object Parfait extends App {
  val config = new GlobalConfig{
    val db = new PostgresDb
    val weather = new WeatherDotComWeatherWS
  }

  config.ping
}