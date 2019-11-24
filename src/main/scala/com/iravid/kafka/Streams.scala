package com.iravid.kafka

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.ActorMaterializer
import akka.kafka.ConsumerSettings
import akka.kafka.scaladsl.Consumer
import akka.kafka.Subscriptions
import akka.stream.scaladsl.Sink
import org.apache.kafka.common.serialization.Serdes

import scala.io.StdIn
import scala.concurrent.Await
import scala.concurrent.duration._

object Streams {
  def main(args: Array[String]): Unit = {
    val stringSerde = Serdes.String()
    implicit val system: ActorSystem = ActorSystem("main")
    implicit val mat: Materializer = ActorMaterializer()

    val settings = ConsumerSettings(system, stringSerde.deserializer(), stringSerde.deserializer())
      .withGroupId("streams")
      .withBootstrapServers("localhost:9092")
      .withProperty("auto.offset.reset", "earliest")
      .withProperty("enable.auto.commit", "false")

    val consumerControl = Consumer
      .committableSource(settings, Subscriptions.topics("streams"))
      .map { rec =>
        println(rec)
        rec
      }
      .to(Sink.ignore)
      .run()


  }
}
