package bedbrains.homesweethomeserver

import bedbrains.platform.UIDProvider
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import org.apache.tomcat.jni.Socket.send
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HomeSweetHomeServerApplication

fun main(args: Array<String>) {
    runApplication<HomeSweetHomeServerApplication>(*args)

    val clientIdentifier: String = UIDProvider.newUID

    val client: Mqtt5AsyncClient = Mqtt5Client.builder()
            .identifier(clientIdentifier)
            .serverHost("localhost")
            .serverPort(1883)
            .buildAsync()

    client.connect()

    client.subscribeWith()
            .topicFilter("test")
            .qos(MqttQos.EXACTLY_ONCE)
            .callback {
                println(it.contentType)
                println(it)
            }
            .send()


    //ToDo: Remove print statement
    println("setup done ;)")
}