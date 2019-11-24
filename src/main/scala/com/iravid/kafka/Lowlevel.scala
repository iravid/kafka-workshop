package com.iravid.kafka

import java.time.Duration
import java.util.concurrent.atomic.AtomicBoolean

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.Serdes

import scala.collection.JavaConverters._

object Lowlevel {
  def main(args: Array[String]): Unit = {
    val stringSerde = Serdes.String()
    val properties = new java.util.Properties
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("group.id", "lowlevel")
    properties.put("auto.offset.reset", "earliest")
    properties.put("enable.auto.commit", "false")
    val consumer = new KafkaConsumer[String, String](properties, stringSerde.deserializer(), stringSerde.deserializer())
    val shouldStop = new AtomicBoolean(false)

    sys.addShutdownHook {
      shouldStop.set(true)
      consumer.wakeup()
    }

    consumer.subscribe(List("lowlevel").asJavaCollection)

    while (!shouldStop.get) {
      val records = consumer.poll(Duration.ofMillis(250))
      println(s"Received ${records.count()} records")

      records.iterator().asScala.foreach(println)

      Thread.sleep(500)
    }

    consumer.close()
  }
}
